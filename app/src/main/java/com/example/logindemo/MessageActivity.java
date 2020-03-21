package com.example.logindemo;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

import de.hdodenhof.circleimageview.CircleImageView;

public class MessageActivity extends AppCompatActivity {

    private CircleImageView image;
    private TextView name;
    private Toolbar mtoolbar;
    private DatabaseReference databaseReference;
    ImageButton btnsend;
    EditText tesxt;
    FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        image=(CircleImageView)findViewById(R.id.imagprofile);
        name=(TextView)findViewById(R.id.useid);
        tesxt=(EditText)findViewById(R.id.textsend);
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        btnsend=(ImageButton)findViewById(R.id.imagesend);
        final String user_id = getIntent().getStringExtra("user_id");
        mtoolbar=(Toolbar)findViewById(R.id.msg_toolbar);
        setSupportActionBar(mtoolbar);
        getSupportActionBar().setTitle("");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        btnsend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String msg=tesxt.getText().toString();
                if(!msg.equals("")){
                    sendmessgae(firebaseUser.getUid(),user_id,msg);
                }
                else {
                    Toast.makeText(MessageActivity.this,"Write Something",Toast.LENGTH_SHORT).show();
                }

                tesxt.setText("");
            }
        });
        mtoolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        databaseReference = FirebaseDatabase.getInstance().getReference().child("Users").child(user_id);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String disimage = dataSnapshot.child("image").getValue().toString();
                String disname = dataSnapshot.child("name").getValue().toString();
                name.setText(disname);
                Picasso.with(MessageActivity.this).load(disimage).placeholder(R.mipmap.ic_launcher).into(image);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
    private void sendmessgae(String sender,String receiver,String message){
        DatabaseReference data=FirebaseDatabase.getInstance().getReference();
        HashMap<String ,String > hashMap=new HashMap<>();
        hashMap.put("sender",sender);
        hashMap.put("receiver",receiver);
        hashMap.put("message",message);
        data.child("Chats").push().setValue(hashMap);

    }
}
