package net.eldiosantos.cloudstorage.dropbox.service.steps;

import cucumber.api.Scenario;
import cucumber.api.java8.En;
import net.eldiosantos.cloudstorage.api.model.Resource;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.service.DropboxDeleteService;
import net.eldiosantos.cloudstorage.dropbox.service.RunCukesTest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 13/02/2017.
 */
public class PreparationSteps implements En {

    private static final StorageConfiguration config = StorageConfiguration.apply();

    private static final Logger LOGGER = LoggerFactory.getLogger(RunCukesTest.class);

    private static Boolean dunit = false;

    public PreparationSteps() {
        Before((scenario) -> {
            if (!dunit) {
                dunit = true;
                cleanFolders(scenario);
            }
        });
        After((scenario) -> {
            //cleanFolders(scenario);
        });
    }

    private void cleanFolders(Scenario scenario) {
        try {
            LOGGER.info("#########################################################################");
            LOGGER.info("Removing test folders before scenario '{}'", scenario.getName());
            final String responses = new BufferedReader(new InputStreamReader(ClassLoader.getSystemClassLoader().getResourceAsStream("test-created-folders.txt")))
                    .lines()
                    .filter(f -> !f.isEmpty())
                    .map(f -> {
                        try {
                            LOGGER.info("Removing folder '{}'", f);
                            return new DropboxDeleteService(config).delete(f);
                        } catch (Exception e) {
                            e.printStackTrace();
                            throw new IllegalStateException("Error trying to clean test folders");
                        }
                    }).map(Resource::getPathDisplay)
                    .collect(Collectors.joining("\n\t-> "));

            LOGGER.info("response list:\n{}", responses);
            LOGGER.info("#########################################################################");
        } catch (Exception e) {
            LOGGER.error("Oops! Some shit happened here before start this scenario ({})...", scenario.getName(), e);
        }
    }
}
