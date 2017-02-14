package net.eldiosantos.cloudstorage.dropbox.service;

import net.eldiosantos.cloudstorage.api.model.Resource;
import net.eldiosantos.cloudstorage.api.service.DeleteService;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.pojo.DeleteRequest;
import net.eldiosantos.cloudstorage.dropbox.pojo.DeleteResponse;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxRequestClient;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by esjunior on 13/02/2017.
 */
public class DropboxDeleteService extends DropboxService implements DeleteService {
    public DropboxDeleteService(StorageConfiguration config) {
        super(config);
    }

    @Override
    public Resource delete(final String path) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        final DeleteResponse deletedResponse = deleteResource(path);
        return new Resource()
                .setId(deletedResponse.getId())
                .setName(deletedResponse.getName())
                .setPathDisplay(deletedResponse.getPathDisplay())
                .setType(deletedResponse.getTag().equals("folder") ? Resource.ResourceType.FOLDER : Resource.ResourceType.FILE);
    }

    public DeleteResponse deleteResource(final String path) throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        return parse(
            new DropboxRequestClient(config.dropbox()).makeRequest(
                gson.toJson(new DeleteRequest().setPath(path))
                ,"/files/delete"
                , "POST"
                , headers
            )
            , DeleteResponse.class
        );
    }
}
