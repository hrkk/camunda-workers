package com.example.workflow;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

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
                    System.out.println("process api=" + this.getApi());
                    return "ok";
                }
            });
            append(new ApiJob() {
                @Override
                public String getApi() {
                    return "api2";
                }

                @Override
                public String process(String input) {
                    System.out.println("process api=" + this.getApi());
                    return "ok";
                }
            });
        }

    };
}
