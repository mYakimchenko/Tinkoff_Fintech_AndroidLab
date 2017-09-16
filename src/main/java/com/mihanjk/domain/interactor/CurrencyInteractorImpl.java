package com.mihanjk.domain.interactor;

import com.mihanjk.domain.mapper.JsonConverter;
import com.mihanjk.domain.model.ApiResponse;
import com.mihanjk.repositories.currency.CurrencyRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CurrencyInteractorImpl implements CurrencyInteractor {
    private final CurrencyRepository repository;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public CurrencyInteractorImpl(CurrencyRepository repository) {
        this.repository = repository;
    }

    @Override
    public ApiResponse getCurrencyExchange(String from, String to) {
        String date = LocalDate.now().format(formatter);
        String currencyJson = repository.getCurrencyExchange(from, to, date);
        return JsonConverter.convertJsonStringToApiResponse(currencyJson);
    }
}
