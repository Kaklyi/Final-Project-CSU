import java.util.ArrayList; // Used to store multiple Home objects
import java.util.Scanner; // Used to capture user input
import java.io.FileWriter; // Used to write inventory data to a file

// Main class that runs the Home Inventory Program
public class HomeInventoryProgram {

    // Home Class: This class represents a single home in the inventory
    static class Home {

        // Required attributes for each home
        private int square_feet;
        private String address;
        private String city;
        private String state;
        private int zip_code;
        private String model_name;
        private String sale_status;

        // ArrayList used to store all homes
        private static ArrayList<Home> homeInventory = new ArrayList<>();

        // Constructor
        // Creates a new Home object with the given values
        public Home(int square_feet, String address, String city, String state,
                int zip_code, String model_name, String sale_status) {

            // "this" refers to the current object being created
            this.square_feet = square_feet;
            this.address = address;
            this.city = city;
            this.state = state;
            this.zip_code = zip_code;
            this.model_name = model_name;
            this.sale_status = sale_status;
        }

        // addhome: Adds a home object to the inventory list
        public static String addHome(Home newHome) {

            try {

                // Add the new home to the ArrayList
                homeInventory.add(newHome);

                return "SUCCESS: Home added to inventory.";

            } catch (Exception e) {

                // If something goes wrong return a failure message
                return "FAILURE: Could not add home.";

            }
        }

        // removehome: Removes a home from inventory using the address
        public static String removeHome(String address) {

            try {

                // Loop through the inventory
                for (int i = 0; i < homeInventory.size(); i++) {

                    // Check if the address matches
                    if (homeInventory.get(i).address.equals(address)) {

                        // Remove the home from the list
                        homeInventory.remove(i);

                        return "SUCCESS: Home removed from inventory.";
                    }
                }

                // If no matching home was found
                return "FAILURE: Home not found.";

            } catch (Exception e) {

                return "FAILURE: Error removing home.";

            }
        }

        // updateHomeStatus: Updates the sale status of a home
        public static String updateHomeStatus(String address, String newStatus) {

            try {

                // Loop through the homes in inventory
                for (Home h : homeInventory) {

                    // Find the matching address
                    if (h.address.equals(address)) {

                        // Update the sale status
                        h.sale_status = newStatus;

                        return "SUCCESS: Sale status updated.";
                    }
                }

                return "FAILURE: Home not found.";

            } catch (Exception e) {

                return "FAILURE: Error updating home.";

            }
        }

        // listhomes: Displays all homes currently in inventory
        public static void listHomes() {

            try {

                System.out.println("\n------ CURRENT HOME INVENTORY ------");

                // Check if the inventory is empty
                if (homeInventory.size() == 0) {

                    System.out.println("No homes currently in inventory.");
                    return;
                }

                // Loop through each home and print details
                for (Home h : homeInventory) {

                    System.out.println("Model Name: " + h.model_name);
                    System.out.println("Square Feet: " + h.square_feet);
                    System.out.println("Address: " + h.address);
                    System.out.println("City: " + h.city);
                    System.out.println("State: " + h.state);
                    System.out.println("Zip Code: " + h.zip_code);
                    System.out.println("Sale Status: " + h.sale_status);

                    // Visual separator between homes
                    System.out.println("-----------------------------------");
                }

            } catch (Exception e) {

                System.out.println("FAILURE: Error displaying homes.");

            }
        }

        // toString:Converts a Home object into a formatted string
        // This helps when writing the object to a file
        public String toString() {

            return "Model: " + model_name +
                    " | Square Feet: " + square_feet +
                    " | Address: " + address + ", " + city + ", " + state + " " + zip_code +
                    " | Status: " + sale_status;
        }

        // Getter method to allow other classes to access
        // the inventory list safely
        public static ArrayList<Home> getInventory() {

            return homeInventory;
        }

    }

    // main class

    public static void main(String[] args) {

        try {

            // Scanner used to capture user input from keyboard
            Scanner scanner = new Scanner(System.in);

            // Program title displayed to the user
            System.out.println("=====================================");
            System.out.println("   NATIONAL BUILDER HOME INVENTORY   ");
            System.out.println("=====================================");

            // Create the first home object using constructor
            Home home1 = new Home(
                    2500,
                    "123 West Kings Hwy",
                    "San Antonio",
                    "TX",
                    78212,
                    "Modern Model",
                    "available");

            // Add the home to inventory
            System.out.println(Home.addHome(home1));

            // Display inventory
            Home.listHomes();

            // Remove the first home
            System.out.println(Home.removeHome("123 West Kings Hwy"));

            // Create another home
            Home home2 = new Home(
                    1950,
                    "321 East Queen Ave",
                    "Austin",
                    "TX",
                    78121,
                    "Classic Model",
                    "available");

            // Add new home to inventory
            System.out.println(Home.addHome(home2));

            // Show inventory again
            Home.listHomes();

            // Update sale status of the home
            System.out.println(Home.updateHomeStatus("321 East Queen Ave", "sold"));

            // Display inventory after update
            Home.listHomes();

            // Ask the user if they want to save the inventory to a file
            System.out.print("\nWould you like to print the inventory to a file? (Y/N): ");

            String response = scanner.next();

            // If user chooses yes
            if (response.equalsIgnoreCase("Y")) {

                // Call method that writes inventory to file
                printToFile();

                System.out.println("Inventory printed to Home.txt in the project folder.");

            } else {

                // If user chooses no
                System.out.println("File will not be printed.");
            }

            // Close scanner to free resources
            scanner.close();

        } catch (Exception e) {

            // General catch for unexpected program errors
            System.out.println("ERROR: A problem occurred in the program.");
        }

    }

    // -------------------------------------------------
    // Method: printToFile
    // Writes the inventory list to a text file
    // -------------------------------------------------
    public static void printToFile() {

        try {

            // Create a text file in the same project folder
            FileWriter writer = new FileWriter("Home.txt");

            // Retrieve the inventory list
            ArrayList<Home> homes = Home.getInventory();

            // Add a title to the file
            writer.write("NATIONAL BUILDER HOME INVENTORY\n");
            writer.write("=====================================\n\n");

            // Loop through homes and write each one to file
            for (Home h : homes) {

                writer.write(h.toString() + "\n");
            }

            // Always close the writer
            writer.close();

        } catch (Exception e) {

            // If file writing fails
            System.out.println("FAILURE: Could not write file.");
        }

    }

}