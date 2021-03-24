package com.example.workers2;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.client.ExternalTaskClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@SpringBootApplication
public class InvadeWorkerApplication {

    public static void main(String[] args) {
        SpringApplication.run(InvadeWorkerApplication.class, args);
        ExternalTaskClient client = ExternalTaskClient.create()
                .baseUrl("http://localhost:8080/engine-rest")
                .asyncResponseTimeout(10000) // long polling timeout
                .build();

        // subscribe to an external task topic as specified in the process
        client.subscribe("InvadeGoul")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {
                    // Put your business logic here

                    log.info("INVADE GOUL!!!");
                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();


        // subscribe to an external task topic as specified in the process
        client.subscribe("InvadePersia")
                .lockDuration(1000) // the default lock duration is 20 seconds, but you can override this
                .handler((externalTask, externalTaskService) -> {
                    // Put your business logic here

                    log.info("INVADE PERSIA!!!");
                    // Complete the task
                    externalTaskService.complete(externalTask);
                })
                .open();
    }
}
