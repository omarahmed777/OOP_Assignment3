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
        vocabulary.put("north", "N");
        vocabulary.put("south", "S");
        vocabulary.put("east", "E");
        vocabulary.put("west", "W");
        vocabulary.put("north east", "NE");
        vocabulary.put("north west", "NW");
        vocabulary.put("south east", "SE");
        vocabulary.put("south west", "SW");
        vocabulary.put("up", "U");
        vocabulary.put("down", "D");
    }

    public void mapping() {
        Scanner sc = new Scanner(System.in);
        int location = INITIAL_LOCATION;

        while (true) {
            /** TODO
             * get the location and print its description to both console and file
             * use the FileLogger and ConsoleLogger objects
             */
            locationMap.get(location);

            /** TODO
             * verify if the location is exit
             */
            if (locationMap.get(location).equals(0)){
                break;
            }


            /** TODO
             * get a map of the exits for the location
             */

            /** TODO
             * print the available exits (to both console and file)
             * crosscheck with the ExpectedOutput files
             * Hint: you can use a StringBuilder to append the exits
             */

            /** TODO
             * input a direction
             * ensure that the input is converted to uppercase
             */

            /** TODO
             * are we dealing with a letter / word for the direction to go to?
             * available inputs are: a letter(the HashMap value), a word (the HashMap key), a string of words that contains the key
             * crosscheck with the ExpectedInput and ExpectedOutput files for examples of inputs
             * if the input contains multiple words, extract each word
             * find the direction to go to using the vocabulary mapping
             * if multiple viable directions are specified in the input, choose the last one
             */

            /** TODO
             * if user can go in that direction, then set the location to that direction
             * otherwise print an error message (to both console and file)
             * check the ExpectedOutput files
             */
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
