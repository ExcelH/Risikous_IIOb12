package com.risikous.android.fragments;


import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import com.risikous.android.R;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class IncidentFragment extends Fragment {


    private static final int REQUEST_CODE_PICK = 0;

    private ArrayAdapter<String> mfileAdapter = null;

    private ArrayList<String> mPathes = new ArrayList<String>();

    public IncidentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_incident, container, false);

        mfileAdapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_list_item_1);
        ListView lView = ((ListView) v.findViewById(R.id.ListViewFiles));
        lView.setAdapter(mfileAdapter);
        lView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String[] string = mPathes.get(position).split("/");
                mfileAdapter.remove(string[string.length-1]);
                mfileAdapter.notifyDataSetChanged();
                mPathes.remove(position);
                if (mPathes.isEmpty()) {
                    mPathes.add(getActivity().getString(R.string.incident_report_no_files));
                }
                return true;
            }
        });
        v.findViewById(R.id.button3).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.addCategory(Intent.CATEGORY_OPENABLE);
                intent.setType("*/*");
                Intent i = Intent.createChooser(intent, "File");
                startActivityForResult(intent, REQUEST_CODE_PICK);
            }
        });

        return v;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == REQUEST_CODE_PICK) {
                String path = data.getData().toString();
                mPathes.add(path.toString());
                String[] string = path.split("/");
                mfileAdapter.add(string[string.length - 1]);
                mfileAdapter.notifyDataSetChanged();
            }
        }

    }
}
