import java.util.Scanner;

public class TradingPlatform {
    private Portfolio portfolio;
    private StockMarket stockMarket;
    private Scanner scanner;

    public TradingPlatform(double initialBalance) {
        portfolio = new Portfolio(initialBalance);
        stockMarket = new StockMarket();
        scanner = new Scanner(System.in);
    }

    public void start() {
        boolean running = true;
        while (running) {
            System.out.println("\n1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    stockMarket.displayMarket();
                    break;
                case 2:
                    buyStock();
                    break;
                case 3:
                    sellStock();
                    break;
                case 4:
                    portfolio.displayPortfolio();
                    break;
                case 5:
                    running = false;
                    System.out.println("Exiting platform...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private void buyStock() {
        System.out.print("Enter the stock symbol you want to buy: ");
        String symbol = scanner.next().toUpperCase();
        Stock stock = stockMarket.findStockBySymbol(symbol);
        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }

        System.out.print("Enter quantity to buy: ");
        int quantity = scanner.nextInt();
        double totalPrice = quantity * stock.getPrice();

        if (totalPrice > portfolio.getCashBalance()) {
            System.out.println("Insufficient funds!");
        } else {
            portfolio.buyStock(stock, quantity);
            portfolio.setCashBalance(portfolio.getCashBalance() - totalPrice);
            System.out.println("Bought " + quantity + " shares of " + stock.getCompanyName());
        }
    }

    private void sellStock() {
        System.out.print("Enter the stock symbol you want to sell: ");
        String symbol = scanner.next().toUpperCase();
        Stock stock = stockMarket.findStockBySymbol(symbol);
        if (stock == null) {
            System.out.println("Stock not found!");
            return;
        }

        System.out.print("Enter quantity to sell: ");
        int quantity = scanner.nextInt();
        int ownedQuantity = portfolio.getStockQuantity(stock);

        if (ownedQuantity < quantity) {
            System.out.println("You don't own enough shares to sell!");
        } else {
            portfolio.sellStock(stock, quantity);
            double saleValue = quantity * stock.getPrice();
            portfolio.setCashBalance(portfolio.getCashBalance() + saleValue);
            System.out.println("Sold " + quantity + " shares of " + stock.getCompanyName());
        }
    }

    public static void main(String[] args) {
        TradingPlatform platform = new TradingPlatform(10000.00);
        platform.start();
    }
}
