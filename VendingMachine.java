import java.util.*;

public class VendingMachine {
    private List<Book> shelf;
    private double locationFactor;
    private int cassette, safe;
    private String password;

    /**
     * Constructor that initialises variables related to class VendingMachine
     * @param locationFactor the factor which affects the pricing of books
     * @param password password to access vending machine restock and safe functions
     */
    public VendingMachine(double locationFactor, String password) {
       this.locationFactor = locationFactor;
       this.password = password;
       cassette = 0;
       safe = 0;
       shelf = new ArrayList<Book>();
    }

    /**
     * Function to get cassette of vending machine
     * @return cassette 
     */
    public int getCassette() {
        return cassette;
    }

    /**
     * Function to insert coins into vending machine
     * @param coin denomination of coin inserted
     */
    public void insertCoin (int coin) {
        if(coin == 1 || coin == 2 || coin == 5 || coin == 10 || coin == 20 || coin == 50 || coin == 100 || coin == 200)
            cassette += coin;
        else
            throw new IllegalArgumentException();
    }

    /**
     * Function to return coins from vending machine and empty cassette
     * @return coins stored in the cassette
     */
    public int returnCoins() {
        int orgCassette = cassette;
        cassette = 0;
        return orgCassette;
    }

    /**
     * Function to restock all books to shelf
     * @param books list of books to restock
     * @param password password to allow/deny access to restock function
     */
    public void restock(List<Book> books, String password) {
        if(this.password.equals(password)) 
            shelf.addAll(books);
        else  
            throw new InvalidPasswordException("Invalid Password.");
    }

    /**
     * Function to empty safe in vending machine
     * @param password password to allow/deny access to safe
     * @return all money stored in the safe
     */
    public int emptySafe(String password) {
        int orgSafe;
        if(this.password.equals(password)) {
            orgSafe = safe;
            safe = 0;
            return orgSafe;
        }
        else {
            throw new InvalidPasswordException("Invalid Password.");
        }
    }

    /**
     * Function to get a catalogue of books stored on the shelf
     * @return catalogue describing books on shelf
     */
    public List<String> getCatalogue() {
        List<String> catalogue = new ArrayList<String>();
        for(Book book : shelf) {
            catalogue.add(book.toString());
        }
        return catalogue;
    }

    /**
     * Function to get the price of a book on the shelf using
     * a specific formula involving locationFactor and no. of pages
     * @param index location of book in the shelf
     * @return price of the book
     */
    public int getPrice(int index) {
        if(index <= shelf.size()) {
            Book curr = shelf.get(index);
            int page = curr.getPages();
            double price = Math.ceil(page * locationFactor);
            return (int)price;
        }
        else
            throw new IndexOutOfBoundsException(index);
    }

    /**
     * Function to make a purchase of a book on the shelf.
     * @param index location of book in shelf
     * @return the book that is being purchased
     */
    public Book buyBook(int index) {
        if(index <= shelf.size()) {
            Book curr = shelf.get(index);
            int cost = getPrice(index);
            if (cost <= cassette) {
                cassette -= cost;
                safe += cost;
                shelf.remove(index);
                return curr;
            }
            else {
                throw new CassetteException("Not enough credits.");
            }
        }
        else 
            throw new IndexOutOfBoundsException(index);
    }
}