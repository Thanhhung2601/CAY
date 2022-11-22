package com.example.loginlogout ;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.loginlogout.Model.User;

import java.util.ArrayList;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth auth;
    TextView email , password ;
    ArrayList<User> users = new ArrayList<User>();
    User newUser ;
    User userFinded = null ;
    SharedPreferences sharedPreferences ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email = findViewById(R.id.tenkhoahocinput);
        password = findViewById(R.id.loginPassword);
        sharedPreferences = getSharedPreferences("dataLogin" , MODE_PRIVATE);
        email.setText(sharedPreferences.getString("taikhoan",""));
        password.setText(sharedPreferences.getString("matkhau",""));
        auth = FirebaseAuth.getInstance();
    }

    public void handleRegister(View view){
        Intent intent = new Intent();
        intent.setClass(MainActivity.this,Register.class);
        startActivityForResult(intent , Register.RESULT);
    }

    @RequiresApi(api = Build.VERSION_CODES.N)
    public void handleLogin(View view){
//        String emailc = email.getText().toString();
//        String pass = password.getText().toString();
//        if (!emailc.isEmpty() && Patterns.EMAIL_ADDRESS.matcher(emailc).matches()) {
//            if (!pass.isEmpty()) {
//                auth.signInWithEmailAndPassword(emailc, pass)
//                        .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
//                            @Override
//                            public void onSuccess(AuthResult authResult) {
//                                Toast.makeText(MainActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
//                                startActivity(new Intent(MainActivity.this, Home.class));
//                                finish();
//                            }
//                        }).addOnFailureListener(new OnFailureListener() {
//                            @Override
//                            public void onFailure(@NonNull Exception e) {
//                                Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
//                            }
//                        });
//            } else {
//                password.setError("Password cannot be empty");
//            }
//        } else if(emailc.isEmpty()) {
//            email.setError("Email cannot be empty");
//        } else {
//            email.setError("Please enter valid email");
//        }
        startActivity(new Intent(MainActivity.this, ListCay.class));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == Register.RESULT){
            newUser = (User) data.getExtras().getSerializable("user");
            users.add(newUser);
        }
    }
}