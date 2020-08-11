package com.teamhalum.shahad.bsmrudiary;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class ContactsDetail extends AppCompatActivity {

    private static final int REQUEST_CALL = 1;

    TextView name, email, post, resNum, officeNum, mobNum1, mobNum2,legend_email, legend_resNum, legend_officeNum, legend_mobNum1, legend_mobnum2;
    String intent_name, intent_email, intent_post, intent_resNum, intent_officeNum, intent_mobNum1, intent_mobNum2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contacts_detail);

        name = (TextView) findViewById(R.id.ContactName);
        email = (TextView) findViewById(R.id.ContactEmail);
        post = (TextView) findViewById(R.id.ContactPost);
        resNum = (TextView) findViewById(R.id.ResNum);
        officeNum = (TextView) findViewById(R.id.OfficeNum);
        mobNum1 = (TextView) findViewById(R.id.Mob1);
        mobNum2 = (TextView) findViewById(R.id.Mob2);

        legend_email = (TextView) findViewById(R.id.Legend_email);
        legend_resNum = (TextView) findViewById(R.id.Legend_ResNum);
        legend_officeNum = (TextView) findViewById(R.id.Legend_OfficeNUm);
        legend_mobNum1 = (TextView) findViewById(R.id.Legend_Mob1);
        legend_mobnum2 = (TextView) findViewById(R.id.Legend_Mob2);

        getIncomingIntent();

        resNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = resNum.getText().toString().trim();

                if (ContextCompat.checkSelfPermission(ContactsDetail.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){
                    ActivityCompat.requestPermissions(ContactsDetail.this,
                            new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL);

                }else{
                    String dial =  value;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", dial, null)));
                }

            }
        });

        officeNum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = officeNum.getText().toString().trim();

                if (ContextCompat.checkSelfPermission(ContactsDetail.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){

                }else{
                    String dial = value;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", dial, null)));
                }

            }
        });

        mobNum1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = mobNum1.getText().toString().trim();

                if (ContextCompat.checkSelfPermission(ContactsDetail.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){

                }else{
                    String dial = value;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", dial, null)));
                }

            }
        });

        mobNum2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = mobNum2.getText().toString().trim();

                if (ContextCompat.checkSelfPermission(ContactsDetail.this, Manifest.permission.CALL_PHONE)
                        != PackageManager.PERMISSION_GRANTED){

                }else{
                    String dial = value;
                    startActivity(new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", dial, null)));
                }

            }
        });

        email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String value = email.getText().toString().trim();
                String[] rec = value.split(",");

                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL,rec);

                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent,"Send email by..."));

            }
        });
    }

    public boolean isNull(String s){
        if(s == null || s.length() == 0)
            return true;
        return false;
    }

    private void getIncomingIntent(){
        if(getIntent().hasExtra("name")){
            intent_name = getIntent().getStringExtra("name");
            if(!isNull(intent_name)){
                name.setText(intent_name);
                name.setVisibility(View.VISIBLE);
            }
        }

        if(getIntent().hasExtra("post")){
            intent_post = getIntent().getStringExtra("post");
            if(!isNull(intent_post)){
                post.setText(intent_post);
                post.setVisibility(View.VISIBLE);
            }
        }

        if(getIntent().hasExtra("email")){
            intent_email = getIntent().getStringExtra("email");
            if(!isNull(intent_email)){
                email.setText(intent_email);
                email.setVisibility(View.VISIBLE);
                legend_email.setVisibility(View.VISIBLE);
            }
        }

        if(getIntent().hasExtra("resNum")){
            intent_resNum = getIntent().getStringExtra("resNum");
            if(!isNull(intent_resNum)){
                resNum.setText(intent_resNum);
                resNum.setVisibility(View.VISIBLE);
                legend_resNum.setVisibility(View.VISIBLE);
            }
        }

        if(getIntent().hasExtra("officeNum")){
            intent_officeNum = getIntent().getStringExtra("officeNum");
            if(!isNull(intent_officeNum)){
                officeNum.setText(intent_officeNum);
                officeNum.setVisibility(View.VISIBLE);
                legend_officeNum.setVisibility(View.VISIBLE);
            }
        }

        if(getIntent().hasExtra("mobNum1")){
            intent_mobNum1 = getIntent().getStringExtra("mobNum1");
            if(!isNull(intent_mobNum1)){
                mobNum1.setText(intent_mobNum1);
                mobNum1.setVisibility(View.VISIBLE);
                legend_mobNum1.setVisibility(View.VISIBLE);
            }
        }

        if(getIntent().hasExtra("mobNum2")){
            intent_mobNum2 = getIntent().getStringExtra("mobNum2");
            if(!isNull(intent_mobNum2)){
                mobNum2.setText(intent_mobNum2);
                mobNum2.setVisibility(View.VISIBLE);
                legend_mobnum2.setVisibility(View.VISIBLE);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        if(requestCode == REQUEST_CALL){
            if(grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                Toast.makeText(ContactsDetail.this, "Try again", Toast.LENGTH_SHORT).show();
            } else{
                Toast.makeText(ContactsDetail.this, "Permission DENIED", Toast.LENGTH_SHORT).show();
            }
        }
        else{
            Toast.makeText(ContactsDetail.this, "reqcode", Toast.LENGTH_SHORT).show();
        }
    }
}
