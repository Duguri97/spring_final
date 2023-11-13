package com.study.reply.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.study.common.vo.PagingVO;
import com.study.exception.BizAccessFailException;
import com.study.reply.service.IReplyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.study.exception.BizNotFoundException;
import com.study.reply.vo.ReplyVO;

@RestController
public class ReplyController {
	@Autowired
	private IReplyService replyService;

	@RequestMapping(value = "/reply/replyList.wow")
	public Map<String,Object> replyList(PagingVO paging, String reCategory, int reParentNo){
		Map<String,Object> map= new HashMap<>();
		List<ReplyVO> replyList = replyService.getReplyListByParent(paging, reCategory, reParentNo);
		map.put("reCategory", reCategory);
		map.put("size" , replyList.size());
		map.put("data" , replyList);
		return map;
	}


	@RequestMapping(value = "/reply/replyRegist.wow")
	public Map<String,Object> replyRegist(ReplyVO reply){
		Map<String,Object> map= new HashMap<>();
		replyService.registReply(reply);
		map.put("msg","등록성공");
		return map;
	}

	@RequestMapping(value = "/reply/replyModify.wow")
	public Map<String,Object> replyModify(ReplyVO reply) throws BizAccessFailException,BizNotFoundException{
		Map<String,Object> map= new HashMap<>();
		replyService.modifyReply(reply);
		map.put("msg","수정성공");
		return map;
	}

	@RequestMapping(value = "/reply/replyDelete.wow")
	public Map<String,Object> replyDelete(ReplyVO reply) throws BizAccessFailException,BizNotFoundException{
		Map<String,Object> map= new HashMap<>();
		replyService.removeReply(reply);
		map.put("msg","삭제성공");
		return map;
	}



}