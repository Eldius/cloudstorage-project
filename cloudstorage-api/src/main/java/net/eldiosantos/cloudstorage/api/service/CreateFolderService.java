package net.eldiosantos.cloudstorage.api.service;

import net.eldiosantos.cloudstorage.api.model.Resource;

/**
 * Created by esjunior on 14/02/2017.
 */
public interface CreateFolderService {
    Resource create(String path) throws Exception;
}
