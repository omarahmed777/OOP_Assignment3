import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Mapping {
    public static final int INITIAL_LOCATION = 95;
    static LocationMap locationMap = new LocationMap();

    HashMap<String, String> vocabulary = new HashMap();
    FileLogger fileLogger = new FileLogger();
    ConsoleLogger consoleLogger = new ConsoleLogger();

    public Mapping() {
        /** TODO
         * complete the vocabulary HashMap <Key, Value> with all directions.
         * use the directions.txt file and crosscheck with the ExpectedInput and ExpectedOutput files to find the keys and the values
         */
        vocabulary.put("QUIT", "Q");
        vocabulary.put("NORTH", "N");
        vocabulary.put("SOUTH", "S");
        vocabulary.put("EAST", "E");
        vocabulary.put("WEST", "W");
        vocabulary.put("NORTH EAST", "NE");
        vocabulary.put("NORTH WEST", "NW");
        vocabulary.put("SOUTH EAST", "SE");
        vocabulary.put("SOUTH WEST", "SW");
        vocabulary.put("UP", "U");
        vocabulary.put("DOWN", "D");
    }

    public void mapping() {
        Scanner sc = new Scanner(System.in);
        int location = INITIAL_LOCATION;

        while (true) {
            /** TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            String message;
            message = locationMap.get(location).getDescription();
            consoleLogger.log(message);
            fileLogger.log(message);


            /** TODO
             * verify if the location is exit
             */
            if (locationMap.get(location).getLocationId() == 0){
                break;
            }


            /** TODO
             * get a map of the exits for the location
             */
            Map<String, Integer> exits = locationMap.get(location).getExits();

            /** TODO
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */
            StringBuilder sb = new StringBuilder();
            sb.append("Available exits are ");
            String availableExits = exits.keySet().toString();
            sb.append(String.format(availableExits).replaceAll("\\[", "").replaceAll("]", ", "));
            consoleLogger.log(sb.toString());
            fileLogger.log(sb.toString());

            /** TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */
            String directionInput = sc.nextLine().toUpperCase();

            /** TODO
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */
            boolean valid = false;
            if (directionInput.length() == 1 || directionInput.length() == 2){ //If letter direction is entered e.g. N, SE, NW, etc.
                if (vocabulary.containsValue(directionInput)){
                    valid = true;
                }
            } else {
                String[] arr = directionInput.split(" ");
                for(int i=0; i<arr.length;i++){ //If multiple viable words are entered, directionInput is set to last valid word
                    if (vocabulary.containsKey(arr[i])){
                        directionInput = arr[i];
                        valid = true;
                    }
                }
            }

            /** TODO
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */
            if (valid){
                location = exits.get(directionInput);
            } else {
                consoleLogger.log("You cannot go in that direction");
                fileLogger.log("You cannot go in that direction");
            }
        }
    }

    public static void main(String[] args) {
        /**TODO
         * run the program from here
         * create a Mapping object
         * start the game
         */
        Mapping mapObj = new Mapping();
        mapObj.mapping();
    }
}
