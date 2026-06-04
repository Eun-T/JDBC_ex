package org.edu.member.dao;

import org.edu.member.common.JDBCUtil;
import org.edu.member.vo.Member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KetMemberDaoImpl implements MemberDao {

    // JDBCUtil을 통해 Connection 객체 가져오기
    private Connection conn = JDBCUtil.getConnection();


    // 회원 등록
    @Override
    public int create(Member member) throws SQLException {
        String sql = "insert into members values (default,?,?,?,?,'Y')";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getMemberId());
            pstmt.setString(2, member.getMemberPw());
            pstmt.setString(3, member.getMemberName());
            pstmt.setString(4, member.getMemberRole());

            int result = pstmt.executeUpdate();

            if (result > 0) conn.commit();

            return result;
        }

    }

    @Override
    public int update(String index,String name,String role) throws SQLException {
        String sql = "update members set name = ?, role = ? where no = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1,name);
            pstmt.setString(2,role);
            pstmt.setString(3, String.valueOf(index));

            int result = pstmt.executeUpdate();

            if(result > 0) conn.commit();

            return result;
        }
    }

    @Override
    public int delete(Member member) throws SQLException {
        String sql = "delete from members where name = ?";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getMemberName());

            int result = pstmt.executeUpdate();

            if (result > 0) conn.commit();

            return result;
        }
    }

    @Override
    public void getList() throws SQLException {
        String sql = "select * from members";

        try(PreparedStatement pstmt = conn.prepareStatement(sql)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()){
                String id = rs.getString("id");
                String name = rs.getString("name");
                String role = rs.getString("role");

                System.out.println(id + name + role);
            }
        }
    }

    @Override
    public void get(Member member) throws SQLException {
        String sql = "select * from members where name = ?";

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, member.getMemberName());
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                String id = rs.getString("id");
                String name = rs.getString("name");
                String role = rs.getString("role");

                System.out.println(id + name + role);
            }
        }
    }


}
