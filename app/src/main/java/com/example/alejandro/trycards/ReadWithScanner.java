package com.example.alejandro.trycards;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Xml;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;


public class ReadWithScanner extends CardClass {



    public ReadWithScanner(String aFileName, int startId, String theName, String theInfo, int theHolder, boolean pubYet) {
        super(startId, theName, theInfo, theHolder, pubYet);
        File myFile = new File(aFileName);
        myFilePath = myFile.getPath();
        //add cardclass object?
    }
    /**
     * Template method that calls {@link //#processLine(String)}.
     */
    @TargetApi(Build.VERSION_CODES.KITKAT)
    public String[][] processLineByLine(String[][] emptyArr) throws IOException {

        //String theFilePath = myFilePath.getPath();
       // try (Scanner scanner = new Scanner(myFilePath, ENCODING.name) {
           try{
               BufferedReader br;
               br = new BufferedReader(new InputStreamReader(new FileInputStream(myFilePath)));
               int loc = 0;
            String line; // new
            String[] currArr = new String[2];
          //  while (scanner.hasNextLine()){
            while((line = br.readLine()) !=null){
                currArr = processLine(line, currArr);
                emptyArr[loc][0] = currArr[0];
                emptyArr[loc][1] = currArr[1];
                loc++;
            }
               br.close();
           } catch (IOException e) {
               // TODO Auto-generated catch block
               e.printStackTrace();
           }

        return emptyArr;
    }


    protected String[] processLine(String aLine, String[] strArr) {
        //use a second Scanner to parse the content of each line
        Scanner sep = new Scanner(aLine);
        sep.useDelimiter("::");
        if (sep.hasNext()) {
            //assumes the line has a certain structure
            String name = sep.next();
            String info = sep.next();
            strArr[0] = name;
            strArr[1] = info;
            return strArr;
        } else {
            strArr[0] = "";
            strArr[1] = "";
            return strArr;
        }
    }

    // PRIVATE
    public String myFilePath;
    //private final static Charset ENCODING = StandardCharsets.UTF_8;

    private static void log(Object aObject) {
        System.out.println(String.valueOf(aObject));
    }
}

