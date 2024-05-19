public class Book {
    private String title, author, content;
    private int edition;

    /**
     * A constructor which initialises values related to class Book
     * 
     * @param t title of book
     * @param a author of book
     * @param c content within book
     * @param e edition of book
     * 
     */
    public Book(String t, String a, String c, int e) {
        title = t;
        author = a;
        content = c;
        edition = e;
    }

    /**
     * Function to return title of book
     * 
     * @return title of book
     */
    public String getTitle() {
        return title;
    }

    /**
     * Function to return author of book
     * 
     * @return author of book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Function to return content of book
     * 
     * @return content
     */
    public String getContent() {
        return content;
    }

    /**
     * Function to return edition of book
     * 
     * @return edition of book
     */
    public int getEdition() {
        return edition;
    }

    /**
     * Function to return number of pages in book
     * 
     * @return pages in book
     */
    public int getPages() {
        double pages = Math.ceil(content.length() / 700);
        return (int)pages;
    }

    @Override
    public String toString() {
       return "Title: " + title + "\nAuthor: " + author + "\nEdition: " + edition + "\n";
    }
}