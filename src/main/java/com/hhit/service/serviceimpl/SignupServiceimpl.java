package com.hhit.service.serviceimpl;

import com.hhit.dao.SignupDao;
import com.hhit.model.SignupBean;
import com.hhit.service.SignupService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    @Override
    public List<SignupBean> getAllSignup(String userid) {
        return signupDao.getAllSignup(userid);
    }
}
