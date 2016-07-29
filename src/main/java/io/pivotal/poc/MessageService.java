package io.pivotal.poc;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;
import javax.jms.TextMessage;

public class MessageService {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${queue}")
    private String destination;

    /**
     * Sends a message to a queue.
     *
     * @param text Message text.
     */
    public void sendMessage(final String text) {
        jmsTemplate.send(destination, new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                return session.createTextMessage(text);
            }
        });
    }

    /**
     * Receives a message from a queue.
     *
     * @return Message text.
     * @throws JMSException
     */
    public String readMessage() throws JMSException {
        String message = null;

        Message msg = jmsTemplate.receive(destination);
        if(msg instanceof TextMessage) {
            message = ((TextMessage) msg).getText();
        }

        return message;
    }
}
