import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {
    public static final int INITIAL_LOCATION = 95;
    static LocationMap locationMap = new LocationMap();

    HashMap<String, String> vocabulary = new HashMap<>();
    FileLogger fileLogger = new FileLogger();
    ConsoleLogger consoleLogger = new ConsoleLogger();

    public Mapping() {
        //Complete vocabulary hash map for all directions
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("EAST", "E");
        vocabulary.put("WEST", "W");
        vocabulary.put("NORTHEAST", "NE");
        vocabulary.put("NORTHWEST", "NW");
        vocabulary.put("SOUTHEAST", "SE");
        vocabulary.put("SOUTHWEST", "SW");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
    }

    public void mapping() {
        Scanner sc = new Scanner(System.in);
        int location = INITIAL_LOCATION;

        while (true) {
            //Get location and print to console & file
            String message;
            message = locationMap.get(location).getDescription();
            consoleLogger.log(message);
            fileLogger.log(message);

            //If current location is the exit
            if (locationMap.get(location).getLocationId() == 0) {
                break;
            }
            //Map of all current location's exits
            Map<String, Integer> exits = locationMap.get(location).getExits();

            StringBuilder sb = new StringBuilder();
            sb.append("Available exits are ");
            String availableExits = exits.keySet().toString();
            sb.append(availableExits.replaceAll("\\[", "").replaceAll("]", ", "));
            consoleLogger.log(sb.toString());
            fileLogger.log(sb.toString());
            String directionInput = sc.nextLine().toUpperCase();

            boolean valid = false;
            boolean letterDirection = false;
            if (directionInput.length() <= 2 && !(directionInput.equals("UP"))) {
                letterDirection = true;
                if (vocabulary.containsValue(directionInput)) {
                    valid = true;
                }
            } else {
                String[] arr = directionInput.split(" ");
                for (int i = 0; i < arr.length; i++) { //If multiple viable words are entered, directionInput is set to last valid word
                    if (vocabulary.containsKey(arr[i])) {
                        directionInput = arr[i].toUpperCase();
                        valid = true;
                    }
                }
            }

            //Update location when a valid move is made, otherwise output error to user
            try {
                if (valid) {
                    if (letterDirection) {
                        location = exits.get(directionInput);
                    } else {
                        location = exits.get(vocabulary.get(directionInput));
                    }
                } else {
                    consoleLogger.log("You cannot go in that direction");
                    fileLogger.log("You cannot go in that direction");
                }
            } catch (NullPointerException e) {
                consoleLogger.log("You cannot go in that direction");
                fileLogger.log("You cannot go in that direction");
            }
        }
    }

    public static void main(String[] args) {
        Mapping mapObj = new Mapping();
        mapObj.mapping();
    }
}
