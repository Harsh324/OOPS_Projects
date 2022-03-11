package Java_projects;

import java.util.*;

enum Type_Account
{
    Saving, Checking, Loan
}

/**
 * Abstract class acting as base class
 */
abstract class Account 
{
    private static SavingAccount _sav = new SavingAccount("ee");
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

    public Account(String Account_Type)
    {
        this.Account_Type = Account_Type;
    }

    public Account()
    {
        this.Name = "";
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

    public String get_AccountType()
    {
        return this.Account_Type;
    }

    abstract void deposit(double D);
    abstract void withdraw(double W);
    abstract double balanceEnquiry();

    static Account get_Account(Type_Account Acc)
    {
        Account account = null;
        switch(Acc)
        {
            case Saving:
                account = _sav;
        }
        return account;
    }

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

    public CheckingAccount(String Account_Type)
    {
        super(Account_Type);
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

    public SavingAccount(String Account_Type)
    {
        super(Account_Type);
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

    public loanAccount(String Account_Type)
    {
        super(Account_Type);
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
