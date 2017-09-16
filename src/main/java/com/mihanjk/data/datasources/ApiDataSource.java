package com.mihanjk.data.datasources;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class ApiDataSource {
    private static final String PREFIX = "http://api.fixer.io/latest?";

    public String getCurrencyExchangeFromApi(String from, String to) {
        String link = String.format("%sbase=%s&symbols=%s", PREFIX ,from, to);
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
            System.err.println("Can't build URL from from: " + from + "and to: " + to);
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Can't establish a connection to server");
            e.printStackTrace();
        } // TODO: 9/15/2017 close connections
        return json.toString();
    }
}
