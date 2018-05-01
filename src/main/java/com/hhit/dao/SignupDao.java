package com.hhit.dao;

import com.hhit.model.SignupBean;
import org.apache.ibatis.annotations.Param;

public interface SignupDao {
    void addSignup(SignupBean signupBean);
    SignupBean searchSignup(@Param("userid") String userid,
                            @Param("jobid") int jobid);

}
