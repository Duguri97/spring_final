package com.study.member.service;

import java.util.List;

import com.study.common.vo.PagingVO;
import com.study.common.vo.SearchVO;
import com.study.exception.BizDuplicateKeyException;
import com.study.exception.BizNotEffectedException;
import com.study.exception.BizNotFoundException;
import com.study.member.vo.MemberVO;
import org.springframework.stereotype.Service;

@Service
public interface IMemberService {

    public List<MemberVO> getMemberList(PagingVO paging, SearchVO search, String searchJob, String searchHobby);
    public MemberVO getMember(String memId) throws BizNotFoundException ;
    public void modifyMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException ;
    public void removeMember(MemberVO member) throws BizNotEffectedException, BizNotFoundException;
    public void registMember(MemberVO member) throws BizNotEffectedException,BizDuplicateKeyException;
}
