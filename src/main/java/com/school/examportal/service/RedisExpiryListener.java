package com.school.examportal.service;

import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.connection.MessageListener;
import org.springframework.stereotype.Component;

@Component
public class RedisExpiryListener implements MessageListener {

    private final TestAttemptService service;

    public RedisExpiryListener(TestAttemptService service) {
        this.service = service;
    }

    @Override
    public void onMessage(Message message, byte[] pattern) {

        String key = message.toString();

        if (key.startsWith("test:")) {
            Long attemptId = Long.parseLong(
                    key.split(":")[3]
            );
            service.submitTest(attemptId);
        }
    }
}
