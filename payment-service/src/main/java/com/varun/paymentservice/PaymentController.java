package com.varun.paymentservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("payment")
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    //@PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "create")
    public void createPayment(@RequestBody Map<String, String> req) {
        log.info("createPayment was hit with request body " + req);
    }

}
