import java.io.*;
import java.util.*;

public class Press {
    private Map<String, List<Book>> shelf;
    private int shelfSize;
    private Map<String, Integer> edition;
    private String pathToBooKDir;

    /**
     * Constructor to initialise variables related to printing press
     * @param pathToBooKDir File path to the directory of books
     * @param shelfSize Size of shelf
     */
    public Press(String pathToBooKDir, int shelfSize) {
        shelf = new HashMap<>();
        edition = new HashMap<>();
        this.shelfSize = shelfSize;
        this.pathToBooKDir = pathToBooKDir;

        try {
            File dirPath = new File(pathToBooKDir);
            File[] fileList = dirPath.listFiles();
            for (File file : fileList) {
                String bookID = file.getName();
                shelf.put(bookID, new LinkedList<>());
                edition.put(bookID, 0);
            }
        } catch (Exception e) {
        }
    }

    /**
     * Function to trigger printing of books
     * @param bookID name of book file in directory
     * @param edition edition of book specified
     * @return book printed
     * @throws IOException
     */
    protected Book print(String bookID, int edition) throws IOException {
        String path = pathToBooKDir + File.separator + bookID;
        BufferedReader br = new BufferedReader(new FileReader(path));       
        String title = "", author = "", content = "";
        String line = br.readLine();
        if (!this.shelf.containsKey(bookID))
            throw new IllegalArgumentException();

        while (line != null) {
            if (line.startsWith("Title:"))
                title = line.substring(7);
            else if (line.startsWith("Author:"))
                author = line.substring(8);
            else if (line.startsWith("*** START OF")) {
                line = br.readLine();
                while (line != null) {
                    content += line;
                    line = br.readLine();
                }
                break;
            }
            line = br.readLine();
        }

        Book book = new Book(title, author, content, edition);
        if (title.isEmpty() || author.isEmpty() || content.isEmpty())
            throw new java.io.IOException();
        else {
            // shelf.get(bookID).add(book);
            return book;
        }
    }

    /**
     * Function to get catalogue of books 
     * @return catalogue of books
     */
    public List<String> getCatalogue() {
        return new ArrayList<>(shelf.keySet());
    }

    /**
     * Function to request a specific book from the shelf and trigger
     * printing of books if there is insufficient stock
     * @param bookID name of book file in directory
     * @param amount number of books requested
     * @return list of books requested
     */
    public List<Book> request(String bookID, int amount) {
        List<Book> books = new ArrayList<>();
        if (!this.shelf.containsKey(bookID)) {
            throw new IllegalArgumentException();
        } 

        int toPrint = amount;
        List<Book> bookShelf = shelf.get(bookID);

        while(toPrint > 0 && bookShelf.isEmpty() == false) {
            books.add(bookShelf.remove(0));
            toPrint -= 1;
        }

        if(toPrint > 0) {
            int newEdition = edition.get(bookID) + 1;
            try {
                int reps = toPrint - bookShelf.size();
                for(int i = 0; i < reps; i++) {
                    Book newBook = print(bookID, newEdition);
                    books.add(newBook);
                }
                for(int i = 0; i < shelfSize; i++) {
                    Book newBook = print(bookID, newEdition);
                    shelf.get(bookID).add(newBook);
                }
                edition.put(bookID, newEdition);
            }
            catch (IOException ioe) {

            }
        }
        return books;
    }
}
