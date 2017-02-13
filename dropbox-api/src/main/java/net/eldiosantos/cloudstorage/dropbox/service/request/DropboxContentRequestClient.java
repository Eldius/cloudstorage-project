package net.eldiosantos.cloudstorage.dropbox.service.request;

import net.eldiosantos.cloudstorage.config.DropboxConfiguration;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 03/02/2017.
 */
public class DropboxContentRequestClient extends AbstractDropboxRequestClient {
    public DropboxContentRequestClient(DropboxConfiguration config) {
        super(config);
    }

    private File request(byte[] request, String method, Map<String, String> _headers, URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        final Map<String, String>headers = new HashMap<>(_headers);
        headers.put("Authorization", String.format("Bearer %s", _config.accessToken()));

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod(method.toUpperCase());

        headers.entrySet().forEach(e -> conn.setRequestProperty(e.getKey(), e.getValue()));

        conn.connect();

        try(OutputStream writer = conn.getOutputStream()) {
            if(request != null) {
                writer.write(request);
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
            throw generateException(url, conn, new String(request, "utf-8"), headers);
        }
    }
    private String request(Path file, String method, Map<String, String> _headers, URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        final Map<String, String>headers = new HashMap<>(_headers);
        headers.put("Authorization", String.format("Bearer %s", _config.accessToken()));

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod(method.toUpperCase());

        headers.entrySet().forEach(e -> conn.setRequestProperty(e.getKey(), e.getValue()));

        conn.connect();

        try(OutputStream out = conn.getOutputStream()) {
            if(file != null) {
                Files.copy(file, out);
            }
            out.flush();
        }

        String content;
        try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            content = in.lines()
                    .collect(Collectors.joining("\n"));
            debug(url.toString(), content);
        } catch (Exception e) {
            throw generateException(url, conn, file.toString(), headers);
        }

        return content;
    }

    public File makeRequest(final byte[] request, final String path, final String method, final Map<String, String>headers) throws Exception {
        final String _url = _config.contentUrl() + path;

        final URL url = new URL(_url);

        return request(request, method, headers, url);
    }
    public String makeRequest(final Path file, final String path, final String method, final Map<String, String>headers) throws Exception {
        final String _url = _config.contentUrl() + path;

        final URL url = new URL(_url);

        return request(file, method, headers, url);
    }
}
