package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.google.firebase.auth.FirebaseAuth;

public class SecondActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;
    private Button logout;
    private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        firebaseAuth=FirebaseAuth.getInstance();
        logout=(Button)findViewById(R.id.button3);
        toolbar=(Toolbar)findViewById(R.id.app_toolbar);
        toolbar.setTitle("Welcome to Chat App");
        setSupportActionBar(toolbar);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              logout();
            }
        });
    }

    private void logout(){
        firebaseAuth.signOut();
        finish();
        startActivity(new Intent(SecondActivity.this,MainActivity.class));
    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.logout:{
                logout();
            }
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

}
