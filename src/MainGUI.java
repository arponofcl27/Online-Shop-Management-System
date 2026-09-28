import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainGUI extends JFrame {

    private CardLayout cardLayout;
    private JPanel mainPanel;

    private JTextField loginNameField;
    private JPasswordField loginPasswordField;

    private JTextField registerIdField;
    private JTextField registerNameField;
    private JPasswordField registerPasswordField;
    private JTextField registerAddressField;

    private Customer loggedInCustomer;

    private Product selectedProduct;
    private Order currentOrder;

    private List<Product> products;

    private final String CUSTOMER_FILE = "customers.txt";

    public MainGUI() {

        setTitle("Online Shop Management System");

        setSize(900, 600);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);

        products = new ArrayList<>();

        loadProducts();

        cardLayout = new CardLayout();

        mainPanel = new JPanel(cardLayout);

        mainPanel.add(createLoginPanel(), "LOGIN");
        mainPanel.add(createRegisterPanel(), "REGISTER");
        mainPanel.add(createDashboardPanel(), "DASHBOARD");
        mainPanel.add(createPaymentPanel(), "PAYMENT");
        mainPanel.add(createMobilePaymentPanel(), "MOBILE");
        mainPanel.add(createConfirmationPanel(), "CONFIRMATION");

        add(mainPanel);

        cardLayout.show(mainPanel, "LOGIN");
    }

    private void loadProducts() {

        products.add(
                new Product(
                        "P001",
                        "Laptop",
                        750,
                        "Electronics",
                        5,
                        "Modern laptop"
                )
        );

        products.add(
                new Product(
                        "P002",
                        "Smartphone",
                        450,
                        "Electronics",
                        8,
                        "Latest smartphone"
                )
        );

        products.add(
                new Product(
                        "P003",
                        "Headphones",
                        80,
                        "Accessories",
                        15,
                        "Wireless headphones"
                )
        );

        products.add(
                new Product(
                        "P004",
                        "Keyboard",
                        45,
                        "Accessories",
                        10,
                        "Mechanical keyboard"
                )
        );

        products.add(
                new Product(
                        "P005",
                        "Mouse",
                        25,
                        "Accessories",
                        20,
                        "Wireless mouse"
                )
        );
    }

    private JPanel createLoginPanel() {

        JPanel panel = new JPanel(new GridBagLayout());

        JPanel box = new JPanel(
                new GridLayout(4, 1, 10, 10)
        );

        JLabel title =
                new JLabel(
                        "ONLINE SHOP MANAGEMENT SYSTEM",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        loginNameField =
                new JTextField();

        loginPasswordField =
                new JPasswordField();

        JButton loginButton =
                new JButton("Login");

        JButton registerButton =
                new JButton("Create Account");

        box.add(title);

        box.add(loginNameField);

        box.add(loginPasswordField);

        box.add(loginButton);

        JPanel buttons =
                new JPanel(new FlowLayout());

        buttons.add(loginButton);
        buttons.add(registerButton);

        JPanel wrapper =
                new JPanel(new BorderLayout(10, 10));

        wrapper.add(
                new JLabel(
                        "Name:",
                        SwingConstants.RIGHT
                ),
                BorderLayout.WEST
        );

        wrapper.add(
                loginNameField,
                BorderLayout.CENTER
        );

        JPanel passwordPanel =
                new JPanel(new BorderLayout());

        passwordPanel.add(
                new JLabel("Password:"),
                BorderLayout.WEST
        );

        passwordPanel.add(
                loginPasswordField,
                BorderLayout.CENTER
        );

        JPanel content =
                new JPanel(new BorderLayout(10, 10));

        content.add(title, BorderLayout.NORTH);

        JPanel fields =
                new JPanel(
                        new GridLayout(2, 2, 10, 10)
                );

        fields.add(new JLabel("Name:"));
        fields.add(loginNameField);

        fields.add(new JLabel("Password:"));
        fields.add(loginPasswordField);

        content.add(fields, BorderLayout.CENTER);

        content.add(buttons, BorderLayout.SOUTH);

        panel.add(content);

        loginButton.addActionListener(e -> login());

        registerButton.addActionListener(e ->
                cardLayout.show(mainPanel, "REGISTER")
        );

        return panel;
    }

    private JPanel createRegisterPanel() {

        JPanel panel =
                new JPanel(new GridBagLayout());

        JPanel content =
                new JPanel(
                        new GridLayout(5, 2, 10, 10)
                );

        JLabel title =
                new JLabel(
                        "CREATE CUSTOMER ACCOUNT",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font("Arial", Font.BOLD, 22)
        );

        registerIdField = new JTextField();
        registerNameField = new JTextField();
        registerPasswordField = new JPasswordField();
        registerAddressField = new JTextField();

        content.add(new JLabel("User ID:"));
        content.add(registerIdField);

        content.add(new JLabel("Name:"));
        content.add(registerNameField);

        content.add(new JLabel("Password:"));
        content.add(registerPasswordField);

        content.add(new JLabel("Address:"));
        content.add(registerAddressField);

        JButton registerButton =
                new JButton("Register");

        JButton backButton =
                new JButton("Back");

        JPanel buttons =
                new JPanel();

        buttons.add(registerButton);
        buttons.add(backButton);

        JPanel wrapper =
                new JPanel(new BorderLayout(10, 10));

        wrapper.add(title, BorderLayout.NORTH);

        wrapper.add(content, BorderLayout.CENTER);

        wrapper.add(buttons, BorderLayout.SOUTH);

        panel.add(wrapper);

        registerButton.addActionListener(e ->
                register()
        );

        backButton.addActionListener(e ->
                cardLayout.show(mainPanel, "LOGIN")
        );

        return panel;
    }

    private void register() {

        String id =
                registerIdField.getText().trim();

        String name =
                registerNameField.getText().trim();

        String password =
                new String(
                        registerPasswordField.getPassword()
                );

        String address =
                registerAddressField.getText().trim();

        if (id.isEmpty()
                || name.isEmpty()
                || password.isEmpty()
                || address.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please fill in all fields."
            );

            return;
        }

        Customer customer =
                Customer.registerCustomer(
                        CUSTOMER_FILE,
                        id,
                        name,
                        password,
                        address
                );

        if (customer == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "User ID already exists or registration failed."
            );

            return;
        }

        JOptionPane.showMessageDialog(
                this,
                "Registration successful!"
        );

        registerIdField.setText("");
        registerNameField.setText("");
        registerPasswordField.setText("");
        registerAddressField.setText("");

        cardLayout.show(mainPanel, "LOGIN");
    }

    private void login() {

        String name =
                loginNameField.getText().trim();

        String password =
                new String(
                        loginPasswordField.getPassword()
                );

        if (name.isEmpty() || password.isEmpty()) {

            JOptionPane.showMessageDialog(
                    this,
                    "Please enter name and password."
            );

            return;
        }

        Customer customer =
                Customer.loginCustomer(
                        CUSTOMER_FILE,
                        name,
                        password
                );

        if (customer == null) {

            JOptionPane.showMessageDialog(
                    this,
                    "Invalid name or password."
            );

            return;
        }

        loggedInCustomer = customer;

        refreshDashboard();

        cardLayout.show(
                mainPanel,
                "DASHBOARD"
        );
    }

    private JPanel createDashboardPanel() {

        JPanel panel =
                new JPanel(new BorderLayout(10, 10));

        JLabel title =
                new JLabel(
                        "PRODUCT DASHBOARD",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        panel.add(title, BorderLayout.NORTH);

        JPanel productPanel =
                new JPanel(
                        new GridLayout(0, 2, 15, 15)
                );

        for (Product product : products) {

            JPanel productCard =
                    createProductCard(product);

            productPanel.add(productCard);
        }

        JScrollPane scrollPane =
                new JScrollPane(productPanel);

        panel.add(
                scrollPane,
                BorderLayout.CENTER
        );

        JButton logoutButton =
                new JButton("Logout");

        JPanel bottom =
                new JPanel();

        bottom.add(logoutButton);

        panel.add(
                bottom,
                BorderLayout.SOUTH
        );

        logoutButton.addActionListener(e -> {

            loggedInCustomer = null;

            cardLayout.show(
                    mainPanel,
                    "LOGIN"
            );
        });

        return panel;
    }

    private JPanel createProductCard(Product product) {

        JPanel card =
                new JPanel(
                        new BorderLayout(5, 5)
                );

        card.setBorder(
                BorderFactory.createTitledBorder(
                        product.getName()
                )
        );

        JTextArea info =
                new JTextArea();

        info.setEditable(false);

        info.setText(
                "Product ID: " +
                        product.getProductId() +
                        "\nCategory: " +
                        product.getCategory() +
                        "\nPrice: $" +
                        product.getPrice() +
                        "\nStock: " +
                        product.getStockQuantity() +
                        "\n\n" +
                        product.getDescription()
        );

        JButton buyButton =
                new JButton("BUY NOW");

        card.add(
                info,
                BorderLayout.CENTER
        );

        card.add(
                buyButton,
                BorderLayout.SOUTH
        );

        buyButton.addActionListener(e -> {

            if (!product.checkStock()) {

                JOptionPane.showMessageDialog(
                        this,
                        "This product is out of stock."
                );

                return;
            }

            selectedProduct = product;

            currentOrder =
                    new Order(
                            generateOrderId(),
                            loggedInCustomer
                    );

            currentOrder.addProduct(
                    selectedProduct
            );

            updatePaymentPanel();

            cardLayout.show(
                    mainPanel,
                    "PAYMENT"
            );
        });

        return card;
    }

    private void refreshDashboard() {

        mainPanel.remove(2);

        mainPanel.add(
                createDashboardPanel(),
                "DASHBOARD"
        );

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createPaymentPanel() {

        JPanel panel =
                new JPanel(new GridBagLayout());

        JPanel content =
                new JPanel(
                        new GridLayout(5, 1, 10, 10)
                );

        JLabel title =
                new JLabel(
                        "SELECT PAYMENT METHOD",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        JLabel productLabel =
                new JLabel(
                        "Product: "
                );

        JLabel priceLabel =
                new JLabel(
                        "Price: "
                );

        JButton cashButton =
                new JButton("Cash Payment");

        JButton mobileButton =
                new JButton("bKash / Mobile Payment");

        JButton backButton =
                new JButton("Back");

        content.add(title);
        content.add(productLabel);
        content.add(priceLabel);
        content.add(cashButton);
        content.add(mobileButton);

        JPanel wrapper =
                new JPanel(new BorderLayout(10, 10));

        wrapper.add(
                content,
                BorderLayout.CENTER
        );

        wrapper.add(
                backButton,
                BorderLayout.SOUTH
        );

        panel.add(wrapper);

        cashButton.addActionListener(e ->
                processCashPayment()
        );

        mobileButton.addActionListener(e ->
                cardLayout.show(
                        mainPanel,
                        "MOBILE"
                )
        );

        backButton.addActionListener(e ->
                cardLayout.show(
                        mainPanel,
                        "DASHBOARD"
                )
        );

        return panel;
    }

    private void updatePaymentPanel() {

        if (selectedProduct == null) {
            return;
        }

        cardLayout.show(
                mainPanel,
                "PAYMENT"
        );
    }

    private void processCashPayment() {

        PaymentMethod cashPayment =
                new CashPayment();

        boolean success =
                currentOrder.checkout(
                        cashPayment
                );

        if (!success) {

            JOptionPane.showMessageDialog(
                    this,
                    "Payment failed."
            );

            return;
        }

        try {

            selectedProduct.reduceStock(1);

        } catch (OutOfStockException e) {

            JOptionPane.showMessageDialog(
                    this,
                    e.getMessage()
            );

            return;
        }

        showConfirmation(
                "Cash Payment",
                currentOrder.getTotalAmount()
        );
    }

    private JPanel createMobilePaymentPanel() {

        JPanel panel =
                new JPanel(new GridBagLayout());

        JPanel content =
                new JPanel(
                        new GridLayout(5, 2, 10, 10)
                );

        JLabel title =
                new JLabel(
                        "bKASH PAYMENT",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font("Arial", Font.BOLD, 24)
        );

        JTextField mobileNumberField =
                new JTextField();

        JPasswordField otpField =
                new JPasswordField();

        JLabel balanceLabel =
                new JLabel(
                        "Demo Balance: $1000"
                );

        JButton payButton =
                new JButton("Pay Now");

        JButton backButton =
                new JButton("Back");

        content.add(
                new JLabel("Mobile Number:")
        );

        content.add(
                mobileNumberField
        );

        content.add(
                new JLabel("OTP PIN:")
        );

        content.add(
                otpField
        );

        content.add(
                balanceLabel
        );

        content.add(
                new JLabel("Demo OTP: 123456")
        );

        content.add(
                payButton
        );

        content.add(
                backButton
        );

        JPanel wrapper =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        wrapper.add(
                title,
                BorderLayout.NORTH
        );

        wrapper.add(
                content,
                BorderLayout.CENTER
        );

        panel.add(wrapper);

        payButton.addActionListener(e -> {

            String mobileNumber =
                    mobileNumberField
                            .getText()
                            .trim();

            String otp =
                    new String(
                            otpField.getPassword()
                    );

            if (!mobileNumber.matches(
                    "01[3-9][0-9]{8}"
            )) {

                JOptionPane.showMessageDialog(
                        this,
                        "Enter a valid Bangladesh mobile number."
                );

                return;
            }

            if (!otp.equals("123456")) {

                JOptionPane.showMessageDialog(
                        this,
                        "Invalid demo OTP."
                );

                return;
            }

            PaymentMethod mobilePayment =
                    new MobilePayment(
                            mobileNumber,
                            1000
                    );

            boolean success =
                    currentOrder.checkout(
                            mobilePayment
                    );

            if (!success) {

                JOptionPane.showMessageDialog(
                        this,
                        "Payment failed. Insufficient demo balance."
                );

                return;
            }

            try {

                selectedProduct.reduceStock(1);

            } catch (OutOfStockException ex) {

                JOptionPane.showMessageDialog(
                        this,
                        ex.getMessage()
                );

                return;
            }

            showConfirmation(
                    "bKash Mobile Payment",
                    currentOrder.getTotalAmount()
            );

            mobileNumberField.setText("");
            otpField.setText("");
        });

        backButton.addActionListener(e ->
                cardLayout.show(
                        mainPanel,
                        "PAYMENT"
                )
        );

        return panel;
    }

    private JPanel createConfirmationPanel() {

        JPanel panel =
                new JPanel(new GridBagLayout());

        return panel;
    }

    private void showConfirmation(
            String paymentMethod,
            double amount) {

        JPanel panel =
                new JPanel(
                        new GridLayout(5, 1, 10, 10)
                );

        JLabel title =
                new JLabel(
                        "ORDER CONFIRMED!",
                        SwingConstants.CENTER
                );

        title.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );

        JLabel orderLabel =
                new JLabel(
                        "Order ID: " +
                                currentOrder.getOrderId(),
                        SwingConstants.CENTER
                );

        JLabel productLabel =
                new JLabel(
                        "Product: " +
                                selectedProduct.getName(),
                        SwingConstants.CENTER
                );

        JLabel paymentLabel =
                new JLabel(
                        "Payment: " +
                                paymentMethod,
                        SwingConstants.CENTER
                );

        JLabel amountLabel =
                new JLabel(
                        "Total Paid: $" +
                                String.format(
                                        "%.2f",
                                        amount
                                ),
                        SwingConstants.CENTER
                );

        JButton doneButton =
                new JButton("Thank You - Continue Shopping");

        panel.add(title);
        panel.add(orderLabel);
        panel.add(productLabel);
        panel.add(paymentLabel);
        panel.add(amountLabel);

        JPanel wrapper =
                new JPanel(
                        new BorderLayout(10, 10)
                );

        wrapper.add(
                panel,
                BorderLayout.CENTER
        );

        wrapper.add(
                doneButton,
                BorderLayout.SOUTH
        );

        mainPanel.remove(5);

        mainPanel.add(
                wrapper,
                "CONFIRMATION"
        );

        mainPanel.revalidate();
        mainPanel.repaint();

        cardLayout.show(
                mainPanel,
                "CONFIRMATION"
        );

        doneButton.addActionListener(e -> {

            refreshDashboard();

            cardLayout.show(
                    mainPanel,
                    "DASHBOARD"
            );
        });
    }

    private String generateOrderId() {

        return "ORD" +
                System.currentTimeMillis();
    }

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            MainGUI gui =
                    new MainGUI();

            gui.setVisible(true);
        });
    }
}