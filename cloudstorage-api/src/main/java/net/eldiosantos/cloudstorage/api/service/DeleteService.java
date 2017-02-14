package net.eldiosantos.cloudstorage.api.service;

import net.eldiosantos.cloudstorage.api.model.Resource;

/**
 * Created by Eldius on 14/02/2017.
 */
public interface DeleteService {
    Resource delete(final String path) throws Exception;
}
