package com.example.to_dolist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.to_dolist.Interfaces.DateValidator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class NewTaskAct extends AppCompatActivity {

    TextView titlepage, addtitle, adddesc, adddate;
    EditText titledoes, descdoes, datedoes;
    Button btnSaveTask, btnCancel;
    DatabaseReference reference;
    Integer doesNum = new Random().nextInt();
    String keydoes = Integer.toString(doesNum);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_task);

        titlepage = findViewById(R.id.titlepage);

        addtitle = findViewById(R.id.addtitle);
        adddesc = findViewById(R.id.adddesc);
        adddate = findViewById(R.id.adddate);

        titledoes = findViewById(R.id.titledoes);
        descdoes = findViewById(R.id.descdoes);
        datedoes = findViewById(R.id.datedoes);

        btnSaveTask = findViewById(R.id.btnSaveTask);
        btnCancel = findViewById(R.id.btnCancel);
        reference = FirebaseDatabase.getInstance().getReference().child("To-Do List").child("Does" + doesNum);
        btnSaveTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String s1 = titledoes.getText().toString();
                String s2 = descdoes.getText().toString();
                String s3 = datedoes.getText().toString();
                //insert data to database
                DateValidator validator = new DateFormatValidator("yyyy-MM-dd");

                if (s1.equals("") || s2.equals("") || s3.equals("")){

                    Toast.makeText(getApplicationContext(), "Fields are required", Toast.LENGTH_SHORT).show();

                }
                if (!validator.isValid(s3)){
                    Toast.makeText(getApplicationContext(), "Wrong date format!", Toast.LENGTH_SHORT).show();
                }

                else {

                    reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                            dataSnapshot.getRef().child("titledoes").setValue(titledoes.getText().toString());
                            dataSnapshot.getRef().child("descdoes").setValue(descdoes.getText().toString());
                            dataSnapshot.getRef().child("datedoes").setValue(datedoes.getText().toString());
                            dataSnapshot.getRef().child("keydoes").setValue(keydoes);

                        Intent a = new Intent(NewTaskAct.this,MainActivity.class);
                        startActivity(a);
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
            }}
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(NewTaskAct.this, MainActivity.class);
                startActivity(i);
            }
        });

        // import font
        Typeface MLight = Typeface.createFromAsset(getAssets(), "fonts/ML.ttf");
        Typeface MMedium = Typeface.createFromAsset(getAssets(), "fonts/MM.ttf");

        // customize font
        titlepage.setTypeface(MMedium);

        addtitle.setTypeface(MLight);
        titledoes.setTypeface(MMedium);

        adddesc.setTypeface(MLight);
        descdoes.setTypeface(MMedium);

        adddate.setTypeface(MLight);
        datedoes.setTypeface(MMedium);

        btnSaveTask.setTypeface(MMedium);
        btnCancel.setTypeface(MLight);


    }
}
