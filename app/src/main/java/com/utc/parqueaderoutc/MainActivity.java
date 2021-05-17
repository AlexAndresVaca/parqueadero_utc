package com.utc.parqueaderoutc;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


/*
*  @authors: Quishpe Vanesa, Tapia Angel, Vaca Alex A.
*  @starts: 16/05/2021
*  @last update: 16/05/2021
*  @Description: Configuración del boton crear cuenta dando acceso a la ventana de registro
*
* */

public class MainActivity extends AppCompatActivity {
    //PROCESO 1
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //PROCESO 2
    public void abrirPantallaRegistro(View vista)
    {
        //Creando un Intent para invocar a la ventana de Registro
        Intent pantallaRegistro = new Intent(getApplicationContext(), Registro.class);
        //Iniciando la pantalla de registro
        startActivity(pantallaRegistro);
    }
}