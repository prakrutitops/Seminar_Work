package com.example.jsoncrudex;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

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
import java.util.List;
import java.util.Map;

public class MainActivity2 extends AppCompatActivity
{
    ListView listView;
    List<Model>list;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        listView=findViewById(R.id.list);
        list=new ArrayList<>();

        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://prakrutitech.buzz/SeminarAPI/view.php", new Response.Listener<String>() {
            @Override
            public void onResponse(String response)
            {

                try
                {
                    JSONArray jsonArray=new JSONArray(response);
                    for(int i=0;i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject=jsonArray.getJSONObject(i);

                        int id=jsonObject.getInt("id");
                        String pname=jsonObject.getString("pname");
                        String pprice= jsonObject.getString("pprice");
                        String pdes= jsonObject.getString("pdes");


                        Model m =new Model();
                        m.setId(id);
                        m.setPname(pname);
                        m.setPprice(pprice);
                        m.setPdes(pdes);

                        list.add(m);
                    }

                }
                catch (JSONException e)
                {
                    e.printStackTrace();
                }

                MyAdapter myAdapter=new MyAdapter(getApplicationContext(),list);
                listView.setAdapter(myAdapter);


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error)
            {
                Toast.makeText(MainActivity2.this, "No Internet", Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue queue= Volley.newRequestQueue(this);
        queue.add(stringRequest);

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        MenuItem m1=menu.add(0,1,0,"Update");
        MenuItem m2=menu.add(0,2,0,"Delete");

        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo acm= (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id=acm.position;



        switch (item.getItemId())
        {

            case 1:

                Model mode1 = list.get(id);
                final String Id2 = String.valueOf(mode1.getId());

                Intent i =new Intent(MainActivity2.this,UpdateActivity.class);
                i.putExtra("myid",Id2);
                i.putExtra("myname",mode1.pname);
                i.putExtra("mysurname",mode1.pprice);
                i.putExtra("myemail",mode1.pdes);


                startActivity(i);


                break;


            case 2:
                Model mode = list.get(id);
                final String Id = String.valueOf(mode.getId());


                AlertDialog.Builder alert=new AlertDialog.Builder(MainActivity2.this);
                alert.setTitle("Delete?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        Toast.makeText(MainActivity2.this, "Deleted", Toast.LENGTH_SHORT).show();


                        StringRequest stringRequest=new StringRequest(Request.Method.POST, "https://prakrutitech.buzz/SeminarAPI/delete.php", new Response.Listener<String>() {
                            @Override
                            public void onResponse(String response) {


                                Intent i =new Intent(MainActivity2.this,MainActivity2.class);
                                startActivity(i);
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error)
                            {

                                Toast.makeText(MainActivity2.this, "No Internet", Toast.LENGTH_SHORT).show();
                            }
                        })
                        {
                            @Override
                            protected Map<String, String> getParams() throws AuthFailureError
                            {

                                HashMap map=new HashMap();
                                map.put("id",Id);
                                return map;
                            }
                        };


                        RequestQueue queue= Volley.newRequestQueue(MainActivity2.this);
                        queue.add(stringRequest);


                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dialogInterface.cancel();
                    }
                });
                alert.show();




                break;
        }

        return super.onContextItemSelected(item);
    }
}