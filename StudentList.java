
import java.io.*; // Importing necessary classes for file input/output operations
import java.text.*; // Importing classes for date formatting
import java.util.*; // Importing utility classes such as Random and Date

public class StudentList {

    public static void main(String[] args) {

        // Check if the provided arguments are valid
        if (ArgumentCheck(args)) {
            System.out.println(Constants.Arguments); // Print error message for invalid arguments
            return; // Exit if arguments are invalid
        }

        // Load the contents of the student list file
        String fileContents = LoadData(Constants.StudentList);

        // If the argument is to show all students
        if (args[Constants.ZERO].equals(Constants.ShowAll)) {
            System.out.println(Constants.Loading);
            String words[] = fileContents.split(Constants.StudentEntryDelimiter); // Split the file contents by delimiter
            for (String word : words) {
                System.out.println(word); // Print each student entry
            }
            System.out.println(Constants.DataLoad);

            // If the argument is to show a random student entry
        } else if (args[Constants.ZERO].equals(Constants.ShowRandom)) {
            System.out.println(Constants.Loading);
            String words[] = fileContents.split(Constants.StudentEntryDelimiter);
            Random random = new Random(); // Create an instance of the Random class
            int randomIndex = random.nextInt(Constants.ZERO, words.length); // Generate a random index
            System.out.println(words[randomIndex]);
            System.out.println(Constants.DataLoad);

            // If the argument is to add a new student entry
        } else if (args[Constants.ZERO].contains(Constants.AddEntry)) {
            System.out.println(Constants.Loading);
            String argValue = args[Constants.ZERO].substring(Constants.ONE); // Extract the new student name from the argument
            UpdateContent(argValue, Constants.StudentList); // Append the new entry to the student list file
            System.out.println(Constants.DataLoad);

            // If the argument is to find a specific student entry
        } else if (args[Constants.ZERO].contains(Constants.FindEntry)) {
            System.out.println(Constants.Loading);
            String words[] = fileContents.split(Constants.StudentEntryDelimiter);
            int indexLocation = Constants.NEGATIVE_ONE; // Initialize the location index
            String argValue = args[Constants.ZERO].substring(Constants.ONE); // Extract the name to find from the argument
            for (int index = Constants.ZERO; index < words.length; index++) {
                if (words[index].trim().equals(argValue.trim())) { // Compare the name with entries in the file
                    indexLocation = index; // Update the location if found
                    break;
                }
            }
            if (indexLocation >= Constants.ZERO) {
                System.out.println(Constants.FoundIt); // Print success message if found
            } else {
                System.out.println(Constants.NotFound); // Print error message if not found
            }
            System.out.println(Constants.DataLoad);

            // If the argument is to show the count of student entries
        } else if (args[Constants.ZERO].contains(Constants.ShowCount)) {
            System.out.println(Constants.Loading);
            String words[] = fileContents.split(Constants.StudentEntryDelimiter);
            System.out.println(words.length + Constants.WordFound); // Print the count of student entries
            System.out.println(Constants.DataLoad);
        }
    }

    // Method to load data from the specified file
    public static String LoadData(String fileName) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName))); // Read the file
            return fileReader.readLine();
        } catch (Exception e) {
            // Catch any exception that occurs during file reading
        }
        return null;
    }

    // Method to update the file content with a new entry
    public static void UpdateContent(String content, String fileName) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true)); // Open the file for appending
            fileWriter.write(Constants.StudentEntryDelimiter + content + Constants.UpdateContent + new SimpleDateFormat(Constants.DateStyle).format(new Date())); // Append the new entry with the current date
            fileWriter.close();
        } catch (Exception e) {
            // Catch any exception that occurs during file writing
        }
    }

    // Method to check if the provided arguments are valid
    public static boolean ArgumentCheck(String[] args) {
        if (args == null || args.length != Constants.ONE) { // If no arguments or more than one argument is provided, return true
            return true;
        } else if (args.length == Constants.ONE && !args[Constants.ZERO].equals(Constants.ShowAll)
                && !args[Constants.ZERO].equals(Constants.ShowCount) && !args[Constants.ZERO].equals(Constants.ShowRandom) && !args[Constants.ZERO].startsWith(Constants.FindEntry)
                && !args[Constants.ZERO].startsWith(Constants.AddEntry)) { // Check if the provided argument is one of the valid commands
            return true;
        }
        return false; // Return false if the arguments are valid
    }
}