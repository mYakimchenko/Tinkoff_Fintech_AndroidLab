package com.mihanjk.domain.interactor;

import com.mihanjk.domain.model.ApiResponse;

public interface CurrencyInteractor {
    ApiResponse getCurrencyExchange(String from, String to);
}
