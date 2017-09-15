package com.mihanjk.Model;

import com.mihanjk.Currency;

import java.util.Map;

public class ApiResponce {
    private final Currency base;
    // TODO: 9/15/17 i need to use some class of date, else user can put any data into date
    private final Map<Currency, Double> rates;
    public ApiResponce(Currency base, String date, Map<Currency, Double> rates) {
        this.base = base;
        this.date = date;
        this.rates = rates;
    }

    @Override
    public String toString() {
        return "ApiResponce{" +
                "base=" + base +
                ", date='" + date + '\'' +
                ", rates=" + rates +
                '}';
    }private final String date;
}
