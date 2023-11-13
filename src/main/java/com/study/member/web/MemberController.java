package com.study.member.web;



import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.vo.PagingVO;
import com.study.common.vo.ResultMessageVO;
import com.study.common.vo.SearchVO;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.service.IMemberService;
import com.study.member.vo.MemberVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class MemberController {

    @Autowired
    ICommCodeService codeService;


    @Autowired
    IMemberService memberService;

    @ExceptionHandler(value = {BizNotFoundException.class
            , BizNotEffectedException.class})
    public String exception(Model model, BizException e) {
        e.printStackTrace();
        ResultMessageVO resultMessageVO = new ResultMessageVO();
        resultMessageVO.messageSetting(false, "에러", "에러났어요", "/", "홈으로가기");
        model.addAttribute("resultMessageVO", resultMessageVO);
        return "common/message";
    }


    @RequestMapping("/member/memberList.wow")
    public String MemberList(Model model,
                             @ModelAttribute("paging") PagingVO paging,
                             @ModelAttribute("search") SearchVO search,
                             @ModelAttribute("searchJob") String searchJob,
                             @ModelAttribute("searchHobby") String searchHobby) {

        List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
        model.addAttribute("jobList", jobList);

        List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
        model.addAttribute("hobbyList", hobbyList);

        List<MemberVO> memberList = memberService.getMemberList(paging, search, searchJob, searchHobby);
        model.addAttribute("memberList", memberList);

        return "member/memberList";
    }


    @GetMapping("/member/memberView.wow")
    public String memberView(Model model,
                             @RequestParam(value = "memId") String memId)
            throws BizNotFoundException, BizException {

        MemberVO member = memberService.getMember(memId);
        model.addAttribute("member", member);

        return "member/memberView";
    }

    @GetMapping("/member/memberEdit.wow")
    public String memberEdit(Model model,
                             @RequestParam(value = "memId") String memId)
            throws BizNotFoundException, BizException {

        List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
        model.addAttribute("jobList", jobList);

        List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
        model.addAttribute("hobbyList", hobbyList);

        MemberVO member = memberService.getMember(memId);
        model.addAttribute("member", member);

        return "member/memberEdit";
    }


    @PostMapping("/member/memberModify.wow")
    public String memberModify(Model model, MemberVO member)
            throws BizNotEffectedException, BizNotFoundException, BizException {

        memberService.modifyMember(member);

        ResultMessageVO resultMessageVO = new ResultMessageVO();
        resultMessageVO.messageSetting(true, "수정", "수정에 성공했습니다.", "/member/memberList.wow", "목록으로");
        model.addAttribute("resultMessageVO", resultMessageVO);

        return "common/message";
    }


    @RequestMapping("/member/memberDelete.wow")
    public String memberDelete(Model model, MemberVO member)
            throws BizNotEffectedException, BizNotFoundException, BizException {

        memberService.removeMember(member);

        ResultMessageVO resultMessageVO = new ResultMessageVO();
        resultMessageVO.messageSetting(true, "삭제", "삭제 성공했습니다.", "/member/memberList.wow", "목록으로");
        model.addAttribute("resultMessageVO", resultMessageVO);

        return "common/message";
    }


    @GetMapping("/member/memberForm.wow")
    public String memberForm(Model model) {

        List<CodeVO> jobList = codeService.getCodeListByParent("JB00");
        model.addAttribute("jobList", jobList);

        List<CodeVO> hobbyList = codeService.getCodeListByParent("HB00");
        model.addAttribute("hobbyList", hobbyList);

        return "member/memberForm";
    }

    @PostMapping("/member/memberRegist.wow")
    public String memberRegist(Model model, MemberVO member)
            throws BizNotEffectedException, BizDuplicateKeyException, BizException {

        memberService.registMember(member);

        ResultMessageVO resultMessageVO = new ResultMessageVO();
        resultMessageVO.messageSetting(true, "등록", "등록에 성공했습니다.", "/member/memberList.wow", "목록으로");
        model.addAttribute("resultMessageVO", resultMessageVO);

        return "common/message";
    }
}