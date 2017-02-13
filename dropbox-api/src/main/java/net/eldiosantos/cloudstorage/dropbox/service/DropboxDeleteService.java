package net.eldiosantos.cloudstorage.dropbox.service;

import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.pojo.DeleteRequest;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxRequestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by esjunior on 13/02/2017.
 */
public class DropboxDeleteService extends DropboxService {
    public DropboxDeleteService(StorageConfiguration config) {
        super(config);
    }

    public String delete(final String path) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return new DropboxRequestClient(config.dropbox()).makeRequest(
            gson.toJson(new DeleteRequest().setPath(path))
            ,"/files/delete"
            , "POST"
            , headers
        );
    }
}
