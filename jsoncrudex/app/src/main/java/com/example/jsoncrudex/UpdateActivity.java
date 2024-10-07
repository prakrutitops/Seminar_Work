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

public class UpdateActivity extends AppCompatActivity {

    EditText edt1,edt2,edt3,edt4;
    Button btn1;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update);

        edt1=findViewById(R.id.edt1);
        edt2=findViewById(R.id.edt2);
        edt3=findViewById(R.id.edt3);
        edt4=findViewById(R.id.edt4);
        btn1=findViewById(R.id.btn1);


        Intent i =getIntent();
        String id=i.getStringExtra("myid");
        edt1.setText(i.getStringExtra("myname"));
        edt2.setText(i.getStringExtra("mysurname"));
        edt3.setText(i.getStringExtra("myemail"));
        edt4.setText(i.getStringExtra("mypass"));
        Toast.makeText(UpdateActivity.this, ""+id, Toast.LENGTH_SHORT).show();

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String name=edt1.getText().toString();
                String surname=edt2.getText().toString();
                String email=edt3.getText().toString();
                String password=edt4.getText().toString();

                StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://prakrutitech.buzz/SeminarAPI/update.php", new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response)
                    {

                        Toast.makeText(UpdateActivity.this, "Inserted", Toast.LENGTH_SHORT).show();
                        Intent i =new Intent(UpdateActivity.this,MainActivity2.class);
                        startActivity(i);

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(UpdateActivity.this, "No Internet", Toast.LENGTH_SHORT).show();

                    }
                })
                {
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError
                    {
                        HashMap map=new HashMap();
                        map.put("id",id);
                        map.put("Name",name);
                        map.put("Surname",surname);
                        map.put("Email",email);
                        map.put("Password",password);
                        return map;
                    }
                };

                RequestQueue queue= Volley.newRequestQueue(UpdateActivity.this);
                queue.add(stringRequest);


            }
        });


    }
}




