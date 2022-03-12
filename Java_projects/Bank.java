package Java_projects;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

class Bank
{

    protected Database _Database = new Database();

    protected boolean Flag;
    protected float Loan_Interest_Rate = 5.0f;
    protected Double Saving_Interest_Rate = 5.0;

    protected Double CreditLimit = 5000.00;

    //abstract void Login();
    //abstract void Open_Account(Account A);

    protected SavingAccount SA = null;
    protected CheckingAccount CA = null;
    protected loanAccount LA = null;
    Scanner sc = new Scanner(System.in);

}



class Admin extends Bank
{
    private String Name, Phone, Email, Address, AccountType, Customer_ID;
    
    public boolean Validate() 
    {
        System.out.println("Enter 1 if new user\nEnter 2 if already user");
        int Num = sc.nextInt();
        if(Num == 2)
        {
            Flag = true;
            return true;
        }
        else
        {
            Flag = false;
            return false;
        }
        
    }

    public void Take_Account_Type() throws IOException
    {
        System.out.println("Please Enter Customer_ID");
        this.Customer_ID = sc.next();


        System.out.println("Select the Account type\nSaving (1)\nChecking (2)\nLoan (3)");
        int Num = sc.nextInt();
        if(Num == 1)
            this.AccountType = "Saving";
        else if(Num == 2)
            this.AccountType = "Checking";
        else if(Num== 3)
            this.AccountType = "Loan";

        List<String[]> Lst = new ArrayList<>();

        Lst = _Database.Return_Details(this.Customer_ID);

        this.Name = Lst.get(1)[1];
        this.Phone = Lst.get(1)[2];
        this.Email = Lst.get(1)[3];
        this.Address = Lst.get(1)[4];

        
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
            SA = new SavingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, Saving_Interest_Rate);
            if(Flag)
            {
                //SA = new SavingAccount(this.AccountType);
                _Database.Add_Account(SA);
            }
            
            else
            {
                //SA = new SavingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, Saving_Interest_Rate);
                _Database.Add_Details(SA);
                _Database.Add_Account(SA);
            }
            System.out.println("New Saving Account is opened");
        }

        else if(this.AccountType == "Checking")
        {
            CA = new CheckingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, CreditLimit);
            if(Flag)
            {
                //CA = new CheckingAccount(this.AccountType);
                _Database.Add_Account(CA);
            }
            else
            {
                //CA = new CheckingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, CreditLimit);
                _Database.Add_Details(CA);
                _Database.Add_Account(CA);
            }
            System.out.println("New Checking Account is opened");
        }

        
        else if(this.AccountType == "Loan")
        {
            LA = new loanAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, Loan_Interest_Rate, 5000.0);
            if(Flag)
            {
                //LA = new loanAccount(this.AccountType);
                _Database.Add_Account(LA);
            }
            else
            {
                //LA = new loanAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, Loan_Interest_Rate, 5000.0);
                _Database.Add_Details(LA);
                _Database.Add_Account(LA);
            }
            System.out.println("New loan Account is opened");
        }


        else
            System.out.println("Please Enter correct Account Type, among ('Saving', 'Checking', 'Loan')");
    }

    public void Print_Transaction_Details() throws FileNotFoundException, IOException
    {
        System.out.println("Enter Customer ID");
        String Customer_ID = sc.next();

        List<Object> Lst = new ArrayList<>();
        Lst = _Database.Get_Transactions(Customer_ID);
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

        Lst = _Database.Return_Details(Customer_ID);
        System.out.println("Printing the customer details");

        System.out.println(Lst);

    }

    public void Print_Account_Details() throws FileNotFoundException, IOException
    {
        System.out.println("Enter Customer ID");
        String Customer_ID = sc.next();
        List<Object> Lst = new ArrayList<>();

        Lst = _Database.Return_Accounts(Customer_ID);

        System.out.println("Printing all accounts details");

        System.out.println(Lst);
    }
}





class Customer extends Bank
{
    private String Name, Phone, Email, Address, AccountType, Customer_ID, Account_ID;
    private Double Balance, Principal_Amount;

    public void Load_Customer()
    {
        System.out.println("Please Enter the Customer_ID");
        this.Customer_ID = sc.nextLine();

        List<String[]> Lst = new ArrayList<>();

        Lst = _Database.Return_Details(this.Customer_ID);

        this.Name = Lst.get(1)[1];
        this.Phone = Lst.get(1)[2];
        this.Email = Lst.get(1)[3];
        this.Address = Lst.get(1)[4];
    }

    public void Load_Account()
    {
        System.out.println("Please Enter the Account ID");
        this.Account_ID = sc.nextLine();

        List<String[]> Lst = new ArrayList<>();

        Lst = _Database.Return_Account(this.Customer_ID, this.Account_ID);

        this.AccountType = Lst.get(1)[1];
        if(this.AccountType == "Saving")
        {
            this.Balance = Double.parseDouble(Lst.get(1)[3]);;
            SA = new SavingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, this.Balance, Saving_Interest_Rate);
        }
        else if(this.AccountType == "Checking")
        {
            this.Balance = Double.parseDouble(Lst.get(1)[3]);
            CA = new CheckingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, this.Balance, CreditLimit);
        }
        else if(this.AccountType == "Loan")
        {
            this.Balance = Double.parseDouble(Lst.get(1)[3]);
            this.Principal_Amount = Double.parseDouble(Lst.get(1)[4]);
            LA = new loanAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, this.Balance, Loan_Interest_Rate, this.Principal_Amount);
        }


    }

    public void Deposit_Money()
    {
        System.out.println("Enter the Amount to Deposit");
        Double Var = sc.nextDouble();
        if(this.AccountType == "Saving")
        {
            SA.deposit(Var);
            System.out.println("Current Balance is : " + SA.balanceEnquiry());
        }
        else if(this.AccountType == "Checking")
        {
            CA.deposit(Var);
            System.out.println("Current Balance is : " + CA.balanceEnquiry());
        }
        else if(this.AccountType == "Loan")
        {
            LA.deposit(Var);
            System.out.println("Current Balance is : " + LA.balanceEnquiry());
        }
    }

    public void Withdraw_Money()
    {
        System.out.println("Enter the Amount to Withdraw");
        Double Var = sc.nextDouble();

        if(this.AccountType == "Saving")
        {
            SA.withdraw(Var);;
            System.out.println("Current Balance is : " + SA.balanceEnquiry());
        }
        else if(this.AccountType == "Checking")
        {
            CA.withdraw(Var);
            System.out.println("Current Balance is : " + CA.balanceEnquiry());
        }
        else if(this.AccountType == "Loan")
        {
            LA.withdraw(Var);
            System.out.println("Current Balance is : " + LA.balanceEnquiry());
        }
    }

    public void Check_Balance()
    {
        
    }
}
