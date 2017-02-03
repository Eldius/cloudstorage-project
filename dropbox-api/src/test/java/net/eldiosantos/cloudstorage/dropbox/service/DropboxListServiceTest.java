package net.eldiosantos.cloudstorage.dropbox.service;

import net.eldiosantos.cloudstorage.dropbox.model.Resource;
import net.eldiosantos.cloudstorage.dropbox.pojo.ListFoldersRequest;
import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by esjunior on 30/01/2017.
 */
@DisplayName("Testing Dropbox listing service")
public class DropboxListServiceTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @BeforeEach
    public void setUp() throws Exception {

    }

    @AfterEach
    public void tearDown() throws Exception {

    }

    @Test
    @DisplayName("Listing a valid folder")
    public void test1() throws Exception {
        final List<Resource> list = new DropboxListService().list(
            new ListFoldersRequest()
                .setIncludeDeleted(false)
                .setIncludeHasExplicitSharedMembers(true)
                .setIncludeMediaInfo(true)
                .setPath("")
                .setRecursive(true)
        );
        list.forEach(e ->
            logger.info(
                String.format(
                    "name: %s => %s [%s | %s]"
                    , e.getViewPath()
                    , e.getName()
                    , e.getId()
                    , e.getType()
                )
            )
        );

        assertFalse(list.isEmpty(), "I hope we have some files here...");

    }

    @Test
    @DisplayName("Listing an invalid folder")
    public void test2() throws Exception {

        assertThrows(Exception.class, () ->
            new DropboxListService().list(
                new ListFoldersRequest()
                    .setIncludeDeleted(false)
                    .setIncludeHasExplicitSharedMembers(true)
                    .setIncludeMediaInfo(true)
                    .setPath("/nonExistentFolder")
                    .setRecursive(true)
            )
        );

    }

        public static <T> Optional<T> resolve(Supplier<T> resolver) {
        try {
            T result = resolver.get();
            return Optional.ofNullable(result);
        }
        catch (NullPointerException e) {
            return Optional.empty();
        }
    }
}
