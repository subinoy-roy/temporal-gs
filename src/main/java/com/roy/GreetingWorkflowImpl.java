package com.roy;

public class GreetingWorkflowImpl implements GreetingWorkflow {

    @Override
    public String greet(String name) {
        return "Hello " + name + "!!!";
    }
}
