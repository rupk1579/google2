package com.example.rupasingh.googlemap;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private Button login, register;
    private EditText etEmail, etPass;
    private DatabaseHelper db;
    private Session session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        db = new DatabaseHelper(this);
        session = new Session(this);
        login = findViewById(R.id.btnLogin);
        register = findViewById(R.id.btnReg);
        etEmail = findViewById(R.id.etEmail);
        etPass = findViewById(R.id.etPass);
        login.setOnClickListener(this);
        register.setOnClickListener(this);

        if (session.loggedin()) {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                login();
                break;
            case R.id.btnReg:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
            default:

        }
    }

    private void login() {
        String email = etEmail.getText().toString();
        String pass = etPass.getText().toString();

        if (db.checkUser(email, pass)) {
            getposition(email,pass);
            session.setLoggedin(true);
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Wrong email/password", Toast.LENGTH_SHORT).show();
        }
    }
    private  void getposition(String e1,String p1)
    {    String str1="";
        String str2="";
        Cursor cursor=db.getAllData();
        int idColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL_1);
        int emailColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL_2);
        int passColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL_3);
        int mobColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL_4);
        int pemailColumnIndex = cursor.getColumnIndex(DatabaseHelper.COL_5);
        //cursor.moveToFirst();
            while(cursor.moveToNext()) {
                int currentID = cursor.getInt(idColumnIndex);
                String currentemail = cursor.getString(emailColumnIndex);
                String currentpass = cursor.getString(passColumnIndex);
                String currentmob = cursor.getString(mobColumnIndex);
                String currentpemail = cursor.getString(pemailColumnIndex);
                if (e1.equals(currentemail) && p1.equals(currentpass) ){
                    str1 = currentmob;
                    str2 = currentpemail;
                    break;
                }
            }
        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();
        editor.putString("1", str1);
        editor.putString("2", str2);
        editor.commit();

//        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//        intent.putExtra("key1", str1);
//        intent.putExtra("key2", str2);
//        startActivity(intent);
//        cursor.close();
//        db.close();


    }
}