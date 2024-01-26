import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class employee {

    private String fileName;

    // constructor
    employee() {
        this.fileName = "DefaultFile.txt";
        createFile(fileName);
    }

    // constructor with parameter
    employee(String file_name) {
        this.fileName = file_name;
        createFile(file_name);

    }

    boolean createFile(String fileName) {
        boolean creation_status = false;
        try {
            File myObj = new File(fileName);
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
                creation_status = true;
            } else {
                System.out.println("File access successful");
                creation_status = false;
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return creation_status;
    }

    int searchLine(String searchText) {

        String line;
        int lineNumber = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            while ((line = reader.readLine()) != null && searchText != line) {

                if (line.equals(searchText)) {
                    return lineNumber;
                }
                lineNumber++;

            }
        } catch (IOException e) {
            System.out.println(e);
        }

        return -1;
    }

    String blockJoiner(String Name) {
        return Name;
    }

    boolean replaceLine(int lineToReplace, String replaceWith) {
        String line;
        int lineNumber = 1;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            List<String> lineList = new ArrayList<>();

            while ((line = reader.readLine()) != null && lineNumber <= lineToReplace + 2) {
               lineList.add(line);
               
            }

            // Check if the target line exists
            if (lineToReplace > 0 && lineNumber <= lineList.size()) {
                // Replace the desired line with new content
                lineList.set(lineToReplace + 1, replaceWith);

                // Write the modified lineList back to the file
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
                    for (String updatedLine : lineList) {
                        writer.write(updatedLine);
                        writer.newLine();
                    }
                }

                return true; // Line replaced successfully
            }

        } catch (IOException e) {
            System.out.println(e);
        }
        return false;
    }
}

public class empManagement {
    public static void main(String[] args) {
        employee emp = new employee("example.txt");
        String blockNum = "2";
        String replaceString = "this line should replace 2nd line of block 2";
        System.out.println(emp.replaceLine(emp.searchLine("block_" + blockNum), replaceString));

    }
}