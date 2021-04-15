package com.varun.orderservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("order")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    ServiceManager serviceManager;

    //@PostMapping(value = "place", consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value ="place")
    public RedirectView placeOrder() throws Exception {
        log.info("confirmOrder was hit");

        //Retrieving the IP/URI & Port of Payment Service
        ServiceData paymentService = new ServiceData(serviceManager.getService("PaymentService"));

        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("http://" + paymentService.getUri() + ":" + paymentService.getPort() + "/payment/create");

        return redirectView;
    }

}

