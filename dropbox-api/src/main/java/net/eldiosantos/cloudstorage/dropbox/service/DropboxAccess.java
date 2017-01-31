package net.eldiosantos.cloudstorage.dropbox.service;

import net.eldiosantos.cloudstorage.config.DropboxConfiguration;
import net.eldiosantos.cloudstorage.config.StorageConfiguration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 30/01/2017.
 */
public class DropboxAccess {

    private final StorageConfiguration config;

    public DropboxAccess() {
        config = StorageConfiguration.apply();
    }

    public DropboxAccess(StorageConfiguration config) {
        this.config = config;
    }

    public void test() throws Exception {
        final DropboxConfiguration _config = config.dropbox();

        final String _url = _config.url();

        final URL url = new URL(_url + "/users/get_current_account");

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setDoInput(true);
        conn.setDoOutput(true);
        conn.setRequestProperty("Authorization", "Bearer <OAUTH2_ACCESS_TOKEN>".replace("<OAUTH2_ACCESS_TOKEN>", _config.accessToken()));

        conn.connect();

        String content;
        try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            content = in.lines()
                .collect(Collectors.joining("\n"));
        }

        System.out.println(content);


    }
}
