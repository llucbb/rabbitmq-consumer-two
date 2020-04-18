package org.llucbb.rabbitmqconsumertwo.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitmqConfig {

    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory, Jackson2JsonMessageConverter converter) {
        RabbitTemplate template = new RabbitTemplate(connectionFactory);
        template.setMessageConverter(converter);

        return template;
    }

    @Bean
    public Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


    /**
     * If you works with other system that already publish to RabbitMQ, chances are you cannot control the __TypeId__
     * header (which is Spring specific). Maybe other publisher use different header name, like type. If it different
     * header name exists (for example, type), second approach:
     */
    /*@Bean(name = "rabbitListenerContainerFactory")
    public SimpleRabbitListenerContainerFactory simpleRabbitListenerContainerFactory(
            SimpleRabbitListenerContainerFactoryConfigurer configurer, ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);

        factory.setAfterReceivePostProcessors(new MessagePostProcessor() {

            @Override
            public Message postProcessMessage(Message message) throws AmqpException {
                var type = message.getMessageProperties().getHeaders().get("type").toString();
                String typeId = null;

                if (StringUtils.equalsIgnoreCase(type, "invoice.paid")) {
                    typeId = InvoicePaidMessage.class.getName();
                } else if (StringUtils.equalsIgnoreCase(type, "invoice.created")) {
                    typeId = InvoiceCreatedMessage.class.getName();
                }

                Optional.ofNullable(typeId).ifPresent(t -> message.getMessageProperties().setHeader("__TypeId__", t));

                return message;
            }

        });

        return factory;
    }*/
}
