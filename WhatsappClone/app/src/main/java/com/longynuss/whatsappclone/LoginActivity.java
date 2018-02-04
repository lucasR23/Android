package com.longynuss.whatsappclone;

import android.Manifest;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

//    private EditText signupPhoneNumber;
//    private EditText signupName;

//    private MyPreferences preferences;
//    private final String standandMessage = "Seu codigo de confirmação do Whatsapp é: ";

//    private final String[] permissions = {
//          Manifest.permission.SEND_SMS,
//          Manifest.permission.INTERNET
//    };

    private EditText emailInput;
    private EditText passInput;
    private Button signinButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Validate permissions and instanciate MyPreferences
//        Permissions.validatePermissions(this,permissions,1);
//        preferences = new MyPreferences(LoginActivity.this);

        //old views
//        signupPhoneNumber = findViewById(R.id.phoneNumber);
//        signupName = findViewById(R.id.name);


        //Apply mask to edittext
//        SimpleMaskFormatter smf = new SimpleMaskFormatter("+NN NN NNNNN-NNNN");
//        MaskTextWatcher mtw = new MaskTextWatcher(signupPhoneNumber, smf);
//        signupPhoneNumber.addTextChangedListener(mtw);

        //show what was save with MyPreference class
//        HashMap<String,String> user = preferences.getUserData();
//        Toast.makeText(getApplicationContext(),user.get("name"),Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),user.get("phone"),Toast.LENGTH_SHORT).show();
//        Toast.makeText(getApplicationContext(),user.get("token"),Toast.LENGTH_SHORT).show();

        emailInput = findViewById(R.id.emailSignUp);
        passInput = findViewById(R.id.passSignUp);
        signinButton = findViewById(R.id.signinButton);

        signinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sign in
            }
        });
    }

    private boolean SendSMS(String phone, String message) {
        try{
            SmsManager smsManager = SmsManager.getDefault();
            smsManager.sendTextMessage(phone,null,message,null,null);
            return true;
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        for (int result : grantResults) {
            Toast.makeText(LoginActivity.this,"Você precisa aceitar a permissão para prosseguir",Toast.LENGTH_SHORT).show();
        }
    }

    public void onSignUpTextClicked(View view){
        Intent intent = new Intent(LoginActivity.this,SignUp.class);
        startActivity(intent);
        finish();
    }
}

//send SMS token to validation
//                String name = signupName.getText().toString();
//                String phone = signupPhoneNumber.getText().toString();
//
//                phone = phone.replace(" ","");
//                phone = phone.replace("+","");
//                phone = phone.replace("-","");
//
//                Random random = new Random();
//                String token = String.valueOf(random.nextInt(9000 - 1000)+1000);
//
//                preferences.saveUserPreferences(name , phone , token);
//
//                String message = standandMessage + token;
//                boolean messageSent = SendSMS("+"+phone,message);
//
//                if (messageSent) {
//                    Toast.makeText(LoginActivity.this,token,Toast.LENGTH_SHORT).show();
//                    Intent intent = new Intent(LoginActivity.this,ValidationActivity.class);
//                    startActivity(intent);
//                    finish();
//                }else{
//                    Toast.makeText(LoginActivity.this,"Falied to send",Toast.LENGTH_SHORT).show();
//                }