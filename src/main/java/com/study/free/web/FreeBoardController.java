package com.study.free.web;

import com.study.attach.vo.AttachVO;
import com.study.code.service.CommCodeServiceImpl;
import com.study.code.service.ICommCodeService;
import com.study.code.vo.CodeVO;
import com.study.common.util.StudyAttachUtils;
import com.study.common.vo.PagingVO;
import com.study.common.vo.ResultMessageVO;
import com.study.common.vo.SearchVO;
import com.study.exception.BizException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.service.FreeBoardServiceImpl;
import com.study.free.service.IFreeBoardService;
import com.study.free.vo.FreeBoardVO;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
public class FreeBoardController {
    @Autowired
    private ICommCodeService codeService;
    // freeBoardController는 commCodeService에 의존합니다.

    @Autowired
    IFreeBoardService freeBoardService;

    @Autowired
    private StudyAttachUtils attachUtils;

    @ExceptionHandler(value = {BizNotFoundException.class, BizNotEffectedException.class, BizPasswordNotMatchedException.class})
    public String exception(Model model, BizException e) {
        e.printStackTrace();
        ResultMessageVO resultMessageVO = new ResultMessageVO();
        resultMessageVO.setMessage("에러났어요.");
        model.addAttribute("resultMessageVO", resultMessageVO);
        return "common/message";
    }
    // try catch 대신 그냥 전부 다 throw 하세요...

    @RequestMapping("/free/freeList.wow")
    public String freeList(Model model
            , @ModelAttribute("paging") PagingVO paging
            , @ModelAttribute("search") SearchVO search
            , @ModelAttribute("searchCategory") String searchCategory) {
        List<CodeVO> cateList = codeService.getCodeListByParent("BC00");
        model.addAttribute("cateList", cateList);

        List<FreeBoardVO> freeList = freeBoardService.getBoardList(paging, search, searchCategory);
        model.addAttribute("freeBoardList", freeList);
        return "free/freeList";
    }

    @RequestMapping(method = RequestMethod.GET, value = "/free/freeView.wow")
//    @GetMapping("/free/freeView")
    public String freeView(Model model, @RequestParam(name = "boNo") int boNo) {

        try {
            FreeBoardVO freeBoard = freeBoardService.getBoard(boNo);
            model.addAttribute("freeBoard", freeBoard);
            freeBoardService.increaseHit(boNo);
        } catch (BizNotFoundException | BizNotEffectedException e) {
            ResultMessageVO resultMessageVO = new ResultMessageVO();
            resultMessageVO.messageSetting(false, "글찾기", "글이없다네...", "/free/freeList.wow", "목록으로");
            model.addAttribute("resultMessageVO", resultMessageVO);
            return "common/message";
        }
        return "free/freeView";
    }

    @GetMapping("/free/freeEdit.wow")
    public String edit(Model model, int boNo) {
        List<CodeVO> codeList = codeService.getCodeListByParent("BC00");
        model.addAttribute("codeList", codeList);

        try {
            FreeBoardVO freeBoard = freeBoardService.getBoard(boNo);
            model.addAttribute("freeBoard", freeBoard);
        } catch (BizNotFoundException e) {
            ResultMessageVO resultMessageVO = new ResultMessageVO();
            resultMessageVO.messageSetting(false, "글찾기", "글찾기실패", "/free/freeList.wow", "목록으로");
            model.addAttribute("resultMessageVO", resultMessageVO);
            return "common/message";
        }
        return "free/freeEdit";
    }

    @PostMapping("/free/freeModify.wow")
    public String freeModify(Model model, FreeBoardVO freeBoard, @RequestParam(name = "boFiles", required = false) MultipartFile[] boFiles) throws BizNotEffectedException, BizNotFoundException, BizPasswordNotMatchedException, IOException {
        if (boFiles != null) {
            List<AttachVO> attaches = attachUtils.getAttachListByMultiparts(boFiles, "FREE", "free");
            freeBoard.setAttaches(attaches);
        }

        ResultMessageVO resultMessageVO = new ResultMessageVO();
        try {
            freeBoardService.modifyBoard(freeBoard);
            resultMessageVO.messageSetting(true, "수정", "수정성공했어요", "/free/freeList.wow", "목록으로");
        } catch (BizNotFoundException | BizNotEffectedException e) {
            resultMessageVO.messageSetting(true, "수정", "수정실패했어요", "/free/freeList.wow", "목록으로");
        } catch (BizPasswordNotMatchedException e) {
            resultMessageVO.messageSetting(true, "수정", "수정실패했어요", "/free/freeList.wow", "목록으로");
        }
        model.addAttribute("resultMessageVO", resultMessageVO);
        return "common/message";
    }

    @PostMapping("/free/freeDelete.wow")
    public String delete(Model model, FreeBoardVO freeBoard) {
        ResultMessageVO resultMessageVO = new ResultMessageVO();
        try {
            freeBoardService.removeBoard(freeBoard);
            resultMessageVO.messageSetting(true, "삭제", "삭제에 성공했어요", "/free/freeList.wow", "목록으로");
        } catch (BizNotEffectedException | BizNotFoundException e) {
            resultMessageVO.messageSetting(true, "삭제", "삭제에 실패했어요", "/free/freeList.wow", "목록으로");
        } catch (BizPasswordNotMatchedException e) {
            resultMessageVO.messageSetting(true, "삭제", "삭제에 실패했어요", "/free/freeList.wow", "목록으로");
        }
        model.addAttribute("resultMessageVO", resultMessageVO);
        return "common/message";
    }

    @GetMapping("/free/freeForm.wow")
    public String freeForm(Model model) {
        List<CodeVO> codeList = codeService.getCodeListByParent("BC00");
        model.addAttribute("codeList", codeList);
        return "free/freeForm";
    }

    @PostMapping("/free/freeRegist.wow")
    public String freeRegist(Model model, FreeBoardVO freeBoard, @RequestParam(required = false, name = "boFiles") MultipartFile[] boFiles) throws IOException {
        if (boFiles != null) { // 파일처리해줍시다.
            // 여기서 이미 파일들이 업로드가 됐죠?
            List<AttachVO> attaches = attachUtils.getAttachListByMultiparts(boFiles, "FREE", "free");
            freeBoard.setAttaches(attaches);
            // DB에 저장하면 좋겠는데...

        }
        ResultMessageVO resultMessageVO = new ResultMessageVO();
        try {
            freeBoardService.registBoard(freeBoard);
            resultMessageVO.messageSetting(true, "등록", "등록성공했어요", "/free/freeList.wow", "목록으로");
        } catch (BizNotEffectedException e) {
            resultMessageVO.messageSetting(true, "등록", "등록실패했어요", "/free/freeList.wow", "목록으로");
        }
        model.addAttribute("resultMessageVO", resultMessageVO);
        return "common/message";
    }
}
