package com.example.pc_.databasesqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by PC- on 04/08/2016.
 */
public class DBController {

    private DatabaseHelper dbHelper;
    private Context context;
    private SQLiteDatabase db;

    public DBController(Context context) {
        this.context = context;
    }

    public DBController open() {
        dbHelper = new DatabaseHelper(context);
        db = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }

    public void insertarData(String nombre, String direccion, int edad){
        ContentValues values = new ContentValues();
        values.put("nombre", nombre);
        values.put("edad", edad);
        values.put("domicilio", direccion);
        db.insert(DatabaseHelper.TABLE_NAME, null, values);
    }

    public Cursor getData() {
        String[] columnas = new String[]{DatabaseHelper._ID, "nombre", "edad", "domicilio"};
        Cursor cursor = db.query(
                DatabaseHelper.TABLE_NAME,
                columnas,
                null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public void delete(long id) {
        db.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + id, null);
    }

}
