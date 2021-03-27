package com.example.workers2;

import lombok.extern.slf4j.Slf4j;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Slf4j
public class CamundaCommands {

    Collection<ApiJob> all() {
        Collection<ApiJob> apiJobs = items.values();
        return apiJobs;
    }

    ApiJob find(String name) {
        return items.get(name);
    }

    private final Map<String, ApiJob> items = new HashMap<String, ApiJob>() {
        void append(ApiJob job) {
            put(job.getApi(), job);
        }

        {
            append(new ApiJob() {
                @Override
                public String getApi() {
                    return "api1";
                }

                @Override
                public String process(String input) {
                    log.info("Invade Persia :: api={}", this.getApi());
                    return "Persia";
                }
            });
            append(new ApiJob() {
                @Override
                public String getApi() {
                    return "api2";
                }

                @Override
                public String process(String input) {
                    log.info("Invade Goul :: api={}", this.getApi());
                    return "Goul";
                }
            });
        }

    };
}
