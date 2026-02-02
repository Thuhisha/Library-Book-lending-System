package com.library.app;

import com.library.service.LibraryService;

public class LibraryMain {

    public static void main(String[] args) {

        LibraryService service = new LibraryService();

        System.out.println("Library Console ");

        String issueResult =
            service.issueBook("BK101", "ST5001", "Meera Nair");
        System.out.println("Issue Book Result: " + issueResult);

        String returnResult =
            service.returnBook(30001);
        System.out.println("Return Book Result: " + returnResult);
    }
}

