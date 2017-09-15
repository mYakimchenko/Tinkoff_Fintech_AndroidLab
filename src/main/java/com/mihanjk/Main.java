package com.mihanjk;

import java.util.Scanner;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter from currency:");
        // TODO: 9/15/2017 try catch illegal argument exception when value not present in enum
        Currency from = Currency.valueOf(scanner.next().toUpperCase());
        System.out.println("Enter to currency:");
        Currency to = Currency.valueOf(scanner.next().toUpperCase());
        ExecutorService executor = Executors.newCachedThreadPool();
        Future<String> result = executor.submit(new getJsonCurrencyExchange(from, to));
        try {
            System.out.println(result.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executor.shutdown();
    }
}
