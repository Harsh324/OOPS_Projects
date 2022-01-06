#include<iostream>
#include<vector>
#include<fstream>
#include<string>
#include<list>

using namespace std;


// customers 
struct customers
{
   int Customer_ID;
   string Name, Address, Phone, Email;
};

// accounts
struct accounts
{
    int Account_ID , Customer_ID;
    string Type;
    double Balance , Credit_limit, Interes_rate, Principal_amt;
    int Loan_duration;
};

// transactions
struct transactions
{
    int Account_ID;
    string Date, Transaction_type;
    double Ammount;
};

// linked list to get all customer details from database at the start of program. 
list <customers> to_customer_List(ifstream &infile);

// linked list to get all account details from database at the start of program. 
list <accounts> to_account_List(ifstream &infile);

// linked list to get all transactions details from database at the start of program. 
list <transactions> to_transactions_List(ifstream &infile);


// Account class
class Account
{
    
    protected:

        double Balance;

        list <customers> Customer;
        list <accounts> account;
        list <transactions> Transaction;
        
        Account()
        {
            ifstream infile("customers.csv");
            ifstream infile1("accounts.csv");
            ifstream infile2("transactions.csv");
            Customer = to_customer_List(infile);
            account = to_account_List(infile1);
            Transaction = to_transactions_List(infile2);
            Balance = 0.0;
        }
        void Deposit(double Amount)
        {
            Balance += Amount;
        }

        bool Withdraw(double Amount)
        {
            if(Amount > Balance)
            {
                Balance -= Amount;
                return true;
            }
            cout<<"Balance is insufficient"<<endl;
            return false;
        }

        double Check_balance()
        {
            return Balance;
        }
};

// Checking account class
class Checking_Account : protected Account
{
    
    protected:
        double Credit_limit;

        bool Withdraw(double Amount)
        {
            if((Balance - Amount) >= 0)
            {
                Balance = Balance - Amount;
                return true;
            }
            else if((Balance - Amount) >= -1 * Credit_limit)
            {
                Balance = Balance - Amount;
                cout<<"Credit limit is reaching deposit to maintain normality"<<endl;
                return true;
            }
            else
            {
                cout<<"You have crossed you credit limit . you can not withdraw. credit limit is "<<Credit_limit<<" , while balance is "<<Check_balance()<<endl;
                return false;
            }
        }
};

// Saving Account class
class Saving_account : protected Account
{
    
    protected:
        double Interest_rate;

        void Interest_Addition()
        {
            double SI = ( Balance * Interest_rate ) / 1200;
            Balance += SI;
        }
};

// Loan Account class
class Loan_account : protected Account
{
    protected:

        double Principle , Interest_Rate;
        int Loan_duration;

        double Interest()
        {
            double SI = ( Principle * Interest_Rate ) / 1200;
            return SI;
        }

        bool Loan_debit()
        {
            while(Loan_duration)
            {
                if(Withdraw(Interest()))
                {
                    Principle = Principle - Interest();
                    return true;
                }
                else
                    return false;
            }
        } 
};

// admin account class
class admin : protected Loan_account
{
    protected:
        // Review the details
        void review(customers Struc)
        {
            cout<<"Name : "<<Struc.Name<<endl;
            cout<<"Address : "<<Struc.Address<<endl;
            cout<<"Email ID : "<<Struc.Email<<endl;
            cout<<"Phone no. : "<<Struc.Phone<<endl;
        }

        // Add new customer
        void New_Customer(customers *Struct)
        {
            for(auto i : Customer)
            {
                if(i.Name == Struct->Name && i.Phone == Struct->Phone && i.Email == Struct->Email)
                {
                    cout<<"User is already registerd"<<endl;
                    return;
                }
            }
            Struct->Customer_ID = Customer.size() + 1;
        }

        // Open account
        void Open_account(accounts Struct, int Customer_id)
        {
            for(auto i : account)
            {
                if(i.Customer_ID == Customer_id && i.Type == Struct.Type)
                {
                    cout<<"account of this type is already opened"<<endl;
                    return;
                }
            }
            Struct.Account_ID = account.size() + 1;
            account.push_back(Struct);
        }
        
        // Saving the details
        void Save(customers Struc)
        {
            Customer.push_back(Struc);
        }

        // Printing all the customer details
        void Get_All()
        {
            cout<<"Outputing all customers "<<endl;
            for(auto i : Customer)
            {
                cout<<"\n*****************\n"<<endl;
                cout<<"ID : "<<i.Customer_ID<<endl;
                cout<<"Name : "<<i.Name<<endl;
                cout<<"Address : "<<i.Address<<endl;
                cout<<"Phone : "<<i.Phone<<endl;
                cout<<"Email : "<<i.Email<<endl;
            }
        }

        // updating database
        void update_database()
        {
            ofstream Customer_file("customers.csv");
            ofstream Account_file("accounts.csv");
            ofstream Transaction_file("transactions.csv");

            Customer_file <<"Customer ID" <<" ; "<< "Name" <<" ; "<< "Address" << " ; "<< "Phone" <<" ; "<< "Email"<<endl;
            for(auto i : Customer)
            {
                Customer_file <<i.Customer_ID<<";"<<i.Name<<";"<<i.Address<<";"<<i.Phone<<";"<<i.Email<<endl;
            }
            Customer_file.close();

            // Account
            Account_file <<"Account ID" << " ; " << "Customer ID" << " ; " << "Type" << " ; " << "Balance" <<" ; " <<"Interest rate" <<" ; " << "Credit Limit" << " ; " << "Principal amount" << " ; "<<"Loan duration"<<endl;
            for(auto i : account)
            {
                Account_file<<i.Account_ID << ";" << i.Customer_ID << ";" << i.Type << ";" << i.Balance <<";"<< i.Interes_rate <<";" << i.Credit_limit <<";"<<i.Principal_amt <<";" << i.Loan_duration <<endl;
            }
            Account_file.close();
        }
};

// Customer account class
class Customer : protected Account
{
    protected:

        // Customer login by customer id and account id
        accounts login(int Customer_id, int Account_id)
        {
            accounts Struc;
            for(auto &i : account)
            {
                if(i.Customer_ID == Customer_id && i.Account_ID == Account_id)
                {
                    Struc = i;
                    break;
                }
            }
            return Struc;
        }

        // Depositing money
        void deposit(transactions Struc, accounts *Struc1)
        {
            for(auto &i : account)
            {
                if(i.Account_ID == Struc1->Account_ID && i.Customer_ID == Struc1->Customer_ID)
                {
                    if(i.Type == Struc1->Type)
                    {
                        Deposit(Struc.Ammount);
                        Struc1->Balance = Check_balance() + Struc.Ammount;
                        Struc1->Interes_rate = 5.0;
                        Struc1->Loan_duration = 0.0;
                        Struc1->Principal_amt = Check_balance() + Struc.Ammount;
                        Struc1->Loan_duration = 0;
                        i = *Struc1;

                        Transaction.push_back(Struc);
                    }
                }
            }
        }

        // withdrawing the money
        void withdraw(accounts *Struct, transactions Struct1)
        {
            for(auto &i : account)
            {
                if(i.Account_ID == Struct->Account_ID && i.Customer_ID == Struct->Customer_ID)
                {
                    Withdraw(Struct1.Ammount);
                    cout<<"Withdrawn ! Current balance is "<<Check_balance()<<endl;
                    Struct->Balance = Check_balance();
                    Struct->Principal_amt = Check_balance();
                    i = *Struct;

                    Transaction.push_back(Struct1);
                }
            }
        }
        
        // Chaeking balance
        void Balance_enquiry(accounts Struct)
        {
            for(auto i : account)
            {
                if(i.Account_ID == Struct.Account_ID && i.Customer_ID == Struct.Customer_ID)
                {
                    cout<<"Balance is : "<<i.Balance<<endl;
                }
            }
        }

        // Get all account details
        void GetAll()
        {
            cout<<"Outputing all customers "<<endl;
            for(auto i : account)
            {
                cout<<"\n*****************\n"<<endl;
                cout<<"Account ID : "<<i.Account_ID<<endl;
                cout<<"Balance : "<<i.Balance<<endl;
                cout<<"Customer ID : "<<i.Customer_ID<<endl;
                cout<<"Credit limit : "<<i.Credit_limit<<endl;
                cout<<"Interest limit : "<<i.Interes_rate<<endl;
                cout<<"Loan duration : "<<i.Loan_duration<<endl;
                cout<<"Principal Amount : "<<i.Principal_amt<<endl;
                cout<<"Type : "<<i.Type<<endl;

            }
        }

        // Saving all list data to files
        void update()
        {
            ofstream Account_file("accounts.csv");
            ofstream Transaction_file("transactions.csv");
            Account_file <<"Account ID" << " ; " << "Customer ID" << " ; " << "Type" << " ; " << "Balance" <<" ; " <<"Interest rate" <<" ; " << "Credit Limit" << " ; " << "Principal amount" << " ; "<<"Loan duration"<<endl;
            for(auto i : account)
            {
                cout<<"Num\n";
                Account_file<<i.Account_ID << ";" << i.Customer_ID << ";" << i.Type << ";" << i.Balance <<";"<< i.Interes_rate <<";" << i.Credit_limit <<";"<<i.Principal_amt <<";" << i.Loan_duration <<endl;
            }
            Account_file.close();

            // Transaction
            Transaction_file <<"Account_ID" <<" ; " << "Date" <<" ; " <<"Transaction type" << " ; " <<"Ammount"<<endl;
            for(auto i : Transaction)
            {
                Transaction_file<<i.Account_ID<<";"<<i.Ammount<<";"<<i.Transaction_type <<";"<<i.Ammount<<endl;
            }
            Transaction_file.close();
        }
};


class Interface : protected admin, protected Customer
{
    public:
        void Main()
        {
            int Num;
            cout<<"Enter 1 if you are admin\nEnter 2 if you are Customer"<<endl;
            cin>>Num;
            if(Num == 1)
            {
                cout<<"\nYou are admin."<<endl;
                cout<<"You can register new customer"<<endl;
                cout<<"You can open account"<<endl;
                int Val= 0;
                customers Struc;
                accounts Struc1;
                while(Val != 6)
                {
                    cout<<"\n"<<endl;
                    cout<<"Enter 1 to register new user\nEnter 2 to review data\nEnter 3 to save record\nEnter 4 to open Account\nEnter 5 to get all customers\nEnter 6 to end process"<<endl;
                    cin>>Val;
                    switch(Val)
                    {
                        case 1:
                            cout<<"Enter Full name : ";
                            getline(cin>>ws, Struc.Name);
                            cout<<"Enter Full address : ";
                            getline(cin, Struc.Address);
                            cout<<"Enter Phone No : ";
                            cin>>Struc.Phone;
                            cout<<"Enter Email : ";
                            cin>>Struc.Email;
                            New_Customer(&Struc);
                            int Num;
                            
                            cout<<"****************************\n"<<endl;
                            cout<<"Review the details again\n"<<endl;
                            review(Struc);
                            break;
                        
                        case 2:
                            cout<<"****************************\n"<<endl;
                            cout<<"Review the details again\n"<<endl;
                            review(Struc);
                            break;
                        
                        case 3:
                            cout<<"****************************\n"<<endl;
                            cout<<"Saving the details."<<endl;
                            Save(Struc);
                            break;

                        case 4:
                            cout<<"****************************\n"<<endl;
                            int Custom_ID;
                            cout<<"Enter Customer ID : ";
                            cin>>Custom_ID;
                            cout<<"Enter account Type: ";
                            cin>>Struc1.Type;
                            Struc1.Customer_ID = Custom_ID;
                            Struc1.Balance = 0.0;
                            Struc1.Credit_limit = 0.0;
                            Struc1.Interes_rate = 0.0;
                            Struc1.Loan_duration = 0;
                            Struc1.Principal_amt = 0.0;
                            Open_account(Struc1, Custom_ID);
                            break;
                        
                        case 5:
                            cout<<"****************************\n"<<endl;
                            Get_All();
                            break;

                        case 6:
                            update_database();
                            cout<<"****************************\n"<<endl;
                            cout<<"Ending the process"<<endl;
                            break;
                    }
                }
            }
            else if(Num == 2)
            {
                transactions TStruct;
                accounts AStruct;
                int Cus_Id, Acc_id;
                cout<<"Enter the Customer ID"<<endl;
                cin>>Cus_Id;
                cout<<"Enter the Account ID"<<endl;
                cin>>Acc_id;
                AStruct = login(Cus_Id, Acc_id);

                cout<<"\nYou are customer."<<endl;
                cout<<"you can deposit, Withdraw."<<endl;
                cout<<"you can check balance also."<<endl;

                TStruct.Account_ID = Acc_id;
        
                int Val = 0;
                double Amt;
                string Type;
                while(Val != 5)
                {
                    cout<<"\n"<<endl;
                    cout<<"Enter 1 to deposit\nEnter 2 to Withdraw\nEnter 3 to check Balance\nEnter 4 to get all account records\nEnter 5 to end procces"<<endl;
                    cin>>Val;
                    switch(Val)
                    {
                        case 1:
                            cout<<"\nEnter the Amount to deposit\n"<<endl;
                            cin>>Amt;
                            TStruct.Ammount = Amt;
                            TStruct.Transaction_type = "Deposit";
                            TStruct.Date = "16/12/2021";
                            deposit(TStruct, &AStruct);
                            break;

                        case 2:
                            cout<<"\nEnter the Amount to withdraw\n"<<endl;
                            cin>>Amt;
                            TStruct.Ammount = Amt;
                            TStruct.Transaction_type = "Withdrawl";
                            withdraw(&AStruct, TStruct);
                            break;
                        
                        case 3:
                            Balance_enquiry(AStruct);
                            break;
                        case 4:
                            GetAll();
                            break;

                        case 5:
                            update();
                            cout<<"****************************\n"<<endl;
                            cout<<"Ending the process"<<endl;
                            break;
                    }
                }
            }
        }
};



int main()
{
    Interface Obj = Interface();
    Obj.Main();
    return 0;
}




list <customers> to_customer_List(ifstream &infile)
{
    string Customer_ID;
    string Name, Address, Phone, Email, endline;
    list <customers> Custom;
    
    while (infile.peek() != EOF)
    {
        customers Struc;
        getline(infile>>ws, Customer_ID, ';');
        getline(infile>>ws, Name, ';');
        getline(infile>>ws, Address, ';');
        getline(infile>>ws, Phone, ';');
        getline(infile>>ws, Email, '\n');
        
        Struc.Customer_ID = atoi(Customer_ID.c_str());
        Struc.Name = Name;
        Struc.Phone = Phone;
        Struc.Email = Email;
        Struc.Address = Address;
        if(Struc.Customer_ID == 0)
            continue;
        Custom.push_back(Struc);
    }
    return Custom;
}

list <accounts> to_account_List(ifstream &infile)
{
    string Account_ID , Customer_ID, Type, Loan_duration;
    string Balance , Credit_limit, Interes_rate, Principal_amt;
    list <accounts> account;
    while(infile.peek() != EOF)
    {
        accounts Struc;
        getline(infile>>ws, Account_ID, ';');
        getline(infile>>ws, Customer_ID, ';');
        getline(infile>>ws, Type, ';');
        getline(infile>>ws, Balance, ';');
        getline(infile>>ws, Interes_rate, ';');
        getline(infile>>ws, Credit_limit, ';');
        getline(infile>>ws, Principal_amt, ';');
        getline(infile>>ws, Loan_duration, '\n');
        
        Struc.Account_ID = atoi(Account_ID.c_str());
        Struc.Customer_ID = atoi(Customer_ID.c_str());
        Struc.Balance = atof(Balance.c_str());
        Struc.Type = Type;
        Struc.Interes_rate = atof(Interes_rate.c_str());
        Struc.Credit_limit = atof(Credit_limit.c_str());
        Struc.Principal_amt = atof(Principal_amt.c_str());
        Struc.Loan_duration = atof(Loan_duration.c_str());
        if(Struc.Account_ID == 0 && Struc.Customer_ID == 0 )
        {
            continue;
        }
        account.push_back(Struc);
    }
    return account;
}

list <transactions> to_transactions_List(ifstream &infile)
{
    string Account_ID, Date, Transaction_type, Ammount;
    list <transactions> Custom;
    while(infile.peek() != EOF)
    {
        getline(infile, Account_ID, ';');
        getline(infile, Date, ';');
        getline(infile, Transaction_type, ';');
        getline(infile, Ammount, '\n');

        transactions Struc;
        Struc.Account_ID = atoi(Account_ID.c_str());
        Struc.Ammount = atof(Ammount.c_str());
        Struc.Date = Date;
        Struc.Transaction_type = Transaction_type;
        if(Struc.Account_ID == 0)
            continue;
        Custom.push_back(Struc);
    }
    return Custom;
}
