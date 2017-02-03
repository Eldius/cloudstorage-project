package net.eldiosantos.cloudstorage.dropbox.service;

import com.google.gson.Gson;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.pojo.DownloadFileRequest;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxContentRequestClient;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxRequestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by esjunior on 30/01/2017.
 */
public class DropboxDownloadService extends DropboxService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final DropboxContentRequestClient client;
    private final Gson gson = new Gson();

    public DropboxDownloadService() {
        client = new DropboxContentRequestClient(StorageConfiguration.apply().dropbox());
    }

    public DropboxDownloadService(DropboxContentRequestClient client) {
        this.client = client;
    }

    public DropboxDownloadService(StorageConfiguration config) {
        this.client = new DropboxContentRequestClient(config.dropbox());
    }

    public void download(final DownloadFileRequest request) throws Exception {
        final Map<String, String>headers = new HashMap<>();
        headers.put("Dropbox-API-Arg", gson.toJson(request));
        headers.put("Content-Type", "");
        File content = client.makeRequest("", "/files/download", "POST", headers);
        logger.debug(String.format("[SERVICE]#################################################\nreturned file: %s\n[SERVICE]#################################################", content.getAbsolutePath()));
    }
}
