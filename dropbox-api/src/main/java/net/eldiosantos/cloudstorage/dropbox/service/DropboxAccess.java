package net.eldiosantos.cloudstorage.dropbox.service;

import com.google.gson.Gson;
import net.eldiosantos.cloudstorage.config.DropboxConfiguration;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.pojo.ListFoldersRequest;
import net.eldiosantos.cloudstorage.dropbox.pojo.ListFoldersResponse;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxRequestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;

/**
 * Created by esjunior on 30/01/2017.
 */
public class DropboxAccess {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DropboxRequestClient client;
    private final Gson gson = new Gson();

    public DropboxAccess() {
        client = new DropboxRequestClient(new Gson(), StorageConfiguration.apply().dropbox());
    }

    public DropboxAccess(DropboxRequestClient client) {
        this.client = client;
    }

    public DropboxAccess(StorageConfiguration config) {
        this.client = new DropboxRequestClient(new Gson(), StorageConfiguration.apply().dropbox());
    }

    public ListFoldersResponse list(final ListFoldersRequest request) throws Exception {
        String content = client.makeRequest(request, "/files/list_folder", "POST", Collections.emptyMap());

        return gson.fromJson(content, ListFoldersResponse.class);
    }

}
