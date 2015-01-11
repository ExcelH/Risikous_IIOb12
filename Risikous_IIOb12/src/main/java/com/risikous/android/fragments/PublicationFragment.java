package com.risikous.android.fragments;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.*;
import android.widget.*;
import com.risikous.android.MainActivity;
import com.risikous.android.R;
import com.risikous.android.adapter.PublicationsAdapter;
import com.risikous.android.model.publications.Publication;
import com.risikous.android.parser.GetRequest;
import com.risikous.android.parser.ParsePublication;

import java.util.List;
import java.util.concurrent.ExecutionException;

/**
 * A simple {@link Fragment} subclass.
 */

public class PublicationFragment extends Fragment {

    private ListView publicationListView;
    private ExpandableListView expandableListView;
    private LinearLayout publicationIDContainer;
    private LinearLayout commentsContainer;
    String xml = null;

    public PublicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        setHasOptionsMenu(true);
        View v = inflater.inflate(R.layout.fragment_publication, container, false);
        publicationListView = (ListView) v.findViewById(R.id.listView);
        try {
            xml = new GET().execute().get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        List<Publication> publication = GetPublication();

        publicationListView.setAdapter(new PublicationsAdapter(getActivity(), R.layout.publication_layout, publication));

        publicationListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                Object o = publicationListView.getItemAtPosition(position);
                Publication fullObject = (Publication) o;
                ((TextView)publicationIDContainer.findViewById(R.id.pubid)).setText(fullObject.getPubID().getPubID());
                ((TextView)publicationIDContainer.findViewById(R.id.entrydate)).setText(fullObject.getEntryDate().getEntryDate());
                ((TextView)publicationIDContainer.findViewById(R.id.revisiondate)).setText(fullObject.getRevisionDate().getRevisionDate());
                ((TextView)publicationIDContainer.findViewById(R.id.titlequest)).setText(fullObject.getTitle().getTitle());
                ((TextView)publicationIDContainer.findViewById(R.id.status)).setText(fullObject.getStatus().getStatus());
                ((TextView)publicationIDContainer.findViewById(R.id.numberofreports)).setText(fullObject.getNumberOfReports().getNumberOfReports());
                ((TextView)publicationIDContainer.findViewById(R.id.numberofcomments)).setText(fullObject.getNumberOfComments().getNumberOfComments());
                toggleCommmentsContainer();

            }
        });
        expandableListView=(ExpandableListView) v.findViewById(R.id.expandableListView);
        publicationIDContainer=(LinearLayout) v.findViewById(R.id.publicationLayout);
        commentsContainer=(LinearLayout) v.findViewById(R.id.commentLayout);
        return v;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        if(isCommentsContainerVisible()){
            menu.findItem(R.id.action_add_comment).setVisible(true);
        }
        super.onPrepareOptionsMenu(menu);
    }
    private void showCommentDailog(){
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
                    public void onClick(DialogInterface dialog,int which) {
                        // Write your code here to execute after dialog
                        Toast.makeText(getActivity(),input.getText(), Toast.LENGTH_SHORT).show();
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
        if(item.getItemId()==R.id.action_add_comment){
            showCommentDailog();
        }
        return super.onOptionsItemSelected(item);
    }

    public void toggleCommmentsContainer() {
        if (commentsContainer.getVisibility() == View.VISIBLE) {
            commentsContainer.setVisibility(View.GONE);
        }else{
            commentsContainer.setVisibility(View.VISIBLE);
        }
        getActivity().supportInvalidateOptionsMenu();
    }
    public boolean isCommentsContainerVisible(){
        return commentsContainer.getVisibility()==View.VISIBLE;
    }
    private List<Publication> GetPublication(){
        ParsePublication p = new ParsePublication();
        List<Publication> results = p.parsePublication(xml);

        return results;
    }

    public class GET extends AsyncTask<Void, Void, String> {

        private String url = "http://94.101.38.155/RisikousRESTful/rest/publications";
        private String data = null;


        @Override
        protected String doInBackground(Void... arg0) {
            GetRequest gr = new GetRequest();

            return gr.GetXML(url, data);
        }
        @Override
        protected void onPostExecute(String result) {
            result = xml;
        }


    }
}
