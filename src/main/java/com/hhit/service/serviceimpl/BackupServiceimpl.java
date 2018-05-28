package com.hhit.service.serviceimpl;

import com.hhit.dao.BackupDao;
import com.hhit.model.BackupBean;
import com.hhit.service.BackupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service(value = "backupService")
public class BackupServiceimpl implements BackupService{

    @Resource
    BackupDao backupDao;

    @Override
    public List<BackupBean> querybackup() {
        return backupDao.querybackup();
    }

    @Override
    public void addbackup(BackupBean backupBean) {
        backupDao.addbackup(backupBean);
    }
}
