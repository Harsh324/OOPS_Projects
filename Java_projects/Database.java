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

class Customer_Database extends Database
{

    public List Return_Details() throws IOException
    {
        String Path = "Java_projects/DataBase/Customers/Details/";
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
    public List Return_Account()
    {
        List<List<String>> result = new ArrayList<>();
        return result;
    }
}
