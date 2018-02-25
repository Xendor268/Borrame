package com.example.xendor2.borrame;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
public class Act1 extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
    public SignInButton btnSignIN;
    public GoogleApiClient googleApiClient;
    public static final int SIGN_IN_CODE=777;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_act1);
        btnSignIN=(SignInButton) findViewById(R.id.btnSignIn);
        GoogleSignInOptions gso= new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).
                requestEmail().build();
        googleApiClient = new GoogleApiClient.Builder(this).
                enableAutoManage(this,this).
                addApi(Auth.GOOGLE_SIGN_IN_API,gso).build();
        btnSignIN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2= Auth.GoogleSignInApi.getSignInIntent(googleApiClient);
                startActivityForResult(i2,SIGN_IN_CODE);
            }
        });
    }
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==SIGN_IN_CODE){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            handleSignInResult(result);
        }
    }
    public void handleSignInResult(GoogleSignInResult result) {
        if (result.isSuccess()){
            Toast.makeText(this,"Error al iniciar sesion",Toast.LENGTH_SHORT).show();
        }else{

            Intent i1=new Intent(this,Act2.class);
            i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_CLEAR_TASK|Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i1);
        }
    }
    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {
    }
}