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

public class activity_single_room_playing extends AppCompatActivity
{
    /***Area for variable declaration***/
    private int numX, numO;
    private int contGuessSinglePlayer = 0;
    private int[] randomNumber;
    private EditText inputSingle;
    private Button buttonGuess;

    private int[] numGuess;
    private String numGuessFullString;
    private int numGuessFullInteger;
    private boolean isNumberRight = true;
    private ListView list;
    private ArrayList<userGuess> guesses = new ArrayList<>();

    private ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_room_playing);

        /**Assigning the variables to the layout objects**/
        buttonGuess = (Button) findViewById(R.id.buttonGuess_Id);
        inputSingle = (EditText) findViewById(R.id.inputNumSingle_Id);

        list = (ListView) findViewById(R.id.ListOfGuesses);

        MakeRandom();

        adapter = new ListAdapter(activity_single_room_playing.this,guesses);
        list.setAdapter(adapter);
    }
    /**
     * Internal functions
     **/
    public void buttonGuessClicked(View v)
    {
        takeGuess();

        if(isNumberRight)
        {
            guesses = show();
            adapter.notifyDataSetChanged();
        }
    }

    private void MakeRandom()
    {
        int numero,error=0;
        boolean testNumber;

        Random makeRandomNumber = new Random();

        randomNumber = new int[4];

        for(int i=0;i<4;i++)
        {
            numero = makeRandomNumber.nextInt(9);
            testNumber = true;

            //have the number been used already ?
            //put the number in test mode
            while(testNumber)
            {
                //check if the generated number exits in the final number
                //if so point the error
                for(int j=0;j<4;j++)
                {
                    if(randomNumber[j]==numero)
                    {
                        error++;
                    }
                }

                //if there is an error in the generated number generates another one
                //and keep in test mode
                if(error>=1)
                {
                    testNumber=true;
                    error=0;

                    numero = makeRandomNumber.nextInt(9);
                }

                //if no error occurred then go out of the test mode
                else
                {
                    testNumber=false;
                    error=0;
                }
            }

            //add the number that was tested to the final number não
            randomNumber[i] = numero;
        }
    }


    public void takeGuess()
    {
        //take the number from input text and cast to int
        numGuess = new int[4];

        // check if number is emptty
        if (!(inputSingle == null) && (inputSingle.length()==4))
        {
            //the number is probably right
            isNumberRight = true;

            numGuessFullString = inputSingle.getText().toString();
            numGuessFullInteger = Integer.parseInt(numGuessFullString);

           //separate numGuessFull in one Array
            numGuess[0] = numGuessFullInteger / 1000;
            numGuess[1] = (numGuessFullInteger % 1000) / 100;
            numGuess[2] = ((numGuessFullInteger % 1000) % 100) / 10;
            numGuess[3] = ((numGuessFullInteger % 1000) % 100) % 10;

            //check if there are repated digits
            for (int num1 = 0; num1 < 4; num1++)
            {
                for (int num2 = 0; num2 < 4; num2++)
                {
                    if (numGuess[num1] == numGuess[num2] && num1 != num2)
                    {
                        isNumberRight = false;
                    }
                }
                num1++;
            }
        }
        else
        {
            isNumberRight = false;
        }

        //check if the number is right
        if (isNumberRight)
        {
            findXandO();
            inputSingle.setText("");
        }
        else
        {
            Toast.makeText(getApplicationContext(), "numero invalido", Toast.LENGTH_LONG).show();
            inputSingle.setText("");
        }
    }

    private void findXandO()
    {
        numO = 0;
        numX = 0;

        contGuessSinglePlayer++;

        //find X
        for (int num1 = 0; num1 < 4; num1++)
        {
            for (int num2 = 0; num2 < 4; num2++)
            {
                if (numGuess[num1] == randomNumber[num2] && num1 != num2)
                {
                    numX++;
                }
            }
        }

        //find O
        for (int num = 0; num < 4; num++)
        {
            if (numGuess[num] == randomNumber[num])
            {
                numO++;
            }
        }

        if (numO == 4)
        {
            startActivity(new Intent(activity_single_room_playing.this, YouWin.class));

        }
        //numeroO.setText("O" + numO);
        // numeroX.setText("X" + numX);
    }

    private ArrayList<userGuess> show()
    {
        userGuess g = new userGuess(numGuessFullString, R.drawable.circulo, R.drawable.orangex,R.drawable.orangex,R.drawable.orangex);
        guesses.add(g);
        return guesses;
    }
}







