package com.longynuss.whatsappclone;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.longynuss.config.FirebaseConfiguration;
import com.longynuss.model.User;

public class SignUp extends AppCompatActivity {

    private EditText nameInput;
    private EditText emailInput;
    private EditText passInput;
    private Button btnSignUp;
    private User user;

    private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        nameInput = findViewById(R.id.nameSignUp);
        emailInput = findViewById(R.id.emailSignUp);
        passInput = findViewById(R.id.passSignUp);

        btnSignUp = findViewById(R.id.signUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                user = new User(nameInput.getText().toString(),emailInput.getText().toString()
                                                                ,passInput.getText().toString());

                signUpUser(user);
            }
        });
    }

    private void signUpUser(User user) {
        auth = FirebaseConfiguration.getFirebaseAuth();
        auth.createUserWithEmailAndPassword(user.getEmail(),user.getPassword())
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(SignUp.this,"Cadastrado com Sucesso",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(SignUp.this,"Erro ao cadastrar",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
