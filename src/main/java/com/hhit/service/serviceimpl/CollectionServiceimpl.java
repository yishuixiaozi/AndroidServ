package com.hhit.service.serviceimpl;

import com.hhit.dao.CollectionDao;
import com.hhit.model.CollectionBean;
import com.hhit.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value="collectionService")
public class CollectionServiceimpl implements CollectionService{
    @Resource
    CollectionDao collectionDao;

    @Override
    public void addCollection(CollectionBean collectionBean) {
        collectionDao.addCollection(collectionBean);
    }
}