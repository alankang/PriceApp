package com.example.bikenmaharjan.priceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class Input extends AppCompatActivity {
    private Button mb;

    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;

    private DatabaseReference myRef;
    private String userID;

    private EditText txtbarcode,txtdate,txtprice,txtbestprice,txtname,txturl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input);

        mb = (Button) findViewById(R.id.btndone);

        txtprice = (EditText)findViewById(R.id.txtprice);
        txtbarcode = (EditText)findViewById(R.id.txtbarcode);
        txtdate = (EditText)findViewById(R.id.txtdate);



        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        FirebaseUser user = mAuth.getCurrentUser();
        userID = user.getUid();

        mb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String product = txtname.getText().toString();
                String price = txtprice.getText().toString();
                String date = txtdate.getText().toString();
                String barcode = txtbarcode.getText().toString();
                String url = "www.google.com";
                String bestprice = "";



                String key = myRef.child("Products").child(userID).push().getKey();
                UserProductInformation userProductInformation = new UserProductInformation(product, price, date, bestprice,barcode,url);
                myRef.child("Products").child(userID).child(key).setValue(userProductInformation);


                Intent i = new Intent(Input.this, HomeActivity.class);
                startActivity(i);
            }
        });



    }
}
