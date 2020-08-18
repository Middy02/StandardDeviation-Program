package com.company;

import static com.company.ProjConstants.*;
import java.util.*;


public class BasicStatistics extends Main {

    //Declaring all field variables for Mean_Median class
    private static double maxNum = 0;

    private int intMax = 1;
    public int getIntMax() {
        return intMax;
    }

    private int intMax2 = 1;
    public int getIntMax2() {
        return intMax2;
    }

    private double[] IntegerArray = new double[MAXDATA];
    public double[] getIntegerArray() {
        return IntegerArray;
    }

    private double[] ModeData = new double[MAXDATA];
    public double[] getModeData() {
        return ModeData;
    }

    private static int ArrayPos = getArrayPos();
    private static double Mean = 0;
    private static double variance = 0;


    public void init() {

        //For loop to assign -1 to all array positions.
        for (ArrayPos = 0; ArrayPos < MAXDATA; ArrayPos++) {
            IntegerArray[ArrayPos] = INVALID_INT;
            ModeData[ArrayPos] = INVALID_INT;
        }
    }//end init method


    public void addDataItem (int newItem){

        boolean firstNum = false;

        for (ArrayPos = 0; ArrayPos < intMax; ArrayPos++) {

            if (Objects.equals(IntegerArray[0], INVALID_INT)) {
                IntegerArray[ArrayPos] = newItem;
                firstNum = true;
            }//end if statement
        }//end for loop

        if (!firstNum){
            IntegerArray[ArrayPos] = newItem;
            intMax++;
        }//end if statement
    }//end addDataItem method


    public void chkMaxInt(){

        double tempMaxNum = 0;
        //Checking for max input number.
        for (ArrayPos = 0; ArrayPos < intMax; ArrayPos++) {
            if (IntegerArray[ArrayPos] < IntegerArray[ArrayPos + 1]) {
                tempMaxNum = IntegerArray[ArrayPos + 1] + 1;
            }
            if (tempMaxNum > maxNum){
                maxNum = tempMaxNum;
            }
        }//end for loop

        //Setting all empty elements in the array to the maxNum for sorting.
        for (ArrayPos = 0; ArrayPos < MAXDATA; ArrayPos++) {
            if (IntegerArray[ArrayPos] == INVALID_INT) {
                IntegerArray[ArrayPos] = maxNum;
            }
        }//end for loop
    }//end chkMaxInt method


    public double calcAverage(){

        //Mean (average)
        intMax = 1;

        for (ArrayPos = 0; ArrayPos < intMax; ArrayPos++) {
            if (IntegerArray[ArrayPos] != maxNum) {
                Mean = Mean + IntegerArray[ArrayPos];
                intMax++;
            }
        }
        intMax--; //subtract 1 because it starts at 1

        Mean = Mean / intMax;

        return Mean;
    }//end calcAverage


    public double calcMedian() {

        double Median;
        //Median
        if (intMax % 2 == 0) { //Checks for even number of integers
            int Median2 = intMax / 2;
            Median = (IntegerArray[Median2] + IntegerArray[Median2 - 1]);
            Median = Median / 2;
        } else {
            int ArrayPos = intMax / 2;
            Median = IntegerArray[ArrayPos];
        }
        return Median;
    }//end calcMedian


    public void calcMode(){

        int ArrayPos2;
        double mode = 0;
        double frequency = 0;

        for (ArrayPos = 0; ArrayPos < intMax; ArrayPos++){

            double tempMode = IntegerArray[ArrayPos];
            double tempFrequency = 0;

            for (ArrayPos2 = 0; ArrayPos2 < intMax; ArrayPos2++){
                 if (IntegerArray[ArrayPos2] == tempMode){
                     tempFrequency++;
                 }
                 if (tempFrequency > frequency){
                     frequency = tempFrequency;
                 }
            }//end for loop
        }//end for loop

        int element = 0;

        for (ArrayPos = 0; ArrayPos < intMax; ArrayPos++){

            double tempMode = IntegerArray[ArrayPos];
            double tempFrequency = 0;

            if (tempMode != mode){

                for (ArrayPos2 = 0; ArrayPos2 < intMax; ArrayPos2++){
                    if (IntegerArray[ArrayPos2] == tempMode){
                        tempFrequency++;
                    }
                }//end for loop

                if (tempFrequency == frequency){
                    mode = tempMode;
                    ModeData[element] = mode;
                    intMax2++;
                    element++;
                }

            }//end tempMode != mode if statement
        }//end for loop
    }//end calcMode method


    public double calcRangeMax(){

       //the range max can be found by taking maxNum and subtracting by 1
        return maxNum - 1;
    }//end calcRangeMax method


    public double calcRangeMin(){

        //the range min can be found by taking the first element in the IntegerArray after the array ahs been sorted
        return IntegerArray[0];
    }//end calcRangeMin method


    public double calcVariance() {

        //Calculate variance
        for (ArrayPos = 0; ArrayPos < intMax; ArrayPos++){
            variance = variance + Math.pow(IntegerArray[ArrayPos] - Mean, 2);
        }

        int varDenom = intMax - 1;
        variance = variance / varDenom;

        return variance;
    }//end calcVariance method


    public double calcStandardDeviation(){

        //the standard deviation can be found by taking the square root of the variance
        return Math.sqrt(variance);
    }//end calcStandardDeviation method

}//end BasicStatistics class
