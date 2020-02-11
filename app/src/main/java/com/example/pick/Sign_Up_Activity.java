package com.example.pick;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Sign_Up_Activity extends AppCompatActivity {

    @BindView(R.id.email_sign_up_edit_text)
    EditText email_sign_up_edit_text;
    @BindView(R.id.password_sign_up_edit_text)
    EditText password_sign_up_edit_text;
    @BindView(R.id.reset_password_button)
    Button sign_up_button;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign__up);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        sign_up_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Sign_Up();
            }
        });

    }

    private void Sign_Up() {
        String email = email_sign_up_edit_text.getText().toString();
        String password = password_sign_up_edit_text.getText().toString();

        if (TextUtils.isEmpty(email)){
            email_sign_up_edit_text.setError("Please enter email");
            return;
        }
        if (TextUtils.isEmpty(password)){
            password_sign_up_edit_text.setError("Please enter password");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            startActivity(new Intent(getApplicationContext(), OrderActivity.class));
                        } else {
                            String error = task.getException().toString();
                            Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
