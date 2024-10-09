import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class TestCsv {


    public static void main(final String[] args) {
        final List<Novel> novels = new ArrayList<>();

        // The name of the file to read
        final String fileName = "dataLab5.csv";

        // Create a File object representing the CSV file
        final File file = new File(fileName);

        try {
            // It will count the lines in the file
            int counter = 0;

            // Create a scanner to read the file
            final Scanner scanner;
            scanner = new Scanner(file);

            // Iterate through each line in the file
            while(scanner.hasNextLine()){
                // To store the content of the current line
                final String line;
                line = scanner.nextLine();

                // to positioned at the first character
                final int firstChar = 0;

                // for the indexes in the line
                int index = firstChar;

                // It will store the current row separating the columns
                String[] columns = new String[3];

                // Check if the first char starts with
                // "" if so then it means the column has a
                // coma inside the string
                if(line.charAt(firstChar) == '"'){
                    // Fix the problem separating the column
                    // with the comma inside in a new string
                    final StringBuilder comaStr = new StringBuilder();

                    // The rest of the row will be stored here
                    final String restLine;

                    do{
                        // If the line at the current index starts with "
                        // then don't add that character to that word.
                        if(line.charAt(index) != '"'){
                            comaStr.append(line.charAt(index));
                        }
                        // move to the next word
                        index++;

                        // it will do it while it does not find the end of the column
                        // which is signalized by the closing ".
                    } while (line.charAt(index) != '"');


                    // Store the rest of the column in this word
                    // is moving two to avoid staring the new string from the closing "
                    // or the space ' '.
                    restLine = line.substring(index + 2);

                    // Store each column in the array of column
                    columns[0] = comaStr.toString();
                    columns[1] = restLine.split(",")[0];
                    columns[2] = restLine.split(",")[1];

                    final String title;
                    final String author;
                    final int yearPublished;

                    title = columns[0];
                    author = columns[1];
                    yearPublished = Integer.parseInt(columns[2]);

                    novels.add(new Novel(title, author, yearPublished));

                } else {
                    // If the line did not start with " then
                    // .split can be used to separate the columns
                    columns = line.split(",");

                    final String title;
                    final String author;
                    final int yearPublished;

                    title = columns[0];
                    author = columns[1];
                    yearPublished = Integer.parseInt(columns[2]);

                    novels.add(new Novel(title, author, yearPublished));
                }

                // Print each column value
//                for (String column : columns) {
//                    System.out.print(column + " | ");
//                }
                //Move to the next line after printing all columns
//                System.out.println();
                // an extra space
//                System.out.println();

                // Increase the counter
                // once the line is done
                counter++;
            }

            int novelCount = 1;
            for (Novel novel : novels) {
                System.out.println("Novel " + novelCount);
                System.out.println("Novel: " + novel.getTitle());
                System.out.println("Author: " + novel.getAuthorName());
                System.out.println("Year Published: " + novel.getYearPublished());
                System.out.println();
                novelCount++;
            }
            System.out.println(counter);

        } catch (final FileNotFoundException e){
            System.out.println("File not found: " + fileName);
        }



    }
}
