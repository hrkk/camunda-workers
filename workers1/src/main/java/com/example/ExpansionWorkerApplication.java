package com.example;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.camunda.bpm.client.interceptor.auth.BasicAuthProvider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Slf4j
@RestController
@SpringBootApplication
public class ExpansionWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ExpansionWorkerApplication.class, args);
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .addInterceptor(new BasicAuthProvider("CaptainHook", "Rum"))
                .build();



        // subscribe to an external task topic as specified in the process
        client.subscribe("DecideOnExpansion")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {
                    // Put your business logic here
                    log.info("Decide where to expand (5 sec)");

                    try {
                        Thread.sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    log.info("After sleep");


                    Map<String, Object> map = new HashMap<>();

                    Random rando = new Random();
                    map.put("north", rando.nextBoolean());

                    // Complete the task
                    //externalTaskService.handleBpmnError(externalTask, "error");
                    externalTaskService.complete(externalTask, map);
                })
                .open();


    }



}
