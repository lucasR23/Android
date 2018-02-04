package com.lucas.frasesdodia;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity
{
    private Button button;
    private TextView text;
    private String[] sentences = {
        "Enquanto houver vontade de lutar haverá esperança de vencer.",
        "Tudo o que um sonho precisa para ser realizado é alguém que acredite que ele possa ser realizado.",
        "O importante não é vencer todos os dias, mas lutar sempre.",
        "Não deixe que as pessoas te façam desistir daquilo que você mais quer na vida. Acredite. Lute. Conquiste. E acima de tudo, seja feliz!",
        "É necessário ter o caos cá dentro para gerar uma estrela."};

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        button = (Button) findViewById(R.id.buttonId);
        text = (TextView) findViewById(R.id.TextId);

        button.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                Random random = new Random();
                text.setText(sentences[random.nextInt(sentences.length)]);
            }
        });
    }
}
