package com.example.tunetown;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;

public class ProfileFragment extends Fragment {
    Button SignOut;
    private String  myStr;
    private TextView UN;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_profile, container, false);
        SignOut = view.findViewById(R.id.so);
        UN =view.findViewById(R.id.user);

        Bundle data = getArguments();

        if (getArguments() != null) {
            String name = getArguments().getString("UN");
            UN.setText(name);
        }

        SignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(requireContext(),MainActivity.class);
                startActivity(intent);
            }
        });


        return view;

    }


}