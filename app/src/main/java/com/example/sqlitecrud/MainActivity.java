package com.example.sqlitecrud;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

public class MainActivity extends AppCompatActivity
{
    TextInputLayout contact, email;
    FloatingActionButton fb;
    Button sbmt;
    String emailpattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

//        name = (TextInputLayout) findViewById(R.id.nametext);
        contact = (TextInputLayout) findViewById(R.id.contacttext);
        email = (TextInputLayout) findViewById(R.id.emailtext);
        fb = (FloatingActionButton) findViewById(R.id.fbtn);
        sbmt = (Button) findViewById(R.id.sbmt_add);


            sbmt.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                  if(TextUtils.isEmpty(email.getEditText().getText().toString())){
                      Toast.makeText(MainActivity.this,"Please Insert email",Toast.LENGTH_LONG).show();
                  } else if(TextUtils.isEmpty(contact.getEditText().getText().toString())) {
                      Toast.makeText(MainActivity.this, "Please Insert phone", Toast.LENGTH_LONG).show();
                  } else if(email.getEditText().getText().toString().trim().matches(emailpattern)){
//                      Toast.makeText(MainActivity.this,"Valid Email Address",Toast.LENGTH_LONG).show();
                      processinsert(contact.getEditText().getText().toString(),email.getEditText().getText().toString());

//                  }
                  } else{
                      Toast.makeText(MainActivity.this,"Invalid Email Address",Toast.LENGTH_LONG).show();
                  }


                }
            });




            fb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(getApplicationContext(),fetchdata.class));
                }
            });


    }

    private void processinsert(String c, String e)
    {
       String res=new dbmanager(this).addrecord(c,e);
       contact.getEditText().setText("");
       email.getEditText().setText("");
       Toast.makeText(getApplicationContext(),res,Toast.LENGTH_SHORT).show();
    }


}