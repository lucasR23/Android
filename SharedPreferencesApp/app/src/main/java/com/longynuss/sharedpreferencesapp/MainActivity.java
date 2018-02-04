package com.longynuss.sharedpreferencesapp;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText inputName;
    private SharedPreferences sharedPreferences;
    private TextView message;
    private static final String ARQUIVO_PREFERENCIA = "ArquivoPreferencia";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sharedPreferences = getSharedPreferences(ARQUIVO_PREFERENCIA,0);

        inputName = findViewById(R.id.editText);
        Button saveButton = findViewById(R.id.button);
        message = findViewById(R.id.textView2);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                SharedPreferences.Editor editor = sharedPreferences.edit();

                String name = inputName.getText().toString();

                if(!name.equals("")){
                    editor.putString("nome",name).apply();
                    message.setText("ola, "+ name);
                }
            }
        });

        if(sharedPreferences.contains("nome")){
            String name = sharedPreferences.getString("nome","usuario indefinido");
            message.setText("ola, "+ name);
        }else{
            message.setText("ola, usuario não definido");
        }
    }
}
