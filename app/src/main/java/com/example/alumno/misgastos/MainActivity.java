package com.example.alumno.misgastos;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    SQLiteDatabase baseDatos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void abrirBaseDatos(){
        try
        {

            baseDatos = openOrCreateDatabase("DINERO", MODE_WORLD_WRITEABLE, null);
            baseDatos.execSQL(crearTablaDinero());
            Toast.makeText(getApplicationContext(),"SI",Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(getApplicationContext(), "Error al abrir o crear la base de datos",Toast.LENGTH_SHORT).show();
        }
    }
    public String crearTablaDinero(){

        baseDatos.execSQL("INSERT INTO DINERO (INGRESO) VALUES (300)");
        return tabla;
    }
    public void sacarDatos(View view){
        abrirBaseDatos();
        Toast.makeText(getApplicationContext(),"AAAAAAA",Toast.LENGTH_SHORT).show();
        String[] args = new String[] {"INGRESOS"};
        /*Cursor c = baseDatos.rawQuery("SELECT INGRESOS FROM DINERO", args);
        Toast.makeText(getApplicationContext(),c.getInt(0),Toast.LENGTH_SHORT).show();
    }*/
    public void crear() {

        String tabla="CREATE TABLE IF NOT EXISTS DINERO(" +
                "INGRESOS INTEGER," +
                "GASTOS INTEGER)";
        baseDatos.execSQL(tabla);
        baseDatos.execSQL("INSERT INTO DINERO (INGRESOS) " +
                "VALUES (300)");
        baseDatos.close();
    }
    public String getIngreso(){
        String b="";
        crear();
        Cursor c = baseDatos.rawQuery("SELECT INGRESOS FROM DINERO",null);
        try{
            if(c.moveToFirst()){
                int i=1;
                do{
                    int a=c.getInt(0);
                    b+=(i)+"="+a;
                    i++;
                }while(c.moveToNext());
            }
        }
        catch(Exception e){
            Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_SHORT).show();
            b="ERROR";
        }
        return b;
    }
    public void sacarDatos(View view){
        String t=getIngreso();
        Toast.makeText(getApplicationContext(),t,Toast.LENGTH_SHORT).show();
    }
    public void aa(View view){
        EditText e=(EditText)findViewById(R.id.editText);
        String a=e.getText().toString();
        Toast.makeText(getApplicationContext(),a,Toast.LENGTH_SHORT).show();
    }
}
