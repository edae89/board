package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.saeyan.dao.BoardCommentDAO;
import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardCommentVO;
import com.saeyan.dto.BoardVO;

public class BoardCommentAction implements Action {
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardCommentVO bcVo = new BoardCommentVO();
		bcVo.setNum(Integer.parseInt(request.getParameter("commentnum")));
		bcVo.setId(request.getParameter("cid"));
		bcVo.setCmemo(request.getParameter("commentmemo"));
		BoardCommentDAO bcDao = BoardCommentDAO.getInstance();
		bcDao.insertBoardComment(bcVo);
		
//		List<BoardCommentVO> clist = bcDao.selectAllCommentsByNum(Integer.parseInt(request.getParameter("commentnum")));
//		request.setAttribute("commentList", clist);
		String url = "BoardServlet?command=board_view&num="+bcVo.getNum();
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
		
	}
}
