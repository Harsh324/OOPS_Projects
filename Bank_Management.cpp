#include<iostream>

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
            //
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
    
};