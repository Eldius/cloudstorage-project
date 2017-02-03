package net.eldiosantos.cloudstorage.dropbox.service.request;

import com.google.gson.Gson;
import net.eldiosantos.cloudstorage.config.DropboxConfiguration;
import net.eldiosantos.cloudstorage.dropbox.service.DropboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 31/01/2017.
 */
public class DropboxRequestClient extends DropboxService {

    private final Gson gson;
    private final DropboxConfiguration _config;

    public DropboxRequestClient(final Gson gson, final DropboxConfiguration config) {
        this.gson = gson;
        _config = config;
    }

    public String makeRequest(final Object request, final String path, final String method, final Map<String, String>headers) throws Exception {
        final String _url = _config.url() + path;

        final URL url = new URL(_url);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestMethod(method.toUpperCase());
        conn.setRequestProperty("Authorization", String.format("Bearer %s", _config.accessToken()));
        conn.setRequestProperty("Content-Type", "application/json");

        headers.entrySet().forEach(e -> conn.setRequestProperty(e.getKey(), e.getValue()));

        conn.connect();

        String content;

        final String payload = gson.toJson(request);
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(conn.getOutputStream()))) {
            writer.append(payload);
            writer.flush();
        }

        try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            content = in.lines()
                    .collect(Collectors.joining("\n"));
            logger.debug("RESPONSE: {}", content);
        } catch (Exception e) {
            generateException(_url, conn, payload);
            content = "";
        }
        debug(_url, content);

        return content;
    }

    private String generateException(String _url, HttpURLConnection conn, String payload) throws Exception {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
            throw new IllegalArgumentException(
                new StringBuffer("[ERROR] ")
                    .append(
                        in.lines()
                            .collect(Collectors.joining("\n"))
                    ).append(String.format("\nURL: %s\nPAYLOAD: %s", _url, payload))
                    .toString()
            );
        }
    }
}
