package com.example.parcial3diferidoivanmendoza.Helpers;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLiteHelper extends SQLiteOpenHelper {

    public AdminSQLiteHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(" CREATE TABLE MD_clientes ( " +
                "id_cliente int PRIMARY KEY, " +
                "sNombreCliente text not null, " +
                "sApellidosCliente text not null, " +
                "sDireccionCliente text not null, " +
                "sCiudadCliente text not null ) ");

        sqLiteDatabase.execSQL( "CREATE TABLE MD_vehiculos ( " +
                "id_vehiculo int PRIMARY KEY, " +
                "Marca text not null, " +
                "sModelo text not null )" );

        sqLiteDatabase.execSQL( "CREATE TABLE MD_ClienteVehiculo( " +
                "id_cliente int, " +
                "id_vehiculo int, " +
                "sMatricula text, " +
                "iKilometros int, " +
                "PRIMARY KEY (id_cliente, id_vehiculo)," +
                "FOREIGN KEY ( id_cliente ) REFERENCES MD_clientes( id_cliente )," +
                "FOREIGN KEY ( id_vehiculo ) REFERENCES MD_vehiculos ( id_vehiculo )" +
                " )" );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
