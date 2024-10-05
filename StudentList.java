
import java.io.*;
import java.text.*;
import java.util.*;

public class StudentList {

    public static void main(String[] args) {

        if (ArgumentCheck(args)) {
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
            String words[] = fileContents.split(Constants.StudentEntryDelimiter);
            System.out.println(words.length + Constants.WordFound);
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

    public static boolean ArgumentCheck(String[] args) {
        if (args == null || args.length != Constants.ONE) {
            return true;
        } else if (args.length == Constants.ONE && !args[Constants.ZERO].equals(Constants.ShowAll)
                && !args[Constants.ZERO].equals(Constants.ShowCount) && !args[Constants.ZERO].equals(Constants.ShowRandom) && !args[Constants.ZERO].startsWith(Constants.FindEntry)
                && !args[Constants.ZERO].startsWith(Constants.AddEntry)) {
            return true;
        }
        return false;
    }
}