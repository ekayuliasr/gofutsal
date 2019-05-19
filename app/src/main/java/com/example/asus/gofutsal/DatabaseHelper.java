package com.example.asus.gofutsal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper{

    public DatabaseHelper(Context context){
        super(context, "Gofutsal.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("Create Table user(username text primary key,password text)");
        db.execSQL("create table pemesanan(id integer primary key, nama text, no_hp text, pemesanan text, tanggal text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists user");
        db.execSQL("drop table if exists pemesanan");
    }

    public boolean insert (String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);
        long ins=db.insert("user",null,contentValues);
        if (ins==-1) return false;
        else return true;
    }
    public Boolean chkusername(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from user where username=?", new String[]{username});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean usernamepassword(String username, String password){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM user WHERE username=? AND password=?",new String[] {username,password});
        if (cursor.getCount()>0) return true;
        else return false;
    }

    public boolean insertPemesanan (String nama, String no_hp, String pemesanan, String tanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama",nama);
        contentValues.put("no_hp",no_hp);
        contentValues.put("pemesanan", pemesanan);
        contentValues.put("tanggal", tanggal);
        long result = db.insert("pemesanan",null,contentValues);
        if (result == -1) return false;
        else return true;
    }

    public Boolean chkPesan(String pemesanan) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from pemesanan where pemesanan=?", new String[]{pemesanan});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public Boolean chkTanggal(String tanggal) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("Select * from pemesanan where tanggal=?", new String[]{tanggal});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public Cursor alldata(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from pemesanan", null);
        return cursor;
    }

    public Pesan getPesan(String nama){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM pemesanan WHERE nama LIKE \"%"+nama+"%\"";
        Cursor cursor = db.rawQuery(query, null);
        Pesan pesan = new Pesan();
        if (cursor.moveToFirst()){
            cursor.moveToFirst();
            pesan.set_id(Integer.parseInt(cursor.getString(0)));
            pesan.setNama(cursor.getString(1));
            pesan.setHp(cursor.getString(2));
            pesan.setPemesanan(cursor.getString(3));
            pesan.setTanggal(cursor.getString(4));
            cursor.close();
        }
        else {
            pesan = null;
        }
        return pesan;
    }


    public int deleteData (String nama) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("pemesanan", "nama = ?", new String[] {nama});
    }

    public void onOpen(){
        SQLiteDatabase db = this.getWritableDatabase();
        super.onOpen(db);
        db = this.getWritableDatabase();
    }

    public synchronized void close(){
        super.close();
    }

    public boolean update(String nama, String no_hp, String pemesanan, String tanggal){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("nama",nama);
        contentValues.put("no_hp",no_hp);
        contentValues.put("pemesanan", pemesanan);
        contentValues.put("tanggal", tanggal);
        db.update("pemesanan",contentValues,"nama= ?",new String[] {nama});

        return true;
    }
}
