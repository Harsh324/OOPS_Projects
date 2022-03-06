package Java_projects;

import java.util.*;

import javax.lang.model.util.ElementScanner14;  


class Interface
{
    public static void main(String[] args) 
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
                    System.out.println("\nEnter 1 to open Account\nEnter 2 to get All Account Details : ");
                    Num = sc.nextInt();
                    if(Num == 1)
                    {
                        // System.out.println("Enter the Customer details");
                        // Adm.Get_Details();
                        int Val = 2;
                        while(Val != 1)
                        {
                            System.out.println("\nEnter the Customer details\n");
                            Adm.Get_Details();
                            System.out.println("\nReview the details once\n");
                            Adm.Review_Details();
                            System.out.println("\nEnter 1 to confirm\nEnter 2 to Enter again");
                            Val = sc.nextInt();
                        }
                        Adm.Open_Account();
                    }
                }
                
            }
            else if(Num == 2)System.out.println("New Account is opened");
            {
                Customer Cstm = new Customer();
                Cstm.Login();
                Account Ac;
            }
        }

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

class Admin extends Bank
{
    private String Name, Phone, Email, Address, AccountType;

    void Login() {
        // TODO Auto-generated method stub
        
    }
    public void Get_Details()
    {
        Scanner sc = new Scanner(System.in);
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

    public void Open_Account()
    {
        Bank B = new Bank();
        if(this.AccountType == "Saving")
        {
            B.Add_Account(new SavingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType));
            System.out.println("New Account is opened");
        }
        else if(this.AccountType == "Checking")
        {
            B.Add_Account(new CheckingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType));
            System.out.println("New Account is opened");
        }
        else if(this.AccountType == "Loan")
        {
            B.Add_Account(new loanAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType));
            System.out.println("New Account is opened");
        }
        else
            System.out.println("Please Enter correct Account Type, among ('Saving', 'Checking', 'Loan')");
    }

    public void Print_All_details()
    {
        
    }

}


class Bank
{

    private ArrayList<Account> Account_List = new ArrayList<Account>();

    //abstract void Login();

    protected void Add_Account(Account A)
    {
        Account_List.add(A);
    }
    //abstract void Open_Account(Account A);

}

/**
 * Abstract class acting as base class
 */
abstract class Account 
{
    private String Name;
    private String Phone;
    private String Email;
    private String Address;
    private String Account_Type;

    protected double Balance;

    public Account(String name, String Phone, String Email, String Address, String Account_Type)
    {
        this.Name = name;
        this.Phone = Phone;
        this.Email = Email;
        this.Address = Address;
        this.Account_Type = Account_Type;
    }

    public String get_Name()
    {
        return this.Name;
    }

    public String get_Phone()
    {
        return this.Phone;
    }

    public String get_Email()
    {
        return this.Email;
    }

    public String get_Address()
    {
        return this.Address;
    }

    abstract void deposit(double D);
    abstract void withdraw(double W);
    abstract double balanceEnquiry();

}

/**
 * CheckingAccount having a credit limit
 */
class CheckingAccount extends Account
{
    private double creditlimit;
    private double Balance1;

    public CheckingAccount(String name, String Phone, String Email, String Address, String Account_Type)
    {
        super(name, Phone, Email, Address, Account_Type);
    }

    @Override
    void deposit(double D) 
    {
        Balance1++;
    }

    @Override
    void withdraw(double W)
    {
        if(Balance1 - W >= creditlimit)
        {
            Balance1 -= W;
        } 
        else
            System.out.println("Can't Initiate Withdrawl ! , Credit limit reached");
    }

    @Override
    double balanceEnquiry()
    {
        return Balance1;
    }
}

class SavingAccount extends Account
{
    private double saving_Balance;
    private double Interest_Rate;
    public SavingAccount(String name, String Phone, String Email, String Address, String Account_Type)
    {
        super(name, Phone, Email, Address, Account_Type);
    }

    @Override
    void deposit(double D) 
    {
        saving_Balance += D;
        
    }

    @Override
    void withdraw(double W)
    {
        if(saving_Balance - W >= 0)
        {
            saving_Balance -= W;
        }
        else
            System.out.println("Can't Initiate Withdrawl ! , Cfredit limit reached");
        
    }

    @Override
    double balanceEnquiry()
    {
        return saving_Balance;
    }

    public void Add_Interest()
    {
        double Interest = (saving_Balance) * (Interest_Rate / 100);
        saving_Balance += Interest;
    }
}

class loanAccount extends Account
{
    private double Principal_amt;
    private float Interest_Rate1;
    private int Month;


    public loanAccount(String name, String Phone, String Email, String Address, String Account_Type)
    {
        super(name, Phone, Email, Address, Account_Type);
    }

    @Override
    void deposit(double D) 
    {
        Principal_amt -= D;
        
    }

    @Override
    void withdraw(double W) 
    {
        Principal_amt += W;
        
    }

    @Override
    double balanceEnquiry() 
    {
        return Principal_amt;
    }

    public void Interest_Addition()
    {
        double Interest = (Principal_amt) * (Interest_Rate1);
        Principal_amt += Interest;
    }
}
