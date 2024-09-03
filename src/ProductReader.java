import javax.swing.JFileChooser;
import java.io.*;
import java.util.*;

public class ProductReader {
    public static void main(String[] args) {
        // Use JFileChooser to select the file
        JFileChooser chooser = new JFileChooser();
        chooser.setDialogTitle("Select a product data file");
        int result = chooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            File selectedFile = chooser.getSelectedFile();

            // Display the contents of the selected file
            displayProductFile(selectedFile);
        } else {
            System.out.println("No file selected. Exiting program.");
        }
    }

    private static void displayProductFile(File file) {
        try (Scanner fileScanner = new Scanner(file)) {
            // Print the header for the formatted output
            System.out.printf("%-10s %-20s %-30s %-10s\n", "ID#", "Name", "Description", "Cost");
            System.out.println("=================================================================================");

            while (fileScanner.hasNextLine()) {
                String line = fileScanner.nextLine();
                String[] productData = line.split(",\\s*"); // Split by comma and optional whitespace

                if (productData.length == 4) {
                    String id = productData[0];
                    String name = productData[1];
                    String description = productData[2];
                    String cost = productData[3];

                    // Print formatted data
                    System.out.printf("%-10s %-20s %-30s $%-10s\n", id, name, description, cost);
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
