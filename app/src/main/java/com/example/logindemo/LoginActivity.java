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

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button login;
    private Toolbar mtoolbar;
    private TextView register;
    private ProgressDialog loginprogress;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        email=(EditText)findViewById(R.id.email);
        password=(EditText)findViewById(R.id.password);
        mtoolbar=(Toolbar)findViewById(R.id.login_toolbar);
        mtoolbar.setTitle("Login");
        mAuth=FirebaseAuth.getInstance();
        setSupportActionBar(mtoolbar);
        login=(Button)findViewById(R.id.login);
        loginprogress=new ProgressDialog(this);
        register=(TextView)findViewById(R.id.registered);
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,RegistrationActivity.class));
            }
        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emaill=email.getText().toString();
                String pass=password.getText().toString();
                if(!TextUtils.isEmpty(emaill)&&!TextUtils.isEmpty(pass)){
                    loginprogress.setTitle("Logging In");
                    loginprogress.setMessage("Please Wait ");
                    loginprogress.setCanceledOnTouchOutside(false);
                    loginprogress.show();
                    loginuser(emaill,pass);
                }
            }
        });

    }
    public void loginuser(String email,String password){
mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
    @Override
    public void onComplete(@NonNull Task<AuthResult> task) {

if(task.isSuccessful()){
    loginprogress.dismiss();
    Intent mainIntent = new Intent(LoginActivity.this,SecondActivity.class);
    mainIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
    startActivity(mainIntent);
    finish();

}
else {
    loginprogress.dismiss();
    Toast.makeText(LoginActivity.this,"Login Failed",Toast.LENGTH_LONG).show();
}
    }
});
    }
}
