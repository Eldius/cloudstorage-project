package net.eldiosantos.cloudstorage.dropbox.service;

import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.pojo.UploadFileRequest;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxContentRequestClient;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by esjunior on 13/02/2017.
 */
public class DropboxUploadService extends DropboxService {

    private final DropboxContentRequestClient client;

    public DropboxUploadService(StorageConfiguration config) {
        super(config);
        this.client = new DropboxContentRequestClient(config.dropbox());
    }

    public String upload(final String source, final String dest) throws Exception {
        UploadFileRequest request = new UploadFileRequest()
                .setPath(dest)
                .setAutorename(true)
                .setMode("add")
                .setMute(false);

        return uploadContent(request, Paths.get(source));
    }

    private String uploadContent(UploadFileRequest request, final Path file) throws Exception {
        final Map<String, String> headers = new HashMap<>();
        headers.put("Dropbox-API-Arg", gson.toJson(request));
        headers.put("Content-Type", "application/octet-stream");
        return client.makeRequest(file, "/files/upload", "POST", headers);
    }
}
