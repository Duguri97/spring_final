package com.study.member.web;

import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.service.MailService;
import com.study.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.mail.MessagingException;

@Controller
public class MemberFormController {

    @Autowired
    private IMemberService memberService;

    @ResponseBody
    @RequestMapping(value = "/join/idCheck.wow", produces = "text/plain;charset=UTF-8")
    public String idCheck(String id) {
        try {
            MemberVO member = memberService.getMember(id);
            return "중복된 아이디";
        } catch (BizNotFoundException e) {
            return "사용가능한 아이디";
        }
    }

    @Autowired
    private MailService mailService;


    @ResponseBody
    @RequestMapping(value = "/join/emailSend.wow")
    public String qwerasdf(String email) throws MessagingException {
        // 이제 여기서 아까 main 메소드에서 했던 메일을 보내면 됩니다.
        // service 에서 mail 보내도록 합시다.
        return mailService.mailSend(email);
    }
}