package net.eldiosantos.cloudstorage.dropbox.service.steps;

import cucumber.api.java8.En;
import net.eldiosantos.cloudstorage.api.model.Resource;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.service.DropboxListService;
import net.eldiosantos.cloudstorage.dropbox.service.DropboxUploadService;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 13/02/2017.
 */
public class UploadFileSteps implements En {

    private final StorageConfiguration config = StorageConfiguration.apply();

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public UploadFileSteps() {
        When("^I send \"([^\"]*)\" to \"([^\"]*)\"$", (String source, String dest) -> {
            try {
                logger.info("########################################");
                logger.info("test: " + Paths.get(source).toAbsolutePath().toString());
                logger.info("########################################");
                new DropboxUploadService(config).upload(Paths.get(source).toString(), dest);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        Then("^I can list the \"([^\"]*)\" files and find the file \"([^\"]*)\" there$", (String remotePath, String fileName) -> {
            final List<Resource> list = new DropboxListService(config).list(remotePath);

            logger.info("## insert file #######################################################");
            logger.info("remote path: {}", remotePath);
            list.forEach(r -> logger.info(String.format("%s => %s", r.getName(), fileName)));
            logger.info("######################################################################");

            Assertions.assertFalse(list.stream().filter(r -> {
                logger.info(String.format("%s = %s", r.getName(), fileName));
                return r.getName().equals(fileName);
            }).collect(Collectors.toSet()).isEmpty(), "Do we have this file here?");
        });
    }
}
