package io.pivotal.poc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class ApplicationController {

//    @Autowired
//    private MQSender mqSender;

    @Autowired
    private MQGateway mqGateway;

    private static Logger logger = Logger.getLogger(ApplicationController.class.getName());

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String receveMessage(ModelMap model
            , @RequestParam String host
            , @RequestParam int port
            , @RequestParam String queueManager
            , @RequestParam String channel
            , @RequestParam String queue
    ) throws Exception {
        logger.info("********" + host);
        logger.info("***Sending to queue");
        mqGateway.send("Message sent from testing application.  Ignore");
        logger.info("***Queue responded to queue");
        model.addAttribute("msgArgument", "Sent message to jms, check logs. ");
        return "index";
    }
}
