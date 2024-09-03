import java.util.*;
import java.io.*;

public class ProductWriter {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        ArrayList<String> productList = new ArrayList<>();
        boolean moreProducts = true;

        while (moreProducts) {
            // Use SafeInput methods to get validated input from the user
            String id = SafeInput.getNonZeroLenString(in, "Enter Product ID");
            String name = SafeInput.getNonZeroLenString(in, "Enter Product Name");
            String description = SafeInput.getNonZeroLenString(in, "Enter Product Description");
            double cost = SafeInput.getDouble(in, "Enter Product Cost");

            // Create a single product record in CSV format
            String product = id + ", " + name + ", " + description + ", " + cost;
            productList.add(product);

            // Ask the user if they want to add another product
            moreProducts = SafeInput.getYNConfirm(in, "Do you want to enter another product?");
        }

        // Get the filename to save the data
        String filename = SafeInput.getNonZeroLenString(in, "Enter the filename to save the product data");

        // Save all complete records to a file
        saveProductsToFile(productList, filename);
    }

    private static void saveProductsToFile(ArrayList<String> productList, String filename) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filename))) {
            for (String product : productList) {
                out.println(product);
            }
            System.out.println("Data successfully saved to " + filename);
        } catch (IOException e) {
            System.out.println("An error occurred while saving the file.");
        }
    }
}
