import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;

public class BookStores {
    private final List<Novel> novels;

    private static final int FIRST_CHAR = 0;
    private static final int ARRAY_SIZE = 3;
    private static final int STRING_ADJUSTMENT = 2;
    private static final int FIRST_ELEMENT_ARRAY = 0;
    private static final int SECOND_ELEMENT_ARRAY = 1;
    private static final int THIRD_ELEMENT_ARRAY = 2;
    private static final char SPECIAL_CHAR = '"';
    private static final String DELIMETER = ",";
    private static final String FILE_NAME = "dataLab5.csv";



    BookStores(){
        novels = new ArrayList<>();
    }


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
    private void populateNovels(final List<Novel> novels){

        // Create a File object representing the CSV file
        final File file = new File(FILE_NAME);

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

                // It will store the current row separating the columns
                String[] columns = new String[ARRAY_SIZE];

                // Check if the first char starts with
                // SPECIAL_CHAR if so then it means the column has a
                // coma inside the string
                if(line.charAt(FIRST_CHAR) == SPECIAL_CHAR){
                    // Fix the problem separating the column
                    // with the comma inside in a new string
                    final StringBuilder comaStr = new StringBuilder();

                    // The rest of the row will be stored here
                    final String restLine;

                    do{
                        final char currentChar = line.charAt(index);

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
                    columns[SECOND_ELEMENT_ARRAY] = restLine.split(DELIMETER)[FIRST_ELEMENT_ARRAY];
                    columns[THIRD_ELEMENT_ARRAY] = restLine.split(DELIMETER)[SECOND_ELEMENT_ARRAY];


                    final int yearPublished;

                    // It is parse since in the file it's been read from
                    // stores the year as a String and not as an int
                    yearPublished = Integer.parseInt(columns[THIRD_ELEMENT_ARRAY]);

                    // Adding them to the list
                    novels.add(new Novel(columns[FIRST_ELEMENT_ARRAY],
                                    columns[SECOND_ELEMENT_ARRAY],
                                    yearPublished));

                } else {
                    // If the line did not start with " then
                    // .split can be used to separate the columns
                    columns = line.split(DELIMETER);

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
}
