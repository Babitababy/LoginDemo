package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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
import com.google.firebase.auth.FirebaseUser;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Text;
    private int counter=5;
    private TextView register;
    private FirebaseAuth firebaseAuth;
    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Name=(EditText)findViewById(R.id.editText);
        Password=(EditText)findViewById(R.id.editText1);
        Login=(Button)findViewById(R.id.button);
        Text=(TextView)findViewById(R.id.textView2);
        register=(TextView)findViewById(R.id.textView3);

        Text.setText("No. of attempts Remaining: 5");
        firebaseAuth=FirebaseAuth.getInstance();
        progressDialog=new ProgressDialog(this);
        FirebaseUser user = firebaseAuth.getCurrentUser();
        if(user!=null){
            startActivity(new Intent(MainActivity.this,SecondActivity.class));
        }
        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Validate(Name.getText().toString(), Password.getText().toString());
            }
        });
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,RegistrationActivity.class));
            }
        });

    }
    public void Validate(String userName,String userPass){
        progressDialog.setMessage("you can login aur register from here");
        progressDialog.show();
        firebaseAuth.signInWithEmailAndPassword(userName,userPass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    progressDialog.dismiss();
                    Toast.makeText(MainActivity.this,"Login Sucessful",Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(MainActivity.this,SecondActivity.class));
                }
                else{
                    Toast.makeText(MainActivity.this,"Login Failed",Toast.LENGTH_SHORT).show();
                    counter--;
                    Text.setText("No. of attempts Remaining:" +counter);
                    progressDialog.dismiss();
                    if(counter==0){
                        Login.setEnabled(false);
                    }
                }
            }
        });
    }
}


//just to know how to push