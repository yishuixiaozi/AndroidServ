package com.hhit.service;

import com.hhit.model.CollectionBean;

import java.util.List;

public interface CollectionService {

    void addCollection(CollectionBean collectionBean);
    List<CollectionBean> getAllCollection(String userid);
    CollectionBean getCollectionTag(String userid,int jobid);
    void deleteCollection(String userid,int jobid);
    List<CollectionBean> collectionquery();
    void collectiondeleteid(int id);
}
