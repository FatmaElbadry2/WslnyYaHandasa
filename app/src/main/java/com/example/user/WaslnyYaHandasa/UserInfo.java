package com.example.user.WaslnyYaHandasa;

import android.graphics.Picture;

/**
 * Created by user on 28/03/2018.
 */

    public class UserInfo {
    public String UserName;
    public int collegeID;
    public Picture picture;
    public String Gender;
    public int phoneNumber;
    public String UserType;
    public boolean saved;
    public UserInfo(){}
    public UserInfo(String name,int id,String Gender,int phone,String type,boolean isSaved){
       UserName=name;
       collegeID=id;
       this.Gender=Gender;
        phoneNumber=phone;
        UserType=type;
        saved=isSaved;
   }

    public String GetUserName(){
         return  UserName;
    }
    public int  GetID(){
        return collegeID;
    }
    public Picture GetPicture(){
        return picture;
    }
    public String  GetGender(){
        return Gender;
    }
    public int  GetPhoneNumber(){
        return phoneNumber;
    }
    public String GetUserType(){return UserType;}

}
