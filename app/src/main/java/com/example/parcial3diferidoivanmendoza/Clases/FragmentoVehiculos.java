package com.example.parcial3diferidoivanmendoza.Clases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.parcial3diferidoivanmendoza.Helpers.AdminSQLiteHelper;
import com.example.parcial3diferidoivanmendoza.R;

public class FragmentoVehiculos extends Fragment {

    private EditText codVehiculo, marca, modelo;
    private Button btBuscar, btInsertar, btModificar, btEliminar, btLimpiarCampos;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragmento_vehiculos, container, false);

        codVehiculo = vista.findViewById( R.id.edtCodigoVehiculo );
        marca = vista.findViewById( R.id.edtMarca );
        modelo = vista.findViewById( R.id.edtModelo );
        btBuscar = vista.findViewById( R.id.btnBuscarVehiculo );
        btInsertar = vista.findViewById( R.id.btnInsertarVehiculo );
        btModificar = vista.findViewById( R.id.btnModificarVehiculo );
        btEliminar = vista.findViewById( R.id.btnEliminarVehiculo );
        btLimpiarCampos = vista.findViewById( R.id.btnLimpiarCamposVehiculo );

        // ACCION PARA INSERTAR UN VEHICULO
        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_vehiculo = codVehiculo.getText().toString(),
                        Marca = marca.getText().toString(),
                        sModelo = modelo.getText().toString();

                ContentValues detalleInsertarVehiculo = new ContentValues();
                detalleInsertarVehiculo.put( "id_vehiculo", id_vehiculo );
                detalleInsertarVehiculo.put( "Marca", Marca );
                detalleInsertarVehiculo.put( "sModelo", sModelo );

                try {
                    db.insert( "MD_vehiculos", null, detalleInsertarVehiculo );
                    db.close();
                    Toast.makeText( getContext(), "Se inserto el vehiculo exitosamente", Toast.LENGTH_LONG ).show();
                }catch ( Exception e ){
                    Toast.makeText( getContext(), "ERROR: No se pudo insertar el registro", Toast.LENGTH_LONG ).show();
                }
            }
        });

        // ACCION PARA BUSCAR UN VEHICULO
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_vehiculo = codVehiculo.getText().toString();

                Cursor fila = db.rawQuery( "select Marca, sModelo from MD_vehiculos WHERE id_vehiculo = " + id_vehiculo,
                        null );

                if ( fila.moveToFirst() ){
                    marca.setText( fila.getString( 0 ) );
                    modelo.setText( fila.getString( 1 ) );
                    Toast.makeText( getContext(), "El registro fue encontrado", Toast.LENGTH_LONG ).show();

                }else {
                    Toast.makeText( getContext(), "ERROR: Registro no encontrado", Toast.LENGTH_LONG ).show();
                }
                db.close();
            }

        });

        //ACCION PARA MODIFICAR UN VEHICULO
        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_vehiculo = codVehiculo.getText().toString(),
                        Marca = marca.getText().toString(),
                        sModelo = modelo.getText().toString();

                ContentValues detalleInsertarVehiculo = new ContentValues();
                detalleInsertarVehiculo.put( "id_vehiculo", id_vehiculo );
                detalleInsertarVehiculo.put( "Marca", Marca );
                detalleInsertarVehiculo.put( "sModelo", sModelo );

                int cat = db.update( "MD_vehiculos", detalleInsertarVehiculo, "id_vehiculo = " + id_vehiculo, null );
                db.close();
                if ( cat == 1 ){
                    Toast.makeText( getContext(), "Se actualizo el vehiculo", Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText( getContext(), "ERROR: no se actualizo el vehiculo", Toast.LENGTH_LONG ).show();
                }
            }
        });

        // ACCION PARA BORRAR UN VEHICULO
        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_vehiculo = codVehiculo.getText().toString();

                int cat = db.delete( "MD_vehiculos", "id_vehiculo = " + id_vehiculo, null );

                if ( cat == 1 ){
                    Toast.makeText( getContext(), "Se elimino el vehiculo", Toast.LENGTH_LONG ).show();
                    codVehiculo.setText("");
                    marca.setText("");
                    modelo.setText("");
                }else{
                    Toast.makeText( getContext(), "ERROR: No se pudo realizar la eliminación del vehiculo", Toast.LENGTH_LONG ).show();
                }

                db.close();
            }
        });

        // ACCION PARA LIMPIAR LOS CAMPOS
        btLimpiarCampos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codVehiculo.setText("");
                marca.setText("");
                modelo.setText("");
            }
        });
        return vista;
    }
}
