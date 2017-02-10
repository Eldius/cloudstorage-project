package net.eldiosantos.cloudstorage.dropbox.service.steps;

import cucumber.api.java8.En;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.api.model.Resource;
import net.eldiosantos.cloudstorage.dropbox.service.DropboxListService;
import org.junit.jupiter.api.Assertions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 09/02/2017.
 */
public class ListFilesSteps implements En {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final StorageConfiguration config = StorageConfiguration.apply();

    private List<Resource>returnedResources;

    public ListFilesSteps() {
        When("^I look to the folder \"([^\"]*)\"$", (String path) -> {
            returnedResources = new DropboxListService(config).list(path);
        });

        Then("^I have \"([^\"]*)\" files$", (Integer listSize) -> {
            final List<Resource>files = returnedResources
                    .stream()
                    .filter(r -> r.getType().equals(Resource.ResourceType.FILE))
                    .collect(Collectors.toList());
            logger.info(
                "\n#######################################\nReturned list size is '{}':{}\n#######################################\n"
                , files.size()
                , files.stream().map(r -> r.getPathDisplay()).collect(Collectors.joining("\n\t- "))
            );
            Assertions.assertTrue(listSize.equals(files.size()), String.format("Do we have %d files here?", listSize));
        });
    }
}
