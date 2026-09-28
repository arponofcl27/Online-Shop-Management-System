import java.util.ArrayList;
import java.util.List;


public class Order {

    private String orderId;
    private Customer customer;
    private List<Product> products;
    private double totalAmount;
    private String orderStatus; // "PENDING", "COMPLETED", "CANCELLED"

    public Order(String orderId, Customer customer) {
        this.orderId = orderId;
        this.customer = customer;
        this.products = new ArrayList<Product>();
        this.totalAmount = 0.0;
        this.orderStatus = "PENDING";
    }

    public String getOrderId() {
        return orderId;
    }

    public Customer getCustomer() {
        return customer;
    }

    public List<Product> getProducts() {
        return products;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String getOrderStatus() {
        return orderStatus;
    }


    public void addProduct(Product product) {
        products.add(product);
    }


    public double calculateTotal() {
        double total = 0.0;
        for (Product product : products) {
            total += product.getPrice();
        }
        this.totalAmount = total;
        return totalAmount;
    }


    public void checkout(PaymentMethod paymentMethod) throws EmptyCartException, PaymentFailedException {
        if (products.isEmpty()) {
            throw new EmptyCartException("Cannot checkout order " + orderId + " — no products in cart.");
        }

        calculateTotal();

        boolean paymentSuccessful = paymentMethod.pay(totalAmount);

        if (paymentSuccessful) {
            orderStatus = "COMPLETED";
        }
    }


    public void cancelOrder() throws InvalidOrderException {
        if (orderStatus.equals("COMPLETED")) {
            throw new InvalidOrderException("Cannot cancel order " + orderId + " — it is already completed.");
        }
        orderStatus = "CANCELLED";
    }

    public void displayOrder() {
        System.out.println("----- Order Information -----");
        System.out.println("Order ID: " + orderId);
        System.out.println("Customer: " + customer.getName());
        System.out.println("Products: " + products.size());
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Status: " + orderStatus);
    }
}


class EmptyCartException extends Exception {
    public EmptyCartException(String message) {
        super(message);
    }
}


class InvalidOrderException extends Exception {
    public InvalidOrderException(String message) {
        super(message);
    }
}


class OrderDemo {

    public static void main(String[] args) {
        Customer customer = new Customer("U001", "Sydul", "pass123", "Dhaka");

        Product laptop = new Product("P001", "Laptop", 750.00, "Electronics", 5, "15-inch laptop");
        Product mouse = new Product("P002", "Mouse", 15.00, "Electronics", 20, "Wireless mouse");

        Order order1 = new Order("O001", customer);
        order1.addProduct(laptop);
        order1.addProduct(mouse);

        try {
            order1.checkout(new CashPayment());
            order1.displayOrder();
        } catch (EmptyCartException | PaymentFailedException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }


        try {
            order1.cancelOrder();
        } catch (InvalidOrderException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        // --- Test 3: checkout on an empty cart (should throw EmptyCartException) ---
        Order order2 = new Order("O002", customer);
        try {
            order2.checkout(new CashPayment());
        } catch (EmptyCartException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        } catch (PaymentFailedException e) {
            System.out.println("Caught exception: " + e.getMessage());
        }

        // --- Test 4: checkout with MobilePayment that has insufficient balance ---
        Order order3 = new Order("O003", customer);
        order3.addProduct(laptop);

        try {
            order3.checkout(new MobilePayment(50.00)); // laptop costs $750, balance is only $50
        } catch (EmptyCartException e) {
            System.out.println("Caught exception: " + e.getMessage());
        } catch (PaymentFailedException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }
    }
}