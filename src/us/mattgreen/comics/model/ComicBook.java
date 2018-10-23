package us.mattgreen.comics.model;

public class ComicBook {
    private String titleOfIssue;
    private double priceOfComic;
    private String description;

    public ComicBook(String titleOfIssue, double priceOfComic, String description) {
        this.titleOfIssue = titleOfIssue;
        this.priceOfComic = priceOfComic;
        this.description = description;
    }

    public String getTitleOfIssue() {
        return titleOfIssue;
    }

    public double getPriceOfComic() {
        return priceOfComic;
    }

    public String getDescription() {
        return description;
    }
}
