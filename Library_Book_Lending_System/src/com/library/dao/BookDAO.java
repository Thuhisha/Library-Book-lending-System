package com.library.dao;

import java.sql.*;
import com.library.bean.Book;
import com.library.util.DBUtil;

public class BookDAO{

    public Book findBook(String bookID) {

        Connection con = DBUtil.getDBConnection();
        if (con == null) return null;

        try {
            PreparedStatement ps =
                con.prepareStatement("SELECT * FROM BOOK_TBL WHERE BOOK_ID=?");
            ps.setString(1, bookID);

            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                Book b = new Book();
                b.setBookID(rs.getString(1));
                b.setTitle(rs.getString(2));
                b.setAuthor(rs.getString(3));
                b.setTotalCopies(rs.getInt(4));
                b.setAvailableCopies(rs.getInt(5));
                return b;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean updateAvailableCopies(String bookID, int count) {
        try {
            Connection con = DBUtil.getDBConnection();
            PreparedStatement ps =
                con.prepareStatement(
                    "UPDATE BOOK_TBL SET AVAILABLE_COPIES=? WHERE BOOK_ID=?");
            ps.setInt(1, count);
            ps.setString(2, bookID);
            return ps.executeUpdate() > 0;
        } catch (Exception e) {
            return false;
        }
    }
}



