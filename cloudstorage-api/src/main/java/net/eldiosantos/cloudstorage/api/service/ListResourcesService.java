package net.eldiosantos.cloudstorage.api.service;

import net.eldiosantos.cloudstorage.api.model.Resource;

import java.util.List;

/**
 * Created by esjunior on 10/02/2017.
 */
public interface ListResourcesService {
    List<Resource> list(final String path);
}
