#include<iostream>
#include<vector>
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
                return true;
            }
            else
            {
                printf("You have crossed you credit limit . you can not withdraw. credit limit is %lf , while blance is %lf",Credit_limit , Check_balance());
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

class Loan : protected Account
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

class Interface : protected Account
{
    int Customer_ID = 0 ;
    string Name , Address , Email , Phone , Account_Type;
    
    public:
        void Open_Account(string Name , string Address , string Email , string Phone , string Account_type)
        {
            this->Name = Name;
            this->Address = Address;
            this->Customer_ID = this->Customer_ID + 1;
            this->Phone = Phone;
            this->Email = Email;
            this->Account_Type = Account_type;
        }
            /*
            cout<<"Enter Full name : ";
            getline(cin, Name);
            cout<<"Enter Full address : ";
            getline(cin, Address);
            cout<<"Enter Phone No : ";
            cin>>Phone;
            cout<<"Enter Email : ";
            cin>>Email;
            ID++;
        }
        void Out()
        {
            
            cout<<"ID is : "<<ID<<endl;
            cout<<"Name is : "<<Name<<endl;
            cout<<"Address is : "<<Address<<endl;
            cout<<"Mobile no is : "<<Phone<<endl;
            cout<<"Email is : "<<Email<<endl;
        }*/

};

class Drive
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


}