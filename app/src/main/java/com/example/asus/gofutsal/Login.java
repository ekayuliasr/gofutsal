package com.example.asus.gofutsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {

    EditText e1, e2;
    Button login, register;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        e1 = (EditText) findViewById(R.id.inputUsername);
        e2 = (EditText) findViewById(R.id.inputPassword);
        login = (Button) findViewById(R.id.btnLogin);
        register = (Button) findViewById(R.id.btnRegis);

        db = new DatabaseHelper(this);

        login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                String username = e1.getText().toString();
                String password = e2.getText().toString();
                Boolean chkusernamepass = db.usernamepassword(username, password);
                if (chkusernamepass == true){
                    Intent intent = new Intent(Login.this, Admin.class);
                    startActivity(intent);
                }

                else{
                    Toast.makeText(getApplicationContext(), "Wrong username or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this, Register.class);
                startActivity(intent);
            }
        });

    }
}
