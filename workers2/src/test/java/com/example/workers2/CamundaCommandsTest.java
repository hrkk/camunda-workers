package com.example.workers2;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CamundaCommandsTest {
    CamundaCommands calls = new CamundaCommands();

    @Test
    void test() {

        String camundaInput = "input";
        ApiJob job = calls.find("api1");

        assertNotNull(job);

        System.out.println(job.process(camundaInput));

    }

    @Test
    void test2() {
        String camundaInput = "input";
        Collection<ApiJob> all = calls.all();
        for (ApiJob job: all) {
            System.out.println(job.process(camundaInput));

        }


    }
}
