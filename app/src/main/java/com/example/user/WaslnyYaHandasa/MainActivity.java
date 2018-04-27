package com.example.user.WaslnyYaHandasa;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    private DatabaseReference ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
       for (int i=0;i<100;i++){
       }
        firebaseAuth = FirebaseAuth.getInstance();
        ref = FirebaseDatabase.getInstance().getReference();
        if (firebaseAuth.getCurrentUser() == null) {
            finish();
            startActivity(new Intent(getApplicationContext(), Login.class));
        }
        else if (firebaseAuth.getCurrentUser() != null){

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

        }

    public void openSignUp()
    {
        Intent intent=new Intent(this , CreateProfile.class);
        startActivity(intent);
    }

    public void openLogin()
    {
        Intent intent=new Intent(this , Login.class);
        startActivity(intent);
    }
}
