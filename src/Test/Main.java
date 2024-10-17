import java.util.List;

public class Main {

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



        System.out.println("Welcome to " + bookstore.getName());
        // Division line
        System.out.println("====================================");
        System.out.println();


        System.out.println("All Titles in UPPERCASE:");
        bookstore.printAllTitles();

        // Division line
        System.out.println("====================================");
        System.out.println();

        System.out.println("\nBook Titles Containing 'the':");
        bookstore.printBookTitle("the");


        // Division line
        System.out.println("====================================");
        System.out.println();

        System.out.println("\nAll Titles in Alphabetical Order:");
        bookstore.printTitlesInAlphaOrder();

        // Division line
        System.out.println("====================================");
        System.out.println();

        System.out.println("\nBooks from the 2000s:");
        bookstore.printGroupByDecade(2000);

        // Division line
        System.out.println("====================================");
        System.out.println();

        System.out.println("\nLongest Book Title:");
        bookstore.getLongest();

        // Division line
        System.out.println("====================================");
        System.out.println();


        System.out.println("\nIs there a book written in 1950?");
        System.out.println(bookstore.isThereABookWrittenIn(1950));
        // Division line
        System.out.println("====================================");
        System.out.println();

        System.out.println("\nHow many books contain 'heart'?");
        System.out.println(bookstore.howManyBooksContain("heart"));

        // Division line
        System.out.println("====================================");
        System.out.println();


        System.out.println("\nPercentage of books written between 1940 and 1950:");
        System.out.println(bookstore.whichPercentWrittenBetween(1940, 1950) + "%");

        // Division line
        System.out.println("====================================");
        System.out.println();

        System.out.println("\nOldest book:");
        oldest = bookstore.getOldestBook();
        System.out.println(oldest.getTitle() + " by " + oldest.getAuthorName() + ", " +
                oldest.getYearPublished());

        // Division Line
        System.out.println("====================================");
        System.out.println();

        System.out.println("\nBooks with titles 15 characters long:");
        fifteenCharTitles = bookstore.getBooksThisLength(15);
        fifteenCharTitles.forEach(novel -> System.out.println(novel.getTitle()));
    }
}
