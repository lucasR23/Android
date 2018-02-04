package com.lucas.alcoolgasolina;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity
{
    private EditText alcoolInput;
    private EditText gasolinaInput;
    private Button button;
    private TextView result;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        alcoolInput = (EditText) findViewById(R.id.alcool_InputId);
        gasolinaInput = (EditText) findViewById(R.id.gasolina_InputId);
        button = (Button)findViewById(R.id.buttonId);
        result = (TextView) findViewById(R.id.resultId);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                String alInput = alcoolInput.getText().toString();
                String gaInput = gasolinaInput.getText().toString();

                if(alInput.isEmpty()||gaInput.isEmpty())
                {
                    result.setText("Digite o preço do alcool e da gasolina");
                }
                else
                {
                    double alcoolPrice = Double.parseDouble(alInput);
                    double gasolinaPrice  = Double.parseDouble(gaInput);

                    double resultNum = alcoolPrice / gasolinaPrice;

                    if(resultNum >=0.7)
                    {
                        result.setText("È melhor utilizar Gasolina");
                    }
                    else
                    {
                        result.setText("È melhor utilizar Alcool");
                    }

                }
            }
        });
    }


}
