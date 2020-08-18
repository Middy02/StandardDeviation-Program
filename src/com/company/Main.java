package com.company;

import static com.company.ProjConstants.*;
import java.io.*;
import java.util.*;


public class Main {

    private static String NO_FILE_NAME = "INVALID FILE NAME";
    private static int ArrayPos;
    public static int getArrayPos() {
        return ArrayPos;
    }

    private static void displayResults(BasicStatistics CalcSD, double theMean, double theMedian, double theVariance, double theStandardDeviation, double minRange, double maxRange){

        System.out.println("\nThe mean of all the integers in the file is: " + theMean);
        System.out.println("The median of all the integers in the file is: " + theMedian);

        boolean firstMode = false;

        if (CalcSD.getModeData()[1] != INVALID_INT){
            System.out.print("The modes of all the integers in the file are: ");
            for (ArrayPos = 0; ArrayPos < CalcSD.getIntMax2() - 1; ArrayPos++){
                if (!firstMode){
                    System.out.print(CalcSD.getModeData()[ArrayPos]);
                    firstMode = true;
                }
                else {
                    System.out.print(", " + CalcSD.getModeData()[ArrayPos]);
                }
            }//end for loop
        }
        else {
            System.out.print("The mode of all the integers in the file is: " + CalcSD.getModeData()[0]);
        }

        System.out.println("\nThe range of all the integers in the file is: " + minRange + " - " + maxRange);
        System.out.println("The variance of all the integers in the file is: " + theVariance);
        System.out.println("The standard deviation of all the integers in the file is: " + theStandardDeviation);

    }//end displayResults method


    public static void main(String[] args) {

        BasicStatistics CalcSD = new BasicStatistics(); //Create new object to access the BasicStatistics class

        //Variables
        boolean FileDone = true;
        String FileName;
        int ElementPos = 0;
        Scanner scanUserFile = new Scanner(System.in);

        int howManyDataItems = INVALID_INT;
        double theMean = INVALID_INT;
        double theMedian = INVALID_INT;
        double theRangeMin = INVALID_INT;
        double theRangeMax = INVALID_INT;
        double theVariance = INVALID_INT;
        double theStandardDeviation = INVALID_INT;


        CalcSD.init(); //calls init method from BasicStatistics class

        //Read Input Text
        System.out.println("Enter the file pathname below");
        FileName = scanUserFile.next();

        //Try and find file name
        try {
            File f = new File(FileName);
            scanUserFile = new Scanner(f);

            while (scanUserFile.hasNextInt()) {
                int NextInt = scanUserFile.nextInt();

                CalcSD.addDataItem(NextInt); //calls addDataItem method from Mean_Median class

                if (CalcSD.getIntMax() == MAXDATA) {
                    System.out.println("\nThere is not enough storage space in the array to store all of the integers in the file, consider making the array larger.");
                    FileDone = false;
                    break;
                }
            }//end while loop

            scanUserFile.close();

            if (FileDone){
                CalcSD.chkMaxInt();
            }

            System.out.println("\nArray Contents:");
            System.out.println("---------------------------------------------------------------------------------");

            Arrays.sort(CalcSD.getIntegerArray());

            //For loop to print elements to screen.
            for (ArrayPos = 0; ArrayPos < CalcSD.getIntMax() - 1; ArrayPos++) {
                System.out.println("The element at position " + ElementPos + " in the integer array is: " + CalcSD.getIntegerArray()[ArrayPos]);
                ElementPos++;
            }//end for loop

            System.out.println("---------------------------------------------------------------------------------");

        }//end try
        catch (FileNotFoundException e) {
            System.out.println("\n" + NO_FILE_NAME + "\n" + e);
            e.printStackTrace();
        }//end catch

        howManyDataItems = CalcSD.getIntMax();
        theMean = CalcSD.calcAverage();
        theMedian = CalcSD.calcMedian();
        CalcSD.calcMode();
        theRangeMin = CalcSD.calcRangeMin();
        theRangeMax = CalcSD.calcRangeMax();
        theVariance = CalcSD.calcVariance();
        theStandardDeviation = CalcSD.calcStandardDeviation();


        //If any calculations fail, it will display an error message.

        if (howManyDataItems != INVALID_INT || howManyDataItems == 1){
            if (theMean != INVALID_INT){
                if (theMedian != INVALID_INT){
                    if (theRangeMin != INVALID_INT){
                        if (theRangeMax != INVALID_INT){
                            if (theVariance != INVALID_INT){
                                if (theStandardDeviation != INVALID_INT){

                                    displayResults(CalcSD, theMean, theMedian, theVariance, theStandardDeviation, theRangeMin, theRangeMax);

                                }//end theStandardDeviation if statement
                                else {
                                    System.out.println("ERROR Standard Deviation: NO STANDARD DEVIATION Calculated");
                                }
                            }//end theVariance if statement
                            else {
                                System.out.println("ERROR Variance: NO VARIANCE Calculated");
                            }
                        }//end theRangeMax if statement
                        else {
                            System.out.println("ERROR Range: NO RANGE MAXIMUM Calculated");
                        }
                    }//end theRangeMin if statement
                    else {
                        System.out.println("ERROR Range: NO RANGE MINIMUM Calculated");
                    }
                }//end theMedian if statement
                else {
                    System.out.println("ERROR Median: NO MEDIAN Calculated");
                }
            }//end theMean if statement
            else{
                System.out.println("ERROR Average: NO AVERAGE Calculated");
            }
        }//end howManyDataItems if statement
        else {
            System.out.println("ERROR Data Read: NO DATA Read from the file");
        }
    } // end main method
} // end main class
