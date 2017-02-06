package net.eldiosantos.cloudstorage.dropbox.service;

import com.google.common.io.Files;
import com.google.gson.Gson;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.model.Resource;
import net.eldiosantos.cloudstorage.dropbox.pojo.DownloadFileRequest;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxContentRequestClient;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxRequestClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
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

    public File download(final Resource resource, final String dest) {

        try {
            final DownloadFileRequest request = new DownloadFileRequest()
                    .setPath(resource.getPathDisplay());
            final File baseDir = Paths.get(dest).toFile();
            if (!baseDir.exists()) {
                baseDir.mkdirs();
            }
            final Map<String, String> headers = new HashMap<>();
            headers.put("Dropbox-API-Arg", gson.toJson(request));
            headers.put("Content-Type", "");
            final File file = client.makeRequest("", "/files/download", "POST", headers);
            final File to = Paths.get(dest, request.getPath()).toFile();
            if(!to.getParentFile().exists()) {
                to.getParentFile().mkdirs();
            }
            Files.move(file, to);
            return file;
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Error trying to download this piece of shit (%s)...", resource.getPathDisplay()), e);
        }
    }
}
