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
public class FragmentoClientes extends Fragment {

    private EditText codCliente, nombres, apellidos, direccion, ciudad;
    private Button btBuscar, btInsertar, btModificar, btEliminar, btLimpiarCampos;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragmento_clientes, container, false);

        codCliente = vista.findViewById( R.id.edtCliente );
        nombres = vista.findViewById( R.id.edtNombres );
        apellidos = vista.findViewById( R.id.edtApellidos );
        direccion = vista.findViewById( R.id.edtDireccion );
        ciudad = vista.findViewById( R.id.edtCiudad );
        btBuscar = vista.findViewById( R.id.btnBuscarCliente );
        btInsertar = vista.findViewById( R.id.btnInsertarCliente );
        btModificar = vista.findViewById( R.id.btnActualizarCliente );
        btEliminar = vista.findViewById( R.id.btnEliminarCliente );
        btLimpiarCampos = vista.findViewById( R.id.btnLimpiarCamposCliente );

        // ACCION PARA INSERTAR UN CLIENTE
        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_cliente = codCliente.getText().toString(),
                        sNombreCliente = nombres.getText().toString(),
                        sApellidosCliente = apellidos.getText().toString(),
                        sDireccionCliente = direccion.getText().toString(),
                        sCiudadCliente = ciudad.getText().toString();

                ContentValues detalleInsertarCliente = new ContentValues();
                detalleInsertarCliente.put( "id_cliente", id_cliente );
                detalleInsertarCliente.put( "sNombreCliente", sNombreCliente );
                detalleInsertarCliente.put( "sApellidosCliente", sApellidosCliente );
                detalleInsertarCliente.put( "sDireccionCliente", sDireccionCliente );
                detalleInsertarCliente.put( "sCiudadCliente", sCiudadCliente );

                try {
                    db.insert( "MD_clientes", null, detalleInsertarCliente );
                    db.close();
                    Toast.makeText( getContext(), "Se inserto el cliente exitosamente", Toast.LENGTH_LONG ).show();
                }catch ( Exception e ){
                    Toast.makeText( getContext(), "ERROR: No se pudo insertar el registro", Toast.LENGTH_LONG ).show();
                }
            }
        });

        // ACCION PARA BUSCAR UN CLIENTE
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_cliente = codCliente.getText().toString();

                Cursor fila = db.rawQuery( "select sNombreCliente, sApellidosCliente, sDireccionCliente, sCiudadCliente from MD_clientes WHERE id_cliente = " + id_cliente,
                        null );

                if ( fila.moveToFirst() ){
                    nombres.setText( fila.getString( 0 ) );
                    apellidos.setText( fila.getString( 1 ) );
                    direccion.setText( fila.getString( 2 ) );
                    ciudad.setText( fila.getString( 3 ) );
                    Toast.makeText( getContext(), "El registro fue encontrado", Toast.LENGTH_LONG ).show();

                }else {
                    Toast.makeText( getContext(), "Registro no encontrado", Toast.LENGTH_LONG ).show();
                }
                db.close();
            }

        });

        //ACCION PARA MODIFICAR UN CLIENTE
        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_cliente = codCliente.getText().toString(),
                        sNombreCliente = nombres.getText().toString(),
                        sApellidosCliente = apellidos.getText().toString(),
                        sDireccionCliente = direccion.getText().toString(),
                        sCiudadCliente = ciudad.getText().toString();

                ContentValues detalleInsertarCliente = new ContentValues();
                detalleInsertarCliente.put( "id_cliente", id_cliente );
                detalleInsertarCliente.put( "sNombreCliente", sNombreCliente );
                detalleInsertarCliente.put( "sApellidosCliente", sApellidosCliente );
                detalleInsertarCliente.put( "sDireccionCliente", sDireccionCliente );
                detalleInsertarCliente.put( "sCiudadCliente", sCiudadCliente );

                int cat = db.update( "MD_clientes", detalleInsertarCliente, "id_cliente = " + id_cliente, null );
                db.close();
                if ( cat == 1 ){
                    Toast.makeText( getContext(), "Se actualizo el cliente", Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText( getContext(), "ERROR: no se actualizo el cliente", Toast.LENGTH_LONG ).show();
                }
            }
        });

        // ACCION PARA BORRAR UN CLIENTE
        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_cliente = codCliente.getText().toString();

                int cat = db.delete( "MD_clientes", "id_cliente = " + id_cliente, null );

                if ( cat == 1 ){
                    Toast.makeText( getContext(), "Se elimino el cliente", Toast.LENGTH_LONG ).show();
                    codCliente.setText( "" );
                    nombres.setText( "" );
                    apellidos.setText( "" );
                    direccion.setText( "" );
                    ciudad.setText( "" );
                }else{
                    Toast.makeText( getContext(), "ERROR: No se pudo realizar la eliminación del cliente", Toast.LENGTH_LONG ).show();
                }

                db.close();
            }
        });

        // ACCION PARA LIMPIAR LOS CAMPOS
        btLimpiarCampos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codCliente.setText("");
                nombres.setText("");
                apellidos.setText("");
                direccion.setText("");
                ciudad.setText("");
            }
        });
        return vista;
    }
}
