package com.example.recyclerviewex;

import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

/**
 * Created by admin on 6/22/2018.
 */

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyClass>
{
    Context context;
    ArrayList<GetterSetter> al;

    public MyAdapter(Context context, ArrayList<GetterSetter> al) {
        this.context = context;
        this.al = al;
    }

    @NonNull
    @Override
    public MyClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.rowlayout,parent,false);

        return new MyClass(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyClass holder, int position) {
            GetterSetter gl=al.get(position);

            holder.im.setImageResource(gl.getImg());
            holder.tv.setText(gl.getName());
    }

    @Override
    public int getItemCount() {
        return al.size();
    }


    public  class MyClass extends RecyclerView.ViewHolder
    {
        ImageView im;
        TextView tv;

        public MyClass(View itemView)
        {
            super(itemView);
            im=itemView.findViewById(R.id.imview);
            tv=itemView.findViewById(R.id.tvview);
        }
    }
}
