package us.mattgreen.comics.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateComicDB {
    //public //CreateComicDB()
    {

        try
        {
            // Create a named constant for the URL.
            // NOTE: This value is specific for Java DB.
            final String DB_URL = "jdbc:derby:ComicDB;create=true";

            // Create a connection to the database.
            Connection conn =
                    DriverManager.getConnection(DB_URL);

            // If the DB already exists, drop the tables.
            dropTables(conn);

            // Build the Comic table.
            buildComicBOokTable(conn);


            // Close the connection.
            conn.close();
        } catch (Exception e)
        {
            System.out.println("Error Creating the Comic Table");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }

    }

    /**
     * The dropTables method drops any existing
     * in case the database already exists.
     */
    public static void dropTables(Connection conn)
    {
        System.out.println("Checking for existing tables.");

        try
        {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            try
            {
                // Drop the Customer table.
                stmt.execute("DROP TABLE Customer");
                System.out.println("Customer table dropped.");
            } catch (SQLException ex)
            {
                // No need to report an error.
                // The table simply did not exist.
            }

            try
            {
                // Drop the Comic table.
                stmt.execute("DROP TABLE ComicBook");
                System.out.println("ComicBOok table dropped.");
            } catch (SQLException ex)
            {
                // No need to report an error.
                // The table simply did not exist.
            }
        } catch (SQLException ex)
        {
            System.out.println("ERROR: " + ex.getMessage());
            ex.printStackTrace();
        }
    }

    /**
     * The buildComicTable method creates the
     * Comic table and adds some rows to it.
     */
    public static void buildComicBOokTable(Connection conn) {
        try {
            // Get a Statement object.
            Statement stmt = conn.createStatement();

            // Create the table.
            stmt.execute("CREATE TABLE ComicBook (" +
                    "Title VARCHAR(100) NOT NULL PRIMARY KEY, " +
                    "Description VARCHAR(100), " +
                    "Price DOUBLE  NOT NULL " +
                    ")");

            // Insert row #1.
            stmt.execute("INSERT INTO ComicBook VALUES ( " +
                    "'Werewolf by Night #32', " +
                    "'1st appearance of Moon Knight', " +
                    "2999.99 )");


            // Insert row #1.
            stmt.execute("INSERT INTO ComicBook VALUES ( " +
                    "'Incredible Hulk #181', " +
                    "'First Apperance of Wolverine', " +
                    "8000.00 )");


            // Insert row #1.
            stmt.execute("INSERT INTO ComicBook VALUES ( " +
                    "'Peter Porker the Spectacular Spider-Ham #1', " +
                    "'Third app of Spider-Ham', " +
                    "40.00 )");

            // Insert row #2.
            stmt.execute("INSERT INTO ComicBook VALUES ( " +
                    "'Invincible Iron Man #55', " +
                    "'1st app. of Thanos', " +
                    "8000.00 )");


            // Insert row #3.
            stmt.execute("INSERT INTO ComicBook VALUES ( " +
                    "'Fantastic Four #1', " +
                    "'First silver age Marvel Comic and 1st app of the F. F.', " +
                    "45000 )");


            // Insert row #4.
            stmt.execute("INSERT INTO ComicBook VALUES ( " +
                    "'Uncanny X-Men #266', " +
                    "'1st app. of Gambit', " +
                    "100.00 )");

            // Insert row #5.
            stmt.execute("INSERT INTO ComicBook VALUES ( " +
                    "'Amazing Spider-Man #101', " +
                    "'1st app of Morbius the Living Vampire', " +
                    "200.00 )");


            // Insert row #6.
            stmt.execute("INSERT INTO ComicBook VALUES ( " +
                    "'Amazing Fantasy #15', " +
                    "'1st app of Spider-Man', " +
                    "100000.00 )");


            System.out.println("Comic table created.");
        } catch (SQLException ex) {
            System.out.println("ERROR: " + ex.getMessage());
        }
    }}