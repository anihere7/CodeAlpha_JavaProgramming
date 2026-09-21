import java.util.HashMap;
import java.util.Map;

public class User {
    private String name;
    private double cashBalance;
    private Map<String, Integer> portfolio;

    public User(String name, double cashBalance) {
        this.name = name;
        this.cashBalance = cashBalance;
        this.portfolio = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public Map<String, Integer> getPortfolio() {
        return portfolio;
    }

    public boolean buyStock(Stock stock, int quantity) {
        double totalCost = stock.getPrice() * quantity;

        if (quantity <= 0) {
            return false;
        }

        if (totalCost > cashBalance) {
            return false;
        }

        cashBalance -= totalCost;

        portfolio.put(
            stock.getSymbol(),
            portfolio.getOrDefault(stock.getSymbol(), 0) + quantity
        );

        return true;
    }

    public boolean sellStock(Stock stock, int quantity) {
        if (quantity <= 0) {
            return false;
        }

        int ownedQuantity = portfolio.getOrDefault(stock.getSymbol(), 0);

        if (quantity > ownedQuantity) {
            return false;
        }

        cashBalance += stock.getPrice() * quantity;

        int remaining = ownedQuantity - quantity;

        if (remaining == 0) {
            portfolio.remove(stock.getSymbol());
        } else {
            portfolio.put(stock.getSymbol(), remaining);
        }

        return true;
    }

    public double calculatePortfolioValue(Map<String, Stock> market) {
        double totalValue = cashBalance;

        for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
            Stock stock = market.get(entry.getKey());

            if (stock != null) {
                totalValue += stock.getPrice() * entry.getValue();
            }
        }

        return totalValue;
    }

    public void displayPortfolio(Map<String, Stock> market) {
        System.out.println("\n========== PORTFOLIO ==========");

        System.out.printf("Cash Balance: ₹%.2f%n", cashBalance);

        if (portfolio.isEmpty()) {
            System.out.println("No stocks owned.");
        } else {
            System.out.printf("%-10s %-15s %-15s%n",
                    "Symbol", "Quantity", "Value");

            for (Map.Entry<String, Integer> entry : portfolio.entrySet()) {
                Stock stock = market.get(entry.getKey());

                if (stock != null) {
                    double value = stock.getPrice() * entry.getValue();

                    System.out.printf(
                        "%-10s %-15d ₹%.2f%n",
                        stock.getSymbol(),
                        entry.getValue(),
                        value
                    );
                }
            }
        }

        System.out.printf(
            "Total Portfolio Value: ₹%.2f%n",
            calculatePortfolioValue(market)
        );

        System.out.println("===============================");
    }
}