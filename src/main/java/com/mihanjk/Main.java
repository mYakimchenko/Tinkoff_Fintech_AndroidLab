package com.mihanjk;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mihanjk.Model.ApiResponce;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter from currency:");
        // TODO: 9/15/2017 try catch illegal argument exception when value not present in enum
        Currency from = Currency.valueOf(scanner.next().toUpperCase());
        System.out.println("Enter to currency:");
        Currency to = Currency.valueOf(scanner.next().toUpperCase());
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> result = executor.submit(new CurrencyExchange(from, to));
        String json = null;
        try {
            json = result.get(10L, TimeUnit.SECONDS);
            System.out.println(json);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (TimeoutException e) {
            System.err.println("Task were executed more than 10 seconds, check your connection");
        }
        executor.shutdown();
        Gson gson = new GsonBuilder().create();
        HashMap<Currency, Double> currencyDoubleHashMap = new HashMap<>();
        currencyDoubleHashMap.put(Currency.RUB, 55.0);
        ApiResponce apiResponce = gson.fromJson(json, ApiResponce.class);
        System.out.println(apiResponce);
        System.out.println(gson.toJson(new ApiResponce(Currency.USD, getCurrentDate(),currencyDoubleHashMap)));
    }

    static String getCurrentDate() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.now();
        return date.format(dateTimeFormatter);
    }
}
