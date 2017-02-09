package net.eldiosantos.cloudstorage.dropbox.service.steps;

import cucumber.api.Scenario;
import cucumber.api.java8.En;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

/**
 * Created by esjunior on 09/02/2017.
 */
public class TimeCounterSteps implements En {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Long startTime;
    private Scenario scenario;

    public TimeCounterSteps() {
        Before((Scenario scenario) -> {
            this.scenario = scenario;
            this.startTime = System.currentTimeMillis();
        });

        After(() -> {
            final Long endTime = System.currentTimeMillis();
            final Duration duration = Duration.between(Instant.ofEpochMilli(startTime), Instant.ofEpochMilli(endTime));
            this.scenario.write(
                new StringBuffer("\n## TEST STATUS ###################################\n")
                    .append("ID: ").append(scenario.getId()).append("\n")
                    .append("name: ").append(scenario.getName()).append("\n")
                    .append("status: ").append(scenario.getStatus()).append("\n")
                    .append("started at: ").append(startTime).append("\n")
                    .append("ended at: ").append(endTime).append("\n")
                    .append("time counter: ").append(endTime - startTime).append(" milisseconds\n")
                    .append("time counter: ").append(duration.get(ChronoUnit.SECONDS)).append(" seconds\n")
                    .append("##################################################")
                    .toString()
            );
        });
    }
}
