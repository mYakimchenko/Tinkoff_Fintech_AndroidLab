package com.mihanjk.repositories.currency;

import com.mihanjk.data.datasources.ApiDataSource;
import com.mihanjk.data.datasources.FileDataSource;
import com.mihanjk.domain.async.Executor;

public class CurrencyRepositoryImpl implements CurrencyRepository {
    private final FileDataSource fileDataSource;
    private final ApiDataSource apiDataSource;
    private final Executor executor = new Executor();

    public CurrencyRepositoryImpl(FileDataSource fileDataSource, ApiDataSource apiDataSource) {
        this.fileDataSource = fileDataSource;
        this.apiDataSource = apiDataSource;
    }

    @Override
    public String getCurrencyExchange(String from, String to, String date) {
        String result = executor.executeCallable(() -> fileDataSource.readFromFile(from, to, date));
        System.out.print("..");
        // TODO: 9/16/2017 do i need to save data from api with date???
        if (result == null) {
            result = executor.executeCallable(() -> apiDataSource.getCurrencyExchangeFromApi(from, to));
            System.out.print("..");
            String finalResult = result;
            executor.executeRunnable(() -> fileDataSource.writeToFile(finalResult));
            System.out.print("..");
        }
        System.out.println();
        executor.shutdown();
        return result;
    }
}
