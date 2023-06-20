package com.example.mysqltarea;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class lista_clientes extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_clientes);

        displayClients("http://10.0.0.10/androiddb/buscar1.php");
    }

    private void displayClients(String url) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONArray jsonArray = new JSONArray(response);

                    ArrayList<Client> clients = new ArrayList<>();

                    // Iterate through the JSON array
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String name = jsonObject.getString("Nombre");
                        int age = jsonObject.getInt("Edad");

                        // Create a Client object and add it to the clients list
                        Client client = new Client(name, age);
                        clients.add(client);
                    }

                    ListView listView = findViewById(R.id.listView);
                    ClientAdapter adapter = new ClientAdapter(getApplicationContext(), clients);
                    listView.setAdapter(adapter);
                    adapter.notifyDataSetChanged();

                    // Set the item click listener for the ListView
                    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            // Retrieve the selected client
                            Client selectedClient = clients.get(position);

                            // Access the client's details
                            String nombre = selectedClient.getName();
                            int edad = selectedClient.getAge();


                            String message = "Selected client: " + nombre + ", Age: " + edad;
                            Toast.makeText(lista_clientes.this, message, Toast.LENGTH_SHORT).show();

                            // Alternatively, you can start a new activity or perform any other operations
                            // Create an Intent to start the other activity
                            Intent intent = new Intent(lista_clientes.this, editView.class);
                            intent.putExtra("nombre", nombre);
                            intent.putExtra("edad", edad);
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), error.toString(), Toast.LENGTH_LONG).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    // Method to handle editing a person


}