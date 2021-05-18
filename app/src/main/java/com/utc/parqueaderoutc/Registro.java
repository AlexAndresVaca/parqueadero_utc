package com.utc.parqueaderoutc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;

import android.widget.Toast;

import java.util.Date;
import java.util.regex.Pattern;


/*
 *  @authors: Quishpe Vanesa, Tapia Angel, Vaca Alex A.
 *  @starts: 16/05/2021
 *  @last update: 17/05/2021
 *  @Description: Pantalla para registrar usuarios y almacenar en una base de datos SQLite
 * */

public class Registro extends AppCompatActivity {
    //Entrada
    EditText txtApellidoRegistro, txtNombreRegistro, txtEmailRegistro, txtPasswordRegistro, txtPasswordConfirmaRegistro, txtTelefonoRegistro, txtDireccionRegistro; //Definiendo objetos  para capturar datos de la vista
    BaseDatos miBdd; //Creando un objeto para acceder a los procesos de la base de datos
    // Método para obtura la fecha
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");

    //Proceso 1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Mapeo de elementos
        txtApellidoRegistro         = (EditText) findViewById(R.id.txtApellidoRegistro);
        txtNombreRegistro           = (EditText) findViewById(R.id.txtNombreRegistro);
        txtEmailRegistro            = (EditText) findViewById(R.id.txtEmailRegistro);
        txtPasswordRegistro         = (EditText) findViewById(R.id.txtPasswordRegistro);
        txtPasswordConfirmaRegistro = (EditText) findViewById(R.id.txtPasswordConfirmaRegistro);
        txtTelefonoRegistro         = (EditText) findViewById(R.id.txtTelefonoRegistro);
        txtDireccionRegistro        = (EditText) findViewById(R.id.txtDireccionRegistro);

        miBdd = new BaseDatos(getApplicationContext()); // Instanciar/construirr la base de datos en el objeto miBdd
    }

    //Proceso 2: Cerrar la ventana de registro = Boton Cancelar
    public void cerrarPantallaRegistro(View vista) {
        finish(); //Cerrando la pantalla de registro
    }

    // Funcion Validar que sea un email
    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();
    }

    //Proceso 3: Registrar a los usuarios
    public void registrarUsuario(View vista) {
        String apellido         = txtApellidoRegistro.getText().toString();
        String nombre           = txtNombreRegistro.getText().toString();
        String email            = txtEmailRegistro.getText().toString();
        String password         = txtPasswordRegistro.getText().toString();
        String passwordconfirma = txtPasswordConfirmaRegistro.getText().toString();
        String telefono         = txtTelefonoRegistro.getText().toString();
        String direccion        = txtDireccionRegistro.getText().toString();
        // Sera el que cuente los errores
        int cont = 0;
        //Validar que el campo APELLIDO no este en blanco
        if (TextUtils.isEmpty(apellido)) {
            cont++;
            txtApellidoRegistro.setError("Debes ingresar mínimo un apellido");
            txtApellidoRegistro.requestFocus();
        }
        //  Validar que el campo NOMBRE no este en blanco
        if (TextUtils.isEmpty(nombre)) {
            cont++;
            txtNombreRegistro.setError("Debes ingresar mínimo un nombre");
            txtNombreRegistro.requestFocus();
        }
        // Validacion de EMAIL no este vacio, y sea un email
        if (TextUtils.isEmpty(email) || !isValidEmail(email)) {
            cont++;
            txtEmailRegistro.setError("Tu email no es válido");
            txtEmailRegistro.requestFocus();
        }
        //Validar que el campo PASSWORD no este en blanco
        if (TextUtils.isEmpty(password) || password.length() < 6) {
            cont++;
            txtPasswordRegistro.setError("Necesitas una contraseña de al menos 6 caracteres");
            txtPasswordRegistro.requestFocus();
        }
        //Valida que las constraseñas sean iguales
        if (!password.equals(passwordconfirma)) {
            cont++;
            txtPasswordConfirmaRegistro.setError("Las contraseñas no coinciden");
            txtPasswordConfirmaRegistro.requestFocus();
        }
        //Validar que el campo TELEFONO no este en blanco
        if (TextUtils.isEmpty(telefono)) {
            cont++;
            txtTelefonoRegistro.setError("Necesitas registras un numero de teléfono");
            txtTelefonoRegistro.requestFocus();
        }
        //Validar que el campo Direccion no este en blanco
        if (TextUtils.isEmpty(direccion)) {
            cont++;
            txtDireccionRegistro.setError("Necesitas registrar tu dirección");
            txtDireccionRegistro.requestFocus();
        }
        // Verificar si no hubo errores en el formulario
        if (cont == 0) {
            // Obturamos la fecha y hora de registro
            String fechaRegistro = simpleDateFormat.format(new Date());
            // Almacenamos la informacion en la BDD
            miBdd.agregarUsuarios(apellido, nombre, email, password, telefono, direccion, fechaRegistro);
            // Muestra un mensaje de confirmacion
            Toast.makeText(getApplicationContext(), "Registro exitoso!", Toast.LENGTH_LONG).show();
            // Retorna al login
            this.cerrarPantallaRegistro(vista);
        }

    }

}


