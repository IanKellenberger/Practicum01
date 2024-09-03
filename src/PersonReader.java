import javax.swing.JFileChooser;
import java.io.*;
import java.util.*;

public class PersonReader {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        // Use JFileChooser to select the file
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select a person data file");
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            // Display the contents of the selected file
            displayPersonFile(selectedFile);
        } else {
            System.out.println("No file selected. Exiting program.");
        }
    }

    private static void displayPersonFile(File file) {
        try (Scanner fileScanner = new Scanner(file)) {
            System.out.printf("%-10s %-15s %-15s %-10s %-5s\n", "ID#", "FirstName", "LastName", "Title", "YOB");
            System.out.println("============================================================");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] personData = line.split(",\\s*"); // Split by comma and optional whitespace

                if (personData.length == 5) {
                    String id = personData[0];
                    String firstName = personData[1];
                    String lastName = personData[2];
                    String title = personData[3];
                    String yearOfBirth = personData[4];

                    // Print formatted data
                    System.out.printf("%-10s %-15s %-15s %-10s %-5s\n", id, firstName, lastName, title, yearOfBirth);
                } else {
                    System.out.println("Error: Incorrect format in data line: " + line);
                }
            }

        } catch (FileNotFoundException e) {
            System.out.println("The file was not found: " + file.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("An error occurred while reading the file.");
        }
    }
}
