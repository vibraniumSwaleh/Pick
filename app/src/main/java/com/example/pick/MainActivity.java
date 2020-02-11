package com.example.pick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
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
import com.google.firebase.auth.FirebaseUser;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.reset_password_edit_text)
    EditText email_edit_text;
    @BindView(R.id.password_sign_in_edit_text)
    EditText password_edit_text;
    @BindView(R.id.reset_password_button)
    Button sign_in_button;
    @BindView(R.id.create_account)
    TextView create_account;
    @BindView(R.id.forgot_password_textview)
    TextView forgot_password_textview;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        sign_in_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                signIn();
            }
        });

        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Sign_Up_Activity.class));
            }
        });

        forgot_password_textview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), Forgot_Password_Activity.class));
            }
        });
    }

    private void signIn() {
        String email = email_edit_text.getText().toString();
        String password = password_edit_text.getText().toString();

        if (TextUtils.isEmpty(email)){
            email_edit_text.setError("Please enter email");
            return;
        }
        if (TextUtils.isEmpty(password)){
            password_edit_text.setError("Please enter password");
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(getApplicationContext(), "Welcome back", Toast.LENGTH_LONG).show();
                            startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        } else {
                            String error = task.getException().toString();
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

//    @Override
//    public void onStart() {
//        super.onStart();
//        // Check if user is signed in (non-null) and update UI accordingly.
//        FirebaseUser currentUser = mAuth.getCurrentUser();
//        if (currentUser != null){
//            startActivity(new Intent(getApplicationContext(), OrderActivity.class));
//        }
//    }

}
