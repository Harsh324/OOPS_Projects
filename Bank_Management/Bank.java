package Bank_Management;
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
        System.out.println("\nSelect the Account type\nSaving (1)\nChecking (2)\nLoan (3)");
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
        System.out.println("\nEnter 1 if new user\nEnter 2 if already user");
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
        System.out.println("\nPlease Enter Customer_ID");
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
        
        System.out.println("\nEnter Customer Name : ");
        this.Name = sc.nextLine();
        sc.nextLine();
        System.out.println("\nEnter Customer Phone : ");
        this.Phone = sc.nextLine();
        sc.nextLine();
        System.out.println("\nEnter Customer Email : ");
        this.Email = sc.nextLine();
        sc.nextLine();
        System.out.println("\nEnter Customer Address : ");
        this.Address = sc.nextLine();
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
        if(this.AccountType.equals("Saving"))
        {
            SA = new SavingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, Saving_Interest_Rate);
            String Add = Double.toString(Saving_Interest_Rate);
            if(Flag)
                _Database.Add_Account(SA, this.Customer_ID, Add);
            
            else
            {
                this.Customer_ID = _Database.Add_Details(SA);
                _Database.Add_Account(SA, this.Customer_ID, Add);
            }
            System.out.println("New Saving Account is opened");
        }

        else if(this.AccountType.equals("Checking"))
        {
            CA = new CheckingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, CreditLimit);
            String Add = Double.toString(CreditLimit);
            if(Flag)
                _Database.Add_Account(CA, this.Customer_ID, Add);

            else
            {
                this.Customer_ID =  _Database.Add_Details(CA);
                _Database.Add_Account(CA, this.Customer_ID, Add);
            }
            System.out.println("New Checking Account is opened");
        }

        
        else if(this.AccountType.equals("Loan"))
        {
            LA = new loanAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, 0.0, Loan_Interest_Rate, 5000.0);
            String Add = Double.toString(LA.get_Principal()) + ";" + Double.toString(Loan_Interest_Rate);
            if(Flag)
                _Database.Add_Account(LA, this.Customer_ID, Add);

            else
            {
                this.Customer_ID =  _Database.Add_Details(LA);
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
        System.out.println("\nPlease Enter the Customer_ID\n");
        this.Customer_ID = sc.nextLine();
        Lst = _Database.Return_Details(this.Customer_ID);

        this.Name = Lst.get(1)[1];
        this.Phone = Lst.get(1)[2];
        this.Email = Lst.get(1)[3];
        this.Address = Lst.get(1)[4];
    }

    public void Load_Account()
    {
        System.out.println("\nPlease Enter the Account ID\n");
        this.Account_ID = sc.nextLine();
        Lst = _Database.Return_Account(this.Customer_ID, this.Account_ID);
        this.AccountType = Lst.get(1)[1];
        this.Balance = Double.parseDouble(Lst.get(1)[2]);

        if(this.AccountType.equals("Saving"))
        {
            SA = new SavingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, this.Balance, Saving_Interest_Rate);
        }
        else if(this.AccountType.equals("Checking"))
        {
            CA = new CheckingAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, this.Balance, CreditLimit);
        }
        else if(this.AccountType.equals("Loan"))
        {
            this.Principal_Amount = Double.parseDouble(Lst.get(1)[4]);
            LA = new loanAccount(this.Name, this.Phone, this.Email, this.Address, this.AccountType, this.Balance, Loan_Interest_Rate, this.Principal_Amount);
        }
        else
            System.out.println("No match");

    }

    public void Deposit_Money() throws FileNotFoundException, IOException
    {
        System.out.println("\nEnter the Amount to Deposit\n");
        Double Var = sc.nextDouble();
        String Transaction_Details = "";
        if(this.AccountType.equals("Saving"))
        {
            SA.deposit(Var);
            System.out.println("\nCurrent Balance is : " + SA.balanceEnquiry());

        }
        else if(this.AccountType.equals("Checking"))
        {
            CA.deposit(Var);
            System.out.println("\nCurrent Balance is : " + CA.balanceEnquiry());
        }
        else if(this.AccountType.equals("Loan"))
        {
            LA.deposit(Var);
            System.out.println("\nCurrent Balance is : " + LA.balanceEnquiry());
        }
        _Database.Add_Transaction(Var,this.Account_ID, this.Customer_ID, "Deposit", this.AccountType);
    }

    public void Withdraw_Money() throws FileNotFoundException, IOException
    {
        System.out.println("\nEnter the Amount to Withdraw\n");
        Double Var = sc.nextDouble();

        if(this.AccountType.equals("Saving"))
        {
            SA.withdraw(Var);;
            System.out.println("\nCurrent Balance is : " + SA.balanceEnquiry());
        }
        else if(this.AccountType.equals("Checking"))
        {
            CA.withdraw(Var);
            System.out.println("\nCurrent Balance is : " + CA.balanceEnquiry());
        }
        else if(this.AccountType.equals("Loan"))
        {
            LA.withdraw(Var);
            System.out.println("\nCurrent Balance is : " + LA.balanceEnquiry());
        }
        _Database.Add_Transaction(Var,this.Account_ID, this.Customer_ID, "Withdraw", this.AccountType);
    }

    public void Check_Balance()
    {
        //System.out.println("Yes there in");
        if(this.AccountType.equals("Saving"))
        {
            System.out.println("\nCurrent Balance is : " + SA.balanceEnquiry());
        }
        else if(this.AccountType.equals("Checking"))
        {
            System.out.println("\nCurrent Balance is : " + CA.balanceEnquiry());
        }
        else if(this.AccountType.equals("Loan"))
        {
            System.out.println("\nCurrent Balance is : " + LA.balanceEnquiry());
        }
    }

    public void Check_Details()
    {
        System.out.println("\nThe Registered details are : \n");
        Account Acc = null;
        if(this.AccountType.equals("Saving"))
            Acc = SA;

        else if(this.AccountType.equals("Checking"))
            Acc = CA;

        else if(this.AccountType.equals("Loan"))
            Acc = LA;

        System.out.println("Name : " + Acc.get_Name());
        System.out.println("Phone : " + Acc.get_Phone());
        System.out.println("Email : " + Acc.get_Email());
        System.out.println("Address : " + Acc.get_Address());
    }

    public void Load_to_Database() throws IOException
    {
        String Add = "";
        if(this.AccountType.equals("Saving"))
        {
            Add = Double.toString(Saving_Interest_Rate);
            _Database.Account_to_Database(SA, this.Customer_ID, this.Account_ID, Add);
        }
        else if(this.AccountType.equals("Checking"))
        {
            Add = Double.toString(CreditLimit);
            _Database.Account_to_Database(CA, this.Customer_ID, this.Account_ID,  Add);
        }
        else if(this.AccountType.equals("Loan"))
        {
            Add = Double.toString(LA.get_Principal()) + ";" + Double.toString(Loan_Interest_Rate);
            _Database.Account_to_Database(LA, this.Customer_ID, this.Account_ID,  Add);
        }

    }
}
