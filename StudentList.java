
import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {

    public static void main(String[] args) {

        //Check arguments
        if (args == null || args.length != 1) {
            System.out.println("Usage: (a | r | c | +studentName | ?studentName)");
            return;
        }
        String fileContents = LoadData("Students.txt");
        if (args[0].equals("a")) {
            System.out.println("Loading data ...");
            String words[] = fileContents.split(", ");
            for (String word : words) {
                System.out.println(word);
            }
            System.out.println("Data Loaded.");
        } else if (args[0].equals("r")) {
            System.out.println("Loading data ...");
            String words[] = fileContents.split(", ");
            Random random = new Random();
            int randomIndex = random.nextInt(0, words.length);
            System.out.println(words[randomIndex]);
            System.out.println("Data Loaded.");
        } else if (args[0].contains("+")) {
            System.out.println("Loading data ...");
            String argValue = args[0].substring(1);
            UpdateContent(argValue, "Students.txt");
            System.out.println("Data Loaded.");
        } else if (args[0].contains("?")) {
            System.out.println("Loading data ...");
            String words[] = fileContents.split(", ");
            boolean done = false;
            String argValue = args[0].substring(1);
            for (int index = 0; index < words.length && !done; index++) {
                if (words[index].equals(argValue)) {
                    System.out.println("We found it!");
                    done = true;
                }
            }
            System.out.println("Data Loaded.");
        } else if (args[0].contains("c")) {
            System.out.println("Loading data ...");
            char characters[] = fileContents.toCharArray();
            boolean in_word = false;
            int count = 0;
            for (char character : characters) {
                if (character == ' ') {
                    if (!in_word) {
                        count++;
                        in_word = true;
                    } else {
                        in_word = false;
                    }
                }
            }
            System.out.println(count + " word(s) found ");
            System.out.println("Data Loaded.");
        }
    }

    public static String LoadData(String fileName) {
        String line = null;
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            line = fileReader.readLine();
        } catch (Exception e) {
        }
        return line;
    }

    public static void UpdateContent(String content, String fileName) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true));
            Date newDate = new Date();
            String dateStyle = "dd/mm/yyyy-hh:mm:ss a";
            DateFormat dateFormat = new SimpleDateFormat(dateStyle);
            String now = dateFormat.format(newDate);
            fileWriter.write(", " + content + "\nList last updated on " + now);
            fileWriter.close();
        } catch (Exception e) {
        }

    }
}