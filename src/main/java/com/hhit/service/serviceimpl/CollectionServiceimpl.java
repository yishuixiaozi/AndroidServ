package com.hhit.service.serviceimpl;

import com.hhit.dao.CollectionDao;
import com.hhit.model.CollectionBean;
import com.hhit.service.CollectionService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value="collectionService")
public class CollectionServiceimpl implements CollectionService{
    @Resource
    CollectionDao collectionDao;

    @Override
    public void addCollection(CollectionBean collectionBean) {
        collectionDao.addCollection(collectionBean);
    }

    @Override
    public List<CollectionBean> getAllCollection(String userid) {
        return collectionDao.getAllCollection(userid);
    }

    @Override
    public CollectionBean getCollectionTag(String userid, int jobid) {
        return collectionDao.getCollectionTag(userid,jobid);
    }

    @Override
    public void deleteCollection(String userid, int jobid) {
        collectionDao.deleteCollection(userid,jobid);
    }

    @Override
    public List<CollectionBean> collectionquery() {
        return collectionDao.collectionquery();
    }

    @Override
    public void collectiondeleteid(int id) {
        collectionDao.collectiondeleteid(id);
    }
}
