package com.example.tubes10119089;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Regist extends AppCompatActivity {
    private FirebaseAuth mAuth;
    final String TAG = "Registration";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regist);

        mAuth = FirebaseAuth.getInstance();

        Button button = findViewById(R.id.btn_registration);
        final EditText username = findViewById(R.id.email);
        final EditText password = findViewById(R.id.password);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp(username.getText().toString(), password.getText().toString());
            }
        });
    }

    private void signUp(String email, String password) {
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    Log.d(TAG, "createUserWithEmail:succes");
                    FirebaseUser user = mAuth.getCurrentUser();

                    Log.d(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Regist.this, "Authentication succes." + user.getEmail(), Toast.LENGTH_SHORT).show();
                    finish();

                } else {
                    Log.d(TAG, "createUserWithEmail:failure", task.getException());
                    Toast.makeText(Regist.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}