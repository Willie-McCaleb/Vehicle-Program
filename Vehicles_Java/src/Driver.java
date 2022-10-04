// Name: Willie McCaleb
//        Current Date: 04/17/21
//        Sources Consulted: Youtube, Stack Overflow
//        By submitting this work, I attest that it is my original work and that I did
//        not violate the University of Mississippi academic policies set forth in the
//        M book.

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Hashtable;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;

public class Driver {
    public static void main(String[] args) {

        System.out.println();

        try {

            File fileReader = new File ("Vehicles.txt");

            Scanner scan = new Scanner(fileReader);

            Hashtable <String, Integer> wordCount;
            wordCount = new Hashtable  <String, Integer>();

            Hashtable <String, Map <String, Integer>> H1;
            H1 = new Hashtable <String,Map<String, Integer>>();

            while( scan.hasNextLine()) {
                String line = scan.nextLine();

                String [] words =	line.split(" ");

                Integer num = wordCount.get(words[0]);

                if( num != null) {
                    wordCount.replace(words[0], num + 1);
                }

                else {
                    wordCount.put(words[0], 1);


                }

                Map <String, Integer> model = H1.get(words[1]);

                if (model != null) {
                    Integer numModel = model.get(words[2]);
                    if(numModel != null) {
                        model.replace(words[2], numModel +1);
                    }
                    else {
                        model.put(words[2], 1);
                    }
                }

                else {
                    Map <String, Integer>	H2 = new HashMap <String, Integer>();

                    H2.put(words [2], 1);

                    H1.put(words[1], H2);

                }

            } System.out.println("Number of vehicle types ");

            String maxType = " ";

            Integer max = 0;

            Set <String> types = wordCount.keySet();
            for (String vehicle :types) {
                Integer typeNum = wordCount.get(vehicle);
                System.out.println(vehicle+ ":" +" " +  typeNum);

                if (typeNum > max) {
                    max = typeNum;
                    maxType = vehicle;
                }

            }
            System.out.println();
            System.out.println("Most occurred type was the " + maxType);
            System.out.println();
            System.out.println("The makes of the models is presented below with its total");
            System.out.println();

            String maxMakeType = " ";

            Integer maxMake = 0;

            String minMakeType = " ";

            int minMake = 3000;

            Set <String> makeS = H1.keySet();
            for (String vehicleType :makeS) {
                Map <String,Integer> make = H1.get(vehicleType);
                int currM = 0; // comparing makes to 0
                Set <String> modelSet = make.keySet();

                for(String m: modelSet) {
                    currM += make.get(m);
                    System.out.println( "Model: " + m + " ,"+ "appearance:  " + make.get(m));
                }
                System.out.println("Total: "+ vehicleType+ " = "  + currM);
                System.out.println();
                if (currM > maxMake) {
                    maxMake = currM;
                    maxMakeType = vehicleType;
                }

                if(currM < minMake) {
                    minMake = currM;
                    minMakeType = vehicleType;
                }



            }

            System.out.println("Most appeared make was the " + maxMakeType);

            System.out.println("Least appeared make was the " + minMakeType);

        }catch (IOException e) {
            System.out.println("Exception");
            e.printStackTrace();
        }

    }



}
