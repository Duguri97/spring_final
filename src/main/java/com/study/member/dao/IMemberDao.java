package com.study.member.dao;

import com.study.common.vo.PagingVO;
import com.study.common.vo.SearchVO;
import com.study.member.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface IMemberDao {

    public int getTotalRowCount(@Param("paging") PagingVO paging, @Param("search") SearchVO search, @Param("searchJob") String searchJob, @Param("searchHobby") String searchHobby);

    public List<MemberVO> getMemberList(@Param("paging") PagingVO paging, @Param("search") SearchVO search, @Param("searchJob") String searchJob, @Param("searchHobby") String searchHobby);

    public MemberVO getMember(String memId);
    public int updateMember(MemberVO member);
    public int deleteMember(MemberVO member);
    public int insertMember(MemberVO member);



}