package org.edu.member.dao;

import org.edu.member.vo.Member;

import java.sql.SQLException;

// DAO(Data Access Object) : 데이터 접근 객체
// DB와 연결되어 SQL을 수행하고 결과를 반환 받는 역할
public interface MemberDao {

    int create(Member member) throws SQLException;

    int update(String index,String name,String role) throws SQLException;

    int delete(Member member) throws SQLException;

    void getList() throws SQLException;

    void get(Member member) throws SQLException ;
}
