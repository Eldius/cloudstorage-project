package net.eldiosantos.cloudstorage.api.service;

import net.eldiosantos.cloudstorage.api.model.Resource;

import java.io.File;

/**
 * Created by esjunior on 10/02/2017.
 */
public interface DownloadService {
    public File download(final Resource resource, final String dest);
    public File download(final Resource resource);
}
