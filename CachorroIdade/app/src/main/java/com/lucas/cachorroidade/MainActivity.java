package com.lucas.cachorroidade;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private TextView resultText;
    private EditText inputText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultText = (TextView) findViewById(R.id.resultId);
        inputText = (EditText) findViewById(R.id.inputTextId);
        button = (Button) findViewById(R.id.buttonId);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputText.getText().toString();

                if (input.isEmpty()) {
                    resultText.setText("Nenhum numero digitado !");
                } else {
                    resultText.setText("A idade do cachorro em anos humanos é: " + (Integer.parseInt(input) * 7) + " Anos");
                }
            }
        });


    }
}