package us.mattgreen.comics.model;

import us.mattgreen.comics.model.ComicBook;

import java.sql.*;
import java.util.*;

/**
 * This class is an expert on comic books and handles pulling
 * the comics from the database
 * 
 * @author  Textbook, with modifications by Jim Lombardo
 * @version 1.02
 */
public class ComicExpert {

    // the list of comic books
    private ArrayList<ComicBook> comicCollection = new ArrayList<>();

    public ComicExpert() {

        // run CreateComicDB to rerun the DB, commented out because it does not need
        // to be run more than once
        //CreateComicDB comicCatalog = new CreateComicDB();
        /*
        comicCollection.add(new ComicBook("Incredible Hulk #181",8000.00,"First Apperance of Wolverine"));
        comicCollection.add(new ComicBook("Peter Porker the Spectacular Spider-Ham #1",40.00,"Third app of Spider-Ham"));
        comicCollection.add(new ComicBook("Fantastic Four #1",45000.00,"First silver age Marvel Comic and 1st app of the F. F. "));
        comicCollection.add(new ComicBook("Invincible Iron Man #55",8000.00,"1st app. of Thanos"));
        comicCollection.add(new ComicBook("Uncanny X-Men #266",100.00,"1st app. of Gambit"));
        comicCollection.add(new ComicBook("Amazing Spider-Man #101",200.00,"1st app of Morbius the Living Vampire"));
        comicCollection.add(new ComicBook("Amazing Fantasy #15",100000.00,"1st app of Spider-Man"));
        */
    }

    /**
     * get the books from the database
     *
     * @return List of books
     */
    public List getBooks() {
        // the DB URL
        final String DB_URL = "jdbc:derby:ComicDB";
        Statement stmt = null;
        Connection conn = null;
        try {
            System.out.println("Connecting to database...");
            // get the connection to the database
            conn = DriverManager.getConnection(DB_URL);
            System.out.println("Creating statement...");
            // create the statement
            stmt = conn.createStatement();
            String sql;
            // the sql statement to execute
            sql = "SELECT Title, Description, Price FROM ComicBook";
            // the results from the query
            ResultSet rs = stmt.executeQuery(sql);

            // while the results object has results in it
            while (rs.next()) {
                //Retrieve by column name
                String id = rs.getString("Title");
                double price = rs.getDouble("Price");
                String descriptionString = rs.getString("Description");

                // add the comic book to the List
                comicCollection.add (new ComicBook(id,price,descriptionString));
            }
            /* STEP 6: Clean-up environment */
            rs.close();
            stmt.close();
            conn.close();
        } catch (SQLException se) {
            //Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            //Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            //finally block used to close resources
            try {
                if (stmt != null)
                    stmt.close();
            } catch (SQLException se2) {
            }// nothing we can do
            try {
                if (conn != null)
                    conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }//end finally try
        }//end try
        // return the list
return comicCollection;
    }
}
