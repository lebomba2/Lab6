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
        <li><a href= "./index.jsp" >Home</a></li>
    </ul></nav>
    <main>
        <h2>Order Form </H2>
        <%--This is the form where the checkboxes are located and the "place order" button is--%>
        <table style="width:100%">
            <!-- Scriptlet -->
            <%
                //This is the list of "books" in the cart
                List recs = (List)request.getAttribute("cartBooks");
                double total = 0;
                //  This is an iterator object from the list of books
                Iterator it = recs.iterator();
                // While the iterator has a next item
                while(it.hasNext()) {
                    // ComicBook object from the iterator object
                    ComicBook b = (ComicBook)(it.next());
                    total += b.getPriceOfComic();
                    // print the ComicBook details in a table
                    out.print("<tr><td>" + b.getTitleOfIssue() + "</td><td>" +  b.getPriceOfComic() + "</td><tr>");
                }
                out.print("<tr><td><hr/></td><td><hr/></td></tr>");
                out.print("<tr><td class='total'>Total:</td><td>" + total + "</td></tr>");
            %>
        </table>
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
