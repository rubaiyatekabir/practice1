
import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {

    public static void main(String[] args) {

        //Check arguments
        if (args == null || args.length != Constants.ONE) {
            System.out.println(Constants.Arguments);
            return;
        }

        String fileContents = LoadData(Constants.StudentList);
        if (args[Constants.ZERO].equals(Constants.ShowAll)) {
            System.out.println(Constants.Loading);
            String words[] = fileContents.split(Constants.StudentEntryDelimiter);
            for (String word : words) {
                System.out.println(word);
            }
            System.out.println(Constants.DataLoad);
        } else if (args[Constants.ZERO].equals(Constants.ShowRandom)) {
            System.out.println(Constants.Loading);
            String words[] = fileContents.split(Constants.StudentEntryDelimiter);
            Random random = new Random();
            int randomIndex = random.nextInt(Constants.ZERO, words.length);
            System.out.println(words[randomIndex]);
            System.out.println(Constants.DataLoad);
        } else if (args[Constants.ZERO].contains(Constants.AddEntry)) {
            System.out.println(Constants.Loading);
            String argValue = args[Constants.ZERO].substring(Constants.ONE);
            UpdateContent(argValue, Constants.StudentList);
            System.out.println(Constants.DataLoad);
        } else if (args[Constants.ZERO].contains(Constants.FindEntry)) {
            System.out.println(Constants.Loading);
            String words[] = fileContents.split(Constants.StudentEntryDelimiter);
            //boolean done = false;
            int indexLocation = Constants.NEGATIVE_ONE;
            String argValue = args[Constants.ZERO].substring(Constants.ONE);
            for (int index = Constants.ZERO; index < words.length; index++) {
                if (words[index].trim().equals(argValue.trim())) {
                    indexLocation = index;
                    break;
                }
            }
            if (indexLocation >= Constants.ZERO) {
                System.out.println(Constants.FoundIt);
            } else {
                System.out.println(Constants.NotFound);
            }
            System.out.println(Constants.DataLoad);
        } else if (args[Constants.ZERO].contains(Constants.ShowCount)) {
            System.out.println(Constants.Loading);
            char characters[] = fileContents.toCharArray();
            boolean in_word = false;
            int count = Constants.ZERO;
            for (char character : characters) {
                if (character == Constants.Space) {
                    if (!in_word) {
                        count++;
                        in_word = true;
                    } else {
                        in_word = false;
                    }
                }
            }
            System.out.println(count + Constants.WordFound);
            System.out.println(Constants.DataLoad);
        }
    }

    public static String LoadData(String fileName) {
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName)));
            return fileReader.readLine();
        } catch (Exception e) {
        }
        return null;
    }

    public static void UpdateContent(String content, String fileName) {
        try {
            BufferedWriter fileWriter = new BufferedWriter(new FileWriter(fileName, true));
            fileWriter.write(Constants.StudentEntryDelimiter + content + Constants.UpdateContent + new SimpleDateFormat(Constants.DateStyle).format(new Date()));
            fileWriter.close();
        } catch (Exception e) {
        }

    }
}