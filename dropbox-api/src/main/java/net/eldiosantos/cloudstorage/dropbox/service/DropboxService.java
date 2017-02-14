package net.eldiosantos.cloudstorage.dropbox.service;

import com.google.gson.Gson;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eldius on 02/02/2017.
 */
public abstract class DropboxService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final Gson gson = new Gson();

    protected final StorageConfiguration config;

    protected DropboxService(StorageConfiguration config) {
        this.config = config;
    }

    protected <T> T parse(final String json, final Class<T> clazz) {
        return gson.fromJson(json, clazz);
    }
}
