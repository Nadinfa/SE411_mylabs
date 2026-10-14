package edu.psu.se411.lab09_inventory.model;

public class SearchBookByAuthor implements SearchCriteria<Item> {

    private String author;

    public SearchBookByAuthor(String author) {
        this.author = author;
    }

    @Override
    public boolean matches(Item item) {

        if (item instanceof Book) {
            Book book = (Book) item;
            return book.getAuthor().equalsIgnoreCase(author);
        }

        return false;
    }
}
