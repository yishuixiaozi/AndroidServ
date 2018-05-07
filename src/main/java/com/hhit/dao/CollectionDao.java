package com.hhit.dao;

import com.hhit.model.CollectionBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CollectionDao {
    void addCollection(CollectionBean collectionBean);
    List<CollectionBean> getAllCollection(String userid);
    CollectionBean getCollectionTag(@Param("userid") String userid,
                                    @Param("jobid") int jobid);
    void deleteCollection(@Param("userid") String userid,
                          @Param("jobid") int jobid);
    List<CollectionBean> collectionquery();
}
