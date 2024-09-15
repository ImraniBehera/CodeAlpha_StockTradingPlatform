import java.util.ArrayList;
import java.util.List;

public class StockMarket {
    private List<Stock> availableStocks;

    public StockMarket() {
        availableStocks = new ArrayList<>();
        initializeMarket();
    }

    private void initializeMarket() {
        availableStocks.add(new Stock("AAPL", "Apple Inc.", 150.00));
        availableStocks.add(new Stock("GOOG", "Alphabet Inc.", 2750.00));
        availableStocks.add(new Stock("TSLA", "Tesla Inc.", 725.00));
        availableStocks.add(new Stock("AMZN", "Amazon.com, Inc.", 3400.00));
    }

    public List<Stock> getAvailableStocks() {
        return availableStocks;
    }

    public Stock findStockBySymbol(String symbol) {
        for (Stock stock : availableStocks) {
            if (stock.getSymbol().equalsIgnoreCase(symbol)) {
                return stock;
            }
        }
        return null;
    }

    public void displayMarket() {
        System.out.println("Stock Market:");
        for (Stock stock : availableStocks) {
            System.out.println(stock.getSymbol() + " (" + stock.getCompanyName() + ") - Price: $" + stock.getPrice());
        }
    }
}
