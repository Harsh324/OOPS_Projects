package Java_projects;

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

class CheckingAccount extends Account
{
    private double creditlimit;

    public CheckingAccount(String name, String Phone, String Email, String Address)
    {
        super(name, Phone, Email, Address);
    }

    @Override
    void deposit(double D) 
    {
        
    }

    @Override
    void withdraw(double W) {
        // TODO Auto-generated method stub
        
    }

    @Override
    double balanceEnquiry() {
        return Balance;
    }

    

}
