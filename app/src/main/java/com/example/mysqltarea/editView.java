package com.example.mysqltarea;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.protobuf.StringValue;

import java.util.HashMap;
import java.util.Map;

public class editView extends AppCompatActivity {

    EditText edtName, edtEdad;
    Button btnEditar, btnBorrar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_view);

        edtName = findViewById(R.id.nombreEdt);
        edtEdad = findViewById(R.id.edtEdad);
        btnEditar = findViewById(R.id.btnEditar);
        btnBorrar = findViewById(R.id.btnBorrar);


        // Retrieve the name and age values from the Intent
        Intent intent = getIntent();
       String nombre  = intent.getStringExtra("nombre");
       int edad = intent.getIntExtra("edad", 0);

       edtName.setText(nombre);
       edtEdad.setText(String.valueOf(edad));

       btnEditar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String name = edtName.getText().toString();
               String age = edtEdad.getText().toString();
               editar("http://10.0.0.10/androiddb/editar.php",name, age);
               Intent intent2 = new Intent(getApplicationContext(), lista_clientes.class);
               startActivity(intent2);
           }
       });

       btnBorrar.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               String name = edtName.getText().toString();
               String age = edtEdad.getText().toString();
               Borrar("http://10.0.0.10/androiddb/borrar.php",name ,age );
               Intent intent2 = new Intent(getApplicationContext(), lista_clientes.class);
               startActivity(intent2);
           }
       });

    }

    private void editar(String url, String nombre, String edad){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Listo", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {

                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("Nombre", nombre);
                parametros.put("Edad", edad);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);;
    }


    private void Borrar(String url, String nombre, String edad){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), "Listo", Toast.LENGTH_LONG).show();

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Nullable
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {



                Map<String, String> parametros = new HashMap<String, String>();
                parametros.put("Nombre", nombre);
                parametros.put("Edad", edad);
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);;
    }
}