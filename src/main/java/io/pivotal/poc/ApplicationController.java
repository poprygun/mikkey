package io.pivotal.poc;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

@Controller
@RequestMapping("/")
public class ApplicationController implements ApplicationContextAware {

    @Autowired
    private ApplicationContext applicationContext;

    @Autowired
    private BagOfProperties bagOfProperties;

    private static Logger logger = Logger.getLogger(ApplicationController.class.getName());


    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    @RequestMapping(value = "/props", method = RequestMethod.GET)
    public String props(ModelMap model) {

        model.addAttribute("bag", bagOfProperties);

        return "bag-of-properties";
    }

    @RequestMapping(value = "/beans", method = RequestMethod.GET)
    public String hello(ModelMap model) {


        String[] beanNames = applicationContext.getBeanDefinitionNames();
        List<String> list = Arrays.asList(beanNames);

        //this bean is of time MQQueueConnectionFactory
        Object bean = applicationContext.getBean("mq-service");

        model.addAttribute("beans", list);

        return "beans";
    }

    @RequestMapping(value = "/Test", method = RequestMethod.GET)
    public String welcome(ModelMap model) {
        model.addAttribute("msgArgument", "Maven Java Web Application Project: Success!");

        return "index";
    }

    @RequestMapping(value = "/Print/{arg}", method = RequestMethod.GET)
    public String welcomeName(@PathVariable String arg, ModelMap model) {
        model.addAttribute("msgArgument", "Maven Java Web Application Project, input variable: " + arg);

        return "index";
    }
}
