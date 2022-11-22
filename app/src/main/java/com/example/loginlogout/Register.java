package com.example.loginlogout;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginlogout.Model.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
public class Register extends AppCompatActivity {
    public static int RESULT = 1000 ;
    TextView email , password  ;
    User newUser ;
    private FirebaseAuth auth ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        email = findViewById(R.id.tenkhoahocinput);
        password = findViewById(R.id.loginPassword);
        auth = FirebaseAuth.getInstance();
    }

    public void handleBack(View view){
        Intent intent = new Intent();
        intent.putExtra("user" , newUser);
        setResult(RESULT , intent);
        finish();
    }

    public void handleRegister(View view){
        String user = email.getText().toString().trim();
        String pass = password.getText().toString().trim();
        if (user.isEmpty()){
            email.setError("Email cannot be empty");
        }
        if (pass.isEmpty()){
            password.setError("Password cannot be empty");
        } else{
            auth.createUserWithEmailAndPassword(user, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if (task.isSuccessful()) {
                        Toast.makeText(Register.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(Register.this, MainActivity.class));
                    } else {
                        Toast.makeText(Register.this, "SignUp Failed" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }
    }
}