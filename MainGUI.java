import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Customer extends User {

    private String address;

    private static Scanner scanner = new Scanner(System.in);

    // Constructor
    public Customer(String userId, String name, String password, String address) {
        super(userId, name, password);

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        if (password == null || password.length() < 4) {
            throw new IllegalArgumentException(
                    "Password must contain at least 4 characters."
            );
        }

        this.address = address;
    }

    // Getter
    public String getAddress() {
        return address;
    }

    // Setter
    public void setAddress(String address) {
        this.address = address;
    }

    // Overriding getRole() from User
    @Override
    public String getRole() {
        return "Customer";
    }

    // Used when a customer places an order
    public void placeOrder(Order order) {
        System.out.println(
                "Customer " + getName() +
                        " placed order " + order.getOrderId()
        );
    }

    // Convert customer information into file format
    public String getFileData() {
        return getUserId() + "," +
                getName() + "," +
                getPassword() + "," +
                getAddress();
    }

    // Read customers from file
    public static ArrayList<Customer> readCustomers(String filePath)
            throws IOException {

        ArrayList<Customer> customers = new ArrayList<>();

        File file = new File(filePath);

        if (!file.exists()) {
            return customers;
        }

        try (BufferedReader reader = new BufferedReader(
                new FileReader(file))) {

            String line;

            while ((line = reader.readLine()) != null) {

                if (line.trim().isEmpty()) {
                    continue;
                }

                String[] data = line.split(",", -1);

                if (data.length >= 4) {

                    Customer customer = new Customer(
                            data[0],
                            data[1],
                            data[2],
                            data[3]
                    );

                    customers.add(customer);
                }
            }
        }

        return customers;
    }

    // =========================================================
    // GUI REGISTRATION METHOD
    // =========================================================

    public static Customer registerCustomer(
            String filePath,
            String userId,
            String name,
            String password,
            String address
    ) throws IOException, DuplicateUserException {

        ArrayList<Customer> customers =
                readCustomers(filePath);

        // Check duplicate user ID
        for (Customer customer : customers) {

            if (customer.getUserId().equalsIgnoreCase(userId)) {

                throw new DuplicateUserException(
                        "User ID already exists."
                );
            }
        }

        Customer newCustomer = new Customer(
                userId,
                name,
                password,
                address
        );

        File file = new File(filePath);

        // Create data folder if it does not exist
        File parentFolder = file.getParentFile();

        if (parentFolder != null && !parentFolder.exists()) {
            parentFolder.mkdirs();
        }

        // Append customer to file
        try (PrintWriter writer = new PrintWriter(
                new FileWriter(file, true))) {

            writer.println(newCustomer.getFileData());
        }

        return newCustomer;
    }

    // =========================================================
    // GUI LOGIN / AUTHENTICATION METHOD
    // =========================================================

    public static Customer authenticate(
            String filePath,
            String name,
            String password
    ) throws IOException {

        ArrayList<Customer> customers =
                readCustomers(filePath);

        for (Customer customer : customers) {

            if (customer.getName().equalsIgnoreCase(name)
                    && customer.getPassword().equals(password)) {

                return customer;
            }
        }

        return null;
    }

    // =========================================================
    // OLD CONSOLE REGISTRATION METHOD
    // =========================================================

    public static void registerCustomer(String filePath)
            throws IOException, DuplicateUserException {

        System.out.println("\n--- Customer Registration ---");

        System.out.print("Enter User ID: ");
        String userId = scanner.nextLine();

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        System.out.print("Enter Address: ");
        String address = scanner.nextLine();

        registerCustomer(
                filePath,
                userId,
                name,
                password,
                address
        );

        System.out.println(
                "Registration successful!"
        );
    }

    // =========================================================
    // OLD CONSOLE LOGIN METHOD
    // =========================================================

    public static Customer loginCustomer(String filePath)
            throws IOException {

        System.out.println("\n--- Customer Login ---");

        System.out.print("Enter Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine();

        Customer customer =
                authenticate(filePath, name, password);

        if (customer != null) {

            System.out.println(
                    "Login successful! Welcome "
                            + customer.getName()
            );

            return customer;

        } else {

            System.out.println(
                    "Invalid name or password."
            );

            return null;
        }
    }
}