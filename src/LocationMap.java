import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME =  "locations.txt";
    private static final String DIRECTIONS_FILE_NAME =  "directions.txt";

    static HashMap<Integer, Location> locations = new HashMap<>();
    static {
        FileLogger fileLogger = new FileLogger();
        ConsoleLogger consoleLogger = new ConsoleLogger();

        /** TODO
         * Read from LOCATIONS_FILE_NAME so that a user can navigate from one location to another
         * use try-with-resources/catch block for the FileReader
         * extract the location and the description on each line
         * print all locations and descriptions to both console and file
         * check the ExpectedOutput files
         * put each location in the locations HashMap using temporary empty hashmaps for exits
         */

        try { BufferedReader br = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME));
            String line, description;
            int locId = 0, commaFound; //Needs initialisation to work
            while ((line = br.readLine()) != null) { //While end of file is not reached
                //Prints all locations and descriptions to both console and file
                fileLogger.log(line);
                consoleLogger.log(line);
                //Find the index where the first occurrence of comma is
                commaFound = line.indexOf(',');
                if (commaFound != -1){ //Makes sure a comma is found in the file
                    // Creates a substring till comma is found, converts to Integer
                    locId = Integer.parseInt(line.substring(0, commaFound));
                }
                //Creates a substring from comma found to the end of the line
                description = line.substring(commaFound + 1, line.length() - 1);
                Map<String, Integer> exits = new HashMap<>(); //Create empty hashmap
                Location location = new Location(locId, description, exits);
                locations.put(locId, location); //Add to locations HashMap
            }
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }

        /**TODO
         * Read from DIRECTIONS_FILE_NAME so that a user can move from A to B, i.e. current location to next location
         * use try-with-resources/catch block for the FileReader
         * extract the 3 elements  on each line: location, direction, destination
         * print all locations, directions and destinations to both console and file
         * check the ExpectedOutput files
         * for each location, create a new location object and add its exit
         */
        try { BufferedReader br = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME));
            String line, direction = "";
            String[] arr;
            int location = 0, destination = 0;
            LinkedHashMap<String, Integer> exits = new LinkedHashMap<>();
            while ((line = br.readLine()) != null) {
                fileLogger.log(line); //Save to file & console
                consoleLogger.log(line);
                arr = line.split(",", 3);
                location = Integer.parseInt(arr[0]);
                direction = arr[1];
                destination = Integer.parseInt(arr[2]);
                exits.put(direction, destination);
                Location locationObj = new Location(location, exits.get(direction).toString(), exits);
                locations.put(location, locationObj);
            }
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
    }

    @Override
    public int size() {
        return LocationMap.locations.size();
    }

    @Override
    public boolean isEmpty() {
        return locations.size() == 0;
    }

    @Override
    public boolean containsKey(Object key) {
        return locations.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return locations.containsValue(value);
    }

    @Override
    public Location get(Object key) {
        if (locations.get(key) == null)
            return null;
        else
            return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        if (locations.containsKey(key))
            return locations.replace(key, value);
        else
            return locations.putIfAbsent(key, value);
    }

    @Override
    public Location remove(Object key) {
        return locations.remove(key);
    }

    @Override //Check this one?
    public void putAll(Map<? extends Integer, ? extends Location> m) {
        locations.putAll(m);
    }

    @Override
    public void clear() {
        locations.clear();
    }

    @Override
    public Set<Integer> keySet() {
        return locations.keySet();
    }

    @Override
    public Collection<Location> values() {
        return locations.values();
    }

    @Override
    public Set<Entry<Integer, Location>> entrySet() {
        return locations.entrySet();
    }
}
