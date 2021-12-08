#include<iostream>
#include<vector>
#include<fstream>

using namespace std;

class Account
{
    protected:
        
        double Balance;
        void Deposit(double Amount)
        {
            Balance += Amount;
        }

        bool Withdraw(double Amount)
        {
            return true;
        }

        double Check_balance()
        {
            return Balance;
        }

};

class Checking_Account : protected Account
{
    double Credit_limit;
    protected:
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
    double Interest_rate;
    protected:
        void Interest_Addition()
        {
            double SI = ( Balance * Interest_rate ) / 1200;
            Balance += SI;
        }
};


class Loan_account : protected Account
{
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


class admin : protected Account
{
    int Customer_ID = 0 ;
    string Name , Address , Email , Phone , Account_Type;
    
    public:

        void review()
        {
            cout<<"Name : "<<this->Name<<endl;
            cout<<"Customer ID : "<<this->Customer_ID<<endl;
            cout<<"Address : "<<this->Address<<endl;
            cout<<"Email ID : "<<this->Email<<endl;
            cout<<"Phone no. : "<<this->Phone<<endl;
            cout<<"Account type : "<<this->Account_Type;
        }
        void Open_account(string Name , string Address , string Email , string Phone, string accounttype)
        {
            this->Name = Name;
            this->Address = Address;
            this->Customer_ID = this->Customer_ID + 1;
            this->Phone = Phone;
            this->Email = Email;
            this->Account_Type = accounttype;
        }

        void Save()
        {
            ifstream File("customers.csv");
            if(!File)
            {
                ofstream File("customers.csv");
                File <<"Customer ID" <<" ; "<< Name <<" ; "<< Address<< " ; "<< Phone <<" ; "<< Email<<endl;
                File.close();
                ofstream File("customers.csv",ios::app);
                File << this->Customer_ID<<" ; "<<this->Name<<" ; "<<this->Address<<" ; "<<this->Phone<<" ; "<<this->Email<<endl;
                File.close();
            }
            else
            {
                ofstream File("customers.csv",ios::app);
                File << this->Customer_ID<<" ; "<<this->Name<<" ; "<<this->Address<<" ; "<<this->Phone<<" ; "<<this->Email<<endl;
                File.close();
            }
            ifstream File1("accounts.csv");
            if(!File1)
            {
                ofstream File1("accounts.csv");
                File1<<"Account ID" << " ; " << "Customer ID" << " ; " << "Type" << " ; " << "Balance" << " ; "<< "Credit Limit" <<" ; "<<"Interest rate" <<" ; " << "Principal amount" << " ; "<<"Loan duration"<<endl;
                
            }
        }
};

class Interface
{
    void Open()
    {
        //
    }

    void Over_view()
    {
        //
    }

    void Save()
    {
        //
    }
};
/*
int main()
{
    string Name , Address , Phone , Email, Account_type;
    Interface Var = Interface();
    Interface Var1 = Interface();
    cout<<"Enter Full name : ";
    getline(cin, Name);
    cout<<"Enter Full address : ";
    getline(cin, Address);
    cout<<"Enter Phone No : ";
    cin>>Phone;
    cout<<"Enter Email : ";
    cin>>Email;
    cout<<"Enter Acount type : Loan or Shaving : ";
    cin>>Account_type;


    Var.Open_Account(Name , Address , Email , Phone , Account_type);
    cout<<"\n*******************"<<endl;
}*/