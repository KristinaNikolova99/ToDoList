package com.example.to_dolist;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class ToDoAdapter extends RecyclerView.Adapter<ToDoAdapter.MyViewHolder> {

    Context context;
    Date date;
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


        final String getTitleDoes = myToDoList.get(i).getTitledoes();
        final String getDescDoes = myToDoList.get(i).getDescdoes();
        final String getDateDoes = myToDoList.get(i).getDatedoes();
        final String getKeyDoes = myToDoList.get(i).getKeydoes();

        Date today = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        String format = formatter.format(today);
        int b = format.compareTo(getDateDoes);

        if(b <= 0){
            myViewHolder.datedoes.setText(myToDoList.get(i).getDatedoes());}
        else {
            myViewHolder.datedoes2.setText(myToDoList.get(i).getDatedoes());
        }

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

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView titledoes, descdoes, datedoes, keydoes,datedoes2;

         public MyViewHolder(@NonNull View itemView) {
             super(itemView);
             titledoes = (TextView) itemView.findViewById(R.id.titledoes);
             descdoes = (TextView) itemView.findViewById(R.id.descdoes);
             datedoes = (TextView) itemView.findViewById(R.id.datedoes);
             datedoes2 = (TextView) itemView.findViewById(R.id.datedoes2);



         }
     }

}
