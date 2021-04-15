package com.example.exercise1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    TextView txDaftar;
    Button btnLogin;
    EditText email,pass;
    TextInputLayout emailErr,passErr;
    boolean isEmailValid,isPassValid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = findViewById(R.id.txEmail);
        pass = findViewById(R.id.txPass);
        btnLogin = findViewById(R.id.btnLogin);
        txDaftar = findViewById(R.id.txDaftar);
        emailErr = findViewById(R.id.txEmailErr);
        passErr = findViewById(R.id.txPassErr);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validasiData();
            }
        });

        txDaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Pendaftaran.class));
            }
        });
    }

    public void validasiData(){
        String trueEmail = "elfuede@mail.com";
        String truePass = "654321";

        if(email.getText().toString().isEmpty()){
            emailErr.setError("Email Wajib Diisi!");
            isEmailValid = false;
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()){
            emailErr.setError("Format Email Kurang Tepat!");
            isEmailValid = false;
        }else{
            isEmailValid = true;
            emailErr.setErrorEnabled(false);
        }
        if(pass.getText().toString().isEmpty()){
            passErr.setError("Password Wajib Diisi!");
            isPassValid = false;
        }else if(pass.getText().length() < 6){
            passErr.setError("Panjang Karakter Minimal 6!");
            isPassValid = false;
        }else{
            isPassValid = true;
            passErr.setErrorEnabled(false);
        }

        if(!email.getText().toString().equals(trueEmail) || !pass.getText().toString().equals(truePass) || !isPassValid || !isEmailValid){
            Toast.makeText(getApplicationContext(),"Email / Password Salah!",Toast.LENGTH_SHORT).show();
            return;
        }
        Toast.makeText(getApplicationContext(),"Login Sukses!",Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(),Kontak.class));
    }
}