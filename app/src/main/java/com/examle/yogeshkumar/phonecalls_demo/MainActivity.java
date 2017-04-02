package com.examle.yogeshkumar.phonecalls_demo;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static final int CALL = 1;
    EditText no;
    Button call;
    Snackbar s;
    ConstraintLayout constraintLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        no = (EditText) findViewById(R.id.phoneid);
        call = (Button) findViewById(R.id.callid);
        constraintLayout=(ConstraintLayout)findViewById(R.id.c);
        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

                askforpermission(Manifest.permission.CALL_PHONE, CALL);}
                else {

                    String number = no.getText().toString();
                    Intent i = new Intent(Intent.ACTION_CALL);
                    i.setData(Uri.parse("tel:" + number));
                    startActivity(i);
                }

            }
        });


    }

    private void askforpermission(final String permission, final int requestcode) {

        if (ContextCompat.checkSelfPermission(MainActivity.this, permission) != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this, permission)) {

                s.make(constraintLayout,"Need Telephony permission to make calls",Snackbar.LENGTH_INDEFINITE)
                        .setAction("GRANT", new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                                ActivityCompat.requestPermissions(MainActivity.this,new String[]{permission},requestcode);
                            }
                        }).show();

            } else {
                ActivityCompat.requestPermissions(MainActivity.this, new String[]{permission}, requestcode);
            }
        } else Toast.makeText(this, "already granted", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        Toast.makeText(this, "Call plz", Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case CALL: {
                //Toast.makeText(this, "Call plz", Toast.LENGTH_SHORT).show();

                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

//                    String number = no.getText().toString();
//                    Intent i = new Intent(Intent.ACTION_CALL);
//                    i.setData(Uri.parse("tel:" + number));
//                    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
//                       // Toast.makeText(this, "Call plz", Toast.LENGTH_SHORT).show();
//                        startActivity(i);
//                    }

                   // Toast.makeText(this, "Call plz", Toast.LENGTH_SHORT).show();
                    Toast.makeText(this, "permission granted", Toast.LENGTH_SHORT).show();
                }
                    else{
                Toast.makeText(this,"permission denied",Toast.LENGTH_SHORT).show();
                    }


        }}
          // Toast.makeText(this,"permission granted",Toast.LENGTH_SHORT).show();


    }
}
