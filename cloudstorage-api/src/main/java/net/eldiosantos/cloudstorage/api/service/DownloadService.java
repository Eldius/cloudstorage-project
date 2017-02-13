package net.eldiosantos.cloudstorage.api.service;

import java.io.File;

/**
 * Created by esjunior on 10/02/2017.
 */
public interface DownloadService {
    public File download(final String resource, final String dest);
    public File download(final String resource);
}
