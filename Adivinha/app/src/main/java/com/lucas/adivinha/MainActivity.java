package com.lucas.adivinha;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private Button buttonPlay;
    private TextView textResult;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        buttonPlay = (Button)findViewById(R.id.buttonPlayId);
        textResult = (TextView)findViewById(R.id.resultId);

        buttonPlay.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Random random = new Random();

                textResult.setText("Numero escolhido: "+random.nextInt(11));
            }
        });

    }
}
