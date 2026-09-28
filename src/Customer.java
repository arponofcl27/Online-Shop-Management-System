import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Customer extends User {

    private String address;

    public Customer(String userId, String name,
                    String password, String address) {

        super(userId, name, password);
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

        if (order != null) {
            System.out.println(
                    "Order placed successfully by " + getName()
            );
        }
    }

    public String getFileData() {

        return getUserId() + "," +
                getName() + "," +
                getPassword() + "," +
                address;
    }

    public static List<Customer> readCustomers(String filePath) {

        List<Customer> customers = new ArrayList<>();

        try (BufferedReader reader =
                     new BufferedReader(new FileReader(filePath))) {

            String line;

            while ((line = reader.readLine()) != null) {

                String[] data = line.split(",");

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

        } catch (IOException e) {
            System.out.println("Could not read customer file.");
        }

        return customers;
    }

    public static Customer registerCustomer(
            String filePath,
            String userId,
            String name,
            String password,
            String address) {

        List<Customer> customers = readCustomers(filePath);

        for (Customer customer : customers) {

            if (customer.getUserId().equals(userId)) {
                return null;
            }
        }

        Customer newCustomer =
                new Customer(userId, name, password, address);

        try (BufferedWriter writer =
                     new BufferedWriter(
                             new FileWriter(filePath, true))) {

            writer.write(newCustomer.getFileData());
            writer.newLine();

        } catch (IOException e) {
            System.out.println("Could not save customer.");
            return null;
        }

        return newCustomer;
    }

    public static Customer loginCustomer(
            String filePath,
            String name,
            String password) {

        List<Customer> customers = readCustomers(filePath);

        for (Customer customer : customers) {

            if (customer.getName().equals(name)
                    && customer.getPassword().equals(password)) {

                return customer;
            }
        }

        return null;
    }
}