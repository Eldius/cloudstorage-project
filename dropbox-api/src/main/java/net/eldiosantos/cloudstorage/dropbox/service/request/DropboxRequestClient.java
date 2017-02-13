package net.eldiosantos.cloudstorage.dropbox.service.request;

import net.eldiosantos.cloudstorage.config.DropboxConfiguration;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 31/01/2017.
 */
public class DropboxRequestClient extends AbstractDropboxRequestClient {

    public DropboxRequestClient(DropboxConfiguration config) {
        super(config);
    }

    public String makeRequest(final String request, final String path, final String method, final Map<String, String>headers) throws Exception {
        final String _url = _config.url() + path;

        final URL url = new URL(_url);

        String content = request(request, method, headers, url);
        debug(_url, content);

        return content;
    }

    private String request(String request, String method, Map<String, String> _headers, URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        final Map<String, String>headers = new HashMap<>(_headers);
        headers.put("Authorization", String.format("Bearer %s", _config.accessToken()));

        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod(method.toUpperCase());

        headers.entrySet().forEach(e -> conn.setRequestProperty(e.getKey(), e.getValue()));

        conn.connect();

        String content;


        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
            logger.debug("request:\n{}", request);
            if(request != null) {
                writer.append(request);
            }
            writer.flush();
        }

        try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            content = in.lines()
                    .collect(Collectors.joining("\n"));
            debug(url.toString(), content);
        } catch (Exception e) {
            throw generateException(url, conn, request, headers);
        }
        return content;
    }

}
