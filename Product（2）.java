public class Product {

    private String productId;
    private String name;
    private double price;
    private String category;
    private int stockQuantity;
    private String description;

    public Product(String productId, String name, double price, String category,
                   int stockQuantity, String description) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.category = category;
        this.stockQuantity = stockQuantity;
        this.description = description;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        if (price <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0.");
        }
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity < 0) {
            throw new IllegalArgumentException("Stock quantity cannot be negative.");
        }
        this.stockQuantity = stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public boolean checkStock() {
        return stockQuantity > 0;
    }


    public void reduceStock(int amount) throws OutOfStockException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Reduction amount must be positive.");
        }
        if (amount > stockQuantity) {
            throw new OutOfStockException(
                    "Not enough stock for '" + name + "'. Available: " + stockQuantity + ", requested: " + amount
            );
        }
        stockQuantity -= amount;
    }


    public void displayProduct() {
        System.out.println("----- Product Information -----");
        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Category: " + category);
        System.out.println("Stock Quantity: " + stockQuantity);
        System.out.println("Description: " + description);
    }


    public String toFileString() {
        return productId + "|" + name + "|" + price + "|" + category + "|"
                + stockQuantity + "|" + description;
    }


    public static Product fromFileString(String line) {
        String[] parts = line.split("\\|");
        return new Product(
                parts[0],
                parts[1],
                Double.parseDouble(parts[2]),
                parts[3],
                Integer.parseInt(parts[4]),
                parts.length > 5 ? parts[5] : ""
        );
    }

    @Override
    public String toString() {
        return "[" + productId + "] " + name + " - $" + price
                + " (" + category + ", stock: " + stockQuantity + ")";
    }
}


class OutOfStockException extends Exception {

    public OutOfStockException(String message) {
        super(message);
    }
}


class ProductDemo {

    public static void main(String[] args) {
        Product laptop = new Product("P001", "Laptop", 750.00, "Electronics", 5, "15-inch laptop");

        laptop.displayProduct();
        System.out.println("In stock? " + laptop.checkStock());

        try {
            laptop.reduceStock(3);
            System.out.println("After selling 3, stock is now: " + laptop.getStockQuantity());

            laptop.reduceStock(10); 

        } catch (OutOfStockException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        try {
            laptop.setPrice(-50); 
 should throw
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        try {
            laptop.setStockQuantity(-5); 
 should throw
        } catch (IllegalArgumentException e) {
            System.out.println("Caught expected exception: " + e.getMessage());
        }

        System.out.println("File format: " + laptop.toFileString());

        Product reloaded = Product.fromFileString(laptop.toFileString());
        System.out.println("Reloaded from file string: " + reloaded);
    }
}
