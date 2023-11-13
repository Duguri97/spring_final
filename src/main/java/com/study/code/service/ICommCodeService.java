package com.study.code.service;

import com.study.code.vo.CodeVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ICommCodeService {
	List<CodeVO> getCodeListByParent(String parentCode) ;
	
}
