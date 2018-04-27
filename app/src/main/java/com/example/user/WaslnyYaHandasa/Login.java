package com.example.user.WaslnyYaHandasa;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.ProgressDialog;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;
import android.content.Intent;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

import android.text.TextUtils;
import android.widget.Toast;
public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText Myemail;
    private EditText MyPassword;
    private TextView Signup;
    private Button login;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference ref;
    private FirebaseDatabase database;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        firebaseAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        if (firebaseAuth.getCurrentUser() != null) {
        FirebaseUser Currentuser=firebaseAuth.getCurrentUser();
        ref.child("Users").child(Currentuser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(! dataSnapshot.exists()){
                    finish();
                    startActivity(new Intent(getApplicationContext(), SaveInfo.class));
                }
                else
                {
                    finish();
                    startActivity(new Intent(getApplicationContext(), UserProfile.class));
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        }
        Myemail = (EditText) findViewById(R.id.email);
        MyPassword = (EditText) findViewById(R.id.password);
        Signup = (TextView) findViewById(R.id.signup);
        login = (Button) findViewById(R.id.login);

        progressDialog = new ProgressDialog(this);

        //attaching listener to button
        login.setOnClickListener(this);
        Signup.setOnClickListener(this);
    }



      private void LoginUser() {
          String email = Myemail.getText().toString().trim();
          String password = MyPassword.getText().toString().trim();
          if (TextUtils.isEmpty(email)) {
              Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
              return ;
          }

          if (TextUtils.isEmpty(password)) {
              Toast.makeText(this, "Please enter password", Toast.LENGTH_LONG).show();
              return ;
          }

          firebaseAuth.signInWithEmailAndPassword(email, password)
                  .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                      @Override
                      public void onComplete(@NonNull Task<AuthResult> task) {
                          progressDialog.dismiss();
                          if (task.isSuccessful()) {
                              FirebaseUser Currentuser=firebaseAuth.getCurrentUser();
                              ref.child("Users").child(Currentuser.getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
                                  @Override
                                  public void onDataChange(DataSnapshot dataSnapshot) {
                                      if(! dataSnapshot.exists()){
                                          finish();
                                          startActivity(new Intent(getApplicationContext(), SaveInfo.class));
                                      }
                                      else
                                      {
                                          finish();
                                          startActivity(new Intent(getApplicationContext(), UserProfile.class));
                                      }

                                  }

                                  @Override
                                  public void onCancelled(DatabaseError databaseError) {

                                  }
                              });

                          }
                          else {
                              Toast.makeText(Login.this,"Couldn't login, please check your email and password..",Toast.LENGTH_SHORT).show();

                          }
                      }
                  });

      }

    @Override
    public void onClick(View view) {
        if(view == login){
            LoginUser();
        }

        if(view == Signup){

            startActivity(new Intent(this, CreateProfile.class));
            finish();

        }
    }
    }

