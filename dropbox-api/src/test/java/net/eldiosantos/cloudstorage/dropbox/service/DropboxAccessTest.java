package net.eldiosantos.cloudstorage.dropbox.service;

import net.eldiosantos.cloudstorage.dropbox.pojo.ListFoldersRequest;
import net.eldiosantos.cloudstorage.dropbox.pojo.ListFoldersResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;
import java.util.function.Supplier;

/**
 * Created by esjunior on 30/01/2017.
 */
public class DropboxAccessTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test1() throws Exception {
        try {
            final ListFoldersResponse list = new DropboxAccess().list(
                new ListFoldersRequest()
                    .setIncludeDeleted(false)
                    .setIncludeHasExplicitSharedMembers(true)
                    .setIncludeMediaInfo(true)
                    .setPath("")
                    .setRecursive(true)
            );
            list.getEntries().forEach(e ->
                logger.info(
                    String.format(
                        "name: %s => %s [%s | %s]"
                        , e.getPathDisplay()
                        , e.getName()
                        , resolve(() -> e.getMediaInfo().getTag()).orElseGet(() -> "")
                        , resolve(() -> e.getMediaInfo().getMetadata().getTag()).orElseGet(() -> "")
                    )
                )
            );
        } catch (Exception e) {
            throw e;
        }
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
