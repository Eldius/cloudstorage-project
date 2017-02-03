package net.eldiosantos.cloudstorage.dropbox.service.request;

import net.eldiosantos.cloudstorage.config.DropboxConfiguration;

import java.io.BufferedWriter;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by esjunior on 03/02/2017.
 */
public class DropboxContentRequestClient extends AbstractDropboxRequestClient {
    public DropboxContentRequestClient(DropboxConfiguration config) {
        super(config);
    }

    private File request(String request, String method, Map<String, String> _headers, URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        final Map<String, String>headers = new HashMap<>(_headers);
        headers.put("Authorization", String.format("Bearer %s", _config.accessToken()));

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod(method.toUpperCase());

        headers.entrySet().forEach(e -> conn.setRequestProperty(e.getKey(), e.getValue()));

        conn.connect();

        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
            if(request != null) {
                writer.append(request);
            }
            writer.flush();
        }


        try(InputStream in = conn.getInputStream()) {
            final File file = File.createTempFile("contentFile", ".tmp");
            file.delete();
            Files.copy(in, file.toPath());

            debug(url.toString(), file);

            return file;
        } catch (Exception e) {
            throw generateException(url, conn, request, headers);
        }
    }

    public File makeRequest(final String request, final String path, final String method, final Map<String, String>headers) throws Exception {
        final String _url = _config.contentUrl() + path;

        final URL url = new URL(_url);

        return request(request, method, headers, url);
    }
}
