package com.hhit.service.serviceimpl;

import com.hhit.dao.SignupDao;
import com.hhit.model.SignupBean;
import com.hhit.service.SignupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service(value = "signupService")
public class SignupServiceimpl implements SignupService{
    @Resource
    SignupDao signupDao;
    @Override
    public void addSignup(SignupBean signupBean) {
        signupDao.addSignup(signupBean);
    }

    @Override
    public SignupBean searchSignup(String userid, int jobid) {
        return signupDao.searchSignup(userid,jobid);
    }
}
