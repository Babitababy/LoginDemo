package com.example.logindemo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.jar.Attributes;

public class MainActivity extends AppCompatActivity {

    private EditText Name;
    private EditText Password;
    private Button Login;
    private TextView Text;
    private int counter=5;
    private TextView register;

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
        if((userName.equals("admin")) && (userPass.equals("1234"))){
            Intent intent=new Intent(MainActivity.this,SecondActivity.class);
            startActivity(intent);
        }
        else{
            counter--;

            Text.setText("No. of attempts Remaining:" +String.valueOf(counter));
            if(counter==0){
                Login.setEnabled(false);
            }
        }
    }
}
