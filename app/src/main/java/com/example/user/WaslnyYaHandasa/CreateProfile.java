package com.example.user.WaslnyYaHandasa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
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

public class CreateProfile extends AppCompatActivity implements View.OnClickListener{
    private Button Next;
    private Button share;
    private Button offer;
    private EditText email;
    private EditText password;
    private EditText Repassword;
    private TextView registered;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private UserInfo user;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_createprofile);
        progressDialog=new ProgressDialog(this);
        firebaseAuth = FirebaseAuth.getInstance();
        Next=(Button)findViewById(R.id.Next);
        email=(EditText) findViewById(R.id.email);
        password=(EditText) findViewById(R.id.password);
        registered=(TextView) findViewById(R.id.registered);
        Repassword=(EditText)findViewById(R.id.repassword);
        Next.setOnClickListener(this);
        registered.setOnClickListener(this);

        /*
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openShareARide();
            }
        });

        offer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openOfferARide();
            }
        });
        */
    }

    private void openShareARide(){
        Intent intent=new Intent(this , ShareARide.class);
        startActivity(intent);
    }

    private void openOfferARide(){
        Intent intent=new Intent(this , ShareARide.class);
        startActivity(intent);
    }

    public int registerUser(){

        String Mymail=email.getText().toString().trim();
        String Mypassword=password.getText().toString().trim();
        String ConfirmedPasswrod=Repassword.getText().toString().trim();
        if (TextUtils.isEmpty(Mymail)){
            //empty email
            Toast.makeText(this,"please enter your email",Toast.LENGTH_SHORT).show();
            return -1;
        }
        if (TextUtils.isEmpty(Mypassword)){
            // empty password
            Toast.makeText(this,"please enter your password",Toast.LENGTH_SHORT).show();
            return -1;
        }
        if (!Mypassword.equals(ConfirmedPasswrod)){
            Toast.makeText(this,"The passwords you entered do not match",Toast.LENGTH_SHORT).show();
            return -1;
        }
        progressDialog.setMessage("Registering user.....");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(Mymail,Mypassword).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(CreateProfile.this,"Registered successfully",Toast.LENGTH_SHORT).show();
                            finish();
                            startActivity(new Intent(getApplicationContext(), SaveInfo.class));
                        }
                        else {
                            Toast.makeText(CreateProfile.this,"Couldn't register please try again",Toast.LENGTH_SHORT).show();
                        }
            }
        });
        return 0;
    }
    public UserInfo GetUser(){
        return user;
    }
    @Override
    public void onClick(View view){
      if (view==Next){
          registerUser();

      }

 if (view==registered){
     finish();
     startActivity(new Intent(this, Login.class));
 }
    }
}
