package net.eldiosantos.cloudstorage.dropbox.service;

import com.google.common.io.Files;
import com.google.gson.Gson;
import net.eldiosantos.cloudstorage.api.model.Resource;
import net.eldiosantos.cloudstorage.api.service.DownloadService;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import net.eldiosantos.cloudstorage.dropbox.pojo.DownloadFileRequest;
import net.eldiosantos.cloudstorage.dropbox.service.request.DropboxContentRequestClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by esjunior on 30/01/2017.
 */
public class DropboxDownloadService extends DropboxService implements DownloadService {

    private final DropboxContentRequestClient client;

    public DropboxDownloadService(final StorageConfiguration config) {
        super(config);
        client = new DropboxContentRequestClient(config.dropbox());
    }

    public File download(final Resource resource, final String dest) {

        try {
            final File file = downloadContent(resource);

            final File to = Paths.get(dest, resource.getPathDisplay()).toFile();
            if(!to.getParentFile().exists()) {
                to.getParentFile().mkdirs();
            }
            Files.move(file, to);
            return to;
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Error trying to download this piece of shit (%s)...", resource.getPathDisplay()), e);
        }
    }

    public File download(Resource resource) {
        try {
            return downloadContent(resource);
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Error trying to get file %s", resource.getPathDisplay()), e);
        }
    }

    @Override
    public File download(String resource) {
        try {
            return download(
                    new Resource()
                            .setPathDisplay(resource)
            );
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Error trying to get file %s", resource), e);
        }
    }

    @Override
    public File download(String resource, String path) {
        try {
            return download(
                new Resource()
                    .setPathDisplay(resource)
                , path
            );
        } catch (Exception e) {
            throw new IllegalArgumentException(String.format("Error trying to get file %s", resource), e);
        }
    }

    private File downloadContent(Resource resource) throws Exception {
        final DownloadFileRequest request = new DownloadFileRequest()
                .setPath(resource.getPathDisplay());
        final Map<String, String> headers = new HashMap<>();
        headers.put("Dropbox-API-Arg", gson.toJson(request));
        headers.put("Content-Type", "");
        return client.makeRequest("".getBytes(), "/files/download", "POST", headers);
    }
}
