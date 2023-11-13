package com.study;

import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HomeController {
    @Autowired
    private IFreeBoardDao freeBoardDao;

    @RequestMapping("/")
    public String home(Model model){
        return "home";
    }

    @ResponseBody
    @RequestMapping(value = "/ch", produces = "text/plain;charset=UTF-8")
    public FreeBoardVO ch() {

        return freeBoardDao.getBoard(500);
    }
    }
