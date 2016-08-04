package com.example.pc_.databasesqlite;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    EditText editNombre, editDomicilio, editEdad;
    Button btnInsertar, btnBorrar;
    DBController dbController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dbController = new DBController(MainActivity.this);
        textView = (TextView) findViewById(R.id.txtContenido);
        editDomicilio = (EditText) findViewById(R.id.editDireccion);
        editEdad = (EditText) findViewById(R.id.editEdad);
        editNombre = (EditText) findViewById(R.id.editNombre);
        btnBorrar = (Button) findViewById(R.id.btnBorrar);
        btnInsertar = (Button) findViewById(R.id.btnInsertar);

    }

    @Override
    protected void onResume() {
        super.onResume();
        btnInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbController.open();
                dbController.insertarData(editNombre.getText().toString(),
                        editDomicilio.getText().toString(),
                        Integer.parseInt(editEdad.getText().toString()));
                showData();
                dbController.close();
            }
        });

        btnBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbController.open();
                dbController.delete(0);
                showData();
                dbController.close();
            }
        });
    }

    public void showData(){
        Cursor cursor = dbController.getData();
        if (cursor.moveToFirst()){
            do {
                //TODO
                //int id = cursor.getInt(0);
                String name = cursor.getString(1);
                int edad = cursor.getInt(2);
                String domicilio = cursor.getString(3);
                textView.append("nombre: " + name + "\n"
                        + " direccion: " + domicilio + "\n"
                        + " edad: " + edad + "\n\n");
            } while (cursor.moveToNext());
        }
    }
}
