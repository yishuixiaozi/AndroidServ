package com.hhit.dao;

import com.hhit.model.CollectionBean;

import java.util.List;

public interface CollectionDao {
    void addCollection(CollectionBean collectionBean);
    List<CollectionBean> getAllCollection(String userid);
}
