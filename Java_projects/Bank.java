package Java_projects;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

import javax.lang.model.util.ElementScanner14;

class Bank
{
    protected String Name, Phone, Email, Address, AccountType, Customer_ID,  Account_ID;

    protected Database _Database = new Database();

    protected boolean Flag;

    protected float Loan_Interest_Rate = 5.0f;
    protected Double Saving_Interest_Rate = 5.0;

    protected Double CreditLimit = 5000.00;

    protected SavingAccount SA = null;
    protected CheckingAccount CA = null;
    protected loanAccount LA = null;

    Scanner sc = new Scanner(System.in);

    protected List<String[]> Lst = new ArrayList<>();

    public void Print(List<String[]> Lst, String Out)
    {
        int Count = 0, Val = 1;
        String[] S1 = new String[20];
        for(String[] Str : Lst)
        {
            if(Count % 2 == 0)
            {
                S1 = Str;
            }
            else
            {
                System.out.println(Out + " : " + Val);
                for(int i = 0; i < Str.length; i++)
                {
                    System.out.print("\t" + S1[i] + " : " + Str[i]);
                }
                System.out.println();
                Val++;
            }
            Count++;  
        }
    }

    public void Input_Account_Type()
    {
        System.out.println("Select the Account type\nSaving (1)\nChecking (2)\nLoan (3)");
        int Num = sc.nextInt();
        sc.nextLine();
        if(Num == 1)
            this.AccountType = "Saving";
        else if(Num == 2)
            this.AccountType = "Checking";
        else if(Num== 3)
            this.AccountType = "Loan";
    }
}



class Admin extends Bank
{
    
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
        sc.nextLine();

        Input_Account_Type();
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
        sc.nextLine();
        System.out.println("Enter Customer Phone : ");
        this.Phone = sc.next();
        sc.nextLine();
        System.out.println("Enter Customer Email : ");
        this.Email = sc.next();
        sc.nextLine();
        System.out.println("Enter Customer Address : ");
        this.Address = sc.next();
        sc.nextLine();

        Input_Account_Type();
    }

    public void Review_Details()
    {
        System.out.println("\tName : " + this.Name);
        System.out.println("\tPhone : " + this.Phone);
        System.out.println("\tEmail : " + this.Email);
        System.out.println("\tAddress : " + this.Address);
        System.out.println("\tAccount_Type : " + this.AccountType);
    }

    public void Open_Account() throws IOException
    {
        if(this.AccountType == "Saving")
        {
            SA = new SavingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, Saving_Interest_Rate);
            String Add = Double.toString(Saving_Interest_Rate);
            if(Flag)
                _Database.Add_Account(SA, this.Customer_ID, Add);
            
            else
            {
                _Database.Add_Details(SA);
                _Database.Add_Account(SA, this.Customer_ID, Add);
            }
            System.out.println("New Saving Account is opened");
        }

        else if(this.AccountType == "Checking")
        {
            CA = new CheckingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, CreditLimit);
            String Add = Double.toString(CreditLimit);
            if(Flag)
                _Database.Add_Account(CA, this.Customer_ID, Add);

            else
            {
                _Database.Add_Details(CA);
                _Database.Add_Account(CA, this.Customer_ID, Add);
            }
            System.out.println("New Checking Account is opened");
        }

        
        else if(this.AccountType == "Loan")
        {
            LA = new loanAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, Loan_Interest_Rate, 5000.0);
            String Add = Double.toString(LA.get_Principal()) + Double.toString(Loan_Interest_Rate);
            if(Flag)
                _Database.Add_Account(LA, this.Customer_ID, Add);

            else
            {
                _Database.Add_Details(LA);
                _Database.Add_Account(LA, this.Customer_ID, Add);
            }
            System.out.println("New loan Account is opened");
        }
        _Database.Put_metadata();
    }

    public void Print_Transaction_Details() throws FileNotFoundException, IOException
    {
        System.out.println("Enter Customer ID");
        String Customer_ID = sc.next();
        sc.nextLine();
        Lst = _Database.Get_Transactions(Customer_ID);
        System.out.println("Printing Few Transactions");
        Print(Lst, "Transaction");
    }

    public void Print_Customer_Details() throws IOException
    {
        System.out.println("Enter Customer ID");
        String Customer_ID = sc.next();
        Lst = _Database.Return_Details(Customer_ID);
        System.out.println("\nPrinting the customer details\n");
        Print(Lst, "Customer");
    }

    public void Print_Account_Details() throws FileNotFoundException, IOException
    {
        System.out.println("Enter Customer ID");
        String Customer_ID = sc.next();
        Lst = _Database.Return_Accounts(Customer_ID);
        System.out.println("\nPrinting all accounts details\n");
        Print(Lst, "Account");
    }
}





class Customer extends Bank
{
    private Double Balance, Principal_Amount;

    public void Load_Customer()
    {
        System.out.println("Please Enter the Customer_ID");
        this.Customer_ID = sc.nextLine();
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
        Lst = _Database.Return_Account(this.Customer_ID, this.Account_ID);

        Print(Lst, "Account");

        this.AccountType = Lst.get(1)[1];
        System.out.println(this.AccountType);
        System.out.println(this.AccountType.length());
        this.Balance = Double.parseDouble(Lst.get(1)[3]);

        if(this.AccountType.equals("Saving"))
        {
            System.out.println("In Load Account");
            SA = new SavingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, this.Balance, Saving_Interest_Rate);
        }
        else if(this.AccountType == "Checking")
        {
            CA = new CheckingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, this.Balance, CreditLimit);
        }
        else if(this.AccountType == "Loan")
        {
            this.Principal_Amount = Double.parseDouble(Lst.get(1)[4]);
            LA = new loanAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, this.Balance, Loan_Interest_Rate, this.Principal_Amount);
        }
        else
            System.out.println("No match");

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
        System.out.println("Yes there in");
        if(this.AccountType == "Saving")
        {
            System.out.println("\nCurrent Balance is : " + SA.balanceEnquiry());
        }
        else if(this.AccountType == "Checking")
        {
            System.out.println("\nCurrent Balance is : " + CA.balanceEnquiry());
        }
        else if(this.AccountType == "Loan")
        {
            System.out.println("\nCurrent Balance is : " + LA.balanceEnquiry());
        }
    }

    public void Check_Details()
    {
        System.out.println("The Registered details are : ");
        if(this.AccountType == "Saving")
        {
            System.out.println("Name : " + SA.get_Name());
            System.out.println("Phone : " + SA.get_Phone());
            System.out.println("Email : " + SA.get_Email());
            System.out.println("Address : " + SA.get_Address());
        }
        else if(this.AccountType == "Checking")
        {
            System.out.println("Name : " + CA.get_Name());
            System.out.println("Phone : " + CA.get_Phone());
            System.out.println("Email : " + CA.get_Email());
            System.out.println("Address : " + CA.get_Address());
        }
        else if(this.AccountType == "Loan")
        {
            System.out.println("Name : " + LA.get_Name());
            System.out.println("Phone : " + LA.get_Phone());
            System.out.println("Email : " + LA.get_Email());
            System.out.println("Address : " + LA.get_Address());
        }
        
    }
}
