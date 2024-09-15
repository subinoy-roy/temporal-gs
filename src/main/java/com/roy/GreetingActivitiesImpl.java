package com.roy;

import io.temporal.activity.Activity;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class GreetingActivitiesImpl implements GreetingActivities {
    @Override
    public String greet(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        String baseUrl = "http://localhost:3000/greeting?name=";
        try {
            return makeUrl(name, stringBuilder, baseUrl);
        } catch (MalformedURLException e) {
            throw Activity.wrap(e);
        }
    }

    @Override
    public String bye(String name) {
        StringBuilder stringBuilder = new StringBuilder();
        String baseUrl = "http://localhost:3000/bye?name=";
        try {
            return makeUrl(name, stringBuilder, baseUrl);
        } catch (MalformedURLException e) {
            throw Activity.wrap(e);
        }
    }

    private String makeUrl(String name, StringBuilder stringBuilder, String baseUrl) throws MalformedURLException {
        URL url = new URL(baseUrl + URLEncoder.encode(name, StandardCharsets.UTF_8));

        try (BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                stringBuilder.append(line).append("\n");
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return stringBuilder.toString();
    }
}
