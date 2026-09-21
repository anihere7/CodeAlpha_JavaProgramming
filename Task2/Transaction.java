import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Transaction {
    private String type;
    private String symbol;
    private int quantity;
    private double price;
    private LocalDateTime timestamp;

    public Transaction(String type, String symbol, int quantity, double price) {
        this.type = type;
        this.symbol = symbol;
        this.quantity = quantity;
        this.price = price;
        this.timestamp = LocalDateTime.now();
    }

    public void displayTransaction() {
        DateTimeFormatter formatter =
                DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

        double total = quantity * price;

        System.out.printf(
                "%-6s %-8s %-10d ₹%-12.2f ₹%-12.2f %s%n",
                type,
                symbol,
                quantity,
                price,
                total,
                timestamp.format(formatter)
        );
    }
}