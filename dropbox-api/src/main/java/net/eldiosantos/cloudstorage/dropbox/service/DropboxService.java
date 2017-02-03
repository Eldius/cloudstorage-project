package net.eldiosantos.cloudstorage.dropbox.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Eldius on 02/02/2017.
 */
public abstract class DropboxService {

    protected final Logger logger = LoggerFactory.getLogger(getClass());

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
}
