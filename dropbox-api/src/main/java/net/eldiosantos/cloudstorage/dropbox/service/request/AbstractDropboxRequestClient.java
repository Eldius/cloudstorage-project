package net.eldiosantos.cloudstorage.dropbox.service.request;

import net.eldiosantos.cloudstorage.config.DropboxConfiguration;
import net.eldiosantos.cloudstorage.dropbox.service.DropboxService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by esjunior on 03/02/2017.
 */
public class AbstractDropboxRequestClient extends DropboxService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

    protected final DropboxConfiguration _config;

    public AbstractDropboxRequestClient(final DropboxConfiguration config) {
        _config = config;
    }

    protected void debug(final String url, final String content) {
        if(logger.isDebugEnabled()) {
            logger.debug(
                String.format(
                    "########################################\nresponse from '%s':\nbody:\n%s\n########################################"
                    , url
                    , content
                )
            );
        }

    }

    protected void debug(final String url, final File content) {
        if(logger.isDebugEnabled()) {
            logger.debug(
                String.format(
                    "########################################\nresponse from '%s':\nresultFile:\n%s\n########################################"
                    , url
                    , content.getAbsolutePath()
                )
            );
        }

    }

    protected Exception generateException(URL url, HttpURLConnection conn, String payload, Map<String, String> headers) {
        try(BufferedReader in = new BufferedReader(new InputStreamReader(conn.getErrorStream()))) {
            return new IllegalArgumentException(
                    new StringBuffer("[ERROR] ")
                            .append(
                                    in.lines()
                                            .collect(Collectors.joining("\n"))
                            )
                            .append("\nheaders:")
                            .append(headers.entrySet().stream().map(h -> String.format("\n\t - %s: %s", h.getKey(), h.getValue())).collect(Collectors.joining()))
                            .append(String.format("\nURL: %s\nPAYLOAD: %s", url.toString(), payload))
                            .toString()
            );
        } catch (Exception e) {
            logger.error("Error generating exception", e);
            throw new IllegalStateException("Error generating exception", e);
        }
    }

}
