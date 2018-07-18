package com.example.rupasingh.googlemap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener{
    private Button reg;
    private TextView tvLogin;
    private EditText etEmail, etPass,etmob,etpemail;
    private DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        db = new DatabaseHelper(this);
        reg = findViewById(R.id.btnReg);
        tvLogin = findViewById(R.id.tvLogin);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        etmob = findViewById(R.id.etmob);
        etpemail = findViewById(R.id.etpemail);


        reg.setOnClickListener(this);
        tvLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnReg:
                register();
                break;
            case R.id.tvLogin:
                startActivity(new Intent(RegisterActivity.this,LoginActivity.class));
                finish();
                break;
            default:

        }
    }

    private void register(){
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();
        String mob=etmob.getText().toString();
        String epmail=etpemail.getText().toString();
        if(email.isEmpty() && pass.isEmpty()&&mob.isEmpty()&&epmail.isEmpty()){
            displayToast("Username/password field empty");
        }else{
            db.insertData(email,pass,mob,epmail);
            displayToast("User registered");
            finish();
        }
    }

    private void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}

