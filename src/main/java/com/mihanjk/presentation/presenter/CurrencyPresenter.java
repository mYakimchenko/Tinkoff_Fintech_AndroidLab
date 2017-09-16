package com.mihanjk.presentation.presenter;

import com.mihanjk.data.entity.Currency;
import com.mihanjk.domain.interactor.CurrencyInteractor;
import com.mihanjk.domain.model.ApiResponse;
import com.mihanjk.domain.model.RateObject;
import com.mihanjk.presentation.view.Console;

public class CurrencyPresenter {
    private final Console view;
    private final CurrencyInteractor interactor;

    public CurrencyPresenter(Console view, CurrencyInteractor interactor) {
        this.view = view;
        this.interactor = interactor;
    }

    public void getCurrencyExchange(Currency from, Currency to) {
        if (from != null && to != null) {
            ApiResponse currencyExchange = interactor.getCurrencyExchange(from.name(), to.name());
            view.showMessage(buildPresentationOfCurrencyExchangeForView(currencyExchange));
        } else {
            view.showError("You need input from and to currency to get currency exchange");
        }
    }

    private String buildPresentationOfCurrencyExchangeForView(ApiResponse currencyExchange) {
        RateObject rates = currencyExchange.getRates();
        return String.format("%s => %s : %s", currencyExchange.getBase(), rates.getName(),
                rates.getRate());
    }
}
