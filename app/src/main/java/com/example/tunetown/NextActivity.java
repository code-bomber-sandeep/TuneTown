package com.example.tunetown;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

public class NextActivity extends AppCompatActivity {
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    TextView name,email;
    Button signOutBtn,cbtn;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_next);

        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        signOutBtn = findViewById(R.id.signout);
        cbtn=findViewById(R.id.cbtn);

        cbtn.setOnClickListener(view -> {
            Intent in = new Intent(NextActivity.this,HomePage.class);
            openFragment();
            startActivity(in);

        });

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);


        GoogleSignInAccount acct = GoogleSignIn.getLastSignedInAccount(this);

        if(acct!=null){
            String personName = acct.getDisplayName();
            String personEmail = acct.getEmail();
            name.setText(personName);
            email.setText(personEmail);
        }

        signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signOut();
            }
        });
    }

    private void openFragment() {
        ProfileFragment pf = new ProfileFragment();
        FragmentTransaction fragmentTransaction =getSupportFragmentManager()
                .beginTransaction();
        Bundle data = new Bundle();
        data.putString("UN","name");

        pf.setArguments(data);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.frag,pf)
                .commit();
    }

    void signOut(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(Task<Void> task) {
                finish();
                startActivity(new Intent(NextActivity.this,MainActivity.class));
            }
        });
    }

}