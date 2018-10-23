<%@ page import="java.util.List" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="us.mattgreen.comics.model.ComicBook" %>
<%--
    Document   : index
    Created on : Sep 28, 2011, 8:04:21 AM
    Author     : jlombardo
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<HTML>
<HEAD>
    <TITLE>Buy More Comics</TITLE>
    <link rel="stylesheet" href="cart.css">
    <meta character="utf-8">
</HEAD>
<BODY>
<div ID="wrapper">
    <H1>Buy More Comics</H1>
    <nav><ul>
        <li><a href= "cart" >Cart</a></li>
    </ul></nav>
    <%--removed hero id. --%>
    <main>
        <h2>Order Form </H2>
        <%--This is the form where the checkboxes are located and the "add to cart" button is--%>
        <form action="order.do" method = "GET">
            <table style="width:100%">
                <!-- Scriptlet -->
                <%
                    // instantiation of a cookie
                    Cookie cookie = null;
                    // instantiation of an array of cookies
                    Cookie[] cookies = null;

                    // Get an array of Cookies associated with this domain (the application)
                    cookies = request.getCookies();


                    //This is the list of "books"
                    List recs = (List)request.getAttribute("books");
                    //  This is an iterator object from the list of books
                    Iterator it = recs.iterator();
                    // While the iterator has a next item
                    while(it.hasNext()) {
                        // boolean that tells whether or not the name of the comic book is contained within the cookie
                        boolean checkboxValue = false;

                        // ComicBook object from the iterator object
                        ComicBook b = (ComicBook)(it.next());
                        // Check the cookie for the comic book
                        if( cookies != null ) {
                            // iterate through the cookies
                            for (int i = 0; i < cookies.length; i++) {
                                cookie = cookies[i];
                                // for each value (comma separated)
                                for (String value: cookie.getValue().split(",")){
                                    if (value.equals(b.getTitleOfIssue())){
                                        checkboxValue = true;
                                    }
                                }
                            }
                        }
                        // print the ComicBook details in a table
                        out.print("<tr><td>" + b.getTitleOfIssue() + "</td><td>" +  b.getPriceOfComic() + "</td><TD><INPUT name='item' TYPE =CHECKBOX VALUE='" + b.getTitleOfIssue() + "'" + ((checkboxValue) ? "checked": "") +"> Add to cart</TD><tr>"
                                + "<tr><td><pre>&Tab;" + b.getDescription() + "</pre>&Tab;</td></tr>");
                    }
                %>
            </table>
            <input type="submit" value="add to cart">

        </form>

</main>
<footer>
    <div>
        8675309 North Bay, MI 49810<br>
        906-555-5555 <br><br>
    </div>
    Copyright &copy; 2018 Buy More Comics <br>
    <a href="mailto:clebombard@my.wctc.edu">clebombard@my.wctc.edu
    </a>
</footer>
</div>
</BODY>
</HTML>
