package com.saeyan.controller.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.BoardCommentDAO;
import com.saeyan.dao.BoardDAO;
import com.saeyan.dao.ReadCountDAO;
import com.saeyan.dto.BoardCommentVO;
import com.saeyan.dto.BoardVO;

public class BoardViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "/board/boardView.jsp";
		String num = request.getParameter("num");
		BoardDAO bDao = BoardDAO.getInstance();
		ReadCountDAO rcDao = ReadCountDAO.getInstance();
		
		//조회 횟수
		ArrayList <String> clickedIdList = null;
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userid");
		
		clickedIdList = rcDao.selectReadCount(num);
		
		int chkFlag = 0;
		for(int i = 0; i < clickedIdList.size(); i++) {
			if(id.equals(clickedIdList.get(i))) {	//id가 해당 글을 조회한 명단에 있으면
				chkFlag = 1;
				break;
			}
		}
		if(chkFlag==0) {
			rcDao.insertReadCount(id, num);		//readcount테이블에 해당글을 조회한 id 추가
			bDao.updateReadCount(num);			//board테이블에 조회+1
		}
		
		BoardVO bVo = bDao.selectOneBoardByNum(num);	//해당 글 세팅
		request.setAttribute("board", bVo);
		
		List<BoardCommentVO> clist = bDao.selectAllCommentsByNum(num);	//해당 글의 댓글 세팅
		request.setAttribute("commentList", clist);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
