package com.example.exercise1;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class Pendaftaran extends AppCompatActivity {
    String agama,jk;
    int statusAgama,statusJK;
    Button daftar,kembali;
    EditText nama,alamat,email,pass,repass;
    TextInputLayout namaErr,alamatErr,emailErr,passErr,repassErr;
    RadioButton islam,kristen,hindu,buddha,katolik,konghucu,kepercayaan,laki,perempuan;
    RadioGroup agama1,agama2,agama3,jeniskelamin;
    boolean isRadioChecked,isEmailValid,isPassValid,isRePassValid,isNamaValid,isAlamatValid;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar);

        daftar = findViewById(R.id.btnDaftar);
        kembali = findViewById(R.id.btnKembali);
        nama = findViewById(R.id.daftarNama);
        alamat = findViewById(R.id.daftarAlamat);
        email = findViewById(R.id.addEmail);
        pass = findViewById(R.id.addPass);
        repass = findViewById(R.id.addRePass);
        namaErr = findViewById(R.id.daftarNamaErr);
        alamatErr = findViewById(R.id.daftarAlamatErr);
        emailErr = findViewById(R.id.addEmailErr);
        passErr = findViewById(R.id.addPassErr);
        repassErr = findViewById(R.id.addRePassErr);
        islam = findViewById(R.id.radioIslam);
        kristen = findViewById(R.id.radioKristen);
        hindu = findViewById(R.id.radioHindu);
        katolik = findViewById(R.id.radioKatolik);
        buddha = findViewById(R.id.radioBuddha);
        konghucu = findViewById(R.id.radioKonghucu);
        kepercayaan = findViewById(R.id.radioAgamaPK);
        laki = findViewById(R.id.radioLaki);
        perempuan = findViewById(R.id.radioPerempuan);
        jeniskelamin = findViewById(R.id.radioJK);
        agama1 = findViewById(R.id.radioAgama);
        agama2 = findViewById(R.id.radioAgama2);
        agama3 = findViewById(R.id.radioAgama3);
        agama = "";
        jk = "";

        daftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setAgama(v);
                setJeniskelamin(v);
                validasiDaftar(v);
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),MainActivity.class));
            }
        });

        agama1.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int i) {
                if (i != -1 && isRadioChecked){
                    isRadioChecked = false;
                    agama2.clearCheck();
                    agama3.clearCheck();
                    statusAgama = i;
                }
                isRadioChecked = true;
            }
        });

        agama2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int j) {
                if (j != -1 && isRadioChecked){
                    isRadioChecked = false;
                    agama1.clearCheck();
                    agama3.clearCheck();
                    statusAgama = j;
                }
                isRadioChecked = true;
            }
        });

        agama3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int k) {
                if (k != -1 && isRadioChecked){
                    isRadioChecked = false;
                    agama1.clearCheck();
                    agama2.clearCheck();
                    statusAgama = k;
                }
                isRadioChecked = true;
            }
        });

        jeniskelamin.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int x) {
                if (x != -1 && isRadioChecked){
                    isRadioChecked = false;
                    statusJK = x;
                }
                isRadioChecked = true;
            }
        });

    }

    public void setAgama(View v){
        int i = statusAgama;

        if(i == R.id.radioIslam){
            agama = "Islam";
        }else if(i == R.id.radioKristen){
            agama = "Kristen";
        }else if(i == R.id.radioKatolik){
            agama = "Katolik";
        }else if(i == R.id.radioHindu){
            agama = "Hindu";
        }else if(i == R.id.radioBuddha){
            agama = "Buddha";
        }else if(i == R.id.radioKonghucu){
            agama = "Konghucu";
        }else if(i == R.id.radioAgamaPK){
            agama = "Aliran Kepercayaan";
        }
    }

    public void setJeniskelamin(View v){
        int i = statusJK;

        if(i == R.id.radioLaki){
            jk = "Laki-Laki";
        }else if(i == R.id.radioPerempuan){
            jk = "Perempuan";
        }
    }

    public void validasiDaftar(View v){
        if(nama.getText().toString().isEmpty() || alamat.getText().toString().isEmpty() ||
                email.getText().toString().isEmpty() || pass.getText().toString().isEmpty() ||
                repass.getText().toString().isEmpty() || agama.isEmpty() || jk.isEmpty()){
            Toast.makeText(getApplicationContext(),"Semua Wajib Diisi!",Toast.LENGTH_LONG).show();
        }

        if(nama.getText().toString().isEmpty()){
            namaErr.setError("Nama Tidak Boleh Kosong");
            isNamaValid = false;
        }else{
            namaErr.setErrorEnabled(false);
            isNamaValid = true;
        }

        if (alamat.getText().toString().isEmpty()){
            alamatErr.setError("Alamat Tidak Boleh Kosong");
            isAlamatValid = false;
        }else{
            alamatErr.setErrorEnabled(false);
            isAlamatValid = true;
        }

        if (email.getText().toString().isEmpty()){
            emailErr.setError("Email Tidak Boleh Kosong");
            isEmailValid = false;
        }else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            emailErr.setError("Format Email Kurang Tepat");
            isEmailValid = false;
        }else{
            emailErr.setErrorEnabled(false);
            isEmailValid = true;
        }

        if (pass.getText().toString().isEmpty()){
            passErr.setError("Password Tidak Boleh Kosong");
            isPassValid = false;
        }else if (pass.getText().length() < 6){
            passErr.setError("Password Minimal 6 Karakter");
            isPassValid = false;
        }else{
            passErr.setErrorEnabled(false);
            isPassValid = true;
        }

        if (repass.getText().toString().isEmpty()){
            repassErr.setError("Password Tidak Boleh Kosong");
            isRePassValid = false;
        }else if (repass.getText().length() < 6){
            repassErr.setError("Password Minimal 6 Karakter");
            isRePassValid = false;
        }else if (!repass.getText().toString().equals(pass.getText().toString())){
            repassErr.setError("Password Tidak Sama");
            isRePassValid = false;
        }else{
            repassErr.setErrorEnabled(false);
            isRePassValid = true;
        }

        if (isNamaValid && isAlamatValid && isEmailValid && isPassValid && isRePassValid && !agama.isEmpty() && !jk.isEmpty()){
            Toast.makeText(getApplicationContext(),"Pendaftaran Berhasil!",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
        }
    }
}
