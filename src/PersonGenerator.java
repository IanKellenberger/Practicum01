import java.util.*;
import java.io.*;

public class PersonGenerator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> personList = new ArrayList<>();
        boolean morePersons = true;

        while (morePersons) {
            // Use SafeInput methods to get validated input from the user
            String id = SafeInput.getNonZeroLenString(in, "Enter ID");
            String firstName = SafeInput.getNonZeroLenString(in, "Enter First Name");
            String lastName = SafeInput.getNonZeroLenString(in, "Enter Last Name");
            String title = SafeInput.getNonZeroLenString(in, "Enter Title (Mr., Mrs., Ms., Dr., etc.)");
            int yearOfBirth = SafeInput.getInt(in, "Enter Year of Birth");

            // Create a single person record in CSV format
            String person = id + ", " + firstName + ", " + lastName + ", " + title + ", " + yearOfBirth;
            personList.add(person);

            // Ask the user if they want to add another person
            morePersons = SafeInput.getYNConfirm(in, "Do you want to enter another person?");
        }

        // Get the filename to save the data
        String filename = SafeInput.getNonZeroLenString(in, "Enter the filename to save the persons data");

        // Save all complete records to a file
        savePersonsToFile(personList, filename);
    }

    private static void savePersonsToFile(ArrayList<String> personList, String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (String person : personList) {
                out.println(person);
            }
            System.out.println("Data successfully saved to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }
    }
}
