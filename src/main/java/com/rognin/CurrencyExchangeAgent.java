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
    public void parseRequest(String request) throws IOException {
        String[] words = request.split(" ");
        amount = Double.parseDouble(words[1]);
        startingCurrency = words[2];
        targetCurrency = words[4];
        conversionRate = getConversionRate();
        double result = amount * conversionRate;
        System.out.println(amount + " " + startingCurrency + " in " + targetCurrency + " is " + result);
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
