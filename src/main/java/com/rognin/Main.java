//TODO:
//Check that the request is formatted correctly
//Throw appropriate errors at every needed point
//..?

package com.rognin;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.println("Hello! You can enter requests with the following structure:\n" +
                "\"Convert $amount $startingCurrency to $targetCurrency\".\n" +
                "Please use ISO 4217 Three Letter Currency Codes for currencies (e. g. EUR, USD). " +
                "Type \"exit\" to quit.");

        CurrencyExchangeAgent agent = new CurrencyExchangeAgent();

        BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
        String request = r.readLine().toLowerCase();

        while(!request.equals("exit")) {
            agent.parseRequest(request);
            request = r.readLine().toLowerCase();
        }
    }
}