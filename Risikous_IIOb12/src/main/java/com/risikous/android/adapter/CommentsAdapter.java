package com.risikous.android.adapter;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import com.risikous.android.R;
import com.risikous.android.model.comment.Comment;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Excel on 11.01.2015.
 */
public class CommentsAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<Comment> listComment;
    private HashMap<Comment, List<Comment>> listSubComment;

    public CommentsAdapter(Context context, List<String> listDataHeader,
                                 HashMap<String, List<String>> listChildData) {
        this._context = context;
        this.listComment = listComment;
        this.listSubComment = listSubComment;
    }

    @Override
    public Object getChild(int groupPosition, int childPosititon) {
        return this.listSubComment.get(this.listComment.get(groupPosition))
                .get(childPosititon);
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public View getChildView(int groupPosition, final int childPosition,
                             boolean isLastChild, View convertView, ViewGroup parent) {

        final Comment childText = (Comment) getChild(groupPosition, childPosition);

        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.comment_layout, null);
        }

        TextView pubID = (TextView) convertView.findViewById(R.id.pubid);
        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        TextView timeStamp = (TextView) convertView.findViewById(R.id.timestamp);

        pubID.setText(childText.getPubID().getName());
        author.setText(childText.getAuthor().getName());
        text.setText(childText.getText().getName());
        timeStamp.setText(childText.getTimeStamp().getName());
        return convertView;
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return this.listSubComment.get(this.listComment.get(groupPosition))
                .size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return this.listComment.get(groupPosition);
    }

    @Override
    public int getGroupCount() {
        return this.listComment.size();
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded,
                             View convertView, ViewGroup parent) {
        Comment headerTitle = (Comment) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.comment_layout, null);
        }

        TextView pubID = (TextView) convertView.findViewById(R.id.pubid);
        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        TextView timeStamp = (TextView) convertView.findViewById(R.id.timestamp);

        pubID.setTypeface(null, Typeface.BOLD);
        author.setTypeface(null, Typeface.BOLD);
        text.setTypeface(null, Typeface.BOLD);
        timeStamp.setTypeface(null, Typeface.BOLD);

        pubID.setText(headerTitle.getPubID().getName());
        author.setText(headerTitle.getAuthor().getName());
        text.setText(headerTitle.getText().getName());
        timeStamp.setText(headerTitle.getTimeStamp().getName());


        return convertView;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}