package net.eldiosantos.cloudstorage.dropbox.service;

import com.google.gson.Gson;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.pojo.CreateFolderRequest;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxRequestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by esjunior on 13/02/2017.
 */
public class DropboxCreateFolderService extends DropboxService {
    private final DropboxRequestClient client;

    public DropboxCreateFolderService(StorageConfiguration config) {
        super(config);
        this.client = new DropboxRequestClient(config.dropbox());
    }

    public String create(final String path) throws Exception {
        final CreateFolderRequest request = new CreateFolderRequest()
                .setAutorename(true)
                .setPath(path);
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return client.makeRequest(gson.toJson(request), "/files/create_folder", "POST", headers);

    }

    public String create(final String path, final Boolean autorename) throws Exception {
        final CreateFolderRequest request = new CreateFolderRequest()
                .setAutorename(autorename)
                .setPath(path);
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return client.makeRequest(gson.toJson(request), "/files/create_folder", "POST", headers);
    }
}
