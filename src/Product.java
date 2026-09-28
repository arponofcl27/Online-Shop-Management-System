public class Product {

    private String productId;
    private String name;
    private double price;
    private String category;
    private int stockQuantity;
    private String description;

    public Product(String productId, String name, double price,
                   String category, int stockQuantity, String description) {

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

    public double getPrice() {
        return price;
    }

    public String getCategory() {
        return category;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public String getDescription() {
        return description;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        if (price > 0) {
            this.price = price;
        }
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setStockQuantity(int stockQuantity) {
        if (stockQuantity >= 0) {
            this.stockQuantity = stockQuantity;
        }
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean checkStock() {
        return stockQuantity > 0;
    }

    public void reduceStock(int amount) throws OutOfStockException {

        if (amount <= 0) {
            return;
        }

        if (amount > stockQuantity) {
            throw new OutOfStockException(
                    "Not enough stock available for " + name
            );
        }

        stockQuantity -= amount;
    }

    public void displayProduct() {

        System.out.println("Product ID: " + productId);
        System.out.println("Name: " + name);
        System.out.println("Price: " + price);
        System.out.println("Category: " + category);
        System.out.println("Stock: " + stockQuantity);
        System.out.println("Description: " + description);
    }

    public String toFileString() {

        return productId + "," +
                name + "," +
                price + "," +
                category + "," +
                stockQuantity + "," +
                description;
    }

    public static Product fromFileString(String line) {

        String[] data = line.split(",");

        if (data.length < 6) {
            return null;
        }

        return new Product(
                data[0],
                data[1],
                Double.parseDouble(data[2]),
                data[3],
                Integer.parseInt(data[4]),
                data[5]
        );
    }

    @Override
    public String toString() {

        return name + " - $" + price +
                " | Stock: " + stockQuantity;
    }
}