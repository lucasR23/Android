package longynuss.com.whatsappclone.activity;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import longynuss.com.whatsappclone.R;
import longynuss.com.whatsappclone.config.FirebaseConfig;
import longynuss.com.whatsappclone.model.User;

public class SignupActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;

    private EditText edtNameSignUp;
    private EditText edtEmailSignUp;
    private EditText edtPassSignUp;
    private User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_actiity);

        user = new User();

        //Firebase instances
        firebaseAuth = FirebaseConfig.getFirebaseAuth();

        //Screen elements
        edtNameSignUp = findViewById(R.id.editText_NameSignUp);
        edtEmailSignUp = findViewById(R.id.editText_EmailSignUp);
        edtPassSignUp =  findViewById(R.id.editText_passSignup);

        Button btnSignUp = findViewById(R.id.btn_signUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = edtNameSignUp.getText().toString();
                String email = edtEmailSignUp.getText().toString();
                String pass = edtPassSignUp.getText().toString();

                if(!email.isEmpty() && !pass.isEmpty() && !name.isEmpty()){
                    user.setName(name);
                    user.setEmail(email);
                    user.setPassword(pass);

                    createUserWithEmail(email,pass);
                }else{
                    //Notify user - Some fields are blank
                    Toast.makeText(SignupActivity.this,R.string.emptyEditTextNotify,
                                                            Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void createUserWithEmail(final String email, final String password){
        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful() && task.getResult() != null) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d("LoginFlow", "createUserWithEmail:success");

                            FirebaseUser fuser = task.getResult().getUser();
                            user.setId(fuser.getUid());
                            user.save();

                            loginUserWithEmail(email,password);
                        } else {
                            // If sign in fails, display a message to the user.
                            Exception exception = task.getException();
                            Log.w("LoginFlow", "createUserWithEmail:failure", exception);

                            try {
                                if (exception!=null) {
                                    throw exception;
                                }
                            } catch(FirebaseAuthWeakPasswordException e) {
                                Log.w("LoginFlow", "FirebaseAuthWeakPasswordException");
                                Toast.makeText(SignupActivity.this,R.string.weakPass,Toast.LENGTH_LONG).show();
                            } catch(FirebaseAuthInvalidCredentialsException e) {
                                Log.w("LoginFlow", "FirebaseAuthInvalidCredentialsException");
                                Toast.makeText(SignupActivity.this,R.string.invalidEmail,Toast.LENGTH_LONG).show();
                            } catch(FirebaseAuthUserCollisionException e) {
                                Log.w("LoginFlow", "FirebaseAuthUserCollisionException");
                                Toast.makeText(SignupActivity.this,R.string.collisionEmail,Toast.LENGTH_LONG).show();
                            } catch(Exception e) {
                                Log.w("LoginFlow", e.getMessage());
                                Toast.makeText(SignupActivity.this,R.string.unkownError,Toast.LENGTH_LONG).show();
                            }finally {
                                returnResult(Activity.RESULT_CANCELED);
                            }

                        }
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
                            Log.w("LoginFlow", "signInWithEmail:failure");
                            returnResult(Activity.RESULT_CANCELED);
                        }
                    }
                });
    }

    private void returnResult(int code){
        setResult(code);
        finish();
    }
}
