package com.risikous.android.fragments;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
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
    String xml = null;

    public PublicationFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
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
                Toast.makeText(getActivity(), "You have chosen: " + " " + fullObject.getPubID().toString(), Toast.LENGTH_LONG).show();
            }
        });

        return v;
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
