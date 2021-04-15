package com.varun.orderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

// can't use RestController because redirect prefix won't work.
@Controller
@RequestMapping("order")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    ServiceManager serviceManager;

    @RequestMapping(value = "place")
    @ResponseBody
    public String placeOrder() {
        log.info("confirmOrder was hit");

        try {
            ServiceData paymentService = new ServiceData(serviceManager.getService("PaymentService"));
            return "redirect:http://" + paymentService.getIp() + ":" + paymentService.getPort() + "/payment/create";
        }
        catch (Exception e) {
            log.info("PaymentService is down");
            return "PaymentService is currently down! Please try again later.";
        }
    }

}

