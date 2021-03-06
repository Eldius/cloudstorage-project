package net.eldiosantos.cloudstorage.dropbox.service;

import net.eldiosantos.cloudstorage.api.model.Resource;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.pojo.ListFoldersRequest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by esjunior on 03/02/2017.
 */
class DropboxDownloadServiceTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private List<Resource>resourceList;
    private StorageConfiguration config = StorageConfiguration.apply();

    @BeforeEach
    void setUp() throws Exception {
        resourceList = new DropboxListService(config).list(
            new ListFoldersRequest()
                .setIncludeDeleted(false)
                .setIncludeHasExplicitSharedMembers(true)
                .setIncludeMediaInfo(true)
                .setPath("")
                .setRecursive(true)
        ).parallelStream()
            .peek(r -> logger.debug(String.format("[DEBUG 01] %s => %s", r.getName(), r.getType())))
            .filter(r -> r.getType().equals(Resource.ResourceType.FILE))
            .peek(r -> logger.debug(String.format("[DEBUG 02] %s => %s", r.getName(), r.getType())))
            .collect(Collectors.toList());
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void download() throws Exception {
        final Resource resource = resourceList.get(0);
        logger.info("Trying to download '{}'", resource.getName());
        final String destFolder = "./build/download";
        new DropboxDownloadService(config).download(resource, destFolder);

        assertTrue(Paths.get(destFolder, resource.getPathDisplay()).toFile().exists(), "Do we have the file?");
    }

    @Test
    void download1() throws Exception {
        final String destFolder = "./build/download_2";
        final DropboxDownloadService service = new DropboxDownloadService(config);

        resourceList.parallelStream()
            .peek(r -> service.download(r, destFolder))
            .forEach(r -> {
                assertTrue(Paths.get(destFolder, r.getPathDisplay()).toFile().exists(), String.format("Do we have the file %s ", r.getName()));
            });
    }

}