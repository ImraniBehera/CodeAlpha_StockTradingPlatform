import java.util.HashMap;
import java.util.Map;

public class Portfolio {
    private double cashBalance;
    private Map<Stock, Integer> ownedStocks;

    public Portfolio(double initialBalance) {
        this.cashBalance = initialBalance;
        this.ownedStocks = new HashMap<>();
    }

    public double getCashBalance() {
        return cashBalance;
    }

    public void setCashBalance(double cashBalance) {
        this.cashBalance = cashBalance;
    }

    public void buyStock(Stock stock, int quantity) {
        ownedStocks.put(stock, ownedStocks.getOrDefault(stock, 0) + quantity);
    }

    public void sellStock(Stock stock, int quantity) {
        int currentQty = ownedStocks.getOrDefault(stock, 0);
        if (currentQty >= quantity) {
            if (currentQty == quantity) {
                ownedStocks.remove(stock);
            } else {
                ownedStocks.put(stock, currentQty - quantity);
            }
        }
    }

    public int getStockQuantity(Stock stock) {
        return ownedStocks.getOrDefault(stock, 0);
    }

    public void displayPortfolio() {
        System.out.println("Portfolio:");
        for (Map.Entry<Stock, Integer> entry : ownedStocks.entrySet()) {
            Stock stock = entry.getKey();
            int quantity = entry.getValue();
            double totalValue = stock.getPrice() * quantity;
            System.out.println(stock.getSymbol() + " (" + stock.getCompanyName() + ") - Quantity: " + quantity + ", Total Value: $" + totalValue);
        }
        System.out.println("Cash Balance: $" + cashBalance);
    }
}
