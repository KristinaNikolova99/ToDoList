package com.example.to_dolist;

        import androidx.annotation.NonNull;
        import androidx.appcompat.app.AppCompatActivity;

        import android.app.AlertDialog;
        import android.app.Dialog;
        import android.content.Context;
        import android.content.DialogInterface;
        import android.content.Intent;
        import android.os.Bundle;
        import android.text.Html;
        import android.view.Gravity;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.widget.Button;
        import android.widget.EditText;
        import android.widget.Toast;

        import com.example.to_dolist.Interfaces.DateValidator;
        import com.google.android.gms.tasks.OnCompleteListener;
        import com.google.android.gms.tasks.Task;
        import com.google.firebase.database.DataSnapshot;
        import com.google.firebase.database.DatabaseError;
        import com.google.firebase.database.DatabaseReference;
        import com.google.firebase.database.FirebaseDatabase;
        import com.google.firebase.database.ValueEventListener;

public class EditTaskDesk extends AppCompatActivity {

    EditText titleDoes, descDoes, dateDoes;
    Button btnSaveUpdate, btnDelete;
    DatabaseReference reference, reference2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_task_desk);

        titleDoes = findViewById(R.id.titledoes);
        descDoes = findViewById(R.id.descdoes);
        dateDoes = findViewById(R.id.datedoes);

        btnSaveUpdate = findViewById(R.id.btnSaveUpdate);
        btnDelete = findViewById(R.id.btnDelete);

        //get a value from previous page
        titleDoes.setText(getIntent().getStringExtra("titledoes"));
        descDoes.setText(getIntent().getStringExtra("descdoes"));
        dateDoes.setText(getIntent().getStringExtra("datedoes"));

        final String keykeyDoes = getIntent().getStringExtra("keydoes");

        reference = FirebaseDatabase.getInstance().getReference().child("To-Do List").child("Does" + keykeyDoes);
        reference.keepSynced(true);

        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirmDialogDemo();
            }
        });

        //insert data to database
        //make an event for button

            btnSaveUpdate.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s3 = dateDoes.getText().toString();
                    String s2 = descDoes.getText().toString();
                    String s1 = titleDoes.getText().toString();
                    DateValidator validator = new DateFormatValidator("yyyy-MM-dd");
                    if (s1.equals("") || s2.equals("") || s3.equals("")) {
                        Toast.makeText(getApplicationContext(), "Fields are required", Toast.LENGTH_SHORT).show();
                    }
                    if (!validator.isValid(s3)) {
                        Toast.makeText(getApplicationContext(), "Wrong date format!", Toast.LENGTH_SHORT).show();
                    } else {

                        reference.addValueEventListener(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                dataSnapshot.getRef().child("titledoes").setValue(titleDoes.getText().toString());
                                dataSnapshot.getRef().child("descdoes").setValue(descDoes.getText().toString());
                                dataSnapshot.getRef().child("datedoes").setValue(dateDoes.getText().toString());
                                dataSnapshot.getRef().child("keydoes").setValue(keykeyDoes);
                                Intent a = new Intent(EditTaskDesk.this, MainActivity.class);
                                startActivity(a);
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
                    }
                }
                });

    }

        private void confirmDialogDemo () {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("ALERT!");
            builder.setMessage("Are you sure u wanna delete this?");
            builder.setCancelable(false);
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    reference.removeValue().addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Intent a = new Intent(EditTaskDesk.this, MainActivity.class);
                                startActivity(a);
                                Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getApplicationContext(), "Failure", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    Toast.makeText(getApplicationContext(), "You've changed your mind to delete", Toast.LENGTH_SHORT).show();
                }
            });

            builder.show();
        }

}



