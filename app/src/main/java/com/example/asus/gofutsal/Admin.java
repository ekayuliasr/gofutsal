package com.example.asus.gofutsal;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Admin extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText EditID, editNama,editTanggal,editNo_HP,editPemesanan;
    Button btnAddData;
    Button btnViewAll;
    Button btnUpdate;
    Button btnDelete;
    Button btnGet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);
        myDb = new DatabaseHelper(this);
        EditID = (EditText) findViewById(R.id.EditID);
        editNama = (EditText)findViewById(R.id.inputNama2);
        editTanggal = (EditText)findViewById(R.id.inputTanggal2);
        editNo_HP = (EditText)findViewById(R.id.inputHp2);
        editPemesanan = (EditText)findViewById(R.id.inputPemesanan2);
        btnAddData = (Button)findViewById(R.id.btnAdd);
        btnViewAll = (Button)findViewById(R.id.btnLihat);
        btnUpdate = (Button)findViewById(R.id.btnEdit);
        btnDelete = (Button)findViewById(R.id.btnHapus);
        btnGet = (Button) findViewById(R.id.btnGet);

        AddData();
        viewAll();
        UpdateData();
        deleteData();
        getPesan();

    }

    //fungsi hapus
    public void deleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDb.deleteData(editNama.getText().toString());
                        if (deletedRows > 0)
                            Toast.makeText(Admin.this,"Data Telah Terhapus!",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(Admin.this,"Data Belum Terhapus!",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void getPesan(){
        btnGet.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){

                DatabaseHelper myDb = new DatabaseHelper(getApplicationContext());
                String nama = editNama.getText().toString();
                if(nama.isEmpty()){
                    Toast.makeText(getApplicationContext(), "Nama harus diisi", Toast.LENGTH_SHORT).show();
                }
                else{
                    myDb.onOpen();
                    Pesan pesan = myDb.getPesan(nama);
                    if (pesan!=null){
                        editNama.setText(pesan.getNama());
                        editNo_HP.setText(pesan.getHp());
                        editPemesanan.setText(pesan.getPemesanan());
                        editTanggal.setText(pesan.getTanggal());
                    }
                    else{
                        Toast.makeText(getApplicationContext(), "Data tidak ditemukan", Toast.LENGTH_SHORT).show();
                    }
                    myDb.close();
                }
            }
        });
    }


    //fungsi update
    public void UpdateData() {
        btnUpdate.setOnClickListener(new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                boolean isUpdate = myDb.update(editNama.getText().toString(),
                                        editTanggal.getText().toString(), editNo_HP.getText().toString(), editPemesanan.getText().toString());
                                if (isUpdate == true)
                                    Toast.makeText(Admin.this, "Data Telah Di Edit", Toast.LENGTH_LONG).show();
                                else
                                    Toast.makeText(Admin.this, "Data Gagal Ter-edit", Toast.LENGTH_LONG).show();
                            }
                        }
                );

            }

            //fungsi tambah
            public void AddData() {
                btnAddData.setOnClickListener(
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {

                                String nama = editNama.getText().toString();
                                String no_hp = editNo_HP.getText().toString();
                                String pemesanan = editPemesanan.getText().toString();
                                String tanggal = editTanggal.getText().toString();

                                if (nama.equals("") || no_hp.equals("") || pemesanan.equals("") || tanggal.equals("")) {
                                    Toast.makeText(getApplicationContext(), "Fields are empty", Toast.LENGTH_SHORT).show();
                                } else {
                                    Boolean chkpesan = myDb.chkPesan(pemesanan);
                                    if (chkpesan == true) {
                                        Boolean chktgl = myDb.chkTanggal(tanggal);
                                        if (chktgl == true) {
                                            Boolean ins = myDb.insertPemesanan(nama, no_hp, pemesanan, tanggal);
                                            if (ins = true) {
                                                Toast.makeText(getApplicationContext(), "Pemesanan berhasil", Toast.LENGTH_SHORT).show();
                                            }
                                        } else {
                                            Toast.makeText(getApplicationContext(), "Cari tanggal lain", Toast.LENGTH_SHORT).show();
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "Cari lapangan lain!!", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }
                        }
                );
            }

            //fungsi menampilkan data
            public void viewAll() {
                btnViewAll.setOnClickListener(
                        new View.OnClickListener() {

                            @Override
                            public void onClick(View v) {
                                Cursor res = myDb.alldata();
                                if (res.getCount() == 0) {
                                    // show message
                                    showMessage("Error", "Noting Found");
                                    return;
                                }

                                StringBuffer buffer = new StringBuffer();
                                while (res.moveToNext()) {
                                    buffer.append("Pemesan :" + res.getString(0) + " - ");
                                    buffer.append(res.getString(1) + " - ");
                                    buffer.append(res.getString(2) + " - ");
                                    buffer.append(res.getString(3) + " - ");
                                    buffer.append(res.getString(4) + " \n\n");
                                }

                                // show all data
                                showMessage("Data", buffer.toString());
                            }
                        }
                );
            }

            //membuat alert dialog
            public void showMessage(String title, String Message) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setCancelable(true);
                builder.setTitle(title);
                builder.setMessage(Message);
                builder.show();

            }
        }
