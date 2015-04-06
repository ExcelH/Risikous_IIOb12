package com.risikous.android.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.risikous.android.model.comment.Comment;
import com.risikous.android.model.comment.part.Author;
import com.risikous.android.model.comment.part.ComID;
import com.risikous.android.model.comment.part.PubID;
import com.risikous.android.model.comment.part.Text;
import com.risikous.android.model.comment.part.TimeStamp;

import java.util.LinkedList;
import java.util.List;


public class SQLiteHelper_SubComment extends SQLiteOpenHelper {

    //Database-Version
    private static final int DATABASE_VERSION = 1;
    //Database-Name
    private static final String DATABASE_NAME = "subCommentDB";


    private static final String TABLE_SUBCOMMENTS = "subComments";
    private static final String KEY_ID = "ID";
    private static final String SUBCOMMENTAUTHOR = "subCommentAuthor";
    private static final String SUBCOMMENTPUBID = "subCommentPubID";
    private static final String SUBCOMMENTTEXT = "subCommentText";
    private static final String SUBCOMMENTTIMESTAMP = "subCommentTimestamp";
    private static final String SUBCOMMENTCOMID = "subCommentComID";


    private static final String[] COLUMNS = {KEY_ID, SUBCOMMENTAUTHOR, SUBCOMMENTPUBID, SUBCOMMENTTEXT, SUBCOMMENTTIMESTAMP, SUBCOMMENTCOMID};


    public SQLiteHelper_SubComment(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println(" CREATE DB ::: ");
        //SQL-Statement um 'subcomment' Tabelle zu erstellen
        String CREATE_SUBCOMMENTS_TABLE = "CREATE TABLE subComments ( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "subCommentAuthor TEXT, " +
                "subCommentPubID TEXT, " +
                "subCommentText TEXT, " +
                "subCommentTimestamp TEXT, " +
                "subCommentComID TEXT)";

        db.execSQL(CREATE_SUBCOMMENTS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop der alten Tabelle
        db.execSQL("DROP TABLE IF EXISTS subComments");

        //Create von neuer 'subcomments' Tabelle
        this.onCreate(db);
    }

    public void addSubComment(Comment comment) {

        System.out.println("addSubComment ::: " + comment.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBCOMMENTAUTHOR, comment.getAuthorDB());
        values.put(SUBCOMMENTPUBID, comment.getPubIDDB());
        values.put(SUBCOMMENTTEXT, comment.getTextDB());
        values.put(SUBCOMMENTTIMESTAMP, comment.getTimeStampDB());
        values.put(SUBCOMMENTCOMID, comment.getComIDDB());

        db.insert(TABLE_SUBCOMMENTS, null, values);
        db.close();
    }

    public List<Comment> getAllSubComments() {

        List<Comment> subComments = new LinkedList<Comment>();

        String query = "SELECT * FROM " + TABLE_SUBCOMMENTS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Comment comment = null;
        if (cursor.moveToFirst()) {
            do {
                comment = new Comment();
                comment.setID(cursor.getInt(0));
                comment.setAuthor(new Author(cursor.getString(1)));
                comment.setPubID(new PubID(cursor.getString(2)));
                comment.setText(new Text(cursor.getString(3)));
                comment.setTimeStamp(new TimeStamp(cursor.getString(4)));
                comment.setComID(new ComID(cursor.getString(5)));

                subComments.add(comment);
            } while (cursor.moveToNext());
        }

        return subComments;
    }

    public int updateSubComment(Comment comment) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(SUBCOMMENTAUTHOR, comment.getAuthorDB());
        values.put(SUBCOMMENTPUBID, comment.getPubIDDB());
        values.put(SUBCOMMENTTEXT, comment.getTextDB());
        values.put(SUBCOMMENTTIMESTAMP, comment.getTimeStampDB());

        int i = db.update(TABLE_SUBCOMMENTS,
                values,
                KEY_ID + " = ?",
                new String[]{String.valueOf(comment.getPubIDDB())});

        db.close();

        System.out.println(" updateSubComment ::: " + comment.toString());

        return i;
    }

    public void deleteSubComment(Comment comment) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_SUBCOMMENTS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(comment.getPubIDDB())});

        db.close();

        System.out.println("deleteSubComment ::: " + comment.toString());
    }

    public boolean verification(String ID) {
        int count = -1;
        Cursor c = null;
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            String query = "SELECT COUNT(*) FROM "
                    + TABLE_SUBCOMMENTS + " WHERE " + SUBCOMMENTPUBID + " = ?";
            c = db.rawQuery(query, new String[]{ID});
            if (c.moveToFirst()) {
                count = c.getInt(0);
            }
            return count > 0;
        } finally {
            if (c != null) {
                c.close();
            }
        }
    }
}

