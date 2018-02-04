package com.longynuss.guess;

import android.widget.ImageView;

/**
 * Created by allan on 09/05/2017.
 */

public class userGuess {

    private String guessUser;
    private int image1;
    private int image2;
    private int image3;
    private int image4;

    public userGuess(String userGuess, int image1, int image2, int image3, int image4) {
        this.guessUser = userGuess;
        this.image1 = image1;
        this.image2 = image2;
        this.image3 = image3;
        this.image4 = image4;
    }

    public userGuess() {


    }



    public String getUserGuess() {
        return guessUser;
    }

    public void setUserGuess(String userGuess) {
        this.guessUser = userGuess;
    }

    public int getImage1() {

        return image1;
    }

    public void setImage1(int image1) {
        this.image1 = image1;
    }

    public int getImage2() {
        return image2;
    }

    public void setImage2(int image2) {
        this.image2 = image2;
    }

    public int getImage3() {
        return image3;
    }

    public void setImage3(int image3) {
        this.image3 = image3;
    }

    public int getImage4() {
        return image4;
    }

    public void setImage4(int image4) {
        this.image4 = image4;
    }
}


