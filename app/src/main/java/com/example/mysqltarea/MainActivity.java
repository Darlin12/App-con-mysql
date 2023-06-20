package com.example.mysqltarea;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    EditText edtName, edtEdad;
    TextView title;
    Button btnVerClientes, btnGuardar, btnMayores, btnMenores;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        edtName = findViewById(R.id.editTextNombre);
        edtEdad = findViewById(R.id.editTextEdad);
        btnVerClientes = findViewById(R.id.ClientesBtn);
        btnGuardar = findViewById(R.id.btnGuardar);
        title = findViewById(R.id.textView3);
        btnMayores = findViewById(R.id.btnMayores);
        btnMenores = findViewById(R.id.btnMenores);



        validar("http://10.0.0.10/androiddb/buscar1.php");

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre = edtName.getText().toString();
                String edad = edtEdad.getText().toString();
                if (nombre.isEmpty() || edad.isEmpty() ){
                    Toast.makeText(getApplicationContext(), "Tienes que llenar ambos campos", Toast.LENGTH_SHORT).show();
                }else{
                    guardar("http://10.0.0.10/androiddb/insertar.php", nombre, edad);
                    edtName.setText("");
                    edtEdad.setText("");               }


            }
        });
        btnMayores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mayoresOmenores("http://10.0.0.10/androiddb/mayores.php");
            }
        });
        btnMenores.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mayoresOmenores("http://10.0.0.10/androiddb/menores.php");
            }
        });
        btnVerClientes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), lista_clientes.class);
                startActivity(intent);

            }
        });
    }

    private void validar(String url){

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                    Toast.makeText(getApplicationContext(), "listo", Toast.LENGTH_SHORT).show();
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
                //parametros.put("Nombre", "Pedro");
                //parametros.put("Edad", "15");
                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    private void guardar(String url, String nombre, String edad){
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

    private void mayoresOmenores(String url){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(getApplicationContext(), response.toString(), Toast.LENGTH_SHORT).show();
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

                return parametros;
            }
        };

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }






}