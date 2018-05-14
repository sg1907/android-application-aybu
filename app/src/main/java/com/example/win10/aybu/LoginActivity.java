package com.example.win10.aybu;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.MultiAutoCompleteTextView;
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


public class LoginActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference users;

    private EditText username, password;
    private Button btnSignIn, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Firebase
        database = FirebaseDatabase.getInstance();
        users = database.getReference("Users");

        username = (EditText) findViewById(R.id.edtTxt_username);
        password = (EditText) findViewById(R.id.edtTxt_password);
        btnSignIn = (Button) findViewById(R.id.button_signIn);
        btnSignUp = (Button) findViewById(R.id.button_signUp);


        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserControl(username.getText().toString(),password.getText().toString());
            }
        });

        final Intent in=  new Intent(getApplicationContext(),SignUpActivity.class);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(in);
            }
        });
    }

    private void UserControl(final String username, final String password) {

        final Intent i=  new Intent(getApplicationContext(),MainActivity.class);

        users.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(dataSnapshot.child(username).exists()){

                    if(!username.isEmpty()){

                        User login = dataSnapshot.child(username).getValue(User.class);

                        if(login.getPassword().equals(password)){
                            Toast.makeText(getApplicationContext(),"Success Login!",Toast.LENGTH_SHORT).show();
                            startActivity(i);
                        }
                        else{
                            Toast.makeText(getApplicationContext(),"Password is Wrong!",Toast.LENGTH_SHORT).show();
                        }
                    }

                    else{
                        Toast.makeText(getApplicationContext(),"Username is Not Registered!",Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

}

