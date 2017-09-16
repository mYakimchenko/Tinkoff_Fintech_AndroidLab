package com.mihanjk.domain.model;

import com.mihanjk.data.entity.Currency;

public class ApiResponse {
    private Currency base;
    private RateObject rates;

    public ApiResponse(Currency base, RateObject rates) {
        this.base = base;
        this.rates = rates;
    }

    public Currency getBase() {
        return base;
    }

    public RateObject getRates() {
        return rates;
    }
}
