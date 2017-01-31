package net.eldiosantos.cloudstorage.dropbox.service;

import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static org.junit.Assert.*;

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
            new DropboxAccess().test();
        } catch (Exception e) {
            logger.error(StorageConfiguration.apply().dropbox().toString(), e);
            throw e;
        }
    }
}
