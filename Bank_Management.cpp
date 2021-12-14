#include<iostream>
#include<vector>
#include<fstream>
#include<string>
#include<list>

using namespace std;


struct customers
{
   int Customer_ID;
   string Name, Address, Phone, Email;
};

struct accounts
{
    int Account_ID , Customer_ID;
    string Type;
    double Balance , Credit_limit, Interes_rate, Principal_amt;
    int Loan_duration;
};


struct transactions
{
    int Account_ID;
    string Date, Transaction_type;
    double Ammount;
};


class Account
{
    protected:
        
        double Balance;
        list <customers> Customer;
        list <accounts> Account;
        list <transactions> Transaction;
    
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


class admin : protected Loan_account
{
    //protected:
        //int Customer_ID = 0 , Account_ID = 0;
        //string Name , Address , Email , Phone , Account_Type;
    
    
    protected:

        void review(customers Struc)
        {
            cout<<"Customer ID : "<<Struc.Customer_ID<<endl;
            cout<<"Name : "<<Struc.Name<<endl;
            cout<<"Address : "<<Struc.Address<<endl;
            cout<<"Email ID : "<<Struc.Email<<endl;
            cout<<"Phone no. : "<<Struc.Phone<<endl;
        }

        void New_Customer(customers Struct)
        {
            for(auto i : Customer)
            {
                if(i.Name == Struct.Name && i.Phone == Struct.Phone && i.Email == Struct.Email)
                {
                    cout<<"User is already registerd"<<endl;
                    return;
                }
            }
            Struct.Customer_ID = Customer.size();
            //Customer.push_back(Struct);
        }
        void Open_account(accounts Struct, int Customer_id)
        {
            for(auto i : Account)
            {
                if(i.Customer_ID == Customer_id && i.Type == Struct.Type)
                {
                    cout<<"account of this type is already opened"<<endl;
                    return;
                }
            }
            //Struct.Customer_ID = Customer_id;
            Struct.Account_ID = Account.size();
            Account.push_back(Struct);
        }

        void Save(customers Struc)
        {
            Customer.push_back(Struc);
        }

        void Get_All()
        {
            cout<<"Outputing all customers "<<endl;
            for(auto i : Customer)
            {
                cout<<"*****************"<<endl;
                cout<<"ID : "<<i.Customer_ID<<endl;
                cout<<"Name : "<<i.Name<<endl;
                cout<<"Address : "<<i.Address<<endl;
                cout<<"Phone : "<<i.Phone<<endl;
                cout<<"Email : "<<i.Email<<endl;
            }
        }
        
        // void review()
        // {
        //     cout<<"Name : "<<this->Name<<endl;
        //     cout<<"Customer ID : "<<this->Customer_ID<<endl;
        //     cout<<"Address : "<<this->Address<<endl;
        //     cout<<"Email ID : "<<this->Email<<endl;
        //     cout<<"Phone no. : "<<this->Phone<<endl;
        //     cout<<"Account type : "<<this->Account_Type;
        // }
        // void Open_account(string Name , string Address , string Email , string Phone, string accounttype)
        // {
        //     this->Name = Name;
        //     this->Address = Address;
        //     this->Customer_ID = this->Customer_ID + 1;
        //     this->Phone = Phone;
        //     this->Email = Email;
        //     this->Account_Type = accounttype;
        // }

        // void Save()
        // {
        //     ifstream File("customers.csv");
        //     if(!File)
        //     {
        //         ofstream File("customers.csv");
        //         File <<"Customer ID" <<" ; "<< Name <<" ; "<< Address<< " ; "<< Phone <<" ; "<< Email<<endl;
        //         File.close();
        //         ofstream File("customers.csv",ios::app);
        //         File << this->Customer_ID<<" ; "<<this->Name<<" ; "<<this->Address<<" ; "<<this->Phone<<" ; "<<this->Email<<endl;
        //         File.close();
        //     }
        //     else
        //     {
        //         ofstream File("customers.csv",ios::app);
        //         File << this->Customer_ID<<" ; "<<this->Name<<" ; "<<this->Address<<" ; "<<this->Phone<<" ; "<<this->Email<<endl;
        //         File.close();
        //     }
        //     ifstream File1("accounts.csv");
        //     if(!File1)
        //     {
        //         ofstream File1("accounts.csv");
        //         File1<<"Account ID" << " ; " << "Customer ID" << " ; " << "Type" << " ; " << "Balance" <<" ; " <<"Interest rate" <<" ; " << "Principal amount" << " ; "<<"Loan duration"<<endl;
        //         File1.close();
        //         ofstream File1("accounts.csv", ios :: app);
        //         File1<<this->Account_ID << " ; " << this->Customer_ID << " ; " << this->Account_Type << " ; " << this->Balance <<" ; "<< this->Interest_Rate <<" ; "<<this->Principle <<" ; " << this->Loan_duration <<endl;
        //         File1.close();
        //     }
        // }
};

class Customer : protected Account
{
    string accountId;
    bool login(int Customer_id, int Account_id)
    {
        for(auto i : Account)
        {
            if(i.Customer_ID == Customer_id && i.Account_ID == Account_id)
                return false;
        }
        return true;

        /*
        ifstream File;
        File.open("customers.txt");
        string CID;
        string Name, address, phone, email;
        int Flag = 0;
        while(getline(File, CID, ';') && Flag == 0)
        {
            getline(File, Name, ';');
            getline(File, address, ';');
            getline(File, phone, ';');
            getline(File, email, '\n');

            if(Name == name && phone == Phone)
            {
                Flag = 1; 
                File.close();
                ifstream File1("accounts.csv");
                int status = 0;
                string type, ACid, Var;
                while(getline(File1, ACid, ';') && status == 0)
                {
                    getline(File1, Var, ';');
                    getline(File1, type, ';');
                    if(Var == CID && type == Type)
                    {
                        status = 1;
                        accountId = ACid;
                        File1.close();
                        return true;
                    }

                }
                cout<<"Account type entered seems not present"<<endl;
            }
        }
        return false;*/
    }

    void deposit(int Customer_id, int Account_id, double Amt, string Type)
    {
        accounts Struc1;
        for(auto i : Account)
        {
            if(i.Account_ID == Account_id && i.Customer_ID == Customer_id)
            {
                if(i.Type == Type)
                {
                    //Deposit(Amt);
                    i.Balance = Check_balance() + Amt;
                    i.Interes_rate = 5.0;
                    i.Loan_duration = 0.0;
                    i.Credit_limit = 0.0;
                    i.Principal_amt = Check_balance() + Amt;

                    transactions Struc;
                    Struc.Account_ID = Account_id;
                    Struc.Transaction_type = "Deposit";
                    Struc.Ammount = Amt;
                    Struc.Date = "13/12/2021";

                    Transaction.push_back(Struc);
                    return ;
                }
            }
        }
        /*
        Deposit(balance);
        ifstream File("accountTransactions.csv");
        if(!File)
        {
            ofstream File("accountTransactions");
            File << "Account ID" <<";" << "Date" << ";"<< "Transaction Type"<<";" << "Amount"<<endl;
            File.close();
            ofstream File("accountTransactions");
            File <<accountId <<";" << Date << ";" <<"Deposit" <<";" <<balance<<endl;
            File.close();
        }*/
    }

    void withdraw(int Customer_id, int Account_id, double Amt, string Type)
    {
        accounts Struc1;
        for(auto i : Account)
        {
            if(i.Account_ID == Account_id && i.Customer_ID == Customer_id)
            {
                if(i.Type == Type)
                {
                    if(Withdraw(Amt))
                    {
                        
                    }
                }
            }
        }
    }

    double Balance_enquiry(int Customer_id, int Account_id, string Type)
    {
        for(auto i : Account)
        {
            if(i.Account_ID == Account_id && i.Customer_ID == Customer_id && i.Type == Type)
            {
                return i.Balance;
            }
        }
        cout<<"Some error occured"<<endl;
        return 0.0;
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
                cout<<"You are admin."<<endl;
                cout<<"You can register new customer"<<endl;
                cout<<"You can open account"<<endl;
                int Val= 0;
                customers Struc;
                accounts Struc1;
                while(Val != 6)
                {
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
                            New_Customer(Struc);
                            break;
                        
                        case 2:
                            cout<<"****************************"<<endl;
                            cout<<"Review the details again"<<endl;
                            review(Struc);
                            break;
                        
                        case 3:
                            cout<<"****************************"<<endl;
                            cout<<"Saving the details."<<endl;
                            Save(Struc);
                            break;

                        case 4:
                            cout<<"****************************"<<endl;
                            int Custom_ID;
                            cout<<"Enter Customer ID : ";
                            cin>>Custom_ID;
                            cout<<"Enter account Type: ";
                            cin>>Struc1.Type;
                            Struc1.Customer_ID = Custom_ID;
                            Open_account(Struc1, Custom_ID);
                            break;
                        
                        case 5:
                            cout<<"****************************"<<endl;
                            cout<<"Ending the process"<<endl;
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