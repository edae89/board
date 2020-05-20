package com.saeyan.controller.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.saeyan.dao.BoardDAO;
import com.saeyan.dto.BoardVO;

public class BoardListAction implements Action{
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("userid");
		String url = "/board/boardList.jsp";
		BoardDAO bDao = BoardDAO.getInstance();
		List<BoardVO> boardList = bDao.selectAllBoards();
		if(boardList != null) {
			for(int j = 0; j <boardList.size(); j++) {
				String indent = "";
				int lvl = boardList.get(j).getLvl();
				System.out.println("lvl:"+lvl);
				if(lvl > 0) {
					String remark = "└";
					for(int i = 0; i < lvl; i++) {
						indent = indent+remark;
					}
					boardList.get(j).setIndent(indent);
//					request.setAttribute("indent", indent);
				}
			}
			request.setAttribute("boardList", boardList);
		}
		RequestDispatcher dispatcher = request.getRequestDispatcher(url);
		dispatcher.forward(request, response);
	}
}
