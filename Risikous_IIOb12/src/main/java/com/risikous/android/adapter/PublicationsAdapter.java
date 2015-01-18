package com.risikous.android.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import com.risikous.android.R;
import com.risikous.android.model.publications.Publication;

import java.util.List;

/**
 * Created by Excel on 08.01.2015.
 */

public class PublicationsAdapter extends ArrayAdapter<Publication> {

    int resource;
    private List<Publication> objects;
    private LayoutInflater inflater = null;

    public PublicationsAdapter(Context context, int resource, List<Publication> objects) {
        super(context, resource, objects);
        this.resource = resource;
        this.objects = objects;
        inflater = (LayoutInflater) getContext().getSystemService(Context
                .LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Publication currentPublication = getItem(position);
        View vi = convertView;
        if (vi == null)
            vi = inflater.inflate(R.layout.publication_layout, parent, false);

        TextView pubPubID = (TextView) vi.findViewById(R.id.pubid);
        TextView pubEntryDate = (TextView) vi.findViewById(R.id.entrydate);
        TextView pubRevisionDate = (TextView) vi.findViewById(R.id.revisiondate);
        TextView pubTitle = (TextView) vi.findViewById(R.id.titlequest);
        TextView pubStatus = (TextView) vi.findViewById(R.id.status);
        TextView pubNumberOfReports = (TextView) vi.findViewById(R.id.numberofreports);
        TextView pubNumberOfComments = (TextView) vi.findViewById(R.id.numberofcomments);

        pubPubID.setText(currentPublication.getPubID().getPubID());
        pubEntryDate.setText(currentPublication.getEntryDate().getEntryDate());
        pubRevisionDate.setText(currentPublication.getRevisionDate().getRevisionDate());
        pubTitle.setText(currentPublication.getTitle().getTitle());
        pubStatus.setText(currentPublication.getStatus().getStatus());
        pubNumberOfReports.setText(currentPublication.getNumberOfReports().getNumberOfReports());
        pubNumberOfComments.setText(currentPublication.getNumberOfComments().getNumberOfComments());

        //verschwinden

        vi.findViewById(R.id.incidentReport).setVisibility(View.GONE);
        vi.findViewById(R.id.minRPZofReporter).setVisibility(View.GONE);
        vi.findViewById(R.id.avgRPZofReporter).setVisibility(View.GONE);
        vi.findViewById(R.id.maxRPZofReporter).setVisibility(View.GONE);
        vi.findViewById(R.id.minRPZofQMB).setVisibility(View.GONE);
        vi.findViewById(R.id.avgRPZofQMB).setVisibility(View.GONE);
        vi.findViewById(R.id.maxRPZofQMB).setVisibility(View.GONE);
        vi.findViewById(R.id.category).setVisibility(View.GONE);
        vi.findViewById(R.id.action).setVisibility(View.GONE);
        vi.findViewById(R.id.assignedReports).setVisibility(View.GONE);

        return vi;
    }
}