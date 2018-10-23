package us.mattgreen.comics.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

@WebServlet(name = "PlaceOrderController")
public class PlaceOrderController extends HttpServlet {

    // handle the Post requests
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    // handle the get requests
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // An enumeration of request parameters
        Enumeration parameters = request.getParameterNames();

        // Orderline is the string that will be a comma-separated list of comic book issue titles
        String orderLine = "";
        String parmName = "";

        // while parameters enumerator has more elements
        while (parameters.hasMoreElements()) {
            // assign the name of the parameter to parmName (must cast to String)
            parmName = (String) parameters.nextElement();
            // string array of parmValues from the request, passing the parmName
            String[] parmValues = request.getParameterValues(parmName);
            // if parmValues contains 1 string
            if (parmValues.length == 1) {
                // assign that string to parmVal
                String parmVal = parmValues[0];
                // if parmVal is an empty string, assign the string "Empty Cart"
                if (parmVal.length() == 0) {
                    orderLine = "Empty Cart";
                // otherwise, add the string to orderLine (append the comic book issue title)
                } else {
                    orderLine = parmVal;
                }
            // if parmValues contains more than one string
            } else {
                //for every string contained in parmValues
                for (int i = 0; i < parmValues.length; i++) {
                    // if the index is 0 (the first string)
                    //  set parmValues[0] to orderLine
                    if (i == 0){
                        orderLine += parmValues[i];
                    // if the index is > 0, append a comma
                    //  and set parmValues[i] to orderLine
                    } else {
                        orderLine += "," + parmValues[i];
                    }
                }
            }
        }
        //creating the cookie, passing the name of the parameter
        //  and orderLine, which is a comma separated string containing
        //  the comic book issue titles the user wants in the cart
        Cookie cookie = new Cookie(parmName,orderLine);
        // age of the cookie in seconds (60 seconds * 60 minutes, so 1 hour)
        cookie.setMaxAge(60 * 60);
        // add cookie to the response
        response.addCookie(cookie);

        // change the status of the response to SC_MOVED_TEMPORARILY
        response.setStatus(response.SC_MOVED_TEMPORARILY);

        //setting location to /cart (the CartController)
        response.setHeader("Location", "cart");

    }
}