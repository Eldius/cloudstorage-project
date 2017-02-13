package net.eldiosantos.cloudstorage.dropbox.service;

import com.google.gson.Gson;
import net.eldiosantos.cloudstorage.api.model.Resource;
import net.eldiosantos.cloudstorage.api.service.ListResourcesService;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.pojo.ListFoldersRequest;
import net.eldiosantos.cloudstorage.dropbox.pojo.ListFoldersResponse;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxRequestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 30/01/2017.
 */
public class DropboxListService extends DropboxService implements ListResourcesService {

    private final DropboxRequestClient client;

    public DropboxListService(StorageConfiguration config) {
        super(config);
        this.client = new DropboxRequestClient(StorageConfiguration.apply().dropbox());
    }

    public List<Resource> list(final ListFoldersRequest request) {
        try {
            Map<String, String> headers = new HashMap<>();
            headers.put("Content-Type", "application/json");
            String content = client.makeRequest(gson.toJson(request), "/files/list_folder", "POST", headers);

            return gson.fromJson(content, ListFoldersResponse.class)
                    .getEntries()
                    .parallelStream()
                    .map(r ->
                            new Resource()
                                    .setId(r.getId())
                                    .setPathDisplay(r.getPathDisplay())
                                    .setName(r.getName())
                                    .setType(r.getTag().equals("folder") ? Resource.ResourceType.FOLDER : Resource.ResourceType.FILE)
                    )
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalStateException(String.format("Error trying to list resources from Dropbox at %s", request.getPath()), e);
        }
    }

    @Override
    public List<Resource> list(final String path) {
        return this.list(
            new ListFoldersRequest()
                .setIncludeDeleted(false)
                .setIncludeHasExplicitSharedMembers(true)
                .setIncludeMediaInfo(true)
                .setPath(path)
                .setRecursive(true)
        );
    }

}
