package com.study.free.service;

import com.study.common.vo.PagingVO;
import com.study.common.vo.SearchVO;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.exception.BizPasswordNotMatchedException;
import com.study.free.vo.FreeBoardVO;

import java.util.List;

public interface IFreeBoardService {
	public List<FreeBoardVO> getBoardList(PagingVO paging, SearchVO search, String searchCategory);
	public FreeBoardVO getBoard(int boNo) throws BizNotFoundException;
	
	public void increaseHit(int boNo) throws BizNotEffectedException;
	
	public void modifyBoard(FreeBoardVO freeBoard) 
			throws BizNotFoundException,BizPasswordNotMatchedException, BizNotEffectedException ;
	public void removeBoard(FreeBoardVO freeBoard)
			throws BizNotFoundException,BizPasswordNotMatchedException, BizNotEffectedException ;
	public void registBoard(FreeBoardVO freeBoard) throws BizNotEffectedException;

}
