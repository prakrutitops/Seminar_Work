package com.example.jsoncrudex;

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

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    EditText edt1,edt2,edt3,edt4;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        edt3=findViewById(R.id.edt3);
        edt4=findViewById(R.id.edt4);
        btn1=findViewById(R.id.btn1);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name=edt1.getText().toString();
                String surname=edt2.getText().toString();
                String email=edt3.getText().toString();
                String password=edt4.getText().toString();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://prakrutitech.buzz/SeminarAPI/insert.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        Toast.makeText(MainActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        Intent i =new Intent(MainActivity.this,MainActivity2.class);
                        startActivity(i);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(MainActivity.this, "No Internet", Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        HashMap map=new HashMap();
                        map.put("Name",name);
                        map.put("Surname",surname);
                        map.put("Email",email);
                        map.put("Password",password);
                        return map;
                    }
                };

                RequestQueue queue= Volley.newRequestQueue(MainActivity.this);
                queue.add(stringRequest);


            }
        });


    }
}