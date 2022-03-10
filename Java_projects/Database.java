package Java_projects;
import java.util.*;
import java.io.*;




class Database
{

    public String get_Customer_ID()
    {
        String Val = "";
        return Val;
    }


    public void Add_Details(Account A) throws FileNotFoundException, IOException
    {
        String Detail = "";
        String Path;


        List<String> Lst = new ArrayList<>();
        Lst = Get_metadata();
        //Updating Customer ID
        String Str = Lst.get(0);

        String Path1 = "Java_projects/DataBase/Customers/" + "2022_CM_" + Str;
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
        
        
        
        Path = Path2 + "2022_CM_" + Str + ".csv";
        File file = new File(Path);
        file.createNewFile();

        String Str1 = "Name;Phone;Email;Address";

        BufferedWriter out = new BufferedWriter(new FileWriter(Path, true));
        out.write(Str1);
        out.write(Detail);
        out.close();


    }

    public void Add_Account(Account A) throws IOException
    {
        String Acc_Details = "";
        String Customer_ID = "";
        String Account_Type = "";
        String Path = "Java_projects/DataBase/Customers/" + Customer_ID + "/Accounts/";
        
        List<String> Lst = new ArrayList<>();
        Lst = Get_metadata();
        //Updating Customer ID
        String Str = Lst.get(1);
        
        Path = Path + "2022_AC_" + Str + ".csv";
        File file = new File(Path);
        file.createNewFile();

        String Str1 = "AccountType;Balance;";

        BufferedWriter out = new BufferedWriter(new FileWriter(Path, true));
        out.write(Str1);
        out.write(Acc_Details);
        out.close();

        if(Account_Type == "Saving")
        {
            String Path5 = "Java_projects/DataBase/Accounts/Saving_Accounts.csv";
            BufferedWriter out1 = new BufferedWriter(new FileWriter(Path, true));
            out1.write(Acc_Details);
        }
        else if(Account_Type == "Loan")
        {
            String Path5 = "Java_projects/DataBase/Accounts/Saving_Accounts.csv";
            BufferedWriter out1 = new BufferedWriter(new FileWriter(Path, true));
            out1.write(Acc_Details);
        }
        else if(Account_Type == "Checking")
        {
            String Path5 = "Java_projects/DataBase/Accounts/Saving_Accounts.csv";
            BufferedWriter out1 = new BufferedWriter(new FileWriter(Path, true));
            out1.write(Acc_Details);
        }
        
    }


    public void Add_Transaction(String Trans_Details, String Customer_ID) throws FileNotFoundException, IOException
    {
        String Path = "Java_projects/DataBase/Customers/" + Customer_ID + "/Transactions/";
        
        List<String> Lst = new ArrayList<>();
        Lst = Get_metadata();
        //Updating Customer ID
        String Str = Lst.get(2);

        Path = Path + "2022_TS_" + Str + ".csv";

        File file = new File(Path);
        file.createNewFile();

        String Str1 = "";

        BufferedWriter out = new BufferedWriter(new FileWriter(Path, true));
        out.write(Str1);
        out.write(Trans_Details);
        out.close();

    }

    public List Get_metadata() throws FileNotFoundException, IOException
    {
        String Path = "Java_projects/DataBase/Metadata/metadata.csv";
        try (BufferedReader br = new BufferedReader(new FileReader(Path))) {

            List<List<String>> result = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                result.add(Arrays.asList(values));
            }
            return result;
            //System.out.println(result);
        }
    }

    /*
    public List Get_Accounts() throws FileNotFoundException, IOException
    {
        List<Object> Result = new ArrayList<>();
        String Path = "Java_projects/DataBase/Customers/Accounts/";
        File folder = new File(Path);
        File[] listOfFiles = folder.listFiles();
        for(File file : listOfFiles)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) 
            {

                List<List<String>> result = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    Result.add(Arrays.asList(values));
                }
                //Result.add(result);
                //System.out.println(result);
            }
        }
        
        return Result;
    }*/

    public List Get_Transactions() throws FileNotFoundException, IOException
    {
        List<Object> Result = new ArrayList<>();
        String Path = "Java_projects/DataBase/Customers/Accounts/";
        File folder = new File(Path);
        File[] listOfFiles = folder.listFiles();
        int Count = 0;
        for(File file : listOfFiles)
        {
            if(Count == 5)
                break;
            try (BufferedReader br = new BufferedReader(new FileReader(file))) 
            {

                //List<List<String>> result = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    Result.add(Arrays.asList(values));
                }
                //Result.add(result);
                //System.out.println(result);
            }
            Count++;
        }
        
        return Result;
    }

    //private String Customer_id, password;

    /*
    public void Customer_Login(String Customer_ID, String Password)
    {
        this.Customer_id = Customer_ID;
        this.password = Password;
    }*/

    public List Return_Details(String Customer_id) throws IOException
    {
        String Path = "Java_projects/DataBase/Customers/Details/" + Customer_id + ".csv";
        try (BufferedReader br = new BufferedReader(new FileReader(Path))) {

            List<List<String>> result = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                String[] values = line.split(";");
                result.add(Arrays.asList(values));
            }
            return result;
            //System.out.println(result);
        }
    }

    public void Return_Account(String Account_ID){};


    public List Return_Accounts(String Customer_ID) throws FileNotFoundException, IOException
    {
        List<Object> Result = new ArrayList<>();
        String Path = "Java_projects/DataBase/Customers/" + Customer_ID + "/Accounts/";
        File folder = new File(Path);
        File[] listOfFiles = folder.listFiles();
        for(File file : listOfFiles)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) 
            {

                List<List<String>> result = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    Result.add(Arrays.asList(values));
                }
                //Result.add(result);
                //System.out.println(result);
            }
        }
        
        return Result;
    }

    public void Return_Transaction(String Account_ID){};

    public List Return_Transactions() throws FileNotFoundException, IOException
    {
        List<Object> Result = new ArrayList<>();
        String Path = "Java_projects/DataBase/Customers/Transactions/";
        File folder = new File(Path);
        File[] listOfFiles = folder.listFiles();
        for(File file : listOfFiles)
        {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) 
            {

                List<List<String>> result = new ArrayList<>();
                String line;
                while ((line = br.readLine()) != null) {
                    String[] values = line.split(";");
                    Result.add(Arrays.asList(values));
                }
                //Result.add(result);
                //System.out.println(result);
            }
        }
        
        return Result;
    }
}
