package com.example.bikenmaharjan.priceapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;


public class Login extends AppCompatActivity {


    private EditText txtemailinput, txtpwinput;
    private Button btnlogin;
    private TextView txtregister;

    private FirebaseAuth mAuth;

    private FirebaseAuth.AuthStateListener mAuthListener;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        FirebaseAuth.getInstance().signOut();

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                if(firebaseAuth.getCurrentUser()!=null){

                    Intent intent = new Intent(Login.this,HomeActivity.class);
//                    intent.putExtra("UserEmail",txtemailinput.getText().toString());

                    startActivity(intent);


                }
            }
        };


        btnlogin = (Button)findViewById(R.id.btnlogin);
        txtregister = (TextView)findViewById(R.id.txtregister);
        txtemailinput =  (EditText)findViewById(R.id.txtemailinput);
        txtpwinput =  (EditText)findViewById(R.id.txtpwinput);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startSignin();
            }
        });

        txtregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Login.this,Register.class);
                startActivity(intent);
                finish();


            }
        });


    }

    @Override
    protected void onStart() {
        super.onStart();

        mAuth.addAuthStateListener(mAuthListener);
    }

    private void startSignin(){
        String email = txtemailinput.getText().toString();
        String password = txtpwinput.getText().toString();


        if(TextUtils.isEmpty(email)||(TextUtils.isEmpty(password))){
            Toast.makeText(Login.this, "Some field is blank!", Toast.LENGTH_LONG).show();

        }
        else{
            mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(!task.isSuccessful()){
                        Toast.makeText(Login.this, "Unsuccessful Sign In", Toast.LENGTH_LONG).show();
                    }
                }
            });

        }



    }
}
