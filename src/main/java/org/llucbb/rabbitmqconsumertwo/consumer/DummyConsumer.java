package org.llucbb.rabbitmqconsumertwo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.llucbb.rabbitmqconsumertwo.entity.DummyMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DummyConsumer {

    @RabbitListener(queues = "q.dummy", ackMode = "AUTO")
    public void listenDummy(DummyMessage message) {
        log.info("{}", message);
    }

}
