package com.saeyan.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.saeyan.dto.BoardVO;
import com.saeyan.dto.ReadCountVO;

import util.DBManager;

public class ReadCountDAO {
	
	private ReadCountDAO() {
		
	}

	private static ReadCountDAO instance = new ReadCountDAO();
	
	public static ReadCountDAO getInstance() {
		return instance;
	}
	
	public void insertReadCount(String clickedid, String num) {
		String sql = "insert into readcount values(?, ?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, clickedid);
			pstmt.setString(2, num);
			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
	}
	
	public ArrayList<String> selectReadCount(String num) {
		String sql = "select * from readcount where num=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
//		ReadCountVO rcVo = null;
		int readCnt = 0;
		ArrayList <String> readIdList = new ArrayList<>();
		try {
			conn = DBManager.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, num);
			rs = pstmt.executeQuery();
			while (rs.next()) {
//				rcVo = new ReadCountVO();
//				rcVo.setNum(rs.getInt("num"));
//				rcVo.setClickedid(rs.getString("clickedid"));
//				readCnt++;
				readIdList.add(rs.getString("clickedid"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBManager.close(conn, pstmt);
		}
		return readIdList;
	}
	
}
