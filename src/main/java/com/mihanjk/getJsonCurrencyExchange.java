package com.mihanjk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.Callable;

public class getJsonCurrencyExchange implements Callable<String> {
    private static final String PREFIX = "http://api.fixer.io/latest?";
    private final Currency from;
    private final Currency to;

    public getJsonCurrencyExchange(Currency from, Currency to) {
        this.from = from;
        this.to = to;
    }

    @Override
    public String call() throws Exception {
        String link = String.format("%sbase=%s&symbols=%s", PREFIX ,from.name(), to.name());
        StringBuilder json = new StringBuilder();
        URL url = null;
        try {
            url = new URL(link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            while ((line = reader.readLine()) != null) {
                json.append(line);
            }
        } catch (MalformedURLException e) {
            System.err.println("Can't build URL from from: " + from.name() + "and to: " + to.name());
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Can't establish a connection to server");
            e.printStackTrace();
        } // TODO: 9/15/2017 close connections
        return json.toString();
    }
}
