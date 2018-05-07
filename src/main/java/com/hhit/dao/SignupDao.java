package com.hhit.dao;

import com.hhit.model.SignupBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface SignupDao {
    void addSignup(SignupBean signupBean);
    SignupBean searchSignup(@Param("userid") String userid,
                            @Param("jobid") int jobid);
    List<SignupBean> getAllSignup(String userid);
    void deleteSignup(int signupid);
    List<SignupBean> signupquery();

}
