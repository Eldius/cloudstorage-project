package net.eldiosantos.cloudstorage.dropbox.service.steps;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import net.eldiosantos.cloudstorage.api.model.Resource;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.service.DropboxDownloadService;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;

/**
 * Created by esjunior on 10/02/2017.
 */
public class DownloadFileSteps implements En {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final StorageConfiguration config = StorageConfiguration.apply();

    private File downloadedFile;

    public DownloadFileSteps() {
        try {
            When("^I look for the file \"([^\"]*)\"$", (String path) -> {
                downloadedFile = new DropboxDownloadService(config).download(
                    new Resource()
                        .setPathDisplay(path)
                );
            });

            Then("^I have this file in my temporary folder$", () -> {
                Assertions.assertNotNull(downloadedFile, "Do we have a temporary file here?");
            });

            When("^I look for the file \"([^\"]*)\" to folder \"([^\"]*)\"$", (String remotePath, String localPath) -> {
                downloadedFile = new DropboxDownloadService(config).download(
                    new Resource()
                        .setPathDisplay(remotePath)
                    , localPath
                );
            });

            Then("^I have this file on the local filesystem \"([^\"]*)\"$", (String localPath) -> {
                logger.info("Downloaded file actual location '{}'", downloadedFile.getAbsolutePath());
                logger.info("Downloaded file right location '{}'", localPath);
                Assertions.assertNotNull(downloadedFile, "We have a downloaded file, right?");
                Assertions.assertTrue(downloadedFile.exists(), "The downloaded file exists?");
                Assertions.assertTrue(downloadedFile.getAbsolutePath().equals(Paths.get(localPath).toFile().getAbsolutePath()), "The file is in the right place?");
            });
        } catch (Exception e) {
            logger.error("Error defining test steps for download features...", e);
        }
    }
}
