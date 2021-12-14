import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME = "locations.txt";
    private static final String DIRECTIONS_FILE_NAME = "directions.txt";

    static HashMap<Integer, Location> locations = new HashMap<>();

    static {
        FileLogger fileLogger = new FileLogger();
        ConsoleLogger consoleLogger = new ConsoleLogger();

        try {
            BufferedReader br = new BufferedReader(new FileReader(LOCATIONS_FILE_NAME));
            String line, description, message;
            int locId = 0, commaFound;
            boolean availableLocations = false;
            while ((line = br.readLine()) != null) { //While end of file is not reached
                if (!availableLocations) { //Prints "Available locations:" only once
                    message = "Available locations:";
                    fileLogger.log(message);
                    consoleLogger.log(message);
                    availableLocations = true;
                }
                //Find the index where the first occurrence of comma is
                commaFound = line.indexOf(',');
                if (commaFound != -1) { //Makes sure a comma is found in the file
                    // Creates a substring till comma is found, converts to Integer
                    locId = Integer.parseInt(line.substring(0, commaFound));
                }
                //Creates a substring from comma found to the end of the line
                description = line.substring(commaFound + 1);
                //Prints all locations and descriptions to both console and file
                message = (locId + ": " + description);
                fileLogger.log(message);
                consoleLogger.log(message);
                Map<String, Integer> exits = new HashMap<>(); //Create empty hashmap
                Location location = new Location(locId, description, exits);
                locations.put(locId, location); //Add to locations HashMap
            }
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(DIRECTIONS_FILE_NAME));
            String line, direction, message;
            String[] arr;
            int location, destination;
            boolean availableDirections = false;
            while ((line = br.readLine()) != null) {
                if (!availableDirections) { //Prints "Available directions:" only once
                    message = "Available directions:";
                    fileLogger.log(message);
                    consoleLogger.log(message);
                    availableDirections = true;
                }
                //Split each line into its location, direction, destination
                arr = line.split(",", 3);
                location = Integer.parseInt(arr[0]);
                direction = arr[1];
                destination = Integer.parseInt(arr[2]);
                //Save output to console and file
                message = (location + ": " + direction + ": " + destination);
                fileLogger.log(message);
                consoleLogger.log(message);
                //Create new Location object and add its exit
                Location locationObj = new Location(location, locations.get(location).getDescription(), locations.get(location).getExits());
                locationObj.addExit(direction, destination);
                locations.put(location, locationObj);
            }
        } catch (IOException e) {
            System.out.println("This file does not exist.");
        }
    }

    @Override
    public int size() {
        return locations.size();
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

    @Override
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
