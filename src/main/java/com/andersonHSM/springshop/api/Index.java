package com.andersonHSM.springshop.api;

import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;

@RestController("/")
@Component
public class Index {

    @Autowired
    KafkaTemplate<String, String> template;

    @PostMapping()
    public String postMethodName() {

        CompletableFuture<SendResult<String, String>> future = template.send("customer-dispatching", "1",
                "consumer message");
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Success");
                System.out.println(result);

            } else {
                System.out.println("Failure");
            }
        });

        return "Successfully went through.";

    }

}
