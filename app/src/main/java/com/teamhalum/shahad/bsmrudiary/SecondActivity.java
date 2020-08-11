package com.teamhalum.shahad.bsmrudiary;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    private TextView textView;
    String item_name;
    RecyclerView secondrecyclerView;
    RecyclerView secondrecyclerView2;
    SecondItemAdapter secondadapter;
    ContactsAdapter contactsAdapter;
    List<Contacts> contactsList;
    List<Items> seconditemsList;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        //Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        //setSupportActionBar(myToolbar);


        getIncomingIntent();


        secondrecyclerView = findViewById(R.id.secondRCV);
        secondrecyclerView.setHasFixedSize(true);
        secondrecyclerView.setLayoutManager(new LinearLayoutManager(this));

        secondrecyclerView2 = findViewById(R.id.secondRCV2);
        secondrecyclerView2.setHasFixedSize(true);
        secondrecyclerView2.setLayoutManager(new LinearLayoutManager(this));

        seconditemsList = new ArrayList<>();
        contactsList = new ArrayList<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference(item_name);
        db.keepSynced(true);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot itemsnapshot : dataSnapshot.getChildren()) {


                        int s = (int) itemsnapshot.getChildrenCount();
//                        String sd="un";
//                        if(s == 1)
//                            sd = "1";
//                        if(s == 0)
//                            sd = "0";
//                        if(s == 2)
//                            sd = "2";
//                        if(s == 3)
//                            sd = "more";
//                        Log.i("nasd", sd);
                        if(s==1) {
                            Items i = itemsnapshot.getValue(Items.class);
                            seconditemsList.add(i);

                        }
                        else if(s>=1){
                            Contacts i = itemsnapshot.getValue(Contacts.class);
                            contactsList.add(i);
                        }
                    }

                    secondadapter = new SecondItemAdapter(SecondActivity.this, seconditemsList);
                    secondrecyclerView.setAdapter(secondadapter);

                    contactsAdapter = new ContactsAdapter(SecondActivity.this, contactsList);
                    secondrecyclerView2.setAdapter(contactsAdapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

    private void getIncomingIntent() {
        if (getIntent().hasExtra("name")) {
            item_name = getIntent().getStringExtra("name");


//            textView = (TextView) findViewById(R.id.testTV);
//            textView.setText(item_name);
        }
    }


}
