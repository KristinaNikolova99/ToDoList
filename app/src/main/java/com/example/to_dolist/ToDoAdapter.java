package com.example.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    Context context;
    ArrayList<MyToDoList> myToDoList;

    public ToDoAdapter(Context c, ArrayList<MyToDoList> p) {
        context = c;
        myToDoList = p;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        return new MyViewHolder(LayoutInflater.from(context).inflate(R.layout.item_todo, viewGroup, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.titledoes.setText(myToDoList.get(i).getTitledoes());
        myViewHolder.descdoes.setText(myToDoList.get(i).getDescdoes());
        myViewHolder.datedoes.setText(myToDoList.get(i).getDatedoes());

        final String getTitleDoes = myToDoList.get(i).getTitledoes();
        final String getDescDoes = myToDoList.get(i).getDescdoes();
        final String getDateDoes = myToDoList.get(i).getDatedoes();
        final String getKeyDoes = myToDoList.get(i).getKeydoes();

        myViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent aa = new Intent(context, EditTaskDesk.class);
                aa.putExtra("titledoes", getTitleDoes);
                aa.putExtra("descdoes", getDescDoes);
                aa.putExtra("datedoes", getDateDoes);
                aa.putExtra("keydoes", getKeyDoes);
                context.startActivity(aa);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myToDoList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titledoes, descdoes, datedoes, keydoes;

         public MyViewHolder(@NonNull View itemView) {
             super(itemView);
             titledoes = (TextView) itemView.findViewById(R.id.titledoes);
             descdoes = (TextView) itemView.findViewById(R.id.descdoes);
             datedoes = (TextView) itemView.findViewById(R.id.datedoes);


         }
     }

}
