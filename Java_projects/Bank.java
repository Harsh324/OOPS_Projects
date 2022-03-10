package Java_projects;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

class Bank
{

    private ArrayList<Account> Account_List = new ArrayList<Account>();
    protected Database Adm = new Database();
    //protected Customer_Database Cstom = new Customer_Database();
    protected boolean Flag;

    //abstract void Login();
    //abstract void Open_Account(Account A);

}



class Admin extends Bank
{
    private String Name, Phone, Email, Address, AccountType;
    Scanner sc = new Scanner(System.in);

    public boolean Validate() 
    {
        System.out.println("Enter 1 if new user\nEnter 2 if already user");
        int Num = sc.nextInt();
        if(Num == 2)
        {
            return true;
        }
        else
        {
            return false;
        }
        
    }

    public void Take_Account_Type()
    {
        System.out.println("Select the Account type\nSaving (1)\nChecking (2)\nLoan (3)");
        int Num = sc.nextInt();
        if(Num == 1)
            this.AccountType = "Saving";
        else if(Num == 2)
            this.AccountType = "Checking";
        else if(Num== 3)
            this.AccountType = "Loan";
    }


    public void Take_Details()
    {
        
        System.out.println("Enter Customer Name : ");
        this.Name = sc.next();
        System.out.println("Enter Customer Phone : ");
        this.Phone = sc.next();
        System.out.println("Enter Customer Email : ");
        this.Email = sc.next();
        System.out.println("Enter Customer Address : ");
        this.Address = sc.next();
        System.out.println("Select the Account type\nSaving (1)\nChecking (2)\nLoan (3)");
        int Num = sc.nextInt();
        if(Num == 1)
            this.AccountType = "Saving";
        else if(Num == 2)
            this.AccountType = "Checking";
        else if(Num== 3)
            this.AccountType = "Loan";
        
    }

    public void Review_Details()
    {
        System.out.println("Check the give details once\n");
        System.out.println("Name : " + this.Name);
        System.out.println("Phone : " + this.Phone);
        System.out.println("Email : " + this.Email);
        System.out.println("Address : " + this.Address);
        System.out.println("Account_Type : " + this.AccountType);
    }

    public void Open_Account() throws IOException
    {
        if(this.AccountType == "Saving")
        {
            if(Flag)
                Adm.Add_Account(new SavingAccount(this.AccountType));
            else
            {
                Adm.Add_Details(new SavingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType));
                Adm.Add_Account(new SavingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType));
            }
            System.out.println("New Saving Account is opened");
        }

        else if(this.AccountType == "Checking")
        {
            if(Flag)
                Adm.Add_Account(new CheckingAccount(this.AccountType));
            else
            {
                Adm.Add_Details(new CheckingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType));
                Adm.Add_Account(new CheckingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType));
            }
            System.out.println("New Checking Account is opened");
        }

        
        else if(this.AccountType == "Loan")
        {
            if(Flag)
                Adm.Add_Account(new loanAccount(this.AccountType));
            else
            {
                Adm.Add_Details(new loanAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType));
                Adm.Add_Account(new loanAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType));
            }
            System.out.println("New loan Account is opened");
        }


        else
            System.out.println("Please Enter correct Account Type, among ('Saving', 'Checking', 'Loan')");
    }

    public void Print_Few_Transactions() throws FileNotFoundException, IOException
    {
        List<Object> Lst = new ArrayList<>();
        Lst = Adm.Get_Transactions();
        System.out.println("Printing Few Transactions");
        for(Object Ls : Lst)
        {
            System.out.println(Ls);
        }
    }

    public void Print_Customer_Details() throws IOException
    {
        System.out.println("Enter Customer ID");
        String Customer_ID = sc.next();

        List<Object> Lst = new ArrayList<>();

        Lst = Adm.Return_Details(Customer_ID);
        System.out.println("Printing the customer details");

        System.out.println(Lst);

    }

    public void Print_Account_Details() throws FileNotFoundException, IOException
    {
        System.out.println("Enter Customer ID");
        String Customer_ID = sc.next();
        List<Object> Lst = new ArrayList<>();

        Lst = Adm.Return_Accounts(Customer_ID);

        System.out.println("Printing all accounts details");

        System.out.println(Lst);
    }
}





class Customer extends Bank
{
    private String Customer_ID;
    //private String Account_type;


    void Login() 
    {
        // TODO Auto-generated method stub
        
    }
}
