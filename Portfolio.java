import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Portfolio {


    private HashMap<String, Double> portfolio = new HashMap<>();

    public void addCrypto(String ticker, double amount) {
        if (portfolio.containsKey(ticker)) {
            portfolio.put(ticker, portfolio.get(ticker) + amount);
        } else {
            portfolio.put(ticker, amount);
        }
    }

    public void displayPortfolio() {
        if (portfolio.isEmpty()) {
            System.out.println("Empty!");
        } else {
            for (Map.Entry<String, Double> entry : portfolio.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        }
    }

    public double getPrice(String ticker) {
        String apiKey = "736629f3-c315-46b8-851c-14d2a591bde8"; 
        String apiUrl = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/quotes/latest?symbol=" + ticker;
        double price = 0.0;

        try {
            URL url = new URL(apiUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("X-CMC_PRO_API_KEY", apiKey);
            connection.setRequestProperty("Accept", "application/json");

            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                StringBuilder response = new StringBuilder();
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line);
                }
                reader.close();
                String jsonResponse = response.toString();
                int priceIndex = jsonResponse.indexOf("\"price\":");
                if (priceIndex != -1) {
                    int startIndex = priceIndex + 8;
                    int endIndex = jsonResponse.indexOf(",", startIndex);
                    String priceString = jsonResponse.substring(startIndex, endIndex);
                    price = Double.parseDouble(priceString);
                }
            } else {
                System.out.println("Error: Couldn't get price for " + ticker);
            }
        } catch (Exception e) {
            System.out.println("Error getting price: " + e.getMessage());
        }

        return price;
    }

    public void calculateTotalValue() {
        if (portfolio.isEmpty()) {
            System.out.println("Portfolio is empty");
            return;
        }

        double totalValue = 0.0;
        for (Map.Entry<String, Double> entry : portfolio.entrySet()) {
            double price = getPrice(entry.getKey());
            if (price > 0) {
                double value = price * entry.getValue();
                totalValue += value;
                System.out.printf("%s: %.2f units @ $%.2f = $%.2f\n", entry.getKey(), entry.getValue(), price, value);
            } else {
                System.out.println("Couldn't fetch price for " + entry.getKey());
            }
        }

        System.out.printf("Total Portfolio Value: $%.2f\n", totalValue);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Portfolio portfolio = new Portfolio();
        boolean running = true;

        while (running) {
            System.out.println("\n1. Add Crypto");
            System.out.println("2. Display Portfolio");
            System.out.println("3. Calculate Total Value");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    System.out.println("Tickers: BTC, ETH, DOGE, ADA, SOL, XRP"); 
                    System.out.print("Enter ticker: ");
                    String ticker = scanner.nextLine().toUpperCase();
                    System.out.print("Enter amount: ");
                    double amount = Double.parseDouble(scanner.nextLine());
                    portfolio.addCrypto(ticker, amount);
                    System.out.println("Added " + amount + " " + ticker);
                    break;
                case "2":
                    portfolio.displayPortfolio();
                    break;
                case "3":
                    portfolio.calculateTotalValue();
                    break;
                case "4":
                    running = false;
                    System.out.println("Exiting");
                    break;
                default:
                    System.out.println("Invalid");
            }
        }

        scanner.close();
    }
}
