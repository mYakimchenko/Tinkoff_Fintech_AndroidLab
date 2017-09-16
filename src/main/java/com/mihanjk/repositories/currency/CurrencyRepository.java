package com.mihanjk.repositories.currency;

public interface CurrencyRepository {
    String getCurrencyExchange(String from, String to, String date);
}
