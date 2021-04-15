package com.varun.orderservice;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.view.RedirectView;

import java.util.Map;

@RestController
@RequestMapping("order")
public class OrderController {
    private static final Logger log = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    ServiceManager serviceManager;

    //TODO: refactor it.
    //@PostMapping(value = "place", consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value ="place")
    public String placeOrder() throws Exception {
        log.info("confirmOrder was hit");

        //Retrieving the IP & Port of Payment Service
        //TODO: test what happens when payment service isn't running.
        ServiceData paymentService = new ServiceData(serviceManager.getService("PaymentService"));

        //TODO: correct address in
        String uri = paymentService.getUri();
        int port = paymentService.getPort();
        String redirect = "redirect:" + uri + port + "/payment/create";
        return redirect;
    }

}

