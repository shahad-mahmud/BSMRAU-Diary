package com.teamhalum.shahad.bsmrudiary;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    DrawerLayout drawerLayout;
    Toolbar toolbar;
    ActionBarDrawerToggle actionBarDrawerToggle;
    NavigationView navigationView;


    RecyclerView recyclerView;
    RecyclerView serchrcv;
    EditText search;
    TextView t;
    ItemAdapter adapter;
    List<Items> itemsList;
    int count = 0;

    ContactsAdapter contactsAdapter;
    List<Contacts> contactsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        setUpToolbar();

        navigationView = (NavigationView)findViewById(R.id.navigation_menu);
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()){
                    case R.id.nav_theuni:

                        Intent i_the_uni=new Intent(MainActivity.this,activity_the_uni.class);
                        startActivity(i_the_uni);

                    break;

                    case R.id.nav_mission:

                        Intent i_mission=new Intent(MainActivity.this,mission.class);
                        startActivity(i_mission);

                        break;

                    case R.id.nav_adminbodies:

                        Intent i_admin=new Intent(MainActivity.this,adminBodies.class);
                        startActivity(i_admin);

                        break;

                    case R.id.nav_syndicate:

                        Intent i_syndicate=new Intent(MainActivity.this,syndicate.class);
                        startActivity(i_syndicate);

                        break;

                    case R.id.nav_academiccouncil:

                        Intent i_aca=new Intent(MainActivity.this,academicCouncil.class);
                        startActivity(i_aca);

                        break;

                    case R.id.nav_aboutBsm:

                        Intent i_abt=new Intent(MainActivity.this,about.class);
                        startActivity(i_abt);

                        break;

                    case R.id.nav_fac:

                        Intent i_fac=new Intent(MainActivity.this,facilities.class);
                        startActivity(i_fac);

                        break;

                    case R.id.nav_mainsite:

                        Intent i_site=new Intent(MainActivity.this,Website.class);
                        startActivity(i_site);

                        break;
                }
                return false;
            }
        });

        Log.i("start","true");

        recyclerView = findViewById(R.id.mainRCV);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        serchrcv = findViewById(R.id.searchRCV);
        serchrcv.setHasFixedSize(true);
        serchrcv.setLayoutManager(new LinearLayoutManager(this));

        itemsList = new ArrayList<>();
        contactsList = new ArrayList<>();

        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Items");
        db.keepSynced(true);

        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if(dataSnapshot.exists()){

                    for (DataSnapshot itemsnapshot : dataSnapshot.getChildren()){
                        Items i = itemsnapshot.getValue(Items.class);
                        itemsList.add(i);
                    }

                    adapter = new ItemAdapter(MainActivity.this, itemsList);
                    recyclerView.setAdapter(adapter);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        search = (EditText) findViewById(R.id.searchET);
        //t = (TextView) findViewById(R.id.test);

        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                //t.setText("zzzzzzzzzzzzzzzzzzzzzzzzzzzz");
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                contactsList.clear();

                String vla = search.getText().toString();
//                int count = 0;
//                count = count+1;
//                t.setText("" + count);
                if(vla.length()>0) {
                    recyclerView.setVisibility(View.GONE);
                    serchrcv.setVisibility(View.VISIBLE);


                    DatabaseReference searchdb = FirebaseDatabase.getInstance().getReference();

                    ValueEventListener valueEventListener = searchdb.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                            int count = 0;
//                            count = count+1;
//                            t.setText("" + count);
                            if (dataSnapshot.exists()) {
                                for (DataSnapshot firstchild : dataSnapshot.getChildren()) {
                                    if (firstchild.exists()) {
                                        for (DataSnapshot secondchild : firstchild.getChildren()) {
                                            Contacts c = secondchild.getValue(Contacts.class);

                                            String name, post, email, resnum, officenum, mobmun1, mobnum2, search_key;

                                            name = post = email = resnum = officenum = mobmun1 = mobnum2 = search_key = "";

                                            name = String.format("%s%s", name, c.getName());
                                            name = name.toLowerCase();
                                            post = String.format("%s%s", post, c.getPost());
                                            post = post.toLowerCase();
                                            resnum = String.format("%s%s", resnum ,c.getRes_num());
                                            resnum = resnum.toLowerCase();
                                            officenum = String.format("%s%s", officenum ,c.getOffice_num());
                                            officenum = officenum.toLowerCase();
                                            mobmun1 = String.format("%s%s", mobmun1 ,c.getMobile_num1());
                                            mobmun1 = mobmun1.toLowerCase();
                                            mobnum2 = String.format("%s%s", mobnum2,c.getMobile_num2());
                                            mobnum2 = mobnum2.toLowerCase();

                                            search_key = search.getText().toString();

                                            //t.setText(name);

                                            if(name.indexOf(search_key)!= -1 || post.indexOf(search_key)!= -1 ||
                                                    email.indexOf(search_key)!= -1 || resnum.indexOf(search_key)!= -1 ||
                                                    officenum.indexOf(search_key)!= -1 || mobmun1.indexOf(search_key)!= -1 ||
                                                    mobnum2.indexOf(search_key)!= -1){
                                                contactsList.add(c);
//                                                count = count+1;
//                                                t.setText("" + count);
                                            }


//                                            if (name.indexOf("sdfds") != -1) {
//                                                t.setText(name);
//                                            } else {
//                                                t.setText("aasd");
//                                            }
//                                            t.setText(name);
                                        }
                                        contactsAdapter = new ContactsAdapter(MainActivity.this, contactsList);
                                        serchrcv.setAdapter(contactsAdapter);
                                    }
                                }

                            }
                        }


                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                Query searchQuery;
                if(vla.length() == 0){
                    recyclerView.setVisibility(View.VISIBLE);
                    serchrcv.setVisibility(View.GONE);
                }

            }

            @Override
            public void afterTextChanged(Editable editable) {

//                count = count+1;
//                t.setText("" + count);
            }
        });

    }

    private void setUpToolbar()
    {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawerlayout);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        actionBarDrawerToggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.app_name,R.string.app_name);
        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();

    }

}
