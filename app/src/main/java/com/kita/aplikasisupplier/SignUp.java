package com.kita.aplikasisupplier;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.vishnusivadas.advanced_httpurlconnection.PutData;

import java.sql.Array;
import java.util.List;

public class SignUp extends AppCompatActivity {

    TextInputEditText textInputEditTextinfullname, textInputEditTextinuser, textInputEditTextinpass, textInputEditTextinemail ;
    Button buttonSignup;
    TextView textViewlogin;
    ProgressBar  progressBar;
    Spinner drop_role;
    String[] List_role = { "industri", "supplier" };
    ArrayAdapter listrole;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        drop_role = findViewById(R.id.dropdown_role);
        textInputEditTextinfullname = findViewById(R.id.fullname);
        textInputEditTextinuser = findViewById(R.id.username);
        textInputEditTextinpass = findViewById(R.id.password);
        textInputEditTextinemail = findViewById(R.id.email);
        buttonSignup = findViewById(R.id.btn_signup);
        textViewlogin = findViewById(R.id.textView3);
        progressBar = findViewById(R.id.progress);



        textViewlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login.class);
                startActivity(intent);
                finish();
            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname, username, password, email, role;
                fullname = String.valueOf(textInputEditTextinfullname.getText());
                username = String.valueOf(textInputEditTextinuser.getText());
                password = String.valueOf(textInputEditTextinpass.getText());
                email = String.valueOf(textInputEditTextinemail.getText());
                role = drop_role.getSelectedItem().toString();
                if (!fullname.equals("") && !username.equals("") && !password.equals("") && !email.equals("") && !role.equals("")) {
                    progressBar.setVisibility(View.VISIBLE);
                    Handler handler = new Handler(Looper.getMainLooper());
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            String[] field = new String[5];
                            field[0] = "fullname";
                            field[1] = "username";
                            field[2] = "password";
                            field[3] = "email";
                            field[4] = "role";
                            String[] data = new String[5];
                            data[0] = fullname;
                            data[1] = username;
                            data[2] = password;
                            data[3] = email;
                            data[4] = role;
                            PutData putData = new PutData("http://192.168.1.2/supp_app/signup.php", "POST", field, data);
                            if (putData.startPut()) {
                                if (putData.onComplete()) {
                                    progressBar.setVisibility(View.GONE);
                                    String result = putData.getResult();
                                    if(result.equals("Registrasi Berhasil")){
                                        Intent intent = new Intent(getApplicationContext(),Login.class);
                                        startActivity(intent);
                                        finish();
                                    }
                                    else {
                                        Toast.makeText(getApplicationContext(),result, Toast.LENGTH_SHORT).show();
                                    }
                                    //End ProgressBar (Set visibility to GONE)
                                   // Log.i("PutData", result);
                                }
                            }
                            //End Write and Read data with URL
                        }
                    });
                }
                    else {
                    Toast.makeText(getApplicationContext(),"Semua Harus Terisi", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}