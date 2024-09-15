package com.roy;

import io.temporal.activity.ActivityOptions;
import io.temporal.common.RetryOptions;
import io.temporal.workflow.Workflow;

import java.time.Duration;

public class GreetingWorkflowImplSpanish implements GreetingWorkflow {
    RetryOptions retryOptions = RetryOptions.newBuilder()
            .setInitialInterval(Duration.ofSeconds(5))
            .setBackoffCoefficient(2.0)
            .setMaximumInterval(Duration.ofSeconds(100))
            .setMaximumAttempts(10)
            .build();

    ActivityOptions activityOptions = ActivityOptions.newBuilder()
            .setStartToCloseTimeout(Duration.ofSeconds(200))
            .setRetryOptions(retryOptions)
            .build();

    GreetingActivities activities = Workflow.newActivityStub(GreetingActivities.class, activityOptions);

    @Override
    public String greet(String name) {
        String greeting = activities.greet(name);
        String bye = activities.bye(name);
        return "\n" +greeting + "\n" + bye;
    }
}
