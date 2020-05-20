package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.saeyan.dto.BoardCommentVO;
import com.saeyan.dto.BoardVO;

import util.DBManager;

public class BoardCommentDAO {

	private static BoardCommentDAO instance = new BoardCommentDAO();
	
	public static BoardCommentDAO getInstance() {
		return instance;
	}

	public void insertBoardComment(BoardCommentVO bcVo) {
		String sql = "insert into boardcomment(num, id, cmemo, cnum) values(?, ?, ?, boardcomment_seq.nextval)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, bcVo.getNum());
			pstmt.setString(2, bcVo.getId());
			pstmt.setString(3, bcVo.getCmemo());
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		
	}
	
	
//	public List<BoardCommentVO> selectAllCommentsByNum(int num) {
//		String sql = "select * from boardComment where num=? order by num desc";
//		List<BoardCommentVO> list = new ArrayList<>();
//		Connection conn = null;
//		Statement stmt = null;
//		ResultSet rs = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = DBManager.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, num);
//			rs = pstmt.executeQuery();
//			while (rs.next()) {
//				BoardCommentVO bcVo = new BoardCommentVO();
//				bcVo.setNum(rs.getInt("num"));
//				bcVo.setId(rs.getString("id"));
//				bcVo.setCmemo(rs.getString("cmemo"));
//				bcVo.setCnum(rs.getInt("cnum"));
//				bcVo.setCdate(rs.getTimestamp("cdate"));
//				list.add(bcVo);
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} finally {
//			DBManager.close(conn, stmt, rs);
//		}
//		return list;
//	}
	
	
}
