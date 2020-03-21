package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;

public class RegistrationActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Email;
    private EditText Password;
    private Button Register;
    private Toolbar mtoolbar;
    private TextView login;
    private ProgressDialog mprogress;
    private DatabaseReference databaseReference;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        Name = (EditText) findViewById(R.id.Name);
        Email = (EditText) findViewById(R.id.Email);
        mAuth = FirebaseAuth.getInstance();
        Password = (EditText) findViewById(R.id.Password);
        Register = (Button) findViewById(R.id.register);
        login = (TextView) findViewById(R.id.loginagain);
        mtoolbar = (Toolbar) findViewById(R.id.register_toolbar);
        mtoolbar.setTitle("Register");
        setSupportActionBar(mtoolbar);
        mprogress=new ProgressDialog(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this, LoginActivity.class));
            }
        });
        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String email = Email.getText().toString();
                String pass = Password.getText().toString();
                if (!TextUtils.isEmpty(name) || !TextUtils.isEmpty(email) || !TextUtils.isEmpty(pass)) {
                    mprogress.setTitle("Registring user...");
                    mprogress.setMessage("Wait for Sometime");
                    mprogress.setCanceledOnTouchOutside(false);
                    mprogress.show();
                    reg_user(name, email, pass);
                }
            }

        });
    }

    public void reg_user(final String name, String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                    FirebaseUser firebaseUser = mAuth.getCurrentUser();
                    String user_id = firebaseUser.getUid();
                    databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
                    HashMap<String, String> hashMap = new HashMap<>();
                    hashMap.put("id", user_id);
                    hashMap.put("username", name);
                    hashMap.put("image", "default");
                    databaseReference.setValue(hashMap).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                mprogress.dismiss();
                                Intent mainIntent = new Intent(RegistrationActivity.this, SecondActivity.class);
                                startActivity(mainIntent);
                                finish();

                            } else {
                                mprogress.dismiss();
                                Toast.makeText(RegistrationActivity.this, "Login Failed", Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }
            }
        });
    }
}