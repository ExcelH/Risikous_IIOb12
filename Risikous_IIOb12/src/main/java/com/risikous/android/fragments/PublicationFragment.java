package com.risikous.android.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.*;
import android.widget.*;

import com.risikous.android.MainActivity;
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
import com.risikous.android.url_uri.Constants;
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
    private RelativeLayout publicationIDContainer;
    private View commentsContainer;
    private ExpandableListView expandableListView;
    private String ClickID = null;

    public PublicationFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_publication, container, false);
        publicationListView = (ListView) v.findViewById(R.id.listView);
        expandableListView = (ExpandableListView) v.findViewById(R.id.expandableListView);
        publicationIDContainer = (RelativeLayout) v.findViewById(R.id.publicationLayout);
        commentsContainer =  v.findViewById(R.id.commentLayout);


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

                        new GET(new ResponseCallback() {
                            @Override
                            public void onResponse(String s) {
                                Publication tmp = new Publication();
                                ParsePublication parsePublication = new ParsePublication();
                                parsePublication.parseCompletedPublication(s, tmp);
                                ((TextView) publicationIDContainer.findViewById(R.id.incidentreport)).setText(tmp.getIncidentReport().getName());
                                ((TextView) publicationIDContainer.findViewById(R.id.minrpzofreporter)).setText(tmp.getMinRPZofReporter().getName());
                                ((TextView) publicationIDContainer.findViewById(R.id.avgrpzofreporter)).setText(tmp.getAvgRPZofReporter().getName());
                                ((TextView) publicationIDContainer.findViewById(R.id.maxrpzofreporter)).setText(tmp.getMaxRPZofReporter().getName());
                                ((TextView) publicationIDContainer.findViewById(R.id.minrpzofqmb)).setText(tmp.getMinRPZofQMB().getName());
                                ((TextView) publicationIDContainer.findViewById(R.id.avgrpzofqmb)).setText(tmp.getAvgRPZofQMB().getName());
                                ((TextView) publicationIDContainer.findViewById(R.id.maxrpzofqmb)).setText(tmp.getMaxRPZofQMB().getName());
                                ((TextView) publicationIDContainer.findViewById(R.id.category)).setText(tmp.getCategory().getName());
                                ((TextView) publicationIDContainer.findViewById(R.id.action)).setText(tmp.getAction().getName());
                                ((TextView) publicationIDContainer.findViewById(R.id.assignedreports)).setText(tmp.getAssignedReports().getName());
                            }
                        }, Constants.PUBLICATION_GET_FULL_PUB_URL(fullObject.getPubID().getPubID())).execute();

                        ClickID = fullObject.getPubID().getPubID();
                        toggleCommmentsContainer();


                    }
                });
            }
        }, Constants.PUBLICATION_GET_URL).execute();

        v.findViewById(R.id.comment_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity) getActivity()).startCommentActivity(ClickID);
            }
        });

        return v;
    }

    public boolean onBackPressed () {
        if(commentsContainer.getVisibility() == View.VISIBLE) {
            commentsContainer.setVisibility(View.GONE);
            return true;
        }
        return false;
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

        return p.parsePublication(xml);
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
