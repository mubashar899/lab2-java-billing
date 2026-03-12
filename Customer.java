import java.util.Scanner;

class Customer {
    private String name;
    private String phone;
    private char packageType;
    private int minutesUsed;
    
    // Constructor
    public Customer(String name, String phone, char packageType, int minutesUsed) {
        this.name = name;
        setPhone(phone);
        this.packageType = packageType;
        setMinutesUsed(minutesUsed);
    }
    
    // Getters and Setters with validation
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getPhone() { return phone; }
    public void setPhone(String phone) {
        if (phone.length() == 11 && phone.matches("\\d+")) {
            this.phone = phone;
        } else {
            throw new IllegalArgumentException("Phone must be 11 digits");
        }
    }
    
    public char getPackageType() { return packageType; }
    public void setPackageType(char packageType) { this.packageType = packageType; }
    
    public int getMinutesUsed() { return minutesUsed; }
    public void setMinutesUsed(int minutesUsed) {
        if (minutesUsed >= 0) {
            this.minutesUsed = minutesUsed;
        } else {
            throw new IllegalArgumentException("Minutes must be >= 0");
        }
    }
    
    // Calculate bill based on package
    public double calculateBill() {
        double bill = 0;
        
        switch (packageType) {
            case 'A':
            case 'a':
                bill = 300;
                if (minutesUsed > 100) {
                    bill += (minutesUsed - 100) * 2;
                }
                break;
                
            case 'B':
            case 'b':
                bill = 500;
                if (minutesUsed > 250) {
                    bill += (minutesUsed - 250) * 1.5;
                }
                break;
                
            case 'C':
            case 'c':
                bill = 800;
                break;
                
            default:
                System.out.println("Invalid package type!");
                return -1;
        }
        
        return bill;
    }
    
    public void displayCustomerInfo() {
        System.out.println("\n--- Customer Details ---");
        System.out.println("Name: " + name);
        System.out.println("Phone: " + phone);
        System.out.println("Package: " + packageType);
        System.out.println("Minutes Used: " + minutesUsed);
        System.out.printf("Total Bill: Rs. %.2f\n", calculateBill());
    }
}

public class CustomerBilling {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String choice;
        
        System.out.println("TELECOM PACKAGE BILLING SYSTEM");
        System.out.println("================================");
        
        do {
            try {
                System.out.print("\nEnter customer name: ");
                String name = sc.nextLine();
                
                System.out.print("Enter phone number (11 digits): ");
                String phone = sc.nextLine();
                
                System.out.print("Enter package type (A/B/C): ");
                char packageType = sc.nextLine().charAt(0);
                
                System.out.print("Enter minutes used: ");
                int minutes = Integer.parseInt(sc.nextLine());
                
                Customer customer = new Customer(name, phone, packageType, minutes);
                customer.displayCustomerInfo();
                
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            
            System.out.print("\nDo you want to add another customer? (yes/no): ");
            choice = sc.nextLine();
            
        } while (choice.equalsIgnoreCase("yes") || choice.equalsIgnoreCase("y"));
        
        System.out.println("\nThank you for using the billing system!");
        sc.close();
    }
}