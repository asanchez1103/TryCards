package com.example.alejandro.trycards;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.io.*;
import java.util.Scanner;


public class MainActivity extends Activity {




    private String readFromFile() {

        String ret = "";

        try {
            InputStream inputStream = openFileInput("redapples.txt");

            if ( inputStream != null ) {
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                String receiveString = "";
                StringBuilder stringBuilder = new StringBuilder();

                while ( (receiveString = bufferedReader.readLine()) != null ) {
                    stringBuilder.append(receiveString);
                }

                inputStream.close();
                ret = stringBuilder.toString();
            }
        }
        catch (FileNotFoundException e) {
            Log.e("login activity", "File not found: " + e.toString());
        } catch (IOException e) {
            Log.e("login activity", "Can not read file: " + e.toString());
        }

        return ret;
    }

    private String readTxt(){

        InputStream inputStream = getResources().openRawResource(R.raw.redapples);
        System.out.println(inputStream);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

        int i;
        try {
            i = inputStream.read();
            while (i != -1)
            {
                byteArrayOutputStream.write(i);
                i = inputStream.read();
            }
            inputStream.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return byteArrayOutputStream.toString();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//Parsing attempt

         CardClass[] redCards = new CardClass[777];//Change number according to lines/proper nouns in redApples.txt
         CardClass[] greenCards = new CardClass[249];//Change number according to lines/adjectives in greenApples.txt


        ReadWithScanner redParse = new ReadWithScanner("C:\\Users\\Alejandro\\Desktop\\TryCards\\app\\assets\\redApples.txt", 1, "", "", 0, false);
        ReadWithScanner greenParse = new ReadWithScanner("C:\\Users\\Alejandro\\Desktop\\TryCards\\app\\assets\\greenApples.txt", 1, "", "", 0, false);

            //TODO: create instances of nonstatic Readwith Scanner, try to get CardClass non-static

            String nameInfoR[][] = new String[777][2];
            String nameInfoG[][] = new String[249][2];
        try {
            nameInfoR = redParse.processLineByLine(nameInfoR);
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < redCards.length; i++) {
                redCards[i] = new CardClass();
                redCards[i].setID(i + 1);
                redCards[i].setName(nameInfoR[i][0]);
                redCards[i].setInfo(nameInfoR[i][1]);
                redCards[i].setHolder(0);
                redCards[i].setFlip(false);
               System.out.print("Red Card # " + Integer.toString(i + 1) + "= \n" + redCards[i].cardName + "\n" + redCards[i].cardInfo + "\n");
            }
        try {
            nameInfoG = greenParse.processLineByLine(nameInfoG);
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (int j = 0; j < greenCards.length; j++) {
                greenCards[j] = new CardClass();
                greenCards[j].setID(j + 1);
                greenCards[j].setName(nameInfoG[j][0]);
                greenCards[j].setInfo(nameInfoG[j][1]);
                greenCards[j].setHolder(0);
                greenCards[j].setFlip(false);
                System.out.print("Green Card # " + Integer.toString(j + 1) + "= \n" + greenCards[j].cardName + "\n" + greenCards[j].cardInfo + "\n");
            }

            System.out.print("Done.");

        // end parsing attempt

        //parent
        LinearLayout parentLayout=(LinearLayout)findViewById(R.id.mainlayout); //activity_main layout


        LayoutInflater layoutInflater = getLayoutInflater();
        View view;

        for (int i = 0; i < 1; i++){
            // add text layout to parent

            //view = layoutInflater.inflate(R.layout.image_layout, parentLayout, false);
            //ImageView imageView = (ImageView)view.findViewById((R.id.image));
            //parentLayout.addView(imageView);

            view = layoutInflater.inflate(R.layout.card_label,parentLayout,false);
            TextView labelTextView = (TextView) view.findViewById((R.id.cardlabel));
             labelTextView.setText(redCards[4].cardName);
            parentLayout.addView(labelTextView);

            view = layoutInflater.inflate(R.layout.card_desc, parentLayout, false);
            TextView descTextView = (TextView)view.findViewById((R.id.carddesc));
            descTextView.setText(redCards[4].cardInfo);
            parentLayout.addView(descTextView);
        }
    }
}
