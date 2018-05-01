package com.hhit.service;

import com.hhit.model.SignupBean;

public interface SignupService {
    void addSignup(SignupBean signupBean);
    SignupBean searchSignup(String userid,int jobid);
}
