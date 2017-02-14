package net.eldiosantos.cloudstorage.dropbox.service.steps;

import cucumber.api.java8.En;
import net.eldiosantos.cloudstorage.api.model.Resource;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.service.DropboxCreateFolderService;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by esjunior on 13/02/2017.
 */
public class CreateFolderSteps implements En {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final StorageConfiguration config = StorageConfiguration.apply();

    private Resource response;

    public CreateFolderSteps() {
        When("^I create the folder \"([^\"]*)\"$", (String folderToCreate) -> {
            try {
                logger.info("Creating folder '{}'", folderToCreate);
                response = new DropboxCreateFolderService(config).create(folderToCreate);
                logger.info("Create folder response:\n{}", response.getPathDisplay());
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Then("^I have this resource \"([^\"]*)\"$", (String folder) -> {
            logger.info(
                "\n##########################################################\n\t- resource: {}\n\t- folder: {}\n##########################################################"
                , response.getPathDisplay()
                , folder
            );
            Assertions.assertTrue(response.getPathDisplay().equalsIgnoreCase(folder));
        });
    }
}
