package io.pivotal.poc;


import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.jms.JMSException;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations={"classpath:/message-service-test.xml"})
public class MessageServiceTest{
    @Autowired
    private MessageService messageService;
    private String message;

    /**
     * Test setup.
     */
    @Before
    public void setUp() {
        message = "TestSpringJMSMQ test message.";
    }

    /**
     * Test that sends a message to a queue.
     */
    @Test
    @Ignore
    public void testSendMessage() {
        messageService.sendMessage(message);
    }

    /**
     * Test that receives a message from a queue.
     *
     * @throws JMSException
     */
    @Test
    @Ignore
    public void testReadMessage() throws JMSException {
        String readMessage = messageService.readMessage();

        assertEquals(readMessage, message);
    }
}
