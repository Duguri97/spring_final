package com.study.free.service;

import com.study.attach.dao.IAttachDao;
import com.study.attach.vo.AttachVO;
import com.study.common.vo.PagingVO;
import com.study.common.vo.SearchVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.dao.IFreeBoardDao;
import com.study.free.vo.FreeBoardVO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FreeBoardServiceImpl implements IFreeBoardService {
    @Autowired
    IFreeBoardDao freeBoardDao;

    @Autowired
    IAttachDao attachDao;

    @Override
    public List<FreeBoardVO> getBoardList(PagingVO paging, SearchVO search, String searchCategory) {

        int totalRowCount = freeBoardDao.getTotalRowCount(paging, search, searchCategory);
        paging.setTotalRowCount(totalRowCount);
        paging.pageSetting();

        List<FreeBoardVO> freeBoardList = freeBoardDao.getBoardList(paging, search, searchCategory);
        return freeBoardList;
    }


    @Override
    public FreeBoardVO getBoard(int boNo) throws BizNotFoundException {

        FreeBoardVO freeBoard = freeBoardDao.getBoard(boNo);
        if (freeBoard == null) throw new BizNotFoundException();

//            resultMap을 통해 이미 freeBoard에는 attaches가 세팅되어있다.
//            List<AttachVO> attches = attachDao.getAttachListByParent("FREE", freeBoard.getBoNo());
//            freeBoard.setAttaches(attches);
        return freeBoard;
    }


    @Override
    public void increaseHit(int boNo) throws BizNotEffectedException {


        int count = freeBoardDao.increaseHit(boNo);
        if (count < 1) throw new BizNotEffectedException();
    }


    @Override
    public void modifyBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {

        FreeBoardVO daoBoardList = freeBoardDao.getBoard(freeBoard.getBoNo());
        String boPass = daoBoardList.getBoPass();
        String inputPass = freeBoard.getBoPass();

        if (!boPass.equals(inputPass)) {
            throw new BizPasswordNotMatchedException();
        }

        int count = freeBoardDao.updateBoard(freeBoard);
        if (count < 1) throw new BizNotEffectedException();

        if (daoBoardList == null) {
            throw new BizNotFoundException();
        }
        // 추가된 첨부파일 DB에 넣는건 regist랑 똑같이...
        List<AttachVO> attaches = freeBoard.getAttaches();
        if (attaches != null) {
            for (AttachVO attach : attaches) {
                // 지금은 위에 freeBoard가 boNo가 0이에요. ==> selectKey를 통해 nextVal세팅된 boNo가 됐습니다.
                attach.setAtchParentNo(freeBoard.getBoNo());
                attachDao.insertAttach(attach);
            }
        }
        // 휴지통버튼에 의해 삭제될 첨부파일들을 처리해줘야합니다.
        int[] delAtchNos = freeBoard.getDelAtchNos();
        if (delAtchNos != null && delAtchNos.length > 0) {
            attachDao.delAtchNos(delAtchNos);
        }
    }

    @Override
    public void removeBoard(FreeBoardVO freeBoard) throws BizNotFoundException, BizPasswordNotMatchedException, BizNotEffectedException {

        FreeBoardVO daoBoardList = freeBoardDao.getBoard(freeBoard.getBoNo());
        String boPass = daoBoardList.getBoPass();
        String inputPass = freeBoard.getBoPass();

        if (!boPass.equals(inputPass)) {
            throw new BizPasswordNotMatchedException();
        }

        int count = freeBoardDao.deleteBoard(freeBoard);

        if (count < 1) throw new BizNotEffectedException();

        if (daoBoardList == null) {
            throw new BizNotFoundException();
        }
    }


    @Override
    public void registBoard(FreeBoardVO freeBoard) throws BizNotEffectedException {

        int count = freeBoardDao.insertBoard(freeBoard);
        if (count < 1) throw new BizNotEffectedException();

        List<AttachVO> attaches = freeBoard.getAttaches();
        for (AttachVO attach : attaches) {
            // 지금은 위에 freeBoard가 boNo가 0이에요. ==> selectKey를 통해 nextVal세팅된 boNo가 됐습니다.
            attach.setAtchParentNo(freeBoard.getBoNo());
            attachDao.insertAttach(attach);
        }
    }
}


