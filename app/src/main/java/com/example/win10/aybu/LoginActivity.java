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

import java.util.ArrayList;
import java.util.List;


public class LoginActivity extends AppCompatActivity {

    private Button btn_signIn;
    private EditText editText_username;
    private EditText editText_password;

    private Spinner spinner_department;

    List<String> categories = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        btn_signIn = (Button) findViewById(R.id.button_signIn);
        editText_username = (EditText) findViewById(R.id.editText_userName);
        editText_password = (EditText) findViewById(R.id.editText_password);

        spinner_department = (Spinner) findViewById(R.id.spinner_department);

        categories.add("Department");
        categories.add("Comp. Eng");
        categories.add("Elec. Eng");
        categories.add("Mach. Eng");
        categories.add("Civil Eng.");
        categories.add("Mater. Eng");


        // Creating adapter for spinne
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, categories);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_department.setAdapter(dataAdapter);


        final Intent in = new Intent(this,MainActivity.class);

        btn_signIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(in);

            }
        });

    }

}

