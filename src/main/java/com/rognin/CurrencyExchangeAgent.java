package com.rognin;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CurrencyExchangeAgent {

    private String startingCurrency;
    private String targetCurrency;
    private double amount;
    private double conversionRate;

    //parse requests with the following structure:
    //"Convert $amount $startingCurrency to $targetCurrency"
    public void parseRequest(String request) {
        // Check if the request has the required structure.
        // While the string should be all lower case at this point, still check for capital
        // letters in case this changes
        if(!request.matches("convert\\s+\\d+(\\.\\d+)?\\s+[a-zA-Z]{3}\\s+to\\s+[a-zA-Z]{3}")){
            System.out.println("I don't understand your request. Please make sure it has the following " +
                    "structure:\n\"Convert $amount $startingCurrency to $targetCurrency\"");
            return;
        }
        String[] words = request.split(" ");
        amount = Double.parseDouble(words[1]);
        startingCurrency = words[2];
        targetCurrency = words[4];
        try {
            conversionRate = getConversionRate();
        } catch (IOException e) {
            System.out.println("Your currency code might be incorrect. " +
                    "Please make sure it is an ISO 4217 Three Letter Currency Code (e. g. EUR, USD)");
            return;
        }
        double result = amount * conversionRate;
        System.out.println(amount + " " + startingCurrency + " in " + targetCurrency + " is " + result + " " + targetCurrency);
    }

    private String buildURL(){
        StringBuilder url = new StringBuilder();
        url.append("https://v6.exchangerate-api.com/v6/b54eb5c3e369a9e23430ebc2/pair/");
        url.append(startingCurrency);
        url.append("/");
        url.append(targetCurrency);
        return url.toString();
    }

    public double getConversionRate() throws IOException {
        // Setting URL
        String url_str = buildURL();

    // Making Request
        URL url = new URL(url_str);
        HttpURLConnection request = (HttpURLConnection) url.openConnection();
        request.connect();

    // Convert to JSON
        JsonElement root = JsonParser.parseReader(new InputStreamReader((InputStream) request.getContent()));
        JsonObject jsonobj = root.getAsJsonObject();

    // Accessing object
        String req_result = jsonobj.get("conversion_rate").getAsString();

        return Double.parseDouble(req_result);
    }

}
