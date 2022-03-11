package Java_projects;

import java.io.IOException;
import java.util.*;


class Interface
{
    public static void main(String[] args) throws IOException 
    {

        Scanner sc = new Scanner(System.in);
        System.out.println("\nWelcome to Bank\n");

        while(true)
        {
            System.out.println("\nif Admin Enter 1\nif Customer Enter 2 : ");
            int Num = sc.nextInt();

            if(Num == 1)
            {
                Admin Adm = new Admin();
                while(true)
                {
                    System.out.println(
                        "\nEnter 1 to open Account\nEnter 2 to get All Account Details\nEnter 3 to Print Customer details\n"
                        + "Enter 4 to print Transaction Details : "
                        );
                    Num = sc.nextInt();
                    if(Num == 1)
                    {
                        // System.out.println("Enter the Customer details");
                        // Adm.Get_Details();

                        if(Adm.Validate())
                        {
                            Adm.Take_Account_Type();
                        }

                        else
                        {
                            int Val = 2;
                            while(Val != 1)
                            {
                                System.out.println("\nEnter the Customer details\n");
                                Adm.Take_Details();
                                System.out.println("\nReview the details once\n");
                                Adm.Review_Details();
                                System.out.println("\nEnter 1 to confirm\nEnter 2 to Enter again");
                                Val = sc.nextInt();
                            }
                        }
                        Adm.Open_Account();

                    }

                    
                    else if(Num == 2)
                    {
                        System.out.println("\nPrinting all the details\n");
                        Adm.Print_Account_Details();
                    }

                    else if(Num == 3)
                    {
                        System.out.println("\nPrinting Customer Details");
                        Adm.Print_Customer_Details();
                    }

                    else if(Num == 4)
                    {
                        System.out.println("\nPrinting Transaction details");
                        Adm.Print_Transaction_Details();
                    }
                }
                
            }
            else if(Num == 2)
            {
                Customer Cstm = new Customer();
                
                
            }
        }

    }
}