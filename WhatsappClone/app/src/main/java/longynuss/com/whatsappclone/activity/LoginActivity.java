package longynuss.com.whatsappclone.activity;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;

import longynuss.com.whatsappclone.R;
import longynuss.com.whatsappclone.config.FirebaseConfig;

public class LoginActivity extends AppCompatActivity {

    private static final int SIGN_UP_REQUEST = 1001;
    private FirebaseAuth firebaseAuth;


    private EditText edtEmail;
    private EditText edtPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseConfig.getFirebaseAuth();

        edtEmail = findViewById(R.id.editText_email);
        edtPass = findViewById(R.id.editText_password);

        Button btnLoginEmail = findViewById(R.id.btn_loginEmail);
        btnLoginEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                String pass = edtPass.getText().toString();
                if(!email.isEmpty() && !pass.isEmpty()){
                    loginUserWithEmail(email,pass);
                }else{
                    //empty edit texts notify user
                    Toast.makeText(LoginActivity.this,R.string.emptyEditTextNotify
                                                            ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView forgotPass = findViewById(R.id.txt_forgotPass);
        forgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = edtEmail.getText().toString();
                if(!email.isEmpty()){
                    firebaseAuth.sendPasswordResetEmail(email);
                    Toast.makeText(LoginActivity.this,R.string.recoverEmailSent
                            ,Toast.LENGTH_SHORT).show();
                }else{
                    //empty edit texts notify user
                    Toast.makeText(LoginActivity.this,R.string.recoverPassNeedEmail
                            ,Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnSignUp= findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this,SignupActivity.class);
                startActivityForResult(intent,SIGN_UP_REQUEST);
            }
        });
    }

    private void loginUserWithEmail(String email, String password){
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LoginFlow", "signInWithEmail:success");
                            returnResult(Activity.RESULT_OK);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w("LoginFlow", "signInWithEmail:failure", task.getException());

                            Exception exception = task.getException();
                            try{
                                if(exception!=null){
                                    throw exception;
                                }
                            }catch (FirebaseAuthInvalidCredentialsException e){
                                Log.w("LoginFlow", "FirebaseAuthInvalidCredentialsException");
                                Toast.makeText(LoginActivity.this,R.string.wrongPass,Toast.LENGTH_LONG).show();
                            }catch (FirebaseAuthInvalidUserException e){
                                Log.w("LoginFlow", "FirebaseAuthInvalidUserException");
                                Toast.makeText(LoginActivity.this,R.string.unkownUser,Toast.LENGTH_LONG).show();
                            }catch(Exception e){
                                Log.w("LoginFlow", e.getMessage());
                                Toast.makeText(LoginActivity.this,R.string.unkownError,Toast.LENGTH_LONG).show();
                            }finally{
                                returnResult(Activity.RESULT_CANCELED);
                            }
                        }
                    }
                });
    }

    private void returnResult(int code){
        setResult(code);
        finish();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(requestCode == SIGN_UP_REQUEST){
            if(resultCode == Activity.RESULT_OK){
                returnResult(Activity.RESULT_OK);
            }else{
                //Error trying to signUp
                //Notify user of the error
                Toast.makeText(LoginActivity.this,
                        R.string.signUpErrorNotify,Toast.LENGTH_SHORT).show();
            }
        }
    }
}
