package com.risikous.android.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.risikous.android.R;
import com.risikous.android.fragments.CommentFragment;
import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.Author;
import com.risikous.android.model.comment.part.ComID;
import com.risikous.android.model.comment.part.PubID;
import com.risikous.android.model.comment.part.Text;
import com.risikous.android.request.PostRequest;
import com.risikous.android.url_uri.Constants;
import com.risikous.android.xml.builder.BuildComment;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Excel on 11.01.2015.
 */
public class CommentsAdapter extends BaseExpandableListAdapter {

    private Context _context;
    private List<Comment> listComment;
    private HashMap<Comment, List<Comment>> listSubComment;

    public CommentsAdapter(Context context, List<Comment> listComment,
                           HashMap<Comment, List<Comment>> listSubComment) {
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

        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        TextView timeStamp = (TextView) convertView.findViewById(R.id.timestamp_comment);

        convertView.findViewById(R.id.comm4).setVisibility(View.GONE);

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
        final Comment headerTitle = (Comment) getGroup(groupPosition);
        if (convertView == null) {
            LayoutInflater infalInflater = (LayoutInflater) this._context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalInflater.inflate(R.layout.comment_layout, null);
        }

        TextView author = (TextView) convertView.findViewById(R.id.author);
        TextView text = (TextView) convertView.findViewById(R.id.text);
        TextView timeStamp = (TextView) convertView.findViewById(R.id.timestamp_comment);

        author.setText(headerTitle.getAuthor().getName());
        text.setText(headerTitle.getText().getName());
        timeStamp.setText(headerTitle.getTimeStamp().getName());

        Button button = (Button) convertView.findViewById(R.id.answercomment_Button);

        if (!headerTitle.getComID().getName().equals("-1")) {

            button.setVisibility(View.VISIBLE);

            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(_context);

                    alertDialog.setTitle("Antwort hinzufügen");

                    final EditText author = new EditText(_context);
                    author.setHint("Author (optional)");
                    final EditText commentText = new EditText(_context);
                    commentText.setHint("Kommentar");

                    LinearLayout ll = new LinearLayout(_context);
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

                                    comment.setComID(new ComID(headerTitle.getComID().getName()));
                                    comment.setAuthor(new Author(author.getText().toString().trim()));
                                    comment.setText(new Text(commentText.getText().toString().trim()));

                                    xml = bC.buildComment(comment);

                                    new POST(Constants.SUBCOMMENT_POST_URL, xml).execute();


                                }
                            });

                    alertDialog.setNegativeButton("Abbrechen",
                            new DialogInterface.OnClickListener() {
                                public void onClick(DialogInterface dialog, int which) {
                                    dialog.cancel();
                                }
                            });

                    alertDialog.show();
                    //Toast.makeText(_context, "'Antworten wird noch nicht unterstützt.'", Toast.LENGTH_SHORT).show();
                }
            });
        }else button.setVisibility(View.GONE);


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

            Toast.makeText(_context, result, Toast.LENGTH_LONG).show();

        }

    }
}