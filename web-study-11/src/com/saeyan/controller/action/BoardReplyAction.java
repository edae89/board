package com.saeyan.controller.action;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardReplyAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String num =request.getParameter("num");
		System.out.println("넘버왔냐:"+num);
		//해당 글의 정보(grp,seq,lvl)를 조회하고
		//1.grp = 글의 grp을 그대로 
		//2.seq = 글의 grp으로 같은 grp 조회->그룹글 수
		//3.lvl = 글의 lv +1
		BoardDAO bDao = BoardDAO.getInstance();			//글
		BoardVO bVo = bDao.selectOneBoardByNum(num);
		int grp = bVo.getGrp();
		int seq = bVo.getSeq()+1;
		int lvl = bVo.getLvl()+1;
		
		//그룹의 기존 시퀀스 update
		bDao.updateBoardSeq(grp, seq);
		
		//답글 세팅
		BoardVO brVo = new BoardVO();
		brVo.setName(request.getParameter("name"));
		brVo.setPass(request.getParameter("pass"));
		brVo.setEmail(request.getParameter("email"));
		brVo.setTitle(request.getParameter("title"));
		brVo.setContent(request.getParameter("content"));
		brVo.setGrp(grp);
		brVo.setSeq(seq);
		brVo.setLvl(lvl);
		System.out.println(bVo.getGrp()+" "+seq+" "+lvl);
		bDao.insertBoardReply(brVo);
		new BoardListAction().execute(request, response);
	}
}