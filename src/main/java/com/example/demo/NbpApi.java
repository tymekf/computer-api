package com.example.demo;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.RestController;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;

@RestController
public class NbpApi {

    private static double dollarRateForThatDay;

    public double getDollarRateForThatDay() {
        return dollarRateForThatDay;
    }

        public static double parseUsdRateFromJson(String responseBody) {
            JSONObject usdPageFromThatDay = new JSONObject(responseBody);
            JSONArray rates = usdPageFromThatDay.getJSONArray("rates");
            for (int i = 0; i < rates.length(); i++) {
                JSONObject dollarRate = rates.getJSONObject(i);
                dollarRateForThatDay = dollarRate.getDouble("ask");
            }
            return dollarRateForThatDay;
        }

    public void fetchDataFromNbpApi(LocalDate purchaseDate) {
        HttpClient client = HttpClient.newHttpClient();
//        HttpRequest request = HttpRequest.newBuilder(URI.create("http://api.nbp.pl/api/exchangerates/rates/c/usd/2022-01-03")).build();
        HttpRequest request = null;
        if (purchaseDate.equals(LocalDate.of(2022, 1, 3))) {
            request = HttpRequest.newBuilder(URI.create("http://api.nbp.pl/api/exchangerates/rates/c/usd/2022-01-03")).build();
        } else if (purchaseDate.equals(LocalDate.of(2022, 1, 10))) {
            request = HttpRequest.newBuilder(URI.create("http://api.nbp.pl/api/exchangerates/rates/c/usd/2022-01-10")).build();
        } else {
            System.out.println("incorrect value");
        }
        client.sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .thenApply(HttpResponse::body)
                .thenApply(NbpApi::parseUsdRateFromJson)
                .join();
    }
}