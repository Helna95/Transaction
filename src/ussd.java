import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ussd {
    public static void main(String[] args)
    {
        BankAccount obj1=new BankAccount("To our services"," ");
        obj1.showMenu();

    }

}

class  Customer{
    String customerName;
    int customerId;
    String password;

    Customer(String customerName,int customerId,String password){
        this.customerName = customerName;
        this.customerId = customerId;
        this.password = password;
    }
}

class BankAccount {
    double balance;
    double previousTransaction;
    String customerName;
    String customerId;
    List<Customer> customerList = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    BankAccount(String cName, String cId) {
        customerName = cName;
        customerId = cId;
    }


    void deposit(double amount) {
        if (amount != 0) {
            this.balance += amount;
            this.previousTransaction = amount;
        }
    }

    void withdraw(double amount) {
        if (amount != 0) {
            balance = balance - amount;
            previousTransaction = -amount;
        }
    }

    void getPreviousTransaction() {
        if (previousTransaction > 0) {
            System.out.println(("deposited :" + previousTransaction));
        } else if (previousTransaction < 0) {
            System.out.println("withdraw :" + Math.abs(previousTransaction));
        } else {
            System.out.println("no transaction occured ");
        }
    }

    public boolean isUserLogin(){
        System.out.println("1. Create new user");
        System.out.println("2. Login");
        int choice = Integer.parseInt(scanner.nextLine());
        Customer customer = null;
        if(choice == 1){
            createCustomer();
            customer = login();
        }else if(choice == 2) {
            customer = login();
        }else {
            System.exit(1);
        }
       return customer != null;
    }

    void showMenu() {
        char option = '\0';
        System.out.println("\n");
         if(!isUserLogin()) {
             System.err.println("User not found");
             return;
         }
        System.out.println("Welcome " + customerName);
        System.out.println("A.check balance");
        System.out.println("B.Deposit");
        System.out.println("C.Withdraw");
        System.out.println("D.Previous Transaction");
        System.out.println("E.Exit");
        do {

            System.out.println("**********************************************************");
            System.out.println("Enter the Option");
            System.out.println("**********************************************************");
            option = scanner.next().charAt(0);
            System.out.println("\n");

            switch (option)
            {
                case 'A':
                    System.out.println("*****************************************************");
                    System.out.println("Your Balance is :"+this.balance);
                    System.out.println("*****************************************************");
                    System.out.println("\n");
                    break;

                case 'B':
                    System.out.println("*****************************************************");
                    System.out.println("Enter the mount you want to deposit");
                    System.out.println("*****************************************************");
                    Double amount=scanner.nextDouble();
                    deposit(amount);
                    System.out.println("\n");
                    break;

                case'C':
                    System.out.println("*****************************************************");
                    System.out.println("Enter the mount you want to withdraw");
                    System.out.println("*****************************************************");
                    Double amount2=scanner.nextDouble();
                    withdraw(amount2);
                    System.out.println("\n");
                    break;

                case 'D':
                    System.out.println("*****************************************************");
                    getPreviousTransaction();
                    System.out.println("*****************************************************");
                    System.out.println("\n");
                    break;

                case'E':
                    System.out.println("****************************************************");
                    break;

                default:
                    System.out.println("invalid option!!!.Please enter again");
                    break;


            }


        }while (option!='E');

            System.out.println("Thanks for using our services!!!");



    }

    private void createCustomer() {
        String username = getName() ;
        String password = getPassword();
        int id = getId();
        customerList.add(new Customer(username,id,password));
        System.out.println("Customer with username = "+username+"  and id = "+id+" has been created");
    }

    private  String getName(){
        System.out.println("Enter username");
        return scanner.nextLine();
    }
    private  String getPassword(){
        System.out.println("Enter password");
        return scanner.nextLine();
    }

    private  int getId(){
        Random rand = new Random();
        return rand.nextInt(1000000);
    }

    private Customer login() {
        String username = getName();
        String password = getPassword();
        Customer customer = null;
        boolean customerFound  = false;
        for(int i=0;i< customerList.size();i++){
            if(customerList.get(i).customerName.equals(username) && customerList.get(i).password.equals(password)){
              customerFound = true;
              customer = customerList.get(i);
              break;
            }
        }
        return  customer;
    }


}