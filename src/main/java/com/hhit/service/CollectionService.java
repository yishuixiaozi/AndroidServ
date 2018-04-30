package com.hhit.service;

import com.hhit.model.CollectionBean;

import java.util.List;

public interface CollectionService {

    void addCollection(CollectionBean collectionBean);
    List<CollectionBean> getAllCollection(String userid);
}
