package com.library.service;

import java.util.Date;
import com.library.bean.*;
import com.library.dao.*;

public class LibraryService {

    BookDAO bookDAO = new BookDAO();
    IssueDAO issueDAO = new IssueDAO();

    public String issueBook(String bookID, String studentID, String studentName) {

        Book book = bookDAO.findBook(bookID);
        if (book == null) return "BOOK NOT FOUND";
        if (book.getAvailableCopies() == 0) return "BOOK NOT AVAILABLE";

        Issue issue = new Issue();
        issue.setIssueID(issueDAO.generateIssueID());
        issue.setBookID(bookID);
        issue.setStudentID(studentID);
        issue.setStudentName(studentName);
        issue.setIssueDate(new Date());

        issueDAO.recordIssue(issue);
        bookDAO.updateAvailableCopies(bookID,
                book.getAvailableCopies() - 1);

        return "SUCCESS";
    }

    public String returnBook(int issueID) {
        return issueDAO.closeIssue(issueID)
                ? "SUCCESS"
                : "INVALID ISSUE";
    }
}
