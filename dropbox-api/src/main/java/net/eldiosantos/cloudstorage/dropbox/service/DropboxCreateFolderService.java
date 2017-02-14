package net.eldiosantos.cloudstorage.dropbox.service;

import net.eldiosantos.cloudstorage.api.model.Resource;
import net.eldiosantos.cloudstorage.api.service.CreateFolderService;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.pojo.CreateFolderRequest;
import net.eldiosantos.cloudstorage.dropbox.pojo.CreateFolderResponse;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxRequestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by esjunior on 13/02/2017.
 */
public class DropboxCreateFolderService extends DropboxService implements CreateFolderService {
    private final DropboxRequestClient client;

    public DropboxCreateFolderService(StorageConfiguration config) {
        super(config);
        this.client = new DropboxRequestClient(config.dropbox());
    }

    public CreateFolderResponse createFolder(final String path) throws Exception {
        return parse(create(path, true), CreateFolderResponse.class);

    }

    public String create(final String path, final Boolean autorename) throws Exception {
        final CreateFolderRequest request = new CreateFolderRequest()
                .setAutorename(autorename)
                .setPath(path);
        final Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return client.makeRequest(gson.toJson(request), "/files/create_folder", "POST", headers);
    }

    public CreateFolderResponse createFolder(final String path, final Boolean autorename) throws Exception {
        return parse(create(path, autorename), CreateFolderResponse.class);
    }

    @Override
    public Resource create(String path) throws Exception {
        final CreateFolderResponse createdFolder = createFolder(path, true);
        return new Resource()
                .setName(createdFolder.getName())
                .setId(createdFolder.getId())
                .setPathDisplay(createdFolder.getPathDisplay())
                .setType(Resource.ResourceType.FOLDER);
    }
}
