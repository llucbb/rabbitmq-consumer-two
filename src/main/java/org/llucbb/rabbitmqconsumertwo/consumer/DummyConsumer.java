package org.llucbb.rabbitmqconsumertwo.consumer;

import lombok.extern.slf4j.Slf4j;
import org.llucbb.rabbitmqmodeltwo.entity.DummyMessage;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class DummyConsumer {

    @RabbitListener(queues = "q.dummy", ackMode = "AUTO")
    public void listenDummy(DummyMessage message) {
        log.info("{}", message);
    }

    /**
     * If you works with other system that already publish to RabbitMQ, chances are you cannot control the __TypeId__
     * header (which is Spring specific). Maybe other publisher use different header name, like type. If it different
     * header name exists (for example, type), first approach:
     * <p>
     * Using if-else in @RabbitListener, based on existing header. Adjust the header name if required, on @Header
     * parameter
     */
    /*@RabbitListener(queues = "q.finance.invoice")
    public void listenInvoiceCreated(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long tag,
                                     @Header("type") String type) throws IOException {
        if (StringUtils.equalsIgnoreCase(type, "invoice.paid")) {
            log.info("Delegate to invoice paid handler");
        } else if (StringUtils.equalsIgnoreCase(type, "invoice.created")) {
            log.info("Delegate to invoice created handler");
        } else {
            log.info("Delegate to default handler");
        }
    }*/


    /**
     * If you works with other system that already publish to RabbitMQ, chances are you cannot control the __TypeId__
     * header (which is Spring specific). Maybe other publisher use different header name, like type. If it different
     * header name exists (for example, type), second approach:
     */
   /*@RabbitHandler
    public void listenInvoiceCreated(InvoiceCreatedMessage message) {
        log.info("Listening invoice created : {}", message);
    }

    @RabbitHandler
    public void listenInvoicePaid(InvoicePaidMessage message) {
        log.info("Listening invoice paid : {}", message);
    }

    @RabbitHandler(isDefault = true)
    public void listenDefault(Message message) {
        log.info("Default invoice listener : {}", message.getMessageProperties().getHeaders());
    }*/

}
