package com.risikous.android.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.*;
import android.widget.*;
import com.risikous.android.R;
import com.risikous.android.adapter.CommentsAdapter;
import com.risikous.android.adapter.PublicationsAdapter;
import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.Author;
import com.risikous.android.model.comment.part.PubID;
import com.risikous.android.model.comment.part.Text;
import com.risikous.android.model.publications.Publication;
import com.risikous.android.request.GetRequest;
import com.risikous.android.request.PostRequest;
import com.risikous.android.url.Constants;
import com.risikous.android.xml.builder.BuildComment;
import com.risikous.android.xml.parser.ParseComment;
import com.risikous.android.xml.parser.ParsePublication;
import com.risikous.android.xml.parser.ParseSubComment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;


public class PublicationFragment extends Fragment {

    private ListView publicationListView;
    private ExpandableListView expandableListView;
    private LinearLayout publicationIDContainer;
    private LinearLayout commentsContainer;
    private String ClickID = null;

    public PublicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_publication, container, false);
        publicationListView = (ListView) v.findViewById(R.id.listView);
        expandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);
        publicationIDContainer = (LinearLayout) v.findViewById(R.id.publicationLayout);
        commentsContainer = (LinearLayout) v.findViewById(R.id.commentLayout);

        new GET(new ResponseCallback() {
            @Override
            public void onResponse(String s) {
                List<Publication> publication = GetPublication(s);

                publicationListView.setAdapter(new PublicationsAdapter(getActivity(), R.layout.publication_layout, publication));

                publicationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                        Object o = publicationListView.getItemAtPosition(position);
                        Publication fullObject = (Publication) o;
                        ((TextView) publicationIDContainer.findViewById(R.id.pubid)).setText(fullObject.getPubID().getPubID());
                        ((TextView) publicationIDContainer.findViewById(R.id.entrydate)).setText(fullObject.getEntryDate().getEntryDate());
                        ((TextView) publicationIDContainer.findViewById(R.id.revisiondate)).setText(fullObject.getRevisionDate().getRevisionDate());
                        ((TextView) publicationIDContainer.findViewById(R.id.titlequest)).setText(fullObject.getTitle().getTitle());
                        ((TextView) publicationIDContainer.findViewById(R.id.status)).setText(fullObject.getStatus().getStatus());
                        ((TextView) publicationIDContainer.findViewById(R.id.numberofreports)).setText(fullObject.getNumberOfReports().getNumberOfReports());
                        ((TextView) publicationIDContainer.findViewById(R.id.numberofcomments)).setText(fullObject.getNumberOfComments().getNumberOfComments());
                        ClickID = fullObject.getPubID().getPubID();
                        toggleCommmentsContainer();
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

                            }
                        }, Constants.COMMENT_GET_URL(fullObject.getPubID().getPubID())).execute();

                    }
                });
            }
        }, Constants.PUBLICATION_GET_URL).execute();


        return v;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if (isCommentsContainerVisible()) {
            menu.findItem(R.id.action_add_comment).setVisible(true);
        }
        super.onPrepareOptionsMenu(menu);
    }

    private void showCommentDailog() {

        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        alertDialog.setTitle("Kommentar hinzufügen");

        final EditText author = new EditText(getActivity());
        final EditText commentText = new EditText(getActivity());

        LinearLayout ll = new LinearLayout(getActivity());
        ll.setOrientation(LinearLayout.VERTICAL);
        ll.addView(author);
        ll.addView(commentText);
        alertDialog.setView(ll);

        alertDialog.setPositiveButton("ok",
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

        alertDialog.setNegativeButton("abrechen",
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

    public void toggleCommmentsContainer() {
        if (commentsContainer.getVisibility() == View.VISIBLE) {
            commentsContainer.setVisibility(View.GONE);
        } else {
            commentsContainer.setVisibility(View.VISIBLE);
        }
        getActivity().supportInvalidateOptionsMenu();
    }

    public boolean isCommentsContainerVisible() {
        return commentsContainer.getVisibility() == View.VISIBLE;
    }

    private List<Publication> GetPublication(String xml) {
        ParsePublication p = new ParsePublication();
        List<Publication> results = p.parsePublication(xml);

        return results;
    }

    private List<Comment> GetComment(String xml) {
        ParseComment p = new ParseComment();
        List<Comment> results = p.parseComment(xml);

        return results;
    }

    private List<Comment> GetSubComment(String xml) {
        ParseSubComment p = new ParseSubComment();
        List<Comment> results = p.parseComment(xml);

        return results;
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

            Fragment frg = new PublicationFragment();
            final FragmentTransaction ft = getFragmentManager().beginTransaction();
            ft.detach(frg);
            ft.attach(frg);
            ft.commit();
            Toast.makeText(getActivity(), result, Toast.LENGTH_LONG).show();

        }

    }

    private interface ResponseCallback {
        public void onResponse(String s);

    }
}
