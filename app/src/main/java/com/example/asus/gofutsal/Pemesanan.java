package com.example.asus.gofutsal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Pemesanan extends AppCompatActivity {

    EditText nm, hp, pesan, tgl;
    Button submit, data;
    DatabaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pemesanan);
        db = new DatabaseHelper(this);

        nm = (EditText) findViewById(R.id.inputNama);
        hp = (EditText) findViewById(R.id.inputHP);
        pesan = (EditText) findViewById(R.id.inputPemesanan);
        tgl = (EditText) findViewById(R.id.inputTgl);
        submit = (Button) findViewById(R.id.btnSumbit2);
        data = (Button) findViewById(R.id.lihatPemesanan);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String nama = nm.getText().toString();
                String no_hp = hp.getText().toString();
                String pemesanan = pesan.getText().toString();
                String tanggal = tgl.getText().toString();

                if (nama.equals("") || no_hp.equals("") || pemesanan.equals("") || tanggal.equals("")){
                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                }
                else{
                    Boolean chkpesan = db.chkPesan(pemesanan);
                    if (chkpesan == true ){
                        Boolean chktgl = db.chkTanggal(tanggal);
                        if (chktgl == true){
                            Boolean ins = db.insertPemesanan(nama, no_hp, pemesanan, tanggal);
                            if (ins  = true){
                               Toast.makeText(getApplicationContext(), "Pemesanan berhasil", Toast.LENGTH_SHORT).show();
                          }
                        }
                        else {
                          Toast.makeText(getApplicationContext(),"Cari tanggal lain", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(getApplicationContext(), "Cari lapangan lain!!", Toast.LENGTH_SHORT).show();
                    }

                }
            }
        });


    }

}
