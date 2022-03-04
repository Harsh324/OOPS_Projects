package Java_projects;

/**
 * Abstract class acting as base class
 */
abstract class Account 
{
    private String Name;
    private String Phone;
    private String Email;
    private String Address;

    protected double Balance;

    public Account(String name, String Phone, String Email, String Address)
    {
        this.Name = name;
        this.Phone = Phone;
        this.Email = Email;
        this.Address = Address;
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

    public CheckingAccount(String name, String Phone, String Email, String Address)
    {
        super(name, Phone, Email, Address);
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
    public SavingAccount(String name, String Phone, String Email, String Address)
    {
        super(name, Phone, Email, Address);
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
    
    public loanAccount(String name, String Phone, String Email, String Address)
    {
        super(name, Phone, Email, Address);
    }

    @Override
    void deposit(double D) {
        // TODO Auto-generated method stub
        
    }

    @Override
    void withdraw(double W) {
        // TODO Auto-generated method stub
        
    }

    @Override
    double balanceEnquiry() {
        // TODO Auto-generated method stub
        return 0;
    }

}
