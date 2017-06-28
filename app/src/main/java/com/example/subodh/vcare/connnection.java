package com.example.subodh.vcare;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.subodh.vcare.VCareDatabaseContract.*;
import com.example.subodh.vcare.AllRawData.*;

/**
 * Created by Subodh on 6/11/2016.
 */
public class connnection extends SQLiteOpenHelper{
    public static final int VERSION=1;
    public static final String DATABASE="vcare.db";
    public static final String TABLE_NAME="patient";
    public static final String ID="id";
    public static final String NAME="name";
    public static final String AGE="age";
    public static final String PHONE="phone";
    public static final String USERNAME="userName";
    public static final String PASSWORD="password";
    public static final String FEESTATUS="feeStatus";
    public static final String STATUS="status";
    SQLiteDatabase db,db1;
    public static final String TABLE_CREATE="create table if not exists "+TABLE_NAME+" ("+ID+" integer primary key autoincrement, "+NAME+
            " text not null, "+AGE+" integer not null, "+PHONE+" integer not null, "+USERNAME+" text not null, "+PASSWORD+" text not null, "+
             FEESTATUS+" text not null, "+STATUS+" text not null);";

    public connnection(Context context)
    {
        super(context, DATABASE, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        final String SQL_CREATE_MEDIACTION_TABLE = "create table if not exists "+ MedicationListEntry.TABLE_NAME_MEDICATION +
                " ("+MedicationListEntry.ID_MEDICATION+" integer primary key autoincrement, "+
                MedicationListEntry.PATIENT_ID_MEDICATION+ " integer not null, "+
                MedicationListEntry.NAME_OF_MEDICATION+ " text not null );";

        final String SQL_CREATE_DOS_TABLE = "create table if not exists "+DosListEntry.TABLE_NAME_DOS+
                " ("+DosListEntry.ID_DOS+" integer primary key autoincrement, "+
                DosListEntry.PROBLEM_ID_DOS+" integer not null, "+
                DosListEntry.NAME_OF_DOS+" text not null );";

        final String SQL_CREATE_DONTS_TABLE = "create table if not exists "+DontsListEntry.TABLE_NAME_DONTS+
                " ("+DontsListEntry.ID_DONTS+" integer primary key autoincrement, "+
                DontsListEntry.PROBLEM_ID_DONTS+" integer not null, "+
                DontsListEntry.NAME_OF_DONTS+" text not null );";

        final String SQL_CREATE_PROBLEM_TABLE = "create table if not exists "+AllProblemsListEntry.TABLE_NAME_PROBLEM+
                " ("+AllProblemsListEntry.ID_PROBLEM+" integer primary key autoincrement, "+
                AllProblemsListEntry.NAME_OF_PROBLEM+" text not null );";

        final String SQL_CREATE_PATIENT_PROBLEMS_TABLE ="create table if not exists "+PatientProblemsListEntry.TABLE_NAME_PATIENT_PROBLEM+
                " ("+PatientProblemsListEntry.ID_PATIENT_PROBLEM+" integer primary key autoincrement, "+
                PatientProblemsListEntry.PATIENT_ID+" integer not null, "+
                PatientProblemsListEntry.PROBLEM_ID+" integer not null, "+
                PatientProblemsListEntry.NAME_OF_PATIENT_PROBLEM+" text not null );";

        final String SQL_CREATE_PATIENT_CHECKUPS_TABLE = "create table if not exists "+PatientCheckupsListEntry.TABLE_NAME_PATIENT_CHECKUP+
                " ("+PatientCheckupsListEntry.ID_PATIENT_CHECKUP+" integer primary key autoincrement, "+
                PatientCheckupsListEntry.PATIENT_ID+" integer not null, "+
                PatientCheckupsListEntry.FEES+" text not null, "+
                PatientCheckupsListEntry.NAME_OF_CHECKUP+" text not null );";


        db.execSQL(SQL_CREATE_MEDIACTION_TABLE);
        db.execSQL(SQL_CREATE_DOS_TABLE);
        db.execSQL(SQL_CREATE_DONTS_TABLE);
        db.execSQL(SQL_CREATE_PROBLEM_TABLE);
        db.execSQL(SQL_CREATE_PATIENT_PROBLEMS_TABLE);
        db.execSQL(SQL_CREATE_PATIENT_CHECKUPS_TABLE);

        db.execSQL(TABLE_CREATE);
        this.db= db;
    }

    public void insertValues(patientContact c)
    {
        db= getWritableDatabase();
        ContentValues values= new ContentValues();
        values.put(NAME,c.name);
        values.put(AGE,c.age);
        values.put(PHONE,c.phone);
        values.put(USERNAME,c.userName);
        values.put(PASSWORD,c.password);
        values.put(FEESTATUS,c.feeStatus);
        values.put(STATUS,c.status);
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public void insertAllProblems()
    {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        for(int i=0; i< AllProblemsList.allProblemsList.length; i++){
            values.put(AllProblemsListEntry.NAME_OF_PROBLEM, AllProblemsList.allProblemsList[i]);
            db.insert(AllProblemsListEntry.TABLE_NAME_PROBLEM,null,values);
            int id=fetchProblemId(AllProblemsList.allProblemsList[i]);
            Log.e("last id: ",String.valueOf(id));
            for (int j=0; j <AllDosList.allDosListForFever.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DosListEntry.NAME_OF_DOS,AllDosList.allDosListForFever[j]);
                values1.put(DosListEntry.PROBLEM_ID_DOS,id);
                db.insert(DosListEntry.TABLE_NAME_DOS,null,values1);
            }
            for (int j=0; j <AllDosList.allDosListForSugar.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DosListEntry.NAME_OF_DOS,AllDosList.allDosListForSugar[j]);
                values1.put(DosListEntry.PROBLEM_ID_DOS,id);
                db.insert(DosListEntry.TABLE_NAME_DOS,null,values1);
            }
            for (int j=0; j <AllDosList.allDosListForUrination.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DosListEntry.NAME_OF_DOS,AllDosList.allDosListForUrination[j]);
                values1.put(DosListEntry.PROBLEM_ID_DOS,id);
                db.insert(DosListEntry.TABLE_NAME_DOS,null,values1);
            }
            for (int j=0; j <AllDosList.allDosListForNausea.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DosListEntry.NAME_OF_DOS,AllDosList.allDosListForNausea[j]);
                values1.put(DosListEntry.PROBLEM_ID_DOS,id);
                db.insert(DosListEntry.TABLE_NAME_DOS,null,values1);
            }
            for (int j=0; j <AllDosList.allDosListForCough.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DosListEntry.NAME_OF_DOS,AllDosList.allDosListForCough[j]);
                values1.put(DosListEntry.PROBLEM_ID_DOS,id);
                db.insert(DosListEntry.TABLE_NAME_DOS,null,values1);
            }
            for (int j=0; j <AllDosList.allDosListForBreathing.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DosListEntry.NAME_OF_DOS,AllDosList.allDosListForBreathing[j]);
                values1.put(DosListEntry.PROBLEM_ID_DOS,id);
                db.insert(DosListEntry.TABLE_NAME_DOS,null,values1);
            }

            for (int j=0; j <AllDontsList.allDontsListForFever.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DontsListEntry.NAME_OF_DONTS,AllDontsList.allDontsListForFever[j]);
                values1.put(DontsListEntry.PROBLEM_ID_DONTS,id);
                db.insert(DontsListEntry.TABLE_NAME_DONTS,null,values1);
            }
            for (int j=0; j <AllDontsList.allDontsListForSugar.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DontsListEntry.NAME_OF_DONTS,AllDontsList.allDontsListForSugar[j]);
                values1.put(DontsListEntry.PROBLEM_ID_DONTS,id);
                db.insert(DontsListEntry.TABLE_NAME_DONTS,null,values1);
            }for (int j=0; j <AllDontsList.allDontsListForUrination.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DontsListEntry.NAME_OF_DONTS,AllDontsList.allDontsListForUrination[j]);
                values1.put(DontsListEntry.PROBLEM_ID_DONTS,id);
                db.insert(DontsListEntry.TABLE_NAME_DONTS,null,values1);
            }for (int j=0; j <AllDontsList.allDontsListForNausea.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DontsListEntry.NAME_OF_DONTS,AllDontsList.allDontsListForNausea[j]);
                values1.put(DontsListEntry.PROBLEM_ID_DONTS,id);
                db.insert(DontsListEntry.TABLE_NAME_DONTS,null,values1);
            }for (int j=0; j <AllDontsList.allDontsListForCough.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DontsListEntry.NAME_OF_DONTS,AllDontsList.allDontsListForCough[j]);
                values1.put(DontsListEntry.PROBLEM_ID_DONTS,id);
                db.insert(DontsListEntry.TABLE_NAME_DONTS,null,values1);
            }for (int j=0; j <AllDontsList.allDontsListForBreathing.length;j++){
                ContentValues values1 = new ContentValues();
                values1.put(DontsListEntry.NAME_OF_DONTS,AllDontsList.allDontsListForBreathing[j]);
                values1.put(DontsListEntry.PROBLEM_ID_DONTS,id);
                db.insert(DontsListEntry.TABLE_NAME_DONTS,null,values1);
            }
        }
        db.close();
    }

    public void insertPatientProblems(int patient_id, int problem_id, String problem_name)
    {
        db = getWritableDatabase();
        db1 = getReadableDatabase();
        ContentValues values = new ContentValues();
        values.put(PatientProblemsListEntry.PROBLEM_ID,problem_id);
        values.put(PatientProblemsListEntry.PATIENT_ID,patient_id);
        values.put(PatientProblemsListEntry.NAME_OF_PATIENT_PROBLEM,problem_name);
        db.insert(PatientProblemsListEntry.TABLE_NAME_PATIENT_PROBLEM,null,values);
        db.close();
        Log.e("values: ",values.toString());
    }

    public void insertMedication(int patient_id,String medicineName)
    {
        db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(MedicationListEntry.PATIENT_ID_MEDICATION,patient_id);
        values.put(MedicationListEntry.NAME_OF_MEDICATION,medicineName);
        db.insert(MedicationListEntry.TABLE_NAME_MEDICATION,null,values);
    }

    public String readvalue(String username)
    {
        db=getReadableDatabase();
        String query="select userName, password from "+TABLE_NAME+";";//id,name,age,phone,userName,password,feeStatus,status
        Cursor cursor= db.rawQuery(query,null);
        String name,pass="do not match";
        if(cursor.moveToFirst())
        {
            do{
                name=cursor.getString(0);
                if(name.equals(username))
                {
                    pass=cursor.getString(1);
                    break;
                }

            }while(cursor.moveToNext());
        }
        db.close();
        return pass;
    }

    public int fetchProblemId(String fieldName)
    {
        int id=0;
        db = getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from "+AllProblemsListEntry.TABLE_NAME_PROBLEM+";",null);
        if(cursor.moveToFirst())
        {
            do{
                if(cursor.getString(cursor.getColumnIndex(AllProblemsListEntry.NAME_OF_PROBLEM)).equals(fieldName)){
                    id = cursor.getInt(cursor.getColumnIndex(AllProblemsListEntry.ID_PROBLEM));
                }
            }while(cursor.moveToNext());
        }
        return id;
    }

    public int[] readProblemIds(int patient_id)
    {
        db = getReadableDatabase();
        int count=0, ids[], index=0;
        String query = "select * "+/* + PatientProblemsListEntry.PROBLEM_ID + "*/ " from "
                + PatientProblemsListEntry.TABLE_NAME_PATIENT_PROBLEM + ";";
        Cursor cursor = db.rawQuery(query,null);
        if(cursor.moveToFirst()){
            do{
                if(cursor.getInt(cursor.getColumnIndex(PatientProblemsListEntry.PATIENT_ID))==patient_id){
                    count++;
                }
            }while(cursor.moveToNext());
        }
        ids = new int[count];
        if(cursor.moveToFirst()){
            do{
                if(cursor.getInt(cursor.getColumnIndex(PatientProblemsListEntry.PATIENT_ID))==patient_id){
                    ids[index]=cursor.getInt(cursor.getColumnIndex(PatientProblemsListEntry.PROBLEM_ID));
                    Log.e("id : ", String.valueOf(ids[index]));
                }
            }while(cursor.moveToNext());
        }
        return ids;
    }

    public String[] readMedication(int patient_id)
    {
        int checkId, index=0;
        String medicationListString[], query;
        db=getReadableDatabase();
        query = "Select * from "+MedicationListEntry.TABLE_NAME_MEDICATION+";";
        Cursor cursor = db.rawQuery(query,null);
        medicationListString = new String[cursor.getCount()];
        if(cursor.moveToFirst())
        {
            do{
                checkId = cursor.getInt(cursor.getColumnIndex(MedicationListEntry.PATIENT_ID_MEDICATION));
                if(checkId == patient_id)
                {
                    medicationListString[index] = cursor.getString(cursor.getColumnIndex(MedicationListEntry.NAME_OF_MEDICATION));
                    index++;
                }
            }
            while (cursor.moveToNext());
        }
        return medicationListString;
    }

    public String[] readDos(int problem_id[]) {
        int index = 0;
        boolean checkId = false;
        String DosListString[], query;
        db = getReadableDatabase();
        query = "Select * from " + DosListEntry.TABLE_NAME_DOS + ";";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                for (int i = 0; i < problem_id.length; i++) {
                    if (cursor.getInt(cursor.getColumnIndex(DosListEntry.PROBLEM_ID_DOS)) == problem_id[i]) {
                        index++;
                        break;
                    }
                }

            }while (cursor.moveToNext());
        }

            DosListString = new String[index];
            index = 0;
            if (cursor.moveToFirst()) {
                do {
                    for (int i = 0; i < problem_id.length; i++) {
                        if (cursor.getInt(cursor.getColumnIndex(DosListEntry.PROBLEM_ID_DOS)) == problem_id[i]) {
                            checkId = true;
                            Log.e("problem id: ", cursor.getInt(cursor.getColumnIndex(DosListEntry.PROBLEM_ID_DOS)) + " " + problem_id[i]);
                            break;
                        }
                    }
                    Log.e("check id: ", String.valueOf(checkId));
                    if (checkId) {
                        DosListString[index] = cursor.getString(cursor.getColumnIndex(DosListEntry.NAME_OF_DOS));
                        index++;
                        checkId = false;
                    }
                }
                while (cursor.moveToNext());
            }
        return DosListString;
        }


    public void truncatePatientProblems()
    {
        db = getWritableDatabase();
        db.execSQL("delete from "+PatientProblemsListEntry.TABLE_NAME_PATIENT_PROBLEM+";");
    }
    public String[] readDonts(int problem_id[])
    {
        int index = 0;
        boolean checkId = false;
        String DontListString[], query;
        db = getReadableDatabase();
        query = "Select * from " + DontsListEntry.TABLE_NAME_DONTS + ";";
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                for (int i = 0; i < problem_id.length; i++) {
                    if (cursor.getInt(cursor.getColumnIndex(DontsListEntry.PROBLEM_ID_DONTS)) == problem_id[i]) {
                        index++;
                        break;
                    }
                }

            }while (cursor.moveToNext());
        }

        DontListString = new String[index];
        index = 0;
        if (cursor.moveToFirst()) {
            do {
                for (int i = 0; i < problem_id.length; i++) {
                    if (cursor.getInt(cursor.getColumnIndex(DontsListEntry.PROBLEM_ID_DONTS)) == problem_id[i]) {
                        checkId = true;
                        Log.e("problem id: ", cursor.getColumnIndex(DontsListEntry.PROBLEM_ID_DONTS) + " " + problem_id[i]);
                        break;
                    }
                }
                Log.e("check id: ", String.valueOf(checkId));
                if (checkId) {
                    DontListString[index] = cursor.getString(cursor.getColumnIndex(DontsListEntry.NAME_OF_DONTS));
                    index++;
                    checkId = false;
                }
            }
            while (cursor.moveToNext());
        }
        return DontListString;
    }

    public String readPatientProblems(int patient_id)
    {
        int checkPatientId;
        String patientProblemsListString, query;
        db=getReadableDatabase();
        query = "Select * from "+PatientProblemsListEntry.TABLE_NAME_PATIENT_PROBLEM+";";
        Cursor cursor = db.rawQuery(query,null);
        patientProblemsListString = "";
        if(cursor.moveToFirst())
        {
            do{
                checkPatientId = cursor.getInt(cursor.getColumnIndex(PatientProblemsListEntry.PATIENT_ID));
                if(checkPatientId == patient_id )
                {
                    patientProblemsListString +=  cursor.getString(cursor.getColumnIndex(PatientProblemsListEntry.NAME_OF_PATIENT_PROBLEM));
                    patientProblemsListString += "\n";
                }
            }
            while (cursor.moveToNext());
        }
        return patientProblemsListString;
    }


    public Cursor readRecord(String username)
    {
        db=getReadableDatabase();
        String query="select * from "+TABLE_NAME+";";//id,name,age,phone,userName,password,feeStatus,status
        Cursor cursor= db.rawQuery(query,null);
        int index= cursor.getColumnIndex("userName");
        if(cursor.moveToFirst())
        {   do
            {

                if(cursor.getString(index).equals(username))
                {
                    return cursor;
                }
            }while(cursor.moveToNext());

        }
        db.close();

        return null;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

}
