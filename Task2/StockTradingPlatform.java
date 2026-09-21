import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StockTradingPlatform {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Map<String, Stock> market = new HashMap<>();
    private static final ArrayList<Transaction> transactions = new ArrayList<>();
    private static User user;

    public static void main(String[] args) {

        initializeMarket();

        System.out.println("======================================");
        System.out.println("       STOCK TRADING PLATFORM");
        System.out.println("======================================");

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        System.out.print("Enter initial investment amount: ₹");
        double balance = scanner.nextDouble();

        while (balance < 0) {
            System.out.print("Enter a valid amount: ₹");
            balance = scanner.nextDouble();
        }

        user = new User(name, balance);

        int choice;

        do {
            displayMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    displayMarket();
                    break;

                case 2:
                    buyStock();
                    break;

                case 3:
                    sellStock();
                    break;

                case 4:
                    user.displayPortfolio(market);
                    break;

                case 5:
                    displayTransactions();
                    break;

                case 6:
                    displayPortfolioPerformance();
                    break;

                case 7:
                    System.out.println("\nThank you for using Stock Trading Platform!");
                    break;

                default:
                    System.out.println("\nInvalid choice. Please try again.");
            }

        } while (choice != 7);

        scanner.close();
    }

    private static void initializeMarket() {
        market.put("AAPL", new Stock("AAPL", "Apple Inc.", 225.50));
        market.put("GOOGL", new Stock("GOOGL", "Alphabet Inc.", 195.75));
        market.put("MSFT", new Stock("MSFT", "Microsoft Corp.", 510.25));
        market.put("AMZN", new Stock("AMZN", "Amazon.com Inc.", 230.40));
        market.put("TSLA", new Stock("TSLA", "Tesla Inc.", 410.80));
    }

    private static void displayMenu() {
        System.out.println("\n========== MAIN MENU ==========");
        System.out.println("1. Display Market Data");
        System.out.println("2. Buy Stock");
        System.out.println("3. Sell Stock");
        System.out.println("4. View Portfolio");
        System.out.println("5. Transaction History");
        System.out.println("6. Portfolio Performance");
        System.out.println("7. Exit");
        System.out.println("===============================");
    }

    private static void displayMarket() {
        System.out.println("\n========== MARKET DATA ==========");

        System.out.printf(
                "%-10s %-25s %-15s%n",
                "Symbol",
                "Company",
                "Price"
        );

        System.out.println("-----------------------------------------------");

        for (Stock stock : market.values()) {
            stock.displayStock();
        }

        System.out.println("===============================================");
    }

    private static void buyStock() {
        displayMarket();

        System.out.print("\nEnter stock symbol: ");
        String symbol = scanner.next().toUpperCase();

        Stock stock = market.get(symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        if (user.buyStock(stock, quantity)) {

            transactions.add(
                    new Transaction(
                            "BUY",
                            symbol,
                            quantity,
                            stock.getPrice()
                    )
            );

            System.out.println(
                    "Successfully purchased "
                    + quantity
                    + " shares of "
                    + symbol
            );

        } else {
            System.out.println(
                    "Purchase failed. Check quantity or available balance."
            );
        }
    }

    private static void sellStock() {
        displayMarket();

        System.out.print("\nEnter stock symbol: ");
        String symbol = scanner.next().toUpperCase();

        Stock stock = market.get(symbol);

        if (stock == null) {
            System.out.println("Stock not found.");
            return;
        }

        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        if (user.sellStock(stock, quantity)) {

            transactions.add(
                    new Transaction(
                            "SELL",
                            symbol,
                            quantity,
                            stock.getPrice()
                    )
            );

            System.out.println(
                    "Successfully sold "
                    + quantity
                    + " shares of "
                    + symbol
            );

        } else {
            System.out.println(
                    "Sale failed. You may not own enough shares."
            );
        }
    }

    private static void displayTransactions() {

        System.out.println("\n========== TRANSACTION HISTORY ==========");

        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }

        System.out.printf(
                "%-6s %-8s %-10s %-14s %-14s %s%n",
                "Type",
                "Symbol",
                "Quantity",
                "Price",
                "Total",
                "Date & Time"
        );

        System.out.println(
                "--------------------------------------------------------------------------"
        );

        for (Transaction transaction : transactions) {
            transaction.displayTransaction();
        }

        System.out.println(
                "=========================================================================="
        );
    }

    private static void displayPortfolioPerformance() {

        double currentValue =
                user.calculatePortfolioValue(market);

        System.out.println("\n========== PORTFOLIO PERFORMANCE ==========");

        System.out.printf(
                "Current Portfolio Value : ₹%.2f%n",
                currentValue
        );

        System.out.printf(
                "Available Cash          : ₹%.2f%n",
                user.getCashBalance()
        );

        System.out.println(
                "============================================"
        );
    }
}