package Java_projects;

import java.io.BufferedReader;
import java.io.BufferedWriter;
// Import the File class
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
// Import the IOException class to handle errors
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.naming.spi.DirStateFactory.Result;
  
public class Bank_Management{


    public static void main(String[] args) throws IOException
    {

        String Path = "Java_projects/DataBase/Customers/Details/One.csv";
        String Path1 = "accounts.csv";
        ArrayList<Object> Result = new ArrayList<>();
        List<String[]> Lst = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(Path1))) 
            {

                
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    //System.out.println(values[0]);
                    Lst.add(values);
                    //Result.add(Arrays.asList(values));
                    //result.add(values);
                }
                //Result.add(result);
                //System.out.println(result);
                System.out.println(Lst.get(1)[1]);
                System.out.println(Lst.get(1)[2]);
                System.out.println(Lst.get(1)[3]);
            }
            // System.out.println(Lst.get(1)[0]);
            // System.out.println(Lst.get(1)[1]);
            // System.out.println(Lst.get(1)[2]);


        /*Result.add("Seed");
        Result.add(5678);
        Result.add("Singh");
        Result.add(26233444);
        String Str = Result.get(0) + ";" + Result.get(1) + ";" + Result.get(2) + ";" + Result.get(3);
        
        BufferedWriter out = new BufferedWriter(new FileWriter(Path, true));
        out.write(Str);
        out.close();*/
        //String Path = "Java_projects/DataBase/Customers/Transactions/";
        /*Path = "Java_projects/DataBase/Customers/" + "2022_AC_1";
        File folder = new File(Path);
        folder.mkdir();*/

        /*
        File[] listOfFiles = folder.listFiles();

        for(File file : listOfFiles)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) 
            {

                
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    Result.add(Arrays.asList(values));
                    //result.add(values);
                }
                //Result.add(result);
                //System.out.println(result);
            }
        }*/
        /*
        System.out.println(Result);
        List<String> Lst = new ArrayList<>();
        for(Object Arr : Result)
        {
            System.out.println(Arr);
            Lst = (List<String>) Arr;
            //System.out.println(Lst);
            for(String Iter : Lst)
            {
                System.out.println(Iter);
            }
        }*/


        // String strMain = "Alpha, Beta, Delta, Gamma, Sigma";
        // String[] arrSplit = strMain.split(", ");
        // for (int i=0; i < arrSplit.length; i++)
        // {
        //     System.out.println(arrSplit[i]);
        // }
    }
}

