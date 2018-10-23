package us.mattgreen.comics.controller;

import us.mattgreen.comics.model.ComicBook;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

// The cart controller
public class CartController extends HttpServlet {
    // The result page to forward the request and response to
    private static final String RESULT_PAGE = "cart.jsp";

    // The DoGet method handles http get requests
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Array of cookies
        Cookie[] cookies = null;

        // Get an array of Cookies associated with this domain
        cookies = request.getCookies();

        // Create an instance of ComicExpert
        us.mattgreen.comics.model.ComicExpert be = new us.mattgreen.comics.model.ComicExpert();

        // List of ComicBook objects to be used by cart.jsp
        List<ComicBook> result = new ArrayList<>();
        // for each object in the List returned by be.getBooks()
        // be.getBooks() returns a List which is a collection of Objects
        //  and cannot be more specific than Object
        for (Object o: be.getBooks()){
            // for each Cookie in the array Cookies
            for(Cookie cookie: cookies){
                // Cast the Object o to type of ComicBook
                ComicBook book = (ComicBook) o;
                // for each String cookieValue returned by cookie.getValue().split(",")
                //  cookie.getValue() returns the value of the cookie.
                //  in the PlaceOrderController, the cookie is built.
                //  The cookie's value is a comma-separated string containing
                //   the titles of the comic books in the cart
                //  The string returned by getValue() is then split on the commas by .split(",")
                //   into a string[]
                for (String cookieValue: cookie.getValue().split(",")){
                    // compare the string returned by book.getTitleOfIssue()
                    //  for equality with the string cookieValue
                    if (book.getTitleOfIssue().equals(cookieValue)){
                        // If the strings are equal, then add the book to the list to be used by cart.jsp
                        result.add(book);
                    }
                }
            }
        }
        // add the result List to the request as an attribute with the reference variable cartBooks
        // The result List is added to the request so that cart.jsp can access it
        request.setAttribute("cartBooks", result);

        // CartController is mapped to the location "/cart"
        // Here, view is a RequestDispatcher object that acts as a wrapper for the resource located at RESULT_PAGE
        //  which is assigned at the top of this file
        RequestDispatcher view = request.getRequestDispatcher(RESULT_PAGE);
        // Here is where the request and response are forwarded to the resource located at RESULT_PAGE,
        //  so while the browser is pointed to /cart (CartController), it's actually seeing /cart.jsp
        view.forward(request, response);
    }
}
