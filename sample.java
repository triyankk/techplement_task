import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ReplaceLine {

    public static void main(String[] args) {
        // Specify the file path
        String filePath = "example.txt";

        // Specify the target line number to replace
        int targetLineNumber = 2;

        // Specify the new content for the line
        String newContent = "This is the replaced line 2.";

        // Replace the specific line
        boolean replaceSuccess = replaceLine(filePath, targetLineNumber, newContent);

        // Print the result
        if (replaceSuccess) {
            System.out.println("Line " + targetLineNumber + " replaced successfully.");
        } else {
            System.out.println("Line " + targetLineNumber + " not found or unable to replace.");
        }
    }

    private static boolean replaceLine(String filePath, int targetLineNumber, String newContent) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read all lines from the file
            List<String> lines = new ArrayList<>();
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }

            // Check if the target line exists
            if (targetLineNumber > 0 && targetLineNumber <= lines.size()) {
                // Replace the desired line with new content
                lines.set(targetLineNumber - 1, newContent);

                // Write the modified lines back to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
                    for (String updatedLine : lines) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                }

                return true; // Line replaced successfully
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // Line not found or unable to replace
    }
}
