package Java_projects;
import java.util.*;
import java.io.*;


class Database 
{
    protected List<Object> Details  = new ArrayList<Object>();
    protected List<Object> Accounts  = new ArrayList<Object>();
    protected List<Object> Transactions  = new ArrayList<Object>();

    

}

class Admin_Database
{
    
}

class Customer_Database
{
    private String Customer_id, password;


    public void Customer_Login(String Customer_ID, String Password)
    {
        this.Customer_id = Customer_ID;
        this.password = Password;
    }

    public List Return_Details() throws IOException
    {
        String Path = "Java_projects/DataBase/Customers/Details/" + this.Customer_id + ".csv";
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


    public List Return_Accounts() throws FileNotFoundException, IOException
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
                    result.add(Arrays.asList(values));
                }
                Result.add(result);
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
                    result.add(Arrays.asList(values));
                }
                Result.add(result);
                //System.out.println(result);
            }
        }
        
        return Result;
    }
}
