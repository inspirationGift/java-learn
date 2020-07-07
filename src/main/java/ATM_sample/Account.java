package ATM_sample;


import java.util.HashMap;


public class Account implements AccountOperation {

    private String customerId;
    private String pinCode;
    private Double balance;

    public Account() {
        this.balance = 0.0;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getPinCode() {
        return pinCode;
    }

    public void setPinCode(String pinCode) {
        this.pinCode = pinCode;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    public Boolean loginToAccount(HashMap<String, String> data) {
        if (data.containsKey(this.customerId)) {
            if (data.get(this.customerId).equals(this.pinCode)) {
                return true;
            } else {
                System.err.println("Your pin-code is wrong");
                return false;
            }
        } else {
            System.err.println("Your customer id is wrong");
            return false;
        }
    }

    public Double withdraw(Double draw) {
        if (this.balance - draw > 0) {
            this.balance -= draw;
            return this.balance;
        }
        return this.balance;
    }

    public Double deposit(Double sum) {
        this.balance += sum;
        return this.balance;
    }
}
