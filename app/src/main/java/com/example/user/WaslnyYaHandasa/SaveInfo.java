package com.example.user.WaslnyYaHandasa;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;


import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SaveInfo extends AppCompatActivity implements View.OnClickListener {
    //private CreateProfile Myprofile=new CreateProfile();

    private EditText Fname;
    private EditText Lname;
    private EditText CollegeId;
    private EditText phone;
    private RadioButton sex;
    private RadioButton Type;
    private Button next;
    private RadioGroup Gender;
    private RadioGroup UserType;
    String username;
    String UserGender;
    String Usertype;
    int id;
    int MyPhone;
    private ProgressDialog progressDialog;
    private FirebaseAuth firebaseAuth;
    private DatabaseReference database;
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_info);
        firebaseAuth=FirebaseAuth.getInstance();
        database= FirebaseDatabase.getInstance().getReference("Users");
        Fname=(EditText)findViewById(R.id.Fname);
        Lname=(EditText)findViewById(R.id.Lname);
        CollegeId=(EditText)findViewById(R.id.CID);
        phone=(EditText)findViewById(R.id.phone);
        Gender=(RadioGroup)findViewById(R.id.radioSex);
        UserType=(RadioGroup)findViewById(R.id.radioType);
        next=(Button)findViewById(R.id.next);
        next.setOnClickListener(this);
    }

    public int  Register(){

        username=Fname.getText().toString().trim()+" "+Lname.getText().toString().trim();
        int selectedgenderId = Gender.getCheckedRadioButtonId();
        sex=(RadioButton)findViewById(selectedgenderId);
        UserGender=sex.getText().toString().trim();
        int selectedtypeId = UserType.getCheckedRadioButtonId();
        Type=(RadioButton)findViewById(selectedtypeId);
        Usertype=Type.getText().toString().trim();
        if (TextUtils.isEmpty(username)){
            //empty email
            Toast.makeText(this,"please fill all the name fields",Toast.LENGTH_SHORT).show();
            return -1;
        }
        if (TextUtils.isEmpty(CollegeId.getText().toString())){
            //empty email
            Toast.makeText(this,"please enter your college ID",Toast.LENGTH_SHORT).show();
            return -1;
        }
        if (TextUtils.isEmpty(phone.getText().toString())){
            //empty email
            Toast.makeText(this,"please enter your phone number",Toast.LENGTH_SHORT).show();
            return -1;
        }
        id=Integer.parseInt(CollegeId.getText().toString().trim());
        MyPhone=Integer.parseInt(phone.getText().toString().trim());
        UserInfo userInfo=new UserInfo(username,id,UserGender,MyPhone,Usertype,true);
        FirebaseUser Currentuser=firebaseAuth.getCurrentUser();
        database.child(Currentuser.getUid()).setValue(userInfo);
        Toast.makeText(this,"Saved successfully",Toast.LENGTH_SHORT).show();
        return 0;
    }

    @Override















    public void onClick(View v) {
        if (v==next){
            if ( Register()==0){
                finish();
                startActivity(new Intent(this, UserProfile.class));
            }
        }

    }

}
