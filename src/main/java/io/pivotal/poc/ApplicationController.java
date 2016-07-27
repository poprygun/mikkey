package io.pivotal.poc;

//import io.pivotal.pcfs.ibmmq.mock.MQQueueConnectionFactory;
import com.ibm.mq.jms.*;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.ibm.msg.client.wmq.WMQConstants;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

import static com.ibm.disthub2.impl.formats.Envelop.Constants.TextMessage;

@Controller
@RequestMapping("/")
public class ApplicationController implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BagOfProperties bagOfProperties;

//    @Autowired
//    private MQQueueConnectionFactory mqQueueConnectionFactory;

//    @Autowired
//    private MessageService messageService;

    @Autowired
    private MQSender mqSender;


    private static Logger logger = Logger.getLogger(ApplicationController.class.getName());




    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String receveMessage(ModelMap model) throws Exception {
        logger.info("***Sending to queue");
        String receivedMessage = mqSender.pingQueue();
        logger.info("***Queue responded to queue");
        model.addAttribute("msgArgument", "Sent and Received message to jms, check logs. " + receivedMessage);
        return "index";
    }

//    @RequestMapping(value = "/props", method = RequestMethod.GET)
//    public String props(ModelMap model) {
//
//        model.addAttribute("bag", mqQueueConnectionFactory);
//
//        return "bag-of-properties";
//    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(ModelMap model) {


        String[] beanNames = applicationContext.getBeanDefinitionNames();
        List<String> list = Arrays.asList(beanNames);

        //this bean is of time MQQueueConnectionFactory
        Object bean = applicationContext.getBean("mq-service");

        model.addAttribute("beans", list);

        return "beans";
    }

    @RequestMapping(value = "/Print/{arg}", method = RequestMethod.GET)
    public String welcomeName(@PathVariable String arg, ModelMap model) {
        model.addAttribute("msgArgument", "Maven Java Web Application Project, input variable: " + arg);

        return "index";
    }
}
