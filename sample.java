import java.util.ArrayList;
import java.util.List;

public class sample {
    public static void main(String[] args) {
        // Create a nested ArrayList of strings
        List<List<String>> nestedArrayList = new ArrayList<>();

        // Add some inner ArrayLists
        nestedArrayList.add(new ArrayList<>(List.of("Apple", "Banana", "Cherry")));
        nestedArrayList.add(new ArrayList<>(List.of("Dog", "Elephant", "Fish")));
        nestedArrayList.add(new ArrayList<>(List.of("Java", "Python", "C++")));

        // Accessing elements using nested loops
        for (int i = 0; i < nestedArrayList.size(); i++) {
            List<String> innerList = nestedArrayList.get(i);
            for (int j = 0; j < innerList.size(); j++) {
                String element = innerList.get(j);
                System.out.println("Element at index " + i + "-" + j + ": " + element);
            }
        }

        // Adding an element to the inner ArrayList
        nestedArrayList.get(1).add("Giraffe");

        // Removing an element from the inner ArrayList
        nestedArrayList.get(0).remove("Banana");

        // Printing the modified nested ArrayList
        System.out.println("\nModified Nested ArrayList:");
        for (List<String> innerList : nestedArrayList) {
            System.out.println(innerList);
        }
    }
}
