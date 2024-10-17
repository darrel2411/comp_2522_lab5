import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represent a BookStore class.
 * @author Darrel Tapilaha
 * @author Manases Villalobos
 * @version 1.0
 */
public class BookStore {
    private final String name;
    private final List<Novel> novels;

    private static final int END_DECADE = 9;
    private static final int MAX_PERCENTAGE = 100;

    /**
     * Constructs a {@code BookStore} object with the specified name.
     * Initializes an empty list of novels and adds a few sample novels.
     *
     * @param name the name of the bookstore
     * @throws IllegalArgumentException if the provided name is blank or empty
     */
    public BookStore(final String name) {
        validateName(name);
        this.name = name;
        this.novels = new ArrayList<>();

        // Add sample novels to the collection
        novels.add(new Novel("All the King's Men", "Robert Penn Warren", 1946));
        novels.add(new Novel("Beloved", "Toni Morrison", 1987));
    }

    /*
     * Validates the bookstore's name.
     * Throws an IllegalArgumentException if the name is empty or blank.
     */
    private void validateName(final String name) {
        if (name.isEmpty() || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    /**
     * Returns the name of the bookstore.
     *
     * @return the name of the bookstore
     */
    public String getName() {
        return name;
    }

    /**
     * Prints all novel titles in the collection in uppercase.
     */
    public void printAllTitles() {
        for (final Novel n : novels) {
            final String title = n.getTitle().toUpperCase();
            System.out.println(title);
        }
    }

    /**
     * Prints the titles of novels that contain the specified word (case-insensitive) in their title.
     *
     * @param word the word to search for in the title
     */
    public void printBookTitle(final String word) {
        for (final Novel n : novels) {
            final String title = n.getTitle().toLowerCase();
            final String search = word.toLowerCase();
            if (title.contains(search)) {
                System.out.println(title);
            }
        }
    }

    /**
     * Prints all novel titles in the collection in alphabetical order.
     */
    public void printTitlesInAlphaOrder() {
        final List<String> titles = new ArrayList<>();
        for (final Novel n : novels) {
            titles.add(n.getTitle());
        }
        Collections.sort(titles);
        for (final String t : titles) {
            System.out.println(t);
        }
    }

    /**
     * Prints the novels that were published in a specific decade.
     *
     * @param decade the starting year of the decade (e.g., 2000 for the 2000s)
     */
    public void printGroupByDecade(final int decade) {
        final int startDecade = decade;
        final int endDecade = startDecade + END_DECADE;
        for (final Novel n : novels) {
            if (n.getYearPublished() >= startDecade && n.getYearPublished() <= endDecade) {
                System.out.println(n);
            }
        }
    }

    /**
     * Prints the novel with the longest title in the collection.
     */
    public void getLongest() {
        Novel longestTitle = novels.get(0);
        for (final Novel n : novels) {
            if (longestTitle.getTitle().length() < n.getTitle().length()) {
                longestTitle = n;
            }
        }
        System.out.println(longestTitle.toString());
    }

    /**
     * Checks if there is any novel written in the specified year.
     *
     * @param year the year to check for
     * @return {@code true} if a novel was written in the specified year, {@code false} otherwise
     */
    public boolean isThereABookWrittenIn(final int year) {
        for (final Novel n : novels) {
            if (n.getYearPublished() == year) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the number of novels that contain the specified word in their title.
     *
     * @param word the word to search for
     * @return the number of novels that contain the specified word
     */
    public int howManyBooksContain(final String word) {
        int counter = 0;
        final String search = word.toLowerCase();
        for (final Novel n : novels) {
            final String title = n.getTitle().toUpperCase();
            if (title.contains(search)) {
                counter++;
            }
        }
        return counter;
    }

    /**
     * Returns the percentage of novels written between the specified years (inclusive).
     *
     * @param first the start year (inclusive)
     * @param last  the end year (inclusive)
     * @return the percentage of novels written between the two years
     */
    public int whichPercentWrittenBetween(final int first, final int last) {
        int counter = 0;
        for (final Novel n : novels) {
            if (n.getYearPublished() >= first && n.getYearPublished() <= last) {
                counter++;
            }
        }
        return counter / novels.size() * MAX_PERCENTAGE;
    }

    /**
     * Returns the oldest novel in the collection based on the year of publication.
     *
     * @return the oldest novel
     */
    public Novel getOldestBook() {
        Novel oldestBook = novels.get(0);
        for (final Novel n : novels) {
            if (n.getYearPublished() < oldestBook.getYearPublished()) {
                oldestBook = n;
            }
        }
        return oldestBook;
    }

    /**
     * Returns a list of novels whose titles have a length greater than or equal to the specified length.
     *
     * @param titleLength the length of the title to match
     * @return a list of novels with titles of the specified length
     */
    public List<Novel> getBooksThisLength(final int titleLength) {
        final List<Novel> novelsWithSpecificLength = new ArrayList<>();
        for (final Novel n : novels) {
            if (n.getTitle().length() >= titleLength) {
                novelsWithSpecificLength.add(n);
            }
        }
        return novelsWithSpecificLength;
    }

    /**
     * The main method that demonstrates the functionality of the {@code BookStore} class.
     *
     * @param args command-line arguments
     */
    public static void main(final String[] args) {
        final BookStore bookstore;
        final Novel oldest;
        final List<Novel> fifteenCharTitles;
        bookstore = new BookStore("Classic Novels Collection");
        System.out.println("Bookstore: " + bookstore.getName());
        System.out.println("All Titles in UPPERCASE:");
        bookstore.printAllTitles();
        System.out.println("\nBook Titles Containing 'the':");
        bookstore.printBookTitle("the");
        System.out.println("\nAll Titles in Alphabetical Order:");
        bookstore.printTitlesInAlphaOrder();
        System.out.println("\nBooks from the 2000s:");
        bookstore.printGroupByDecade(2000);
        System.out.println("\nLongest Book Title:");
        bookstore.getLongest();
        System.out.println("\nIs there a book written in 1950?");
        System.out.println(bookstore.isThereABookWrittenIn(1950));
        System.out.println("\nHow many books contain 'heart'?");
        System.out.println(bookstore.howManyBooksContain("heart"));
        System.out.println("\nPercentage of books written between 1940 and 1950:");
        System.out.println(bookstore.whichPercentWrittenBetween(1940, 1950) + "%");
        System.out.println("\nOldest book:");
        oldest = bookstore.getOldestBook();
        System.out.println(oldest.getTitle() + " by " + oldest.getAuthorName() + ", " +
                oldest.getYearPublished());
        System.out.println("\nBooks with titles 15 characters long:");
        fifteenCharTitles = bookstore.getBooksThisLength(15);
        fifteenCharTitles.forEach(novel -> System.out.println(novel.getTitle()));


//        // part 2
//        final BookShop bs;
//        bs = new BookShop()

    }
}
