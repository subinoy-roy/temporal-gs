package com.roy;

import io.temporal.client.WorkflowClient;
import io.temporal.client.WorkflowOptions;
import io.temporal.client.WorkflowStub;
import io.temporal.serviceclient.WorkflowServiceStubs;

import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.time.Duration;
import java.util.UUID;

public class Starter {
    public static void main(String[] args) throws MalformedURLException, UnsupportedEncodingException {
        WorkflowServiceStubs workflowService = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(workflowService);
        WorkflowOptions options = WorkflowOptions.newBuilder()
                .setWorkflowId(UUID.randomUUID().toString())
                .setTaskQueue("greeting-tasks")
                //.setWorkflowExecutionTimeout(Duration.ofMillis(30000))
                .build();

        GreetingWorkflow workflow = client.newWorkflowStub(GreetingWorkflow.class, options);
        String greeting = workflow.greet(args[0]);
        String workflowId = WorkflowStub.fromTyped(workflow).getExecution().getWorkflowId();
        System.out.println(workflowId + " " + greeting);

    }
}