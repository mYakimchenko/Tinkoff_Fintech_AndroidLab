package com.mihanjk.presentation.view;

import com.mihanjk.data.entity.Currency;
import com.mihanjk.di.JustSimpleDI;
import com.mihanjk.presentation.presenter.CurrencyPresenter;
import com.mihanjk.utils.DiFactory;

import java.util.Arrays;
import java.util.Scanner;

public class ConsoleView implements Console {
    private final Scanner scanner = new Scanner(System.in);
    private final CurrencyPresenter presenter = new CurrencyPresenter(this, new JustSimpleDI().getCurrencyInteractor());

    public void getUserInputForCurrencyExchange() {
        System.out.println("Enter from currency:");
        // TODO: 9/15/2017 try catch illegal argument exception when value not present in enum
        Currency from = null;
        Currency to = null;
        try {
            from = Currency.valueOf(scanner.next().toUpperCase());
            System.out.println("Enter to currency:");
            to = Currency.valueOf(scanner.next().toUpperCase());
        } catch (IllegalArgumentException e) {
            System.err.println("Unsupported currency, please choose one of this list:\n" +
                    Arrays.toString(Currency.values()));
        }
        presenter.getCurrencyExchange(from, to);
    }

    @Override
    public void showError(String message) {
        System.err.println(message);
    }

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
