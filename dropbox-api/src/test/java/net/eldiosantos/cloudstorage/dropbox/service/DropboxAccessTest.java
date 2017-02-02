package net.eldiosantos.cloudstorage.dropbox.service;

import net.eldiosantos.cloudstorage.dropbox.model.Resource;
import net.eldiosantos.cloudstorage.dropbox.pojo.ListFoldersRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
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
