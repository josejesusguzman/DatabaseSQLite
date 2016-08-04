package com.example.pc_.databasesqlite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by PC- on 04/08/2016.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    static final String DB_NAME = "CursoUPIICSA.db"; //Nombre de la BD
    static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "CURSO";
    public static final String _ID = "id";

    String sqliteCreate = "CREATE TABLE "
            + TABLE_NAME
            + " ( "
            + _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "nombre TEXT, "
            + "edad INTEGER, "
            + "domicilio TEXT );";

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqliteCreate);
    }

    //Se llama cuando el valor de la version de la BD se cambia
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int currentVersion) {
        db.execSQL("DROP TABLE IF EXIST CURSO");
        db.execSQL(sqliteCreate);
    }
}
