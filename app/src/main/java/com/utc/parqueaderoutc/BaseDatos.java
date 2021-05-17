package com.utc.parqueaderoutc;

/*
 *  @authors: Quishpe Vanesa, Tapia Angel, Vaca Alex A.
 *  @starts: 16/05/2021
 *  @last update: 16/05/2021
 *  @Description: [Es] Base de datos
 *
 * */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.Date;

public class BaseDatos extends SQLiteOpenHelper {

    private static final int versionBdd_usuarios = 1;// version de base de Datos
    private static final String bdd_usuarios = "app_parqueadero";// Nombre de Base de Datos
    private static final String tablaUsuarios = "create table usuario(" +
            "id_usu integer primary key autoincrement," +
            "apellido_usu text," +
            "nombre_usu text," +
            "email_usu text," +
            "password_usu text," +
            "telefono_usu text,"+
            "direccion_usu text,"+
            "fecha_registro_usu date)";

    //Constructor
    public BaseDatos(Context contexto){
        super(contexto,bdd_usuarios,null,versionBdd_usuarios);
    }

    // Proceso 1
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(tablaUsuarios); // Ejecutar una solicitud SQL, en este caso la creacion de la tabla

    }
    // Proceso 2
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS usuario"); // Elimina la antigua version de la base de datos
        db.execSQL(tablaUsuarios);

    }

    // Metodo insercion de datos, retorna TRUE si agrego y FALSE si fallo
    public boolean agregarUsuarios(String apellido, String nombre, String email, String password, String telefono, String direccion, Date fecha){
        SQLiteDatabase miBdd = getWritableDatabase(); // Llama la base de datos en el objeto miBdd

        if(miBdd!= null){ // Validar que la base de datos exista
            // Preparar SQL
            String sql = "INSERT INTO usuario (apellido_usu, nombre_usu, email_usu, password_usu, telefono_usu, direccion_usu, fecha_registro_usu) " +
                    "VALUES ('"+ apellido +"','"+ nombre +"','"+ email +"','"+ password +"', '"+ telefono +"','"+ direccion +"','"+ fecha +"')";
            // Ejecutar el SQL
            miBdd.execSQL(sql);
            // cerrar la conexion
            miBdd.close();
            // Retornar que se ha ingresado correctamente
            return true;
        }
        return false; // retorno cuando no exista la base de datos
    }
}



