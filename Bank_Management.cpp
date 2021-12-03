#include<iostream>

class Account
{
    double Balance;
    protected:
        void Deposit()
        {
            //
        }

        void Withdraw()
        {
            //
        }

        void Check_balance()
        {
            //
        }

};

class Checking_Account : protected Account
{
    double Credit_limit;
    protected:
        void Over_draft()
        {
            //
        }
};

class Saving_account : protected Account
{
    double Interest_rate;
    protected:
        void Interest_Addition()
        {
            //
        }
};

class Loan : protected Account
{
    double Principle , Interest_Rate;
    int Month;
    
};