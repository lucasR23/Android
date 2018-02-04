package com.longynuss.mynotes;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private static final String FILE_NAME = "file.txt";

    private EditText textField;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textField = findViewById(R.id.editText2);
        btnSave = findViewById(R.id.button);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = textField.getText().toString();

                if(!text.equals("")){
                    WriteInFile(text,FILE_NAME);
                }
        }});

        String textSaved = ReadOfFile(FILE_NAME);
        if(textSaved!=null){
            textField.setText(textSaved);
        }

    }

    private void WriteInFile(String text, String name){
        try {
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(openFileOutput(name, Context.MODE_PRIVATE));
            outputStreamWriter.write(text);
            outputStreamWriter.close();
        }catch (IOException e){
            Log.v("MainAcctivity", e.toString());
        }
    }

    private String ReadOfFile(String name){
        String result="", line="";
        try {
            InputStream file = openFileInput(name);
            if(file != null){
                InputStreamReader inputStreamReader = new InputStreamReader(file);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                while((line = bufferedReader.readLine()) != null){
                    result += line;
                }

                file.close();
            }
        }catch (IOException e){
            Log.v("MainAcctivity", e.toString());
        }

        return result;
    }
}
