package net.eldiosantos.cloudstorage.dropbox.service.steps;

import cucumber.api.java8.En;
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

    private Class<? extends Exception> exceptionType;

    public DownloadFileSteps() {
        try {
            When("^I look for the file \"([^\"]*)\"$", (String path) -> {
                try {
                    downloadedFile = new DropboxDownloadService(config).download(path);
                    logger.info("Downloadded file {}", downloadedFile.getAbsolutePath());
                } catch (Exception e) {
                    logger.error("Error trying to download file '{}'", path, e);
                    exceptionType = e.getClass();
                }
            });

            Then("^I have this file in my temporary folder$", () -> {
                Assertions.assertNotNull(downloadedFile, "Do we have a temporary file here?");
            });

            When("^I look for the file \"([^\"]*)\" to folder \"([^\"]*)\"$", (String remotePath, String localPath) -> {
                try {
                    downloadedFile = new DropboxDownloadService(config).download(remotePath, localPath);
                } catch (Exception e) {
                    exceptionType = e.getClass();
                }
            });

            Then("^I have this file on the local filesystem \"([^\"]*)\"$", (String localPath) -> {
                logger.info("Downloaded file actual location '{}'", downloadedFile!=null ? downloadedFile.getAbsolutePath() : "null");
                logger.info("Downloaded file right location '{}'", localPath);
                Assertions.assertNotNull(downloadedFile, "We have a downloaded file, right?");
                Assertions.assertTrue(downloadedFile.exists(), "The downloaded file exists?");
                Assertions.assertTrue(downloadedFile.getAbsolutePath().equals(Paths.get(localPath).toFile().getAbsolutePath()), "The file is in the right place?");
            });

            Then("^I have an \"([^\"]*)\" exception on my download attempt$", (String exceptionClassName) -> {
                logger.info("Looking for exception of type {}", exceptionClassName);
                logger.info("We had an exception of type {}", exceptionType.getName());
                Assertions.assertNotNull(exceptionType, "We had an exception type here?");
                Assertions.assertTrue(exceptionType.getCanonicalName().equalsIgnoreCase(exceptionClassName), "We had an exception of this type?");
            });

        } catch (Exception e) {
            logger.error("Error defining test steps for download features...", e);
        }
    }
}
