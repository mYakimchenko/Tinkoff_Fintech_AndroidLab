package com.mihanjk.domain.mapper;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mihanjk.domain.model.ApiResponse;
import com.mihanjk.domain.model.RateObject;

import java.io.BufferedReader;
import java.io.StringReader;

public class JsonConverter {
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(RateObject.class, new RatesDeserializer())
            .create();

    private JsonConverter() {}

    public static ApiResponse convertJsonStringToApiResponse(String json) {
        return gson.fromJson(new BufferedReader(new StringReader(json)), ApiResponse.class);
    }
}
