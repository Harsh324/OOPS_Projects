package Java_projects;
import java.util.*;
import java.io.*;



class Database
{
    String Customer_Count, Account_Count, Transaction_Count;

    public Database()
    {
        this.Get_metadata();
    }


    public void Add_Details(Account A) throws FileNotFoundException, IOException
    {
        String Detail = "2022_CM_" + this.Customer_Count + ";" + A.get_Name() + ";" + A.get_Phone() + ";" + A.get_Email()
        + ";" + A.get_Address();

        String Path1 = "Java_projects/DataBase/Customers/" + "2022_CM_" + this.Customer_Count;
        File folder = new File(Path1);
        folder.mkdir();

        String Path2 = Path1 + "/Details";
        String Path3 = Path1 + "/Accounts";
        String Path4 = Path1 + "/Transactions";

        folder = new File(Path2);
        folder.mkdir();

        folder = new File(Path3);
        folder.mkdir();

        folder = new File(Path4);
        folder.mkdir();
        
        String Path = Path2 + "/" + "2022_CM_" + this.Customer_Count + ".csv";
        File file = new File(Path);
        file.createNewFile();

        BufferedWriter out = new BufferedWriter(new FileWriter(Path, true));
        out.write("Customer_ID;Name;Phone;Email;Address");
        out.newLine();
        out.write(Detail);
        out.newLine();
        out.close();

        System.out.println("Your Customer_ID is : " + "2022_CM_" + this.Customer_Count);
        this.Customer_Count = Integer.toString(Integer.parseInt(this.Customer_Count) + 1);
    }

    public void Add_Account(Account A, String Customer_ID, String Extra) throws IOException
    {
        String Path = "Java_projects/DataBase/Customers/" + Customer_ID + "/Accounts/";
        
        
        Path = Path + "2022_AC_" + this.Account_Count + ".csv";
        File file = new File(Path);
        file.createNewFile();


        BufferedWriter out = new BufferedWriter(new FileWriter(Path, true));

        if(A.get_AccountType() == "Saving")
        {
            out.write("Account_ID;Account_Type;Balance;Interest_Rate");
            out.newLine();
            out.write("2022_AC_" + this.Account_Count + ";" + A.get_AccountType() + ";" + A.balanceEnquiry() + ";" + Extra);

            String Path5 = "Java_projects/DataBase/Accounts/Saving_Accounts.csv";
            BufferedWriter out1 = new BufferedWriter(new FileWriter(Path5, true));
            out1.newLine();
            out1.write("2022_AC_" + this.Account_Count + ";" + A.get_AccountType() + ";" + A.balanceEnquiry() + ";" + Extra);
            out1.close();
        }

        else if(A.get_AccountType() == "Loan")
        {
            out.write("Account_ID;Account_Type;Balance;Principal_Amt;Interest_Rate");
            out.newLine();
            out.write("2022_AC_" + this.Account_Count + ";" + A.get_AccountType() + ";" + A.balanceEnquiry() + ";" + Extra);

            String Path5 = "Java_projects/DataBase/Accounts/Loan_Accounts.csv";
            BufferedWriter out1 = new BufferedWriter(new FileWriter(Path5, true));
            out1.newLine();
            out1.write("2022_AC_" + this.Account_Count + ";" + A.get_AccountType() + ";" + A.balanceEnquiry() + ";" + Extra);
            out1.close();
        }
        
        else if(A.get_AccountType() == "Checking")
        {
            out.write("Account_ID;Account_Type;Balance;Credit_Limit");
            out.newLine();
            out.write("2022_AC_" + this.Account_Count + ";" + A.get_AccountType() + ";" + A.balanceEnquiry() + ";" + Extra);
            
            String Path5 = "Java_projects/DataBase/Accounts/Checking_Accounts.csv";
            BufferedWriter out1 = new BufferedWriter(new FileWriter(Path5, true));
            out1.newLine();
            out1.write("2022_AC_" + this.Account_Count + ";" + A.get_AccountType() + ";" + A.balanceEnquiry() + ";" + Extra);
            out1.close();
        }

        out.close();
        System.out.println("Your Account_ID is : " + "2022_AC_" + this.Account_Count);

        this.Account_Count = Integer.toString(Integer.parseInt(this.Account_Count) + 1);
        
    }

    public void Account_to_Database(Account A, String Customer_ID, String Account_ID , String Extra) throws IOException
    {
        System.out.println(Customer_ID + " ; " + Account_ID);
        String Path = "Java_projects/DataBase/Customers/" + Customer_ID + "/Accounts/";
        Path = Path + Account_ID + ".csv";


        BufferedWriter out = new BufferedWriter(new FileWriter(Path));

        if(A.get_AccountType().equals("Saving"))
        {
            out.write("Account_ID;Account_Type;Balance;Interest_Rate");
            out.newLine();
            out.write(Account_ID + ";" + A.get_AccountType() + ";" + A.balanceEnquiry() + ";" + Extra);
        }

        else if(A.get_AccountType().equals("Checking"))
        {
            out.write("Account_ID;Account_Type;Balance;Principal_Amt;Interest_Rate");
            out.newLine();
            out.write(Account_ID + ";" + A.get_AccountType() + ";" + A.balanceEnquiry() + ";" + Extra);
        }
        
        else if(A.get_AccountType().equals("Loan"))
        {
            out.write("Account_ID;Account_Type;Balance;Credit_Limit");
            out.newLine();
            out.write(Account_ID + ";" + A.get_AccountType() + ";" + A.balanceEnquiry() + ";" + Extra);
        }

        out.close();;
    }


    public void Add_Transaction(String Trans_Details, String Customer_ID, String Type) throws FileNotFoundException, IOException
    {
        String Path = "Java_projects/DataBase/Customers/" + Customer_ID + "/Transactions/";

        Path = Path + "2022_TS_" + this.Transaction_Count + ".csv";

        File file = new File(Path);
        file.createNewFile();

        String Str1 = "Transaction_ID"+ ";" + "Account_ID" + ";" + "Account_Type" + ";" + Type;

        BufferedWriter out = new BufferedWriter(new FileWriter(Path, true));
        out.write(Str1);
        out.newLine();
        out.write(Trans_Details);
        out.close();

        BufferedWriter out1 = new BufferedWriter(new FileWriter("Java_projects/DataBase/Transactions/Transactions.csv", true));
        out1.newLine();
        out1.write(Trans_Details);
        out1.close();
        
        this.Transaction_Count = Integer.toString(Integer.parseInt(this.Transaction_Count) + 1);

    }

    public void Put_metadata() throws IOException
    {
        String Str1 = "Customer_ID; Account_ID; Transaction_ID";
        String Path = "Java_projects/DataBase/Metadata/metadata.csv";
        BufferedWriter out = new BufferedWriter(new FileWriter(Path));
        out.write(Str1);
        out.newLine();
        out.write(this.Customer_Count + ";" + this.Account_Count + ";" + this.Transaction_Count);
        out.close();
    }

    public void Get_metadata()
    {
        List<String[]> Counts = new ArrayList<>();
        String Path = "Java_projects/DataBase/Metadata/metadata.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(Path))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Counts.add(values);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }

        this.Customer_Count = Counts.get(1)[0];
        this.Account_Count = Counts.get(1)[1];
        this.Transaction_Count = Counts.get(1)[2];
        
    }

    public List Get_Transactions(String Customer_ID) throws FileNotFoundException, IOException
    {
        List<Object> Result = new ArrayList<>();
        String Path = "Java_projects/DataBase/Customers/" + Customer_ID + "/";
        File folder = new File(Path);
        File[] listOfFiles = folder.listFiles();
        for(File file : listOfFiles)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) 
            {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    Result.add(Arrays.asList(values));
                }
            }
        }
        
        return Result;
    }

    public List Return_Details(String Customer_id)
    {
        List<String[]> Customer_Details = new ArrayList<>();
        String Path = "Java_projects/DataBase/Customers/" + Customer_id + "/Details/" + Customer_id + ".csv";
        try (BufferedReader br = new BufferedReader(new FileReader(Path)))
        {

            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Customer_Details.add(values);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        return Customer_Details;
    }

    public List Return_Account(String Customer_ID, String Account_ID)
    {
        List<String[]> Account_Details = new ArrayList<>();
        String Path = "Java_projects/DataBase/Customers/" + Customer_ID + "/Accounts/" + Account_ID + ".csv";

        try (BufferedReader br = new BufferedReader(new FileReader(Path)))
        {
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                Account_Details.add(values);
            }
        }
        catch(Exception e)
        {
            System.out.println(e);
        }
        
        return Account_Details;
    }


    public List Return_Accounts(String Customer_ID)
    {
        List<String[]> Account_Details = new ArrayList<>();
        String Path = "Java_projects/DataBase/Customers/" + Customer_ID + "/Accounts/";

        File folder = new File(Path);
        File[] listOfFiles = folder.listFiles();
        for(File file : listOfFiles)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) 
            {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    Account_Details.add(values);
                }
            }
            catch(Exception e)
            {
                System.out.println(e);
            }
        }
        return Account_Details;
    }
}
