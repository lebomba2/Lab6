package us.mattgreen.comics.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * This is the main controller for the Beer Advice App.
 * 
 * @author    Textbook, with modifications by Jim Lombardo
 * @version   1.00
 */
public class ComicSelectionController extends HttpServlet {
   private static final String RESULT_PAGE = "first.jsp";

   // This is a SERVLET
    // only examples are doGet and doPOST
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        // Create an instance of ComicExpert
        us.mattgreen.comics.model.ComicExpert be = new us.mattgreen.comics.model.ComicExpert();
        // Retrieve List of books from the ComicExpert
        List result = be.getBooks();
        // Add the List of books to the request attribute so RESULT_PAGE can access it
        request.setAttribute("books", result);
        // wrap the resource located at RESULT_PAGE
        RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
        // Forward the request and response to the resource RESULT_PAGE
        view.forward(request, response);
    }

}
