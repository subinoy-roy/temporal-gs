package com.roy;

import io.temporal.activity.ActivityInterface;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

@ActivityInterface
public interface GreetingActivities {
    public String greet(String name);
    public String bye(String name);
}
