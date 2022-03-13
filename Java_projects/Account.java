package Java_projects;

import java.util.*;

/**
 * Abstract class acting as base class
 */
abstract class Account 
{
    private String Name, Phone, Email, Address, Account_Type;
    protected double Balance;

    public Account(String name, String Phone, String Email, String Address, String Account_Type, Double Balance)
    {
        this.Name = name;
        this.Phone = Phone;
        this.Email = Email;
        this.Address = Address;
        this.Account_Type = Account_Type;
        this.Balance = Balance;
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

}

/**
 * CheckingAccount having a credit limit
 */
class CheckingAccount extends Account
{
    private double creditlimit;

    public CheckingAccount(String name, String Phone, String Email, String Address, String Account_Type, Double Balance, Double Creditlimit)
    {
        super(name, Phone, Email, Address, Account_Type, Balance);
        this.creditlimit = Creditlimit;
    }

    void deposit(double D) 
    {
        Balance++;
    }

    void withdraw(double W)
    {
        if(Balance - W >= creditlimit)
        {
            Balance -= W;
        } 
        else
            System.out.println("Can't Initiate Withdrawl ! , Credit limit reached");
    }

    double balanceEnquiry()
    {
        return Balance;
    }
}

class SavingAccount extends Account
{
    private double Saving_Interest_Rate;


    public SavingAccount(String name, String Phone, String Email, String Address, String Account_Type, double Balance, double Saving_Interest_Rate)
    {
        super(name, Phone, Email, Address, Account_Type, Balance);
        this.Saving_Interest_Rate = Saving_Interest_Rate;
    }

    void deposit(double D) 
    {
        Balance += D;
        
    }

    void withdraw(double W)
    {
        if(Balance - W >= 0)
        {
            Balance -= W;
        }
        else
            System.out.println("Can't Initiate Withdrawl ! , Not Sufficient Balance");
        
    }

    double balanceEnquiry()
    {
        return Balance;
    }

    public void Add_Interest()
    {
        double Interest = (Balance) * (Saving_Interest_Rate / 100);
        Balance += Interest;
    }
}

class loanAccount extends Account
{
    private double Principal_amt;
    private float Loan_Interest_Rate;

    public loanAccount(String name, String Phone, String Email, String Address, String Account_Type, Double Balance, float Loan_Interest_Rate, Double Principal_Amt)
    {
        super(name, Phone, Email, Address, Account_Type, Balance);
        this.Loan_Interest_Rate = Loan_Interest_Rate;
        this.Principal_amt = Principal_Amt;

    }

    void deposit(double D) 
    {
        Principal_amt -= D;
        
    }

    void withdraw(double W) 
    {
        Principal_amt += W;
        
    }

    double balanceEnquiry() 
    {
        return Principal_amt;
    }

    double get_Principal()
    {
        return this.Principal_amt;
    }

    public void Interest_Addition()
    {
        double Interest = (Principal_amt) * (Loan_Interest_Rate);
        Principal_amt += Interest;
    }
}
