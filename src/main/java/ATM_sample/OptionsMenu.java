package ATM_sample;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Scanner;

public class OptionsMenu extends Account {
    Scanner inputMenu = new Scanner(System.in);
    DecimalFormat decimalFormat = new DecimalFormat("'$'##,##0.00");
    HashMap<String, String> data = new HashMap<String, String>();

    public void getLogin() {
        int x = 1;
        do {
            try {
                data.put("11111", "2222");
                data.put("22222", "3333");

                System.out.println("Welcome to ATM machine");
                System.out.println("Please enter your customer number");

                // customer number input
                setCustomerId(inputMenu.next());
                // customer pin-code
                System.out.println("Please enter your pin-code");
                setPinCode(inputMenu.next());
            } catch (Exception e) {
                System.out.println(e);
                exitError();
                x = destroy();
            }
        } while (x == 1);
    }

    @Override
    public void setPinCode(String pinCode) {
        super.setPinCode(pinCode);
        if (loginToAccount(data)) {
            getAccountType();
        }
        getLogin();
    }

    private void getAccountType() {
        System.out.println("Select action you want to do:");
        System.out.println("1 - Check Account");
        System.out.println("2 - Operations with Account");
        exitMethod();

        int selection = inputMenu.nextInt();

        switch (selection) {
            case 1:
                System.out.println("Your account balance amount is $" + getBalance());
                getOptions();
                break;
            case 2:
                getOptions();
                break;
            case 3:
                exitMethod();
                break;
            default:
                exitError();
                break;
        }
    }

    private void getOptions() {

        System.out.println("Choose action:");
        System.out.println("1 - Withdraw amount");
        System.out.println("2 - Deposit amount");
        exitMethod();

        int selection = inputMenu.nextInt();

        switch (selection) {
            case 1:
                System.out.print("Please write the required amount to withdraw: $");
                withdrawMessages(isDouble(inputMenu.next()));
                break;
            case 2:
                System.out.print("Please write the required amount to deposit: $");
                deposit(isDouble(inputMenu.next()));
                System.out.println("Your account balance amount is $" + getBalance());
                toContinue();
                break;
            case 3:
                toContinue();
                break;
            default:
                exitError();
                break;
        }
    }

    private void exitMethod() {
        System.out.println("3 - Exit");
    }

    private void exitError() {
        System.err.println("Unknown error occurred");
        toContinue();
    }

    private void toContinue() {
        System.out.println("Do you wont to continue? (y/n):");
        if (inputMenu.next().equals("y")) {
            getLogin();
        }
        System.out.println("Your session is closed! Thank you for using ATM");
    }

    private void withdrawMessages(Double val) {
        if (getBalance() > val) {
            System.out.println("Please get your money.");
            withdraw(val);
        } else {
            System.out.print("Your balance is less " + getBalance() + " .Please choose another amount $: ");
            withdrawMessages(isDouble(inputMenu.next()));
        }
        toContinue();
    }

    private Integer destroy() {
        inputMenu.close();
        return 2;
    }

    private Double isDouble(String val) {
        try {
            return Double.parseDouble(val);
        } catch (Exception e) {
            System.err.print("Put numeric values only: ");
            return isDouble(inputMenu.next());
        }
    }
}
