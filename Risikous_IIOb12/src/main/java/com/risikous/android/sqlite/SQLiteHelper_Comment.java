package com.risikous.android.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.Author;
import com.risikous.android.model.comment.part.PubID;
import com.risikous.android.model.comment.part.Text;
import com.risikous.android.model.comment.part.TimeStamp;


import java.util.LinkedList;
import java.util.List;


public class SQLiteHelper_Comment extends SQLiteOpenHelper {

    //Database-Version
    private static final int DATABASE_VERSION = 1;
    //Database-Name
    private static final String DATABASE_NAME = "commentDB";


    private static final String TABLE_COMMENTS = "comments";
    private static final String KEY_ID = "ID";
    private static final String COMMENTAUTHOR = "commentAuthor";
    private static final String COMMENTPUBID = "commentPubID";
    private static final String COMMENTTEXT = "commentText";
    private static final String COMMENTTIMESTAMP = "commentTimestamp";

    private static final String[] COLUMNS = {KEY_ID, COMMENTAUTHOR, COMMENTPUBID, COMMENTTEXT, COMMENTTIMESTAMP};


    public SQLiteHelper_Comment(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println(" CREATE DB ::: ");
        //SQL-Statement um 'comment' Tabelle zu erstellen
        String CREATE_COMMENTS_TABLE = "CREATE TABLE comments ( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "commentAuthor TEXT, " +
                "commentPubID TEXT, " +
                "commentText TEXT, " +
                "commentTimestamp TEXT)";

        db.execSQL(CREATE_COMMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop der alten Tabelle
        db.execSQL("DROP TABLE IF EXISTS comments");

        //Create von neuer 'comments' Tabelle
        this.onCreate(db);
    }

    public void addComment(Comment comment) {

        System.out.println("addComment ::: " + comment.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COMMENTAUTHOR, comment.getAuthorDB());
        values.put(COMMENTPUBID, comment.getPubIDDB());
        values.put(COMMENTTEXT, comment.getTextDB());
        values.put(COMMENTTIMESTAMP, comment.getTimeStampDB());

        db.insert(TABLE_COMMENTS, null, values);
        db.close();
    }

    public List<Comment> getAllComments() {

        List<Comment> comments = new LinkedList<>();

        String query = "SELECT * FROM " + TABLE_COMMENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Comment comment= null;
        if (cursor.moveToFirst()) {
            do {
                comment = new Comment();
                comment.setID(cursor.getInt(0));
                comment.setAuthor(new Author(cursor.getString(1)));
                comment.setPubID(new PubID(cursor.getString(2)));
                comment.setText(new Text(cursor.getString(3)));
                comment.setTimeStamp(new TimeStamp(cursor.getString(4)));

                comments.add(comment);
            } while (cursor.moveToNext());
        }

        return comments;
    }

    public int updateComment(Comment comment) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(COMMENTAUTHOR, comment.getAuthorDB());
        values.put(COMMENTPUBID, comment.getPubIDDB());
        values.put(COMMENTTEXT, comment.getTextDB());
        values.put(COMMENTTIMESTAMP, comment.getTimeStampDB());

        int i = db.update(TABLE_COMMENTS,
                values,
                KEY_ID + " = ?",
                new String[]{String.valueOf(comment.getPubIDDB())});

        db.close();

        System.out.println(" updateComment ::: "+comment.toString());

        return i;
    }

    public void deleteComment(Comment comment) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_COMMENTS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(comment.getPubIDDB())});

        db.close();

        System.out.println("deleteComment ::: " + comment.toString());
    }

    public boolean verification(String ID){
        int count = -1;
        Cursor c = null;
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            String query = "SELECT COUNT(*) FROM "
                    + TABLE_COMMENTS + " WHERE " + COMMENTPUBID + " = ?";
            c = db.rawQuery(query, new String[] {ID});
            if (c.moveToFirst()) {
                count = c.getInt(0);
            }
            return count > 0;
        }
        finally {
            if (c != null) {
                c.close();
            }
        }
    }
}

