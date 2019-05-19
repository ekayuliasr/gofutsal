package com.example.asus.gofutsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    EditText e1, e2, e3;
    Button login, submit;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        db = new DatabaseHelper(this);
        e1 = (EditText) findViewById(R.id.inputUsername2);
        e2 = (EditText) findViewById(R.id.inputPassword2);
        e3 = (EditText) findViewById(R.id.inputCPassword);
        login = (Button) findViewById(R.id.btnLogin2);
        submit = (Button) findViewById(R.id.btnRegis2);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = e1.getText().toString();
                String pass = e2.getText().toString();
                String cPass = e3.getText().toString();
                if (user.equals("") || pass.equals("") || cPass.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    if (pass.equals(cPass)){
                        Boolean chkUsername = db.chkusername(user);
                        if (chkUsername == true){
                            Boolean insert = db.insert(user, pass);
                            if (insert = true){
                                Toast.makeText(getApplicationContext(), "Registration successfully", Toast.LENGTH_SHORT).show();
                            }
                        }
                        else {
                            Toast.makeText(getApplicationContext(), "Username already exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Password do not match", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent intent = new Intent(Register.this, Login.class);
                startActivity(intent);
            }
        });
    }
}
