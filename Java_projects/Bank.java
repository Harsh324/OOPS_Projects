package Java_projects;
import java.util.*;

class Bank
{

    private ArrayList<Account> Account_List = new ArrayList<Account>();

    //abstract void Login();

    protected void Add_Account(Account A)
    {
        Account_List.add(A);
    }
    //abstract void Open_Account(Account A);
    protected void Print_All_details()
    {
        for(Account Acc : Account_List)
        {
            System.out.println("Name : " + Acc.get_Name());
            System.out.println("Phone : " + Acc.get_Phone());
            System.out.println("Email : " + Acc.get_Email());
            System.out.println("Address : " + Acc.get_Address());
        }
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
