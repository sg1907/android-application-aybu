package com.example.win10.aybu;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.win10.aybu.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class SignUpActivity extends AppCompatActivity {


    FirebaseDatabase database;
    DatabaseReference users;

    private EditText edtUserName, edtPassword, edtMail;
    private Button btnRegister;

    private Spinner spinner_department;

    List<String> categories = new ArrayList<String>();

    //List<String> departments = new ArrayList<String>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        edtUserName = (EditText) findViewById(R.id.editText_userName);
        edtPassword = (EditText) findViewById(R.id.editText_password);
        edtMail = (EditText) findViewById(R.id.editText_email);

        btnRegister = (Button) findViewById(R.id.button_register);

        spinner_department = (Spinner) findViewById(R.id.spinner_department);

        String[] departments;

        departments = getResources().getStringArray(R.array.departments);

        for(int i=0; i<departments.length; i++)
            categories.add(departments[i]);

/*
        categories.add("Department");
        categories.add("Comp. Eng");
        categories.add("Elec. Eng");
        categories.add("Mach. Eng");
        categories.add("Civil Eng.");
        categories.add("Mater. Eng");
*/

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_department.setAdapter(dataAdapter);

        final Intent i=  new Intent(getApplicationContext(),LoginActivity.class);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                final User user = new User(edtUserName.getText().toString(),
                        edtPassword.getText().toString(),
                        edtMail.getText().toString(),spinner_department.getSelectedItem().toString());

                users.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if(dataSnapshot.child(user.getUsername()).exists())
                            Toast.makeText(SignUpActivity.this,"The Username is Already Exists!",Toast.LENGTH_SHORT).show();
                        else{
                            users.child(user.getUsername()).setValue(user);
                            Toast.makeText(SignUpActivity.this,"Success Register!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        //add your custom code
                    }
                });

                startActivity(i);

            }
        });

    }


}
