package Java_projects;

import java.io.BufferedReader;
// Import the File class
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
// Import the IOException class to handle errors
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
  
public class Bank_Management{


    public static void main(String[] args) throws IOException
    {
  
        // try {
        //     File Obj = new File("Java_projects/DataBase/Customers/Details/Text.csv");
        //     if (Obj.createNewFile()) {
        //         System.out.println("File created: "
        //                            + Obj.getName());
        //     }
        //     else {
        //         System.out.println("File already exists.");
        //     }
        // }
        // catch (IOException e) {
        //     System.out.println("An error has occurred.");
        //     e.printStackTrace();
        // }


        String Path = "Java_projects/DataBase/Customers/Details/Text.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(Path))) {

            List<List<String>> result = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                result.add(Arrays.asList(values));
            }

            System.out.println(result);
        }
    }
}

