package com.risikous.android.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.risikous.android.R;
import com.risikous.android.adapter.CommentsAdapter;
import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.Author;
import com.risikous.android.model.comment.part.PubID;
import com.risikous.android.model.comment.part.Text;
import com.risikous.android.model.publications.Publication;
import com.risikous.android.request.GetRequest;
import com.risikous.android.request.PostRequest;
import com.risikous.android.url_uri.Constants;
import com.risikous.android.xml.builder.BuildComment;
import com.risikous.android.xml.parser.ParseComment;
import com.risikous.android.xml.parser.ParseSubComment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * Created by Excel on 23.01.2015.
 */
public class CommentFragment extends Fragment {
    private ExpandableListView expandableListView;
    private String ClickID;

    public CommentFragment() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_comment, container, false);
        expandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);

        Bundle bundle = this.getArguments();
        ClickID = bundle.getString("pubID");

        ((TextView) v.findViewById(R.id.comment_title)).setText("Kommentare zu " + ClickID);

        new GET(new ResponseCallback() {
            @Override
            public void onResponse(String s) {
                List<Comment> comment = GetComment(s);
                List<Comment> subComment = GetSubComment(s);
                HashMap<Comment, List<Comment>> commentHashMap = new HashMap<Comment, List<Comment>>();
                for (Comment tmpComment : comment) {
                    List<Comment> tmpSubComments = new ArrayList<Comment>();
                    Iterator<Comment> i = subComment.iterator();
                    while (i.hasNext()) {
                        Comment tmpSubComment = i.next();
                        if (tmpSubComment.getPubID() != null && tmpComment.getPubID() != null && tmpComment.getPubID().getName().equalsIgnoreCase(tmpSubComment.getPubID().getName())) {
                            tmpSubComments.add(tmpSubComment);
                            i.remove();
                        }
                    }
                    commentHashMap.put(tmpComment, tmpSubComments);
                }
                expandableListView.setAdapter(new CommentsAdapter(getActivity(), comment, commentHashMap));
                getActivity().supportInvalidateOptionsMenu();

            }
        }, Constants.COMMENT_GET_URL(ClickID)).execute();

        return v;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {

        menu.findItem(R.id.action_add_comment).setVisible(true);

        super.onPrepareOptionsMenu(menu);
    }

    private void showCommentDailog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setTitle("Kommentar hinzufügen");

        final EditText author = new EditText(getActivity());
        author.setHint("Author (optional)");
        final EditText commentText = new EditText(getActivity());
        commentText.setHint("Kommentar");

        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(author);
        ll.addView(commentText);
        alertDialog.setView(ll);

        alertDialog.setPositiveButton("OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        String xml = null;
                        Comment comment = new Comment();
                        BuildComment bC = new BuildComment();
                        PostRequest pR = new PostRequest();

                        comment.setPubID(new PubID(ClickID));
                        comment.setAuthor(new Author(author.getText().toString().trim()));
                        comment.setText(new Text(commentText.getText().toString().trim()));

                        xml = bC.buildComment(comment);

                        new POST(Constants.COMMENT_POST_URL, xml).execute();


                    }
                });

        alertDialog.setNegativeButton("Abbrechen",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

        alertDialog.show();

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_add_comment) {
            showCommentDailog();
        }
        return super.onOptionsItemSelected(item);
    }

    private List<Comment> GetComment(String xml) {
        ParseComment p = new ParseComment();

        return p.parseComment(xml);
    }

    private List<Comment> GetSubComment(String xml) {
        ParseSubComment p = new ParseSubComment();

        return p.parseComment(xml);
    }

    private interface ResponseCallback {
        public void onResponse(String s);

    }

    public class GET extends AsyncTask<Void, Void, String> {


        private String data = null;
        private ResponseCallback responseCallback;
        private String url = null;

        public GET(ResponseCallback callback, String url) {
            this.url = url;
            responseCallback = callback;
        }

        @Override
        protected String doInBackground(Void... arg0) {
            GetRequest gr = new GetRequest();
            return gr.GetXML(url, data);
        }

        @Override
        protected void onPostExecute(String result) {
            if (responseCallback != null)
                responseCallback.onResponse(result);

        }
    }

    public class POST extends AsyncTask<Void, Void, String> {

        private String data = null;
        private String url = null;

        public POST(String url, String data) {
            this.url = url;
            this.data = data;
        }

        @Override
        protected String doInBackground(Void... arg0) {
            PostRequest pR = new PostRequest();

            return pR.postXML(url, data);
        }

        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

        }

    }


}
