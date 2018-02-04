package com.longynuss.guess;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

public class activity_single_room_playing extends AppCompatActivity {
    /***Area for variable declaration***/
    // int i
    int numX, numO;
    int contGuessSinglePlayer = 0;
    Random testRandomNumber = new Random();
    int[] randomNumber;
    EditText inputSingle;
    Button buttonGuess;
    Button aux00, aux01, aux02, aux03, aux04, aux05, aux06, aux07, aux08, aux09;
    int[] numGuess;
    String numGuessFullString;
    int numGuessFullInteger;
    boolean isNumberRight = true;
    ListView list;
    ArrayList<userGuess> guesses = new ArrayList<userGuess>();
    /**
     * variaveis testes apagar depois
     **/
    TextView testeNumeroAleatorio;
    TextView numeroX;
    TextView numeroO;
    String acum = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_room_playing);

        /**Assigning the variables to the layout objects**/
        buttonGuess = (Button) findViewById(R.id.buttonGuess_Id);
        inputSingle = (EditText) findViewById(R.id.inputNumSingle_Id);
        testeNumeroAleatorio = (TextView) findViewById(R.id.testeNumeroAleatorio);
        list = (ListView) findViewById(R.id.ListOfGuesses);

        // numeroX = (TextView) findViewById(R.id. numerosX);
        //  numeroO= (TextView) findViewById(R.id. numerosO);

        aux00 = (Button) findViewById(R.id.button00);
        aux01 = (Button) findViewById(R.id.button01);
        aux02 = (Button) findViewById(R.id.button02);
        aux03 = (Button) findViewById(R.id.button03);
        aux04 = (Button) findViewById(R.id.button04);
        aux05 = (Button) findViewById(R.id.button05);
        aux06 = (Button) findViewById(R.id.button06);
        aux07 = (Button) findViewById(R.id.button07);
        aux08 = (Button) findViewById(R.id.button08);
        aux09 = (Button) findViewById(R.id.button09);
        /**End**/

        // comNumber=new int [4];
        //numGuess = new int[4];
        //  randomArray = new int[10];

        MakeRandom();
        testeNumeroAleatorio.setText(acum);

        ArrayAdapter adapter = new ListAdapter(activity_single_room_playing.this,guesses);
        list.setAdapter(adapter);

        buttonGuess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(inputSingle!=null){takeGuess();
                    guesses =show();
                }




                //show();
            }
        });
    }
    /**
     * Internal functions
     **/
    private void MakeRandom() {
        randomNumber = new int[4];
        int numero;

        for (int i = 0; i < 4; i++) {
            numero = testRandomNumber.nextInt(9);
            for (int j = 0; j < 4; j++) {
                if (numero == randomNumber[j] && j != i) {
                   // numero = testRandomNumber.nextInt(9);
                    j=0;
                } else {
                    randomNumber[i] = numero;
                }
            }
            acum = acum + randomNumber[i];
        }
    }
    public void takeGuess() {

        //take the number from input text and cast to int
        numGuess = new int[4];
        numGuessFullString = inputSingle.getText().toString();
        numGuessFullInteger = Integer.parseInt(numGuessFullString);

        // check if number is emptty

        //check if the number has 4 digits
        if (inputSingle.length() == 4) {
            //separate numGuessFull in one Array
            numGuess[0] = numGuessFullInteger / 1000;
            numGuess[1] = (numGuessFullInteger % 1000) / 100;
            numGuess[2] = ((numGuessFullInteger % 1000) % 100) / 10;
            numGuess[3] = ((numGuessFullInteger % 1000) % 100) % 10;

            //check if there are repated digits
            for (int num1 = 0; num1 < 4; num1++) {
                for (int num2 = 0; num2 < 4; num2++) {
                    if (numGuess[num1] == numGuess[num2] && num1 != num2) {
                        isNumberRight = false;
                    }
                }
                num1++;
            }
        } else {
            isNumberRight = false;

        }



        //check if the number is right
        if (isNumberRight) {
            findXandO();

            inputSingle.setText("");
        } else {

            Toast.makeText(getApplicationContext(), "numero invalido", Toast.LENGTH_LONG).show();
            inputSingle.setText("");
            isNumberRight = true;
        }

    }

    private void findXandO() {
        numO = 0;
        numX = 0;

        contGuessSinglePlayer++;

        //find X
        for (int num1 = 0; num1 < 4; num1++) {
            for (int num2 = 0; num2 < 4; num2++) {
                if (numGuess[num1] == randomNumber[num2] && num1 != num2) {
                    numX++;
                }
            }
        }

        //find O
        for (int num = 0; num < 4; num++) {
            if (numGuess[num] == randomNumber[num]) {
                numO++;
            }
        }

        if (numO == 4) {
            startActivity(new Intent(activity_single_room_playing.this, YouWin.class));

        }
        //numeroO.setText("O" + numO);
        // numeroX.setText("X" + numX);
    }

    private ArrayList<userGuess> show() {

        userGuess g = new userGuess(numGuessFullString, R.drawable.circulo, R.drawable.orangex,R.drawable.orangex,R.drawable.orangex);
         guesses.add(g);
         return guesses;
    }
}







