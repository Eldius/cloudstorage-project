package net.eldiosantos.cloudstorage.dropbox.service.steps;

import cucumber.api.DataTable;
import cucumber.api.java8.En;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 08/02/2017.
 */
public class MyStepdefs implements En {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    public MyStepdefs() {
        try {
            Given("I have (\\d+) cukes in my belly", (Integer cukes) -> {
                logger.info("Cukes: %d", cukes);
            });
            Given("I take an action of '(.*)'", (String action) -> {
                logger.info(String.format("my action is '%s'", action));
            });
        } catch (Exception e) {
            logger.error("Error defining stepdefs", e);
        }
    }
}
