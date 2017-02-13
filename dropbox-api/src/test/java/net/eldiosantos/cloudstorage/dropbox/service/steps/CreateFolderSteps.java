package net.eldiosantos.cloudstorage.dropbox.service.steps;

import cucumber.api.java8.En;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.service.DropboxCreateFolderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by esjunior on 13/02/2017.
 */
public class CreateFolderSteps implements En {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final StorageConfiguration config = StorageConfiguration.apply();

    public CreateFolderSteps() {
        When("^I create the folder \"([^\"]*)\"$", (String folderToCreate) -> {
            try {
                logger.info("Creating folder '{}'", folderToCreate);
                final String response = new DropboxCreateFolderService(config).create(folderToCreate);
                logger.info("Create folder response:\n{}", response);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }
}
