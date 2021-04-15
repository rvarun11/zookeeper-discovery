package com.varun.paymentservice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("payment")
public class PaymentController {
    private static final Logger log = LoggerFactory.getLogger(PaymentController.class);

    //@PostMapping(value = "create", consumes = MediaType.APPLICATION_JSON_VALUE)
    @GetMapping(value = "create")
    public String createPayment() {
        log.info("createPayment was hit");
        return "createPayment was hit successfully!";
    }

}
