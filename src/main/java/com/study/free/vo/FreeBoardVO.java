package com.study.free.vo;

import com.study.attach.vo.AttachVO;
import com.study.excel.ExcelColumn;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.ArrayList;
import java.util.List;

public class FreeBoardVO {
    @ExcelColumn(headerName = "글번호")
    private int boNo;                       /* 글 번호 */
    @ExcelColumn(headerName = "글제목")
    private String boTitle;                 /* 글 제목 */
    @ExcelColumn(headerName = "카테고리")
    private String boCategory;              /* 글 분류 코드 */
    @ExcelColumn(headerName = "작성자")
    private String boWriter;                /* 작성자명 */
    private String boPass;                  /* 비밀번호 */
    private String boContent;               /* 글 내용 */
    private int boHit;                      /* 조회수 */
    private String boRegDate;               /* 등록 일자 */
    private String boModDate;               /* 수정 일자 */
    private String boDelYn;                 /* 삭제 여부 */

    private String boCategoryNm;

    private List<AttachVO> attaches;
    private int[] delAtchNos;



    public int getBoNo() {
        return boNo;
    }

    public void setBoNo(int boNo) {
        this.boNo = boNo;
    }

    public String getBoTitle() {
        return boTitle;
    }

    public void setBoTitle(String boTitle) {
        this.boTitle = boTitle;
    }

    public String getBoCategory() {
        return boCategory;
    }

    public void setBoCategory(String boCategory) {
        this.boCategory = boCategory;
    }

    public String getBoWriter() {
        return boWriter;
    }

    public void setBoWriter(String boWriter) {
        this.boWriter = boWriter;
    }

    public String getBoPass() {
        return boPass;
    }

    public void setBoPass(String boPass) {
        this.boPass = boPass;
    }

    public String getBoContent() {
        return boContent;
    }

    public void setBoContent(String boContent) {
        this.boContent = boContent;
    }

    public int getBoHit() {
        return boHit;
    }

    public void setBoHit(int boHit) {
        this.boHit = boHit;
    }

    public String getBoRegDate() {
        return boRegDate;
    }

    public void setBoRegDate(String boRegDate) {
        this.boRegDate = boRegDate;
    }

    public String getBoModDate() {
        return boModDate;
    }

    public void setBoModDate(String boModDate) {
        this.boModDate = boModDate;
    }

    public String getBoDelYn() {
        return boDelYn;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.MULTI_LINE_STYLE);
    }

    public String getBoCategoryNm() {
        return boCategoryNm;
    }

    public void setBoCategoryNm(String boCategoryNm) {
        this.boCategoryNm = boCategoryNm;
    }

    public void setBoDelYn(String boDelYn) {
        this.boDelYn = boDelYn;
    }

    public List<AttachVO> getAttaches() {
        return attaches;
    }

    public void setAttaches(List<AttachVO> attaches) {
        this.attaches = attaches;
    }

    public int[] getDelAtchNos() {
        return delAtchNos;
    }

    public void setDelAtchNos(int[] delAtchNos) {
        this.delAtchNos = delAtchNos;
    }

}

