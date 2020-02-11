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
import com.google.firebase.auth.FirebaseAuth;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Forgot_Password_Activity extends AppCompatActivity {

    @BindView(R.id.reset_password_edit_text)
    EditText reset_password_edit_text;
    @BindView(R.id.reset_password_button)
    Button reset_password_button;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        ButterKnife.bind(this);
        mAuth = FirebaseAuth.getInstance();

        reset_password_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetPassword();
            }
        });
    }

    private void resetPassword() {
        String reset_email = reset_password_edit_text.getText().toString();
        if (TextUtils.isEmpty(reset_email)){
                reset_password_edit_text.setError("Please enter email");
                return;
        }

        mAuth.sendPasswordResetEmail(reset_email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Email sent for password reset",Toast.LENGTH_SHORT).show();
                }else {
                    String error = task.getException().toString();
                    Toast.makeText(getApplicationContext(), error, Toast.LENGTH_LONG).show();
                }
            }
        });
    }
}
