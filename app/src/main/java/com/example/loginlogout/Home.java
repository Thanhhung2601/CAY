package com.example.loginlogout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.loginlogout.Model.User;

public class Home extends AppCompatActivity {
    TextView userName , email ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        userName = findViewById(R.id.textView8);
        email = findViewById(R.id.textView9);
        User user = (User) getIntent().getSerializableExtra("userData");
        userName.setText(user.getUserName());
        email.setText(user.getEmail());
    }

    public void handleLogout (View view){
        finish();
    }

    public void navigateListView(View view){
        Toast.makeText(this, "Runn", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent();
        intent.setClass(Home.this , ListCay.class);
        startActivity(intent);
    }
}