package com.library.dao;

import java.sql.*;
import com.library.bean.Issue;
import com.library.util.DBUtil;

public class IssueDAO {

    public int generateIssueID() {
        try {
            Connection con = DBUtil.getDBConnection();
            Statement st = con.createStatement();
            ResultSet rs =
                st.executeQuery("SELECT NVL(MAX(ISSUE_ID),30000)+1 FROM ISSUE_TBL");
            if (rs.next()) return rs.getInt(1);
        } catch (Exception e) {}
        return 0;
    }

    public boolean recordIssue(Issue issue) {
        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement("INSERT INTO ISSUE_TBL VALUES (?,?,?,?,?,?)");

            ps.setInt(1, issue.getIssueID());
            ps.setString(2, issue.getBookID());
            ps.setString(3, issue.getStudentID());
            ps.setString(4, issue.getStudentName());
            ps.setDate(5,
                new java.sql.Date(issue.getIssueDate().getTime()));
            ps.setDate(6, null);

            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean closeIssue(int issueID) {
        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "UPDATE ISSUE_TBL SET RETURN_DATE=SYSDATE WHERE ISSUE_ID=?");
            ps.setInt(1, issueID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}

