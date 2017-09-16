package com.mihanjk.di;

import com.mihanjk.data.datasources.ApiDataSource;
import com.mihanjk.data.datasources.FileDataSource;
import com.mihanjk.domain.interactor.CurrencyInteractor;
import com.mihanjk.domain.interactor.CurrencyInteractorImpl;
import com.mihanjk.presentation.presenter.CurrencyPresenter;
import com.mihanjk.presentation.view.ConsoleView;
import com.mihanjk.repositories.currency.CurrencyRepository;
import com.mihanjk.repositories.currency.CurrencyRepositoryImpl;

public class JustSimpleDI {
    private final ApiDataSource apiDataSource;
    private final FileDataSource fileDataSource;
    private final CurrencyRepository currencyRepository;
    private final CurrencyInteractor currencyInteractor;

    public JustSimpleDI() {
        apiDataSource = new ApiDataSource();
        fileDataSource = new FileDataSource();
        currencyRepository = new CurrencyRepositoryImpl(fileDataSource, apiDataSource);
        currencyInteractor = new CurrencyInteractorImpl(currencyRepository);
    }

    public ApiDataSource getApiDataSource() {
        return apiDataSource;
    }

    public FileDataSource getFileDataSource() {
        return fileDataSource;
    }

    public CurrencyRepository getCurrencyRepository() {
        return currencyRepository;
    }

    public CurrencyInteractor getCurrencyInteractor() {
        return currencyInteractor;
    }

    public CurrencyPresenter getCurrencyPresenter(ConsoleView consoleView) {
        return new CurrencyPresenter(consoleView, currencyInteractor);
    }
}
