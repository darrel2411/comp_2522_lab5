import javax.imageio.stream.ImageInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BookStore {
    private final String name;
    private final List<Novel> novels;

    private static final int END_DECADE = 9;
    private static final int MAX_PERCENTAGE = 100;

    public BookStore(final String name) {
        validateName(name);
        this.name = name;
        this.novels = new ArrayList<>();

        novels.add(new Novel("All the King's Men", "Robert Penn Warren", 1946));
        novels.add(new Novel("Beloved", "Toni Morrison", 1987));
    }

    private void validateName(final String name) {
        if(name.isEmpty() || name.isBlank()){
            throw new IllegalArgumentException("Name cannot be empty");
        }
    }

    public String getName() {
        return name;
    }

    public void printAllTitles(){
        for(final Novel n: novels){
            // place title toUpperCase in a local variable
            final String title;
            title = n.getTitle().toUpperCase();

            System.out.println(title);
        }
    }

    public void printBookTitle(final String word){
        for(final Novel n: novels){

            final String title;
            final String search;

            title = n.getTitle().toLowerCase();
            search = word.toLowerCase();

            if(title.contains(search)){
                System.out.println(title);
            }
        }
    }

    public void printTitlesInAlphaOrder(){
        final List<String> titles;
        titles = new ArrayList<>();
        for(final Novel n: novels){
            titles.add(n.getTitle());
        }
        Collections.sort(titles);

        //print after sorted titles
        for(final String t: titles){
            System.out.println(t);
        }

    }

    public void printGroupByDecade(final int decade){
        final int startDecade;
        final int endDecade;

        startDecade = decade;
        endDecade = startDecade + END_DECADE;

        for(final Novel n: novels){
            if(n.getYearPublished() >= startDecade &&
               n.getYearPublished() <= endDecade)
            {
                System.out.println(n);
            }
        }
    }

    public void getLongest(){
        // not final because it needs to be re-assigned
        // if longer title is found in the loop below
        Novel longestTitle;
        longestTitle = novels.get(0);

        for(final Novel n: novels){
            if(longestTitle.getTitle().length() < n.getTitle().length()){
                longestTitle = n;
            }
        }
        System.out.println(longestTitle.toString());
    }

    public boolean isThereABookWrittenIn(final int year){
        for(final Novel n: novels){
            if(n.getYearPublished() == year){
                return true;
            }
        }
        return false;
    }

    public int howManyBooksContain(final String word){
        // not final because it needs to be re-assigned
        // if contain word is found in the loop below
        int counter;
        final String search;

        counter = 0;
        search = word.toLowerCase();

        for(final Novel n: novels){
            final String title;
            title = n.getTitle().toUpperCase();
            if(title.contains(search)){
                counter++;
            }
        }
        return counter;
    }

    public int whichPercentWrittenBetween(final int first,
                                          final int last)
    {
        // not final because it needs to be re-assigned
        // if contain word is found in the loop below
        int counter;
        final int calculatePercent;

        counter = 0;

        for(final Novel n: novels){
            if(n.getYearPublished() >= first &&
               n.getYearPublished() <= last)
            {
                counter++;
            }
        }
        calculatePercent = counter / novels.size() * MAX_PERCENTAGE;
        return calculatePercent;
    }

    public Novel getOldestBook(){
        Novel oldestBook;
        oldestBook = novels.get(0);
        for(final Novel n: novels){
            if(n.getYearPublished() < oldestBook.getYearPublished()){
                oldestBook = n;
            }
        }
        return oldestBook;
    }

    public List<Novel> getBooksThisLength(final int titleLength){
        final List<Novel> novelsWithSpecificLength;
        novelsWithSpecificLength = new ArrayList<>();

        for(final Novel n: novels){
            if(n.getTitle().length() >= titleLength){
                novelsWithSpecificLength.add(n);
            }
        }
        return novelsWithSpecificLength;
    }

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
    }
}
