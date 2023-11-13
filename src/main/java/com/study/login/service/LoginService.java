package com.study.login.service;

import com.study.login.vo.UserVO;
import com.study.member.dao.IMemberDao;
import com.study.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {
    @Autowired
    IMemberDao memberDao;

    public UserVO getUser(String memId) {

            MemberVO member = memberDao.getMember(memId);
            if (member == null) {
                return null;
            } else {
                String role = "USER";
                if (member.getMemId().equals("next49")) {
                    role="MANAGER";
                }
                UserVO user = new UserVO(
                        member.getMemId()
                        , member.getMemName()
                        , member.getMemPass()
                        , role);
                return user;
            }
        }
    }

