package com.risikous.android.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.*;
import com.risikous.android.R;
import com.risikous.android.adapter.CommentsAdapter;
import com.risikous.android.adapter.PublicationsAdapter;
import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.publications.Publication;
import com.risikous.android.parser.GetRequest;
import com.risikous.android.parser.ParseComment;
import com.risikous.android.parser.ParsePublication;
import com.risikous.android.parser.ParseSubComment;
import com.risikous.android.url.Constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */

public class PublicationFragment extends Fragment {

    private ListView publicationListView;
    private ExpandableListView expandableListView;
    private LinearLayout publicationIDContainer;
    private LinearLayout commentsContainer;

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
                        }, Constants.COMMENT_URL(fullObject.getPubID().getPubID())).execute();

                    }
                });
            }
        }, Constants.PUBLICATION_URL).execute();


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
        // Creating alert Dialog with one Button
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());

        //AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();

        // Setting Dialog Title
        alertDialog.setTitle("Kommentar hinzufügen");

        final EditText input = new EditText(getActivity());
        alertDialog.setView(input);

        // Setting Positive "Yes" Button
        alertDialog.setPositiveButton("ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(getActivity(), input.getText(), Toast.LENGTH_SHORT).show();
                    }
                });
        // Setting Negative "NO" Button
        alertDialog.setNegativeButton("abrechen",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // Write your code here to execute after dialog
                        dialog.cancel();
                    }
                });

        // closed

        // Showing Alert Message
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

    private interface ResponseCallback {
        public void onResponse(String s);

    }
}
