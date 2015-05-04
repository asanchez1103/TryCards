package com.example.alejandro.trycards;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.view.Menu;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.io.*;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Parsing attempt (from main of ReadWithScanner.java)
        int numRedCards = 777;
        int numGreenCards = 249;
        int cardID = 10; // ID's start at 10 to avoid confusing with holder. Red cards have ID < 1000 Green cards have ID > 1000

        ReadWithScanner redParse = new ReadWithScanner(this, R.raw.redapples,1,"","",0,false);
        ReadWithScanner greenParse = new ReadWithScanner(this, R.raw.greenapples,1,"","",0,false);

        CardClass redCards[] = new CardClass[numRedCards];
        CardClass greenCards[] = new CardClass[numGreenCards];

        String nameInfoR[][] = new String[numRedCards][2];
        String nameInfoG[][] = new String[numGreenCards][2];

        RelativeLayout rl;
        Button b;
        FrameLayout frame;
        TextView t1;
        RelativeLayout.LayoutParams param1, param2;




        try {
            nameInfoR = redParse.processLineByLine(nameInfoR);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for(int i = 0; i< redCards.length; i++){
            redCards[i] = new CardClass();
            redCards[i].setID(cardID);
            redCards[i].setName(nameInfoR[i][0]);
            redCards[i].setInfo(nameInfoR[i][1]);
            redCards[i].setHolder(0);
            redCards[i].setFlip(false);
            System.out.print("Red Card #" + Integer.toString(i+1) + ":    ID: " + redCards[i].cardID +  "\nNAME: " + redCards[i].cardName + "\nDESC: " + redCards[i].cardInfo +"\n");
            cardID++;
        }
        try {
            nameInfoG = greenParse.processLineByLine(nameInfoG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cardID = 1000; //green card ID start at 1000
        for(int j =0; j<greenCards.length; j++){
            greenCards[j] = new CardClass();
            greenCards[j].setID(cardID);
            greenCards[j].setName(nameInfoG[j][0]);
            greenCards[j].setInfo(nameInfoG[j][1]);
            greenCards[j].setHolder(0);
            greenCards[j].setFlip(false);
            System.out.print("Green Card # " + Integer.toString(j+1) +":    ID: " + greenCards[j].cardID + " \nNAME: " + greenCards[j].cardName + "\nDESC: " + greenCards[j].cardInfo +"\n");
            cardID++;
        }
        System.out.print("Done.");
        // end parsing

        //parent
        LinearLayout parentLayout=(LinearLayout)findViewById(R.id.mainlayout); //activity_main layout


        LayoutInflater layoutInflater = getLayoutInflater();
        View view;




        for (int i = 0; i < 100; i++){
            // add text layout to parent



            view = layoutInflater.inflate(R.layout.card_holder, parentLayout, false);
            RelativeLayout relativeLayout = (RelativeLayout)view.findViewById((R.id.cardholder));
            parentLayout.addView(relativeLayout);

            TextView labelTextView = (TextView) view.findViewById((R.id.cardlabel));
             labelTextView.setText(redCards[i+9].cardName);
            //parentLayout.addView(labelTextView);

            //view = layoutInflater.inflate(R.layout.card_desc, parentLayout, false);
            TextView descTextView = (TextView)view.findViewById((R.id.carddesc));
            descTextView.setText(redCards[i+9].cardInfo);
           // parentLayout.addView(descTextView);
        }
    }
}
