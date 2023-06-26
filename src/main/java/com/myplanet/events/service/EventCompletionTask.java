package com.myplanet.events.service;

import com.myplanet.events.service.impl.EventServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EventCompletionTask {

    @Autowired
    EventServiceImpl service;

    @Scheduled(cron = "0 */1 * * * *")
    public void completedExpiredEvents() {
        System.out.println("INSIDE CRON JOB");
        service.markEventsAsCompleted();
    }
}
