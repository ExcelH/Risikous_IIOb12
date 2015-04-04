package com.risikous.android.sqlite;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.risikous.android.model.publications.Publication;
import com.risikous.android.model.publications.part.Action;
import com.risikous.android.model.publications.part.AssignedReports;
import com.risikous.android.model.publications.part.AvgRPZofQMB;
import com.risikous.android.model.publications.part.AvgRPZofReporter;
import com.risikous.android.model.publications.part.Category;
import com.risikous.android.model.publications.part.EntryDate;
import com.risikous.android.model.publications.part.IncidentReport;
import com.risikous.android.model.publications.part.MaxRPZofQMB;
import com.risikous.android.model.publications.part.MaxRPZofReporter;
import com.risikous.android.model.publications.part.MinRPZofQMB;
import com.risikous.android.model.publications.part.MinRPZofReporter;
import com.risikous.android.model.publications.part.NumberOfComments;
import com.risikous.android.model.publications.part.NumberOfReports;
import com.risikous.android.model.publications.part.PubID;
import com.risikous.android.model.publications.part.RevisionDate;
import com.risikous.android.model.publications.part.Status;
import com.risikous.android.model.publications.part.Title;

import java.util.LinkedList;
import java.util.List;


public class SQLiteHelper_Publication extends SQLiteOpenHelper {

    //Database-Version
    private static final int DATABASE_VERSION = 1;
    //Database-Name
    private static final String DATABASE_NAME = "publicationDB";


    private static final String TABLE_PUBLICATIONS = "publications";
    private static final String KEY_ID = "ID";
    private static final String PUBLICATIONID = "publicationID";
    private static final String PUBLICATIONENTRYDATE = "publicationEntryDate";
    private static final String PUBLICATIONNUMBEROFREPORTS = "publicationNumberOfReports";
    private static final String PUBLICATIONNUMBEROFCOMMENTS = "publicationNumberOfComments";
    private static final String PUBLICATIONREVISIONDATE = "publicationRevisionDate";
    private static final String PUBLICATIONSTATUS = "publicationStatus";
    private static final String PUBLICATIONTITLE = "publicationTitle";
    private static final String PUBLICATIONINCIDENTREPORT = "publicationIncidentReport";
    private static final String PUBLICATIONMINRPZOFREPORTER = "publicationMinRPZofReporter";
    private static final String PUBLICATIONAVGRPZOFREPORTER = "publicationAvgRPZofReporter";
    private static final String PUBLICATIONMAXRPZOFREPORTER = "publicationMaxRPZofReporter";
    private static final String PUBLICATIONMINRPZOFQMB = "publicationMinRPZofQMB";
    private static final String PUBLICATIONAVGRPZOFQMB = "publicationAvgRPZofQMB";
    private static final String PUBLICATIONMAXRPZOFQMB = "publicationMaxRPZofQMB";
    private static final String PUBLICATIONCATEGORY = "publicationCategory";
    private static final String PUBLICATIONACTION = "publicationAction";
    private static final String PUBLICATIONASSIGNEDREPORTS = "publicationAssignedReports";

    private static final String[] COLUMNS = {KEY_ID, PUBLICATIONID, PUBLICATIONENTRYDATE, PUBLICATIONNUMBEROFREPORTS, PUBLICATIONNUMBEROFCOMMENTS, PUBLICATIONREVISIONDATE,
                            PUBLICATIONSTATUS, PUBLICATIONTITLE, PUBLICATIONINCIDENTREPORT, PUBLICATIONMINRPZOFREPORTER, PUBLICATIONAVGRPZOFREPORTER, PUBLICATIONMAXRPZOFREPORTER,
                            PUBLICATIONMINRPZOFQMB, PUBLICATIONAVGRPZOFQMB, PUBLICATIONMAXRPZOFQMB, PUBLICATIONCATEGORY, PUBLICATIONACTION, PUBLICATIONASSIGNEDREPORTS};


    public SQLiteHelper_Publication(Context context) {

        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        System.out.println(" CREATE DB ::: ");
        //SQL-Statement um 'publications' Tabelle zu erstellen
        String CREATE_PUBLICATIONS_TABLE = "CREATE TABLE publications ( " +
                "ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "publicationID TEXT, " +
                "publicationEntryDate TEXT, " +
                "publicationNumberOfReports TEXT, " +
                "publicationNumberOfComments TEXT, " +
                "publicationRevisionDate TEXT, " +
                "publicationStatus TEXT, " +
                "publicationTitle TEXT, " +
                "publicationIncidentReport TEXT, " +
                "publicationMinRPZofReporter TEXT, " +
                "publicationAvgRPZofReporter TEXT, " +
                "publicationMaxRPZofReporter TEXT, " +
                "publicationMinRPZofQMB TEXT, " +
                "publicationAvgRPZofQMB TEXT, " +
                "publicationMaxRPZofQMB TEXT, " +
                "publicationCategory TEXT, " +
                "publicationAction TEXT, " +
                "publicationAssignedReports TEXT)";

        db.execSQL(CREATE_PUBLICATIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        //Drop der alten Tabelle
        db.execSQL("DROP TABLE IF EXISTS publications");

        //Create von neuer 'publications' Tabelle
        this.onCreate(db);
    }

    public void addPublication(Publication publication) {

        System.out.println("addPublication ::: " + publication.toString());

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PUBLICATIONID, publication.getPubIDDB());
        values.put(PUBLICATIONENTRYDATE, publication.getEntryDateDB());
        values.put(PUBLICATIONNUMBEROFREPORTS, publication.getNumberOfReportsDB());
        values.put(PUBLICATIONNUMBEROFCOMMENTS, publication.getNumberOfCommentsDB());
        values.put(PUBLICATIONREVISIONDATE, publication.getRevisionDateDB());
        values.put(PUBLICATIONSTATUS, publication.getStatusDB());
        values.put(PUBLICATIONTITLE, publication.getTitleDB());
        values.put(PUBLICATIONINCIDENTREPORT, publication.getIncidentReportDB());
        values.put(PUBLICATIONMINRPZOFREPORTER, publication.getMinRPZofReporterDB());
        values.put(PUBLICATIONAVGRPZOFREPORTER, publication.getAvgRPZofReporterDB());
        values.put(PUBLICATIONMAXRPZOFREPORTER, publication.getMaxRPZofReporterDB());
        values.put(PUBLICATIONMINRPZOFQMB, publication.getMinRPZofQMBDB());
        values.put(PUBLICATIONAVGRPZOFQMB, publication.getAvgRPZofQMBDB());
        values.put(PUBLICATIONMAXRPZOFQMB, publication.getMaxRPZofQMBDB());
        values.put(PUBLICATIONCATEGORY, publication.getCategoryDB());
        values.put(PUBLICATIONACTION, publication.getActionDB());
        values.put(PUBLICATIONASSIGNEDREPORTS, publication.getAssignedReportsDB());

        db.insert(TABLE_PUBLICATIONS, null, values);
        db.close();
    }

    public List<Publication> getAllPublications() {

        List<Publication> publications = new LinkedList<Publication>();

        String query = "SELECT * FROM " + TABLE_PUBLICATIONS;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        Publication publication = null;
        if (cursor.moveToFirst()) {
            do {
                publication = new Publication();
                publication.setID(cursor.getInt(0));
                publication.setPubID(new PubID(cursor.getString(1)));
                publication.setEntryDate(new EntryDate(cursor.getString(2)));
                publication.setNumberOfReports(new NumberOfReports(cursor.getString(3)));
                publication.setNumberOfComments(new NumberOfComments(cursor.getString(4)));
                publication.setRevisionDate(new RevisionDate(cursor.getString(5)));
                publication.setStatus(new Status(cursor.getString(6)));
                publication.setTitle(new Title(cursor.getString(7)));
                publication.setIncidentReport(new IncidentReport(cursor.getString(8)));
                publication.setMinRPZofReporter(new MinRPZofReporter(cursor.getString(9)));
                publication.setAvgRPZofReporter(new AvgRPZofReporter(cursor.getString(10)));
                publication.setMaxRPZofReporter(new MaxRPZofReporter(cursor.getString(11)));
                publication.setMinRPZofQMB(new MinRPZofQMB(cursor.getString(12)));
                publication.setAvgRPZofQMB(new AvgRPZofQMB(cursor.getString(13)));
                publication.setMaxRPZofQMB(new MaxRPZofQMB(cursor.getString(14)));
                publication.setCategory(new Category(cursor.getString(15)));
                publication.setAction(new Action(cursor.getString(16)));
                publication.setAssignedReports(new AssignedReports(cursor.getString(17)));

                publications.add(publication);
            } while (cursor.moveToNext());
        }


        return publications;
    }

    public int updatePublication(Publication publication) {

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(PUBLICATIONID, publication.getPubIDDB());
        values.put(PUBLICATIONENTRYDATE, publication.getEntryDateDB());
        values.put(PUBLICATIONNUMBEROFREPORTS, publication.getNumberOfReportsDB());
        values.put(PUBLICATIONNUMBEROFCOMMENTS, publication.getNumberOfCommentsDB());
        values.put(PUBLICATIONREVISIONDATE, publication.getRevisionDateDB());
        values.put(PUBLICATIONSTATUS, publication.getStatusDB());
        values.put(PUBLICATIONTITLE, publication.getTitleDB());
        values.put(PUBLICATIONINCIDENTREPORT, publication.getIncidentReportDB());
        values.put(PUBLICATIONMINRPZOFREPORTER, publication.getMinRPZofReporterDB());
        values.put(PUBLICATIONAVGRPZOFREPORTER, publication.getAvgRPZofReporterDB());
        values.put(PUBLICATIONMAXRPZOFREPORTER, publication.getMaxRPZofReporterDB());
        values.put(PUBLICATIONMINRPZOFQMB, publication.getMinRPZofQMBDB());
        values.put(PUBLICATIONAVGRPZOFQMB, publication.getAvgRPZofQMBDB());
        values.put(PUBLICATIONMAXRPZOFQMB, publication.getMaxRPZofQMBDB());
        values.put(PUBLICATIONCATEGORY, publication.getCategoryDB());
        values.put(PUBLICATIONACTION, publication.getActionDB());
        values.put(PUBLICATIONASSIGNEDREPORTS, publication.getAssignedReportsDB());

        int i = db.update(TABLE_PUBLICATIONS,
                values,
                KEY_ID + " = ?",
                new String[]{String.valueOf(publication.getPubIDDB())});

        db.close();

        System.out.println(" updatePublication ::: "+publication.toString());

        return i;
    }

    public void deletePublication(Publication publication) {

        SQLiteDatabase db = this.getWritableDatabase();

        db.delete(TABLE_PUBLICATIONS,
                KEY_ID + " = ?",
                new String[]{String.valueOf(publication.getPubIDDB())});

        db.close();

        System.out.println("deletePublication ::: " + publication.toString());
    }

    public boolean verification(String ID){
        int count = -1;
        Cursor c = null;
        SQLiteDatabase db = this.getReadableDatabase();

        try {
            String query = "SELECT COUNT(*) FROM "
                    + TABLE_PUBLICATIONS + " WHERE " + PUBLICATIONID + " = ?";
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

