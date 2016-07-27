package io.pivotal.poc;

import com.ibm.mq.*;
import com.ibm.mq.constants.CMQC;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
public class MQSender {
    @Value("${mq.host:204.194.139.183}")
    private String host;
    @Value("${mq.port:1414}")
    private int port;
    @Value("${mq.channel:FAPI_SVRCONN}")
    private String channel;
    @Value("${mq.queuemanager:CAT_FAPO01}")
    private String queuemanager;
    @Value("${mq.queue:FAPI.CL.QUEUE}")
    private String queue;

    /**
     *
     * @return echo message sent to queue
     * @throws Exception
     */
    public String pingQueue() throws Exception {
        MQEnvironment.hostname = host;
        MQEnvironment.port = port;
        MQEnvironment.channel = channel;
        MQEnvironment.properties.put(CMQC.TRANSPORT_PROPERTY, CMQC.TRANSPORT_MQSERIES_CLIENT);

        String nl = System.getProperty("line.separator");

        logger.info("****Sending message" + nl
        + " Hostname: " + host + nl
        + " Port: " + port + nl
        + " Channel: " + channel + nl
        + " Queuemanager: " + queuemanager);

        MQQueueManager qMgr = new MQQueueManager(queuemanager);
        logger.info(qMgr.toString());

        int openOptions = CMQC.MQOO_BROWSE | CMQC.MQOO_INQUIRE | CMQC.MQOO_OUTPUT | CMQC.MQOO_INPUT_AS_Q_DEF;

        MQQueue destQueue = qMgr.accessQueue(queue, openOptions);
        logger.info("Queue size:" + destQueue.getCurrentDepth());
        MQMessage mqMessage = new MQMessage();
        logger.info("MQMessage message created");
        mqMessage.writeUTF("Sending Sample message");
        MQPutMessageOptions pmo = new MQPutMessageOptions();

        destQueue.put(mqMessage, pmo);
        destQueue.get(mqMessage);

        int len = mqMessage.getDataLength();
        logger.info("Length : " + len);
        String receivedMessage = mqMessage.readString(len - 1);
        logger.info("GET: " + receivedMessage);
        destQueue.close();
        qMgr.disconnect();
        return receivedMessage;
    }

    private static Logger logger = Logger.getLogger(MQSender.class.getName());
}
