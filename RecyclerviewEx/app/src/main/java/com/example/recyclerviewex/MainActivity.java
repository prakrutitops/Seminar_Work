package com.example.recyclerviewex;

import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;

    int img[]={R.mipmap.ic_launcher,R.mipmap.ic_launcher};
    String name[]={"Oreo","Smile"};

    ArrayList<GetterSetter> al=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv=findViewById(R.id.rev);

        RecyclerView.LayoutManager layoutManager=new LinearLayoutManager(MainActivity.this);
        rv.setLayoutManager(layoutManager);


        for(int i=0;i<img.length;i++)
        {
            GetterSetter gl=new GetterSetter(img[i],name[i]);
            al.add(gl);

            MyAdapter my=new MyAdapter(MainActivity.this,al);
            rv.setAdapter(my);
        }
    }
}
