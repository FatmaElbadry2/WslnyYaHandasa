package com.example.user.WaslnyYaHandasa;
import android.app.Dialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

public class UserProfile extends AppCompatActivity {
    private static final String TAG = "UserProfile";
    private static final int ERROR_DIALOG_REQUEST = 9001;
    private Button offerbutton;
    private Button sharebutton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

       if(isServicesOK()){
            offerbutton = (Button) findViewById(R.id.Offer_Ride);
            offerbutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openOfferRide();

                }
            });
            sharebutton=(Button) findViewById(R.id.share_Ride);
            sharebutton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    openshareRide();

                }
            });
        }


    }

    public void openOfferRide(){
        Intent intent = new Intent(this, OfferARide.class);
        startActivity(intent);
    }
    public void openshareRide(){
        Intent intent = new Intent(this, ShareARide.class);
        startActivity(intent);
    }


    public boolean isServicesOK(){
        Log.d(TAG, "isServicesOK: checking google services version");

        int available = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(UserProfile.this);

        if(available == ConnectionResult.SUCCESS){
            //everything is fine and the user can make map requests
            Log.d(TAG, "isServicesOK: Google Play Services is working");
            return true;
        }
        else if(GoogleApiAvailability.getInstance().isUserResolvableError(available)){
            //an error occured but we can resolve it
            Log.d(TAG, "isServicesOK: an error occured but we can fix it");
            Dialog dialog = GoogleApiAvailability.getInstance().getErrorDialog(UserProfile.this, available, ERROR_DIALOG_REQUEST);
            dialog.show();
        }else{
            Toast.makeText(this, "You can't make map requests", Toast.LENGTH_SHORT).show();
        }
        return false;
    }
}

