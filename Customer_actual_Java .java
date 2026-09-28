import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Customer extends User {

    private String address;

    private static Scanner input = new Scanner(System.in);

    public Customer(String userId, String name, String password, String address) {
        super(userId, name, password);

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }

        this.address = address;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getRole() {
        return "Customer";
    }


    public void placeOrder(Order order) {
        System.out.println(getName() + " placed order " + order.getOrderId());
    }


    public String getFileData() {
        return getUserId() + "," + getName() + "," + getPassword() + "," + address;
    }


    public static ArrayList<Customer> readCustomers(String filePath) throws IOException {
        ArrayList<Customer> customers = new ArrayList<Customer>();
        File file = new File(filePath);

        if (!file.exists()) {
            return customers;
        }

        Scanner fileReader = new Scanner(file);

        while (fileReader.hasNextLine()) {
            String line = fileReader.nextLine();
            String[] data = line.split(",");

            if (data.length == 4) {
                Customer customer = new Customer(data[0], data[1], data[2], data[3]);
                customers.add(customer);
            }
        }

        fileReader.close();
        return customers;
    }


    public static void registerCustomer(String filePath) throws IOException, DuplicateUserException {

        System.out.print("Enter user ID: ");
        String userId = input.nextLine();

        System.out.print("Enter name: ");
        String name = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        System.out.print("Enter address: ");
        String address = input.nextLine();

        ArrayList<Customer> customers = readCustomers(filePath);

        for (Customer customer : customers) {
            if (customer.getUserId().equalsIgnoreCase(userId)) {
                throw new DuplicateUserException("User ID '" + userId + "' is already registered.");
            }
        }

        Customer customer = new Customer(userId, name, password, address);

        File file = new File(filePath);
        File folder = file.getParentFile();

        if (folder != null && !folder.exists()) {
            folder.mkdirs();
        }

        FileWriter fileWriter = new FileWriter(file, true);
        PrintWriter writer = new PrintWriter(fileWriter);
        writer.println(customer.getFileData());
        writer.close();

        System.out.println("Registration successful.");
    }


    public static void loginCustomer(String filePath) throws IOException {

        System.out.print("Enter user ID: ");
        String userId = input.nextLine();

        System.out.print("Enter password: ");
        String password = input.nextLine();

        ArrayList<Customer> customers = readCustomers(filePath);

        for (Customer customer : customers) {
            boolean correctId = customer.getUserId().equalsIgnoreCase(userId);
            boolean correctPassword = customer.getPassword().equals(password);

            if (correctId && correctPassword) {
                System.out.println("Login successful. Welcome " + customer.getName());
                return;
            }
        }

        System.out.println("Wrong user ID or password.");
    }
}


abstract class User {

    private String userId;
    private String name;
    private String password;

    public User(String userId, String name, String password) {
        this.userId = userId;
        this.name = name;
        this.password = password;
    }

    public String getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public abstract String getRole();
}


class DuplicateUserException extends Exception {
    public DuplicateUserException(String message) {
        super(message);
    }
}


class CustomerDemo {

    public static void main(String[] args) {
        String filePath = "data/customers.txt";
        Scanner menuInput = new Scanner(System.in);
        int choice = 0;

        while (choice != 3) {
            System.out.println();
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            try {
                choice = Integer.parseInt(menuInput.nextLine());

                if (choice == 1) {
                    Customer.registerCustomer(filePath);
                } else if (choice == 2) {
                    Customer.loginCustomer(filePath);
                } else if (choice == 3) {
                    System.out.println("Program closed.");
                } else {
                    System.out.println("Invalid choice.");
                }

            } catch (DuplicateUserException exception) {
                System.out.println(exception.getMessage());

            } catch (IOException exception) {
                System.out.println("File error: " + exception.getMessage());

            } catch (NumberFormatException exception) {
                System.out.println("Please enter a number from 1 to 3.");

            } catch (IllegalArgumentException exception) {
                System.out.println("Invalid input: " + exception.getMessage());
            }
        }
    }
}