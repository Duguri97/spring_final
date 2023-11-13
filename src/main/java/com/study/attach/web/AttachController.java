package com.study.attach.web;

import com.study.attach.service.AttachServiceImpl;
import com.study.attach.vo.AttachVO;
import com.study.exception.BizNotFoundException;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

@Controller
public class AttachController {

    @Autowired
    private AttachServiceImpl attachService;

    @Value("#{util['file.upload.path']}")
    private String uploadPath;


    @ResponseBody
    @RequestMapping("/attach/download/{atchNo}")
    public String attachDownload(@PathVariable("atchNo") int atchNo, HttpServletResponse resp) throws IOException, BizNotFoundException {
        // DB에서 atchNo에 대한 AttachVO 조회하기...
        AttachVO attach = attachService.getAttach(atchNo);
        String originalName = new String(attach.getAtchOriginalName().getBytes("utf-8"), "iso-8859-1");
        // 혹시 안되면 iso-8859-1 추가("utf-8" 뒤에)
        String fileName = attach.getAtchFileName(); // 저장되어있는 파일이름. 랜덤값 // ==> 화면하단에 보여진다? resp에 뭔가 전달될거다. 인코딩 해줘야한다.
        // resp의 utf-8로는 안됩니다. resp에 파일로 들어갈 때(stream) 인코딩이 되어있어야한다.
        String filePath = attach.getAtchPath(); // 저장되어있는 폴더 경로
        String path = uploadPath + File.separatorChar + filePath;   // /home/pc36/Aupload/free 까지
        // "/" File.separator를 쓰는 이유는 운영체제마다 경로가 달라서...
        File file = new File(path, fileName);
        if (!file.isFile()) throw new BizNotFoundException("파일없음");
        resp.setHeader("Content-Type", "application/octet-stream;");
        resp.setHeader("Content-Disposition", "attachment;filename=\"" + originalName + "\";");
        resp.setHeader("Content-Transfer-Encoding", "binary;");
        resp.setContentLength((int) file.length()); // 진행율
        resp.setHeader("Pragma", "no-cache;");
        resp.setHeader("Expires", "-1;");
        // 저장된 파일을 응답객체의 스트림으로 내보내기,  resp의 outputStream에  해당파일을 복사
        FileUtils.copyFile(file, resp.getOutputStream());
        resp.getOutputStream().close();


        return "";
    }
}
