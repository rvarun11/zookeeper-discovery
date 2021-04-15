package com.varun.orderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("order")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    ServiceManager serviceManager;

    @GetMapping(value = "place")
    public RedirectView placeOrder() {
        log.info("confirmOrder was hit");
        String url = "";
        try {
            ServiceData paymentService = new ServiceData(serviceManager.getService("PaymentService"));
            url = "http://" + paymentService.getIp() + ":" + paymentService.getPort() + "/payment/create";
        }
        catch (Exception e) {
            log.info("PaymentService is not running");
        }
        return new RedirectView(url);
    }

}

