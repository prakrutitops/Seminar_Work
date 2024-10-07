package com.example.dbex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ShowActivity extends AppCompatActivity
{
    ListView listView;
    List<Model>list;
    DbHelper dbHelper;
    ArrayList<HashMap<String,String>>arrayList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);

        listView=findViewById(R.id.list);
        list=new ArrayList<>();
        dbHelper=new DbHelper(getApplicationContext());

        list=dbHelper.viewdata();

        for(Model m:list)
        {
            HashMap map = new HashMap();
            map.put("id",m.getId());
            map.put("name",m.getName());
            map.put("number",m.getPhone());
            arrayList.add(map);

        }
        String from[]={"name","number"};
        int to[]={R.id.txt1,R.id.txt2};

        SimpleAdapter simpleAdapter =new SimpleAdapter(ShowActivity.this,arrayList,R.layout.design,from,to);
        listView.setAdapter(simpleAdapter);

        registerForContextMenu(listView);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo)
    {
        MenuItem m1 =menu.add(0,1,0,"Update");
        MenuItem m2 =menu.add(0,2,0,"Delete");
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item)
    {
        AdapterView.AdapterContextMenuInfo acm = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int id=acm.position;
        Model m =list.get(id);

        switch (item.getItemId())
        {
            case 1:

                Intent i =new Intent(ShowActivity.this,UpdateActivity.class);
                i.putExtra("id",m.getId());
                i.putExtra("name",m.getName());
                i.putExtra("number",m.getPhone());
                startActivity(i);

                break;

            case 2:
                AlertDialog.Builder alert =new AlertDialog.Builder(ShowActivity.this);
                alert.setTitle("DELETE?");
                alert.setPositiveButton("YES", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                        dbHelper.delete(m.getId());
                        startActivity(new Intent(ShowActivity.this,ShowActivity.class));

                    }
                });
                alert.setNegativeButton("NO", new DialogInterface.OnClickListener() {
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

    @Override
    public void onBackPressed() {
        finishAffinity();
    }
}