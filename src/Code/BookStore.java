import java.io.File;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.Map;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Collections;
import java.util.Scanner;


/**
 * Represent a BookStore class.
 * @author Darrel Tapilaha
 * @author Manases Villalobos
 * @version 1.0
 */
public class BookStore {
    private final String name;
    private final List<Novel> novels;
    private final Map<String, Novel> novelMap;
    private final Set<String> keys;

    private static final int END_DECADE = 9;
    private static final int MAX_PERCENTAGE = 100;
    private static final int FIRST_CHAR = 0;
    private static final int ARRAY_SIZE = 3;
    private static final int STRING_ADJUSTMENT = 2;
    private static final int FIRST_ELEMENT_ARRAY = 0;
    private static final int SECOND_ELEMENT_ARRAY = 1;
    private static final int THIRD_ELEMENT_ARRAY = 2;
    private static final char SPECIAL_CHAR = '"';
    private static final String SPECIAL_WORD = "the";
    private static final String DELIMITER = ",";
    private static final String FILE_NAME = "dataLab5.csv";
    private static final int NOTHING = 0;

    /**
     * Constructs a {@code BookStore} object with the specified name.
     * Initializes an empty list of novels and adds a few sample novels.
     *
     * @param name the name of the bookstore
     * @throws IllegalArgumentException if the provided name is blank or empty
     */
    public BookStore(final String name) {
        validateName(name);

        final List<String> keyList ;


        this.name = name;
        this.novels = new ArrayList<>();
        this.novelMap = new HashMap<>();

        populateNovels(this.novels);

        // Part 2
        createMap(this.novels);
        this.keys = novelMap.keySet();
        printTitlesAndClean(this.keys, SPECIAL_WORD);
        keyList = new ArrayList<>(keys);
        // Step 3: Sort the List
        Collections.sort(keyList);

        // Printing the new list
        System.out.println("Printing list sorted and without the word \"" +
                            SPECIAL_WORD + "\"");
        System.out.println();
        for (final String key : keyList) {
            System.out.println(this.novelMap.get(key));
            System.out.println();
        }

    }


    /*
     * It will iterate through the keys and
     * print them then check for the ones that contains
     * the specialWord and delete them.
     * @param Keys representing the keys
     * @param specialWords as a String
     */
    private void printTitlesAndClean(final Set<String> keys,
                                     final String specialWord)
    {
        final Iterator<String> iterator;
        iterator = this.keys.iterator();

        System.out.println("All novel titles:");

        while (iterator.hasNext())
        {
            final String key;
            key = iterator.next();
            System.out.println(key);

            if(key.contains(specialWord))
            {
                iterator.remove();
            }

        }

        System.out.println();
    }


    /*
     * It populates the hashMap putting the title of the novel
     * as a key and the novel as the value.
     * @param novels as a List of novels.
     */
    private void createMap(final List<Novel> novels) {
        validateNovelList(novels);
        for(final Novel novel : novels) {
            final String title;

            // Gets the title of the novel
            title = novel.getTitle();

            this.novelMap.put(title, novel);
        }
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

    /*
     * Throw new IllegalArgumentException if novel is empty or equals to null value.
     */
    private void validateNovelList(final List<Novel> novels) {
        if (novels == null || novels.isEmpty()) {
            throw new IllegalArgumentException("Novel list is empty!");
        }
    }

    /**
     * Returns the name of the bookstore.
     *
     * @return the name of the bookstore as a String
     */
    public String getName() {
        return name;
    }

    /**
     * Prints all novel titles in the collection in uppercase.
     */
    public void printAllTitles() {
        validateNovelList(this.novels);

        for (final Novel n : novels) {
            final String title;
            title = n.getTitle().toUpperCase();
            System.out.println(title);
        }
    }

    /**
     * Prints the titles of novels that contain the specified word (case-insensitive) in their title.
     *
     * @param word the word to search for in the title
     */
    public void printBookTitle(final String word) {
        validateNovelList(this.novels);

        for (final Novel n : novels) {
            final String title;
            final String search;

            title = n.getTitle().toLowerCase();
            search = word.toLowerCase();

            if (title.contains(search)) {
                System.out.println(title);
            }
        }
    }

    /**
     * Prints all novel titles in the collection in alphabetical order.
     */
    public void printTitlesInAlphaOrder() {
        final List<String> titles;

        titles = new ArrayList<>();

        validateNovelList(this.novels);
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
        final int startDecade;
        final int endDecade;

        startDecade = decade;
        endDecade = startDecade + END_DECADE;

        validateNovelList(this.novels);

        for (final Novel n : novels) {
            if (n.getYearPublished() >= startDecade &&
                n.getYearPublished() <= endDecade)
            {
                System.out.println(n);
            }
        }
    }

    /**
     * Prints the novel with the longest title in the collection.
     */
    public void getLongest() {
        // need to re-assign longest title if found the new longest title
        Novel longestTitle;
        longestTitle = null;

        // throw error if list of novels is null
        validateNovelList(this.novels);

        for (final Novel n : novels) {
            if(longestTitle == null){
                longestTitle = n;
            } else {
                final int longestTitleLength;
                final int currentTitleLength;

                longestTitleLength = longestTitle.getTitle().length();
                currentTitleLength = n.getTitle().length();

                if (longestTitleLength < currentTitleLength) {
                    longestTitle = n;
                }
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
        validateNovelList(this.novels);
        for (final Novel n : novels) {
            if(n != null && n.getYearPublished() == year) {
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
        // need to re-assign the counter
        int counter;
        final String search;

        counter = NOTHING;
        search = word.toLowerCase();

        validateNovelList(this.novels);
        for (final Novel n : novels) {
            final String title = n.getTitle().toLowerCase();
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
    public double whichPercentWrittenBetween(final int first, final int last) {
        // need to re-assign the counter
        int counter;
        final double output;

        counter = NOTHING;

        validateNovelList(this.novels);
        for (final Novel n : novels) {
            final int currentBookYearPublished;
            currentBookYearPublished = n.getYearPublished();

            if (currentBookYearPublished >= first &&
                currentBookYearPublished <= last)
            {
                counter++;
            }
        }

        output = (double) counter / novels.size() * MAX_PERCENTAGE;

        return output;
    }

    /**
     * Returns the oldest novel in the collection based on the year of publication.
     *
     * @return the oldest novel
     */
    public Novel getOldestBook() {
        Novel oldestBook;

        oldestBook = null;

        validateNovelList(this.novels);
        for (final Novel n : novels) {
            final int thisYearPublished;
            final int thatYearPublished;

            thisYearPublished = n.getYearPublished();


            if(oldestBook == null){
                oldestBook = n;
                continue;
            }

            thatYearPublished = oldestBook.getYearPublished();

            if (thisYearPublished < thatYearPublished) {
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
        final List<Novel> novelsWithSpecificLength;

        novelsWithSpecificLength = new ArrayList<>();

        validateNovelList(this.novels);
        for (final Novel n : novels) {
            final int thisTitleLength;

            thisTitleLength = n.getTitle().length();

            if (thisTitleLength >= titleLength) {
                novelsWithSpecificLength.add(n);
            }
        }
        return novelsWithSpecificLength;
    }

//    /**
//     * It will print all the novels that are in the list.
//     */
//    public void printNovels(){
//        // need to re-assign the counter
//        int counter;
//        counter = NOTHING;
//        for(final Novel n: novels){
//            counter++;
//            System.out.println("Novel " + counter);
//            System.out.println("Title: " + n.getTitle());
//            System.out.println("Author " + n.getAuthorName());
//            System.out.println("Year published" + n.getYearPublished());
//            System.out.println();
//        }
//    }


    /*
     * It will populate the List of novels.
     * It will read a CSV file which contains all
     * the novels info to create the novel object.
     * However, the CSV separates the columns using a
     * comma as a delimiter, but in the file some of the titles
     * have comma inside the string, therefore an extra column
     * might be created by mistake, corrupting list and throw
     * Illegal exception.
     *
     * Those title that have a comma in the string, CSV wrap it
     * in SPECIAL_CHAR here is the follow step by step how is being fix:
     *
     * Step 1:
     * Store the current line in a String variable.
     *
     * Step 2:
     * Create a string array where each column will be store
     * separately.
     *
     * Step 3:
     * Check if the first char of the line is equals to
     * the SPECIAL_CHAR.
     * If that is true it means the String contains a comma
     * inside the string.
     * e.g of the special case
     * "Are You There God? It's Me, Margaret.",Judy Blume,1970
     *
     * Are You There God? It's Me | Margaret. | Judy Blume | 1970
     *
     * a fourth column would be created
     *
     * Step 4:
     * create a new String builder
     * do a while loop where it will loop through
     * the string until it finds the closing SPECIAL_CHAR
     * and append the char it finds in the meantime to the
     * string builder.
     *
     * e.g final content in the string builder
     * comaStr = "Are You There God? It's Me, Margaret.";
     *
     * that string builder variable will be store in the first
     * index in the columns array.
     *
     * colums[0] = comaStr;
     *
     * Step 5:
     * After it is done getting the string with a comma
     * it will create a substring with the rest of the line string.
     * it has an adjustment in the index, from where it was left off
     * move to the next to char, one char for the empty space and the
     * other to start after the comma separating the columns.
     *
     * rest of the line would be
     * e.g
     * "Judy Blume,1970"
     *
     * Step 6:
     * Separate the remaining columns using the .split() method and
     * store them in the columns array.
     *
     * After that columns would be
     * columns = {"Are You There God? It's Me, Margaret.",
     *            "Judy Blume",
     *            "1970"};
     *
     * Step 7:
     * the third parameter to create a novel object
     * is an integer representing the year.
     * Therefore, we will create a new int variable
     * where it will store the year obtained from the last element in
     * the columns array, but since it is a string we'll have to parse
     * it as an integer.
     *
     * e.g.
     * convert "1970" which is a string
     * to
     * 1970 an integer now
     *
     *
     * step 8:
     * Use the three elements from the columns array to create the novel object.
     * e.g.
     * novels.add(new Novel(columns[0], columns[1], yearPublished));
     *
     * @param novels
     */
    private void populateNovels(final List<Novel> novels) {

        // Create a File object representing the CSV file
        final File file;
        file = new File(FILE_NAME);

        try {

            // Create a scanner to read the file
            final Scanner scanner;
            scanner = new Scanner(file);

            // Iterate through each line in the file
            while(scanner.hasNextLine()){
                // To store the content of the current line
                final String line;
                line = scanner.nextLine();

                // for the indexes in the line
                int index = FIRST_CHAR;



                // Check if the first char starts with
                // SPECIAL_CHAR if so then it means the column has a
                // coma inside the string
                if(line.charAt(FIRST_CHAR) == SPECIAL_CHAR){
                    // It will store the current row separating the columns
                    final String[] columns;
                    columns = new String[ARRAY_SIZE];

                    // Fix the problem separating the column
                    // with the comma inside in a new string
                    final StringBuilder comaStr;

                    comaStr = new StringBuilder();

                    // The rest of the row will be stored here
                    final String restLine;

                    // It will start getting the string with the comma inside.
                    do{
                        final char currentChar;

                        currentChar = line.charAt(index);

                        // If the line at the current index starts with SPECIAL CHAR
                        // then don't add that character to that word.
                        if(currentChar != SPECIAL_CHAR){
                            final char charToAdd;

                            charToAdd = line.charAt(index);

                            comaStr.append(charToAdd);
                        }
                        // move to the next word
                        index++;

                        // it will do it while it does not find the end of the column
                        // which is signalized by the closing of the SPECIAL_CHAR.
                    } while (line.charAt(index) != SPECIAL_CHAR);


                    final int newIndex;
                    newIndex = index + STRING_ADJUSTMENT;

                    // Store the rest of the column in this word
                    // is moving two to avoid staring the new string from the closing
                    // SPECIAL_CHAR or empty char.
                    restLine = line.substring(newIndex);

                    // Store each column in the array of column
                    columns[FIRST_ELEMENT_ARRAY] = comaStr.toString();
                    columns[SECOND_ELEMENT_ARRAY] = restLine.split(DELIMITER)[FIRST_ELEMENT_ARRAY];
                    columns[THIRD_ELEMENT_ARRAY] = restLine.split(DELIMITER)[SECOND_ELEMENT_ARRAY];


                    final int yearPublished;

                    // It is parse since in the file it's been read from
                    // stores the year as a String and not as an int
                    yearPublished = Integer.parseInt(columns[THIRD_ELEMENT_ARRAY]);

                    // Adding them to the list
                    novels.add(new Novel(columns[FIRST_ELEMENT_ARRAY],
                                         columns[SECOND_ELEMENT_ARRAY],
                                         yearPublished));

                } else {

                    // It will store the current row separating the columns
                    final String[] columns;
                    // If the line did not start with " then
                    // .split can be used to separate the columns
                    columns = line.split(DELIMITER);

                    final int yearPublished;

                    yearPublished = Integer.parseInt(columns[THIRD_ELEMENT_ARRAY]);

                    // Adding to the list
                    novels.add(new Novel(columns[FIRST_ELEMENT_ARRAY],
                                         columns[SECOND_ELEMENT_ARRAY],
                                         yearPublished));
                }

            }

        } catch (final FileNotFoundException e){
            System.out.println("File not found: " + FILE_NAME);
        }
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
