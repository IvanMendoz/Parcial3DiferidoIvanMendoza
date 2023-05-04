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

public class FragmentoCarrosClientes extends Fragment {

    private EditText codClienteVehiculo, codVehiculoCliente, matricula, kilometraje;
    private Button btBuscar, btInsertar, btModificar, btEliminar, btLimpiarCampos;

    @Nullable
    @Override
    public android.view.View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View vista = inflater.inflate(R.layout.fragmento_vehiculos_clientes, container, false);

        codClienteVehiculo = vista.findViewById( R.id.edtCodigoClienteVehiculo );
        codVehiculoCliente = vista.findViewById( R.id.edtCodigoVehiculoCliente );
        matricula = vista.findViewById( R.id.edtMatricula );
        kilometraje = vista.findViewById( R.id.edtKilometros );
        btBuscar = vista.findViewById( R.id.btnBuscarVehiculoRenta );
        btInsertar = vista.findViewById( R.id.btnInsertarVehiculoRenta );
        btModificar = vista.findViewById( R.id.btnModificarVehiculoRenta );
        btEliminar = vista.findViewById( R.id.btnEliminarVehiculoRenta );
        btLimpiarCampos = vista.findViewById( R.id.btnLimpiarCamposVehiculoRenta );

        // ACCION PARA INSERTAR UN VEHICULO EN RENTA
        btInsertar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_cliente = codClienteVehiculo.getText().toString(),
                        id_vehiculo = codVehiculoCliente.getText().toString(),
                        sMatricula = matricula.getText().toString(),
                        iKilometros = kilometraje.getText().toString();

                ContentValues detalleInsertarVehiculoRenta = new ContentValues();
                detalleInsertarVehiculoRenta.put( "id_cliente", id_cliente );
                detalleInsertarVehiculoRenta.put( "id_vehiculo", id_vehiculo );
                detalleInsertarVehiculoRenta.put( "sMatricula", sMatricula );
                detalleInsertarVehiculoRenta.put( "iKilometros", iKilometros );

                try {
                    db.insert( "MD_ClienteVehiculo", null, detalleInsertarVehiculoRenta );
                    db.close();
                    Toast.makeText( getContext(), "Se inserto el vehiculo en renta exitosamente", Toast.LENGTH_LONG ).show();
                }catch ( Exception e ){
                    Toast.makeText( getContext(), "ERROR: No se pudo insertar el registro", Toast.LENGTH_LONG ).show();
                }
            }
        });

        // ACCION PARA BUSCAR UN VEHICULO EN RENTA
        btBuscar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_cliente = codClienteVehiculo.getText().toString();

                Cursor fila = db.rawQuery( "select id_vehiculo, sMatricula, iKilometros from MD_ClienteVehiculo WHERE id_cliente = " + id_cliente,
                        null );

                if ( fila.moveToFirst() ){
                    codVehiculoCliente.setText( fila.getString( 0 ) );
                    matricula.setText( fila.getString( 1 ) );
                    kilometraje.setText( fila.getString( 2 ) );
                    Toast.makeText( getContext(), "El registro fue encontrado", Toast.LENGTH_LONG ).show();

                }else {
                    Toast.makeText( getContext(), "ERROR: Registro no encontrado", Toast.LENGTH_LONG ).show();
                }
                db.close();
            }
        });

        //ACCION PARA MODIFICAR UN VEHICULO EN RENTA
        btModificar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_cliente = codClienteVehiculo.getText().toString(),
                        id_vehiculo = codVehiculoCliente.getText().toString(),
                        sMatricula = matricula.getText().toString(),
                        iKilometros = kilometraje.getText().toString();

                ContentValues detalleInsertarVehiculoRenta = new ContentValues();
                detalleInsertarVehiculoRenta.put( "id_cliente", id_cliente );
                detalleInsertarVehiculoRenta.put( "id_vehiculo", id_vehiculo );
                detalleInsertarVehiculoRenta.put( "sMatricula", sMatricula );
                detalleInsertarVehiculoRenta.put( "iKilometros", iKilometros );

                int cat = db.update( "MD_ClienteVehiculo", detalleInsertarVehiculoRenta, "id_cliente = " + id_cliente, null );
                db.close();
                if ( cat == 1 ){
                    Toast.makeText( getContext(), "Se actualizo el vehiculo en renta", Toast.LENGTH_LONG ).show();
                }else{
                    Toast.makeText( getContext(), "ERROR: no se actualizo el vehiculo en renta", Toast.LENGTH_LONG ).show();
                }
            }
        });

        // ACCION PARA BORRAR UN VEHICULO EN RENTA
        btEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AdminSQLiteHelper admin = new AdminSQLiteHelper( getContext(), "Parcial3Diferido", null, 1 );
                SQLiteDatabase db =admin.getWritableDatabase();
                String id_cliente = codClienteVehiculo.getText().toString();

                int cat = db.delete( "MD_ClienteVehiculo", "id_cliente = " + id_cliente, null );

                if ( cat == 1 ){
                    Toast.makeText( getContext(), "Se elimino el vehiculo en renta", Toast.LENGTH_LONG ).show();
                    codClienteVehiculo.setText("");
                    codVehiculoCliente.setText("");
                    matricula.setText("");
                    kilometraje.setText("");
                }else{
                    Toast.makeText( getContext(), "ERROR: No se pudo realizar la eliminación del vehiculo en renta", Toast.LENGTH_LONG ).show();
                }

                db.close();
            }
        });

        // ACCION PARA LIMPIAR LOS CAMPOS
        btLimpiarCampos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                codClienteVehiculo.setText("");
                codVehiculoCliente.setText("");
                matricula.setText("");
                kilometraje.setText("");
            }
        });
        return vista;
    }
}
