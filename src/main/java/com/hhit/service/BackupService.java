package com.hhit.service;

import com.hhit.model.BackupBean;

import java.util.List;

public interface BackupService {

    List<BackupBean> querybackup();
    void addbackup(BackupBean backupBean);
}
