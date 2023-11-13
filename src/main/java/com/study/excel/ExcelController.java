package com.study.excel;

import com.study.common.vo.SearchVO;
import com.study.free.vo.FreeBoardVO;
import com.study.member.vo.MemberVO;
import org.apache.ibatis.javassist.Loader;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
public class ExcelController {

    // 파일이미지 미리보기도 까먹음
    @Autowired
    IExcelDao excelDao;

    @ResponseBody
    @RequestMapping("/excel/freeList.wow")
    public void excelFreeList(HttpServletResponse response, SearchVO searchVO, String searchCategory) throws IOException, IllegalAccessException {
        List<FreeBoardVO> freeBoardList = excelDao.getFreeBoardBySearch(searchVO, searchCategory);
        SimpleExcelUtil simpleExcelUtil = new SimpleExcelUtil("freeSheet1", FreeBoardVO.class, freeBoardList);
        simpleExcelUtil.writeOutPutStream(response, "freeBoard");
    }


    @ResponseBody
    @RequestMapping("/excel/memberList.wow")
    public void memberListExcel(HttpServletResponse response, SearchVO searchVO, String searchJob, String searchHobby) throws IllegalAccessException, IOException {
        List<MemberVO> memberList = excelDao.getMemberBySearch(searchVO, searchJob, searchHobby);
        SimpleExcelUtil simpleExcelUtil = new SimpleExcelUtil("memberSheet1", MemberVO.class, memberList);
        simpleExcelUtil.writeOutPutStream(response, "member");
    }
}
