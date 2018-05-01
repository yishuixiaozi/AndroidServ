package com.hhit.service;

import com.hhit.model.SignupBean;

import java.util.List;

public interface SignupService {
    void addSignup(SignupBean signupBean);
    SignupBean searchSignup(String userid,int jobid);
    List<SignupBean> getAllSignup(String userid);
}
