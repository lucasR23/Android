package com.longynuss.whatsappclone;

import android.preference.Preference;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.longynuss.helper.MyPreferences;

import java.util.HashMap;

public class ValidationActivity extends AppCompatActivity {

    private EditText tokenInput;
    private Button validationButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_validation);

        tokenInput = findViewById(R.id.tokenValidationInput);
        validationButton = findViewById(R.id.validationButton);

        validationButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                MyPreferences preferences = new MyPreferences(ValidationActivity.this);

                HashMap<String,String> user = new HashMap<>();
                user = preferences.getUserData();

                String tokenTyped = tokenInput.getText().toString();

                if (tokenTyped.equals(user.get("token"))){
                    Toast.makeText(ValidationActivity.this,"Token Correto",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(ValidationActivity.this,"Erro na validação",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
