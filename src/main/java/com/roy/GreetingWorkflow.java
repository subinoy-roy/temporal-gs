package com.roy;

import io.temporal.workflow.WorkflowInterface;
import io.temporal.workflow.WorkflowMethod;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;

@WorkflowInterface
public interface GreetingWorkflow {
    @WorkflowMethod
    String greet(String name) throws MalformedURLException, UnsupportedEncodingException;
}
