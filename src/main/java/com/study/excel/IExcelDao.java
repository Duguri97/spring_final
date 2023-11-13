package com.study.excel;

import com.study.common.vo.SearchVO;
import com.study.free.vo.FreeBoardVO;
import com.study.member.vo.MemberVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface IExcelDao {

    public List<FreeBoardVO> getFreeBoardBySearch(@Param("search") SearchVO search, @Param("searchCategory") String searchCategory);

    public List<MemberVO> getMemberBySearch(@Param("search") SearchVO search, @Param("searchJob") String searchJob, @Param("searchHobby") String searchHobby);

}
