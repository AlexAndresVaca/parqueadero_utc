package com.utc.parqueaderoutc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;


/*
 *  @authors: Quishpe Vanesa, Tapia Angel, Vaca Alex A.
 *  @starts: 16/05/2021
 *  @last update: 16/05/2021
 *  @Description: Pantalla para registrar usuarios y almacenar en una base de datos SQLite
 * */
public class Registro extends AppCompatActivity {
    //Entrada
    EditText txtApellidoRegistro, txtNombreRegistro, txtEmailRegistro, txtPasswordRegistro, txtPasswordConfirmaRegistro, txtTelefonoRegistro, txtDireccionRegistro; //Definiendo objetos  para capturar datos de la vista
    BaseDatos miBdd; //Creando un objeto para acceder a los procesos de la base de datos

    //Proceso 1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);
        //Mapeo de elementos
        txtApellidoRegistro = (EditText) findViewById(R.id.txtApellidoRegistro);
        txtNombreRegistro = (EditText) findViewById(R.id.txtNombreRegistro);
        txtEmailRegistro = (EditText) findViewById(R.id.txtEmailRegistro);
        txtPasswordRegistro = (EditText) findViewById(R.id.txtPasswordRegistro);
        txtPasswordConfirmaRegistro = (EditText) findViewById(R.id.txtPasswordConfirmaRegistro);
        txtTelefonoRegistro = (EditText) findViewById(R.id.txtTelefonoRegistro);
        txtDireccionRegistro = (EditText) findViewById(R.id.txtDireccionRegistro);
        miBdd = new BaseDatos(getApplicationContext()); // Instanciar/construirr la base de datos en el objeto miBdd
    }

    //Proceso 2: Cerrar la ventana de registro = Boton Cancelar
    public void cerrarPantallaRegistro(View vista) {
        finish(); //Cerrando la pantalla de registro
    }

    // Validar que sea un email
    private boolean isValidEmail(String email) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email).matches();

    }

    //Proceso 3: Registrar a los usuarios
    public void registrarUsuario(View vista) {
        String apellido = txtApellidoRegistro.getText().toString();
        String nombre = txtNombreRegistro.getText().toString();
        String email = txtEmailRegistro.getText().toString();
        String password = txtPasswordRegistro.getText().toString();
        String passwordconfirma = txtPasswordConfirmaRegistro.getText().toString();
        String telefono = txtTelefonoRegistro.getText().toString();
        String direccion = txtDireccionRegistro.getText().toString();
        int cont = 0;
        //Validar que el campo APELLIDO no este en blanco
        if (TextUtils.isEmpty(apellido)) {
            cont++;
            txtApellidoRegistro.setError("Campo Requerido");
            txtApellidoRegistro.requestFocus();
        }
        //Validar que el campo NOMBRE no este en blanco
        if (TextUtils.isEmpty(nombre)) {
            cont++;
            txtNombreRegistro.setError("Campo Requerido");
            txtNombreRegistro.requestFocus();
        }
        if (TextUtils.isEmpty(email) || isValidEmail(email)) {
            cont++;
            txtEmailRegistro.setError("Tu email no es válido");
            txtEmailRegistro.requestFocus();
        }
        //Valida que las constraseñas sean iguales
        if (!password.equals(passwordconfirma) || password.length() >= 6) {
            cont++;
            txtPasswordRegistro.setError("Contraseñas no coinciden o no tiene al menos 6 caracteres");
            txtPasswordRegistro.requestFocus();
        }
        if (cont > 0) {
            //
            //Toast.makeText(getApplicationContext(), "Error en el formulario", Toast.LENGTH_LONG).show();//Mostrando el mensaje de erros
        } else {
            miBdd.agregarUsuarios(apellido, nombre, email, password, passwordconfirma, telefono, direccion);
            // de aqui regresa
            this.cerrarPantallaRegistro(vista);
        }

    }
}


