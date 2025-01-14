package com.roy;

import io.temporal.client.WorkflowClient;
import io.temporal.serviceclient.WorkflowServiceStubs;
import io.temporal.worker.Worker;
import io.temporal.worker.WorkerFactory;

public class GreetingWorker {
    public static void main(String[] args) {
        WorkflowServiceStubs service = WorkflowServiceStubs.newLocalServiceStubs();
        WorkflowClient client = WorkflowClient.newInstance(service);
        WorkerFactory factory = WorkerFactory.newInstance(client);

        Worker worker = factory.newWorker("greeting-tasks");

        // Register Workflow
        worker.registerWorkflowImplementationTypes(GreetingWorkflowImplSpanish.class);

        // Register Activity
        worker.registerActivitiesImplementations(new GreetingActivitiesImpl());

        // Start Factory
        factory.start();
    }
}
