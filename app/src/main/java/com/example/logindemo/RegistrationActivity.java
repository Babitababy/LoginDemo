package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegistrationActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Email;
    private EditText Password;
    private Button Register;
    private TextView login;
    private FirebaseAuth firebaseAuth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        setUpViews();
        firebaseAuth=FirebaseAuth.getInstance();

        Register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validate()){

                    String email=Email.getText().toString().trim();
                    String pass=Password.getText().toString().trim();
                    firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {

                            if (task.isSuccessful()) {
                                Toast.makeText(RegistrationActivity.this, "Registration Successful", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(RegistrationActivity.this,MainActivity.class));

                            }
                            else{
                                Toast.makeText(RegistrationActivity.this, "Registration UnSuccessful", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegistrationActivity.this,MainActivity.class ));
            }
        });

    }
    private void setUpViews(){
        Name=(EditText)findViewById(R.id.editText2);
        Email=(EditText)findViewById(R.id.editText3);
        Password=(EditText)findViewById(R.id.editText4);
        Register=(Button)findViewById(R.id.button2);
        login=(TextView)findViewById(R.id.textView4);
    }
    private Boolean validate(){
        Boolean result=false;

        String name=Name.getText().toString();
        String email=Email.getText().toString();
        String pass=Password.getText().toString();
        if(name.isEmpty() || email.isEmpty() || pass.isEmpty()){
            Toast.makeText(this,"Please Enter ALl Details",Toast.LENGTH_LONG).show();
        }
        else{
            result=true;
        }
        return result;
    }
}
