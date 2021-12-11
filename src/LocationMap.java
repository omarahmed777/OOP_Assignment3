import java.awt.*;
import java.io.*;
import java.util.*;

//class that behaves like a map
public class LocationMap implements Map<Integer, Location> {

    private static final String LOCATIONS_FILE_NAME =  "locations.txt";
    private static final String DIRECTIONS_FILE_NAME =  "directions.txt";

    static HashMap<Integer, Location> locations;
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
                //Find the index where the comma is
                commaFound = line.indexOf(',');
                if (commaFound != -1){ //Makes sure a comma is found in the file
                    // Creates a substring till comma is found, converts to Integer
                    locId = Integer.parseInt(line.substring(0, commaFound));
                }
                //Creates a substring from comma found to the end of the line
                description = line.substring(commaFound + 1, line.length() - 1);
                HashMap<String, Integer> exits = new HashMap<>(); //Create empty hashmap
                Location location = new Location(locId, description, exits);
                locations.put(locId, location); //Add to locations HashMap
                line = br.readLine();
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
            String line, direction = ""; //Needs initialisation to work
            int location = 0, destination = 0; //Needs initialisation to work
            StringBuilder sbLocation = new StringBuilder(),
                    sbDirection = new StringBuilder(),
                    sbDestination = new StringBuilder();
            boolean firstCommaFound = false, secondCommaFound = false;
            while ((line = br.readLine()) != null) {
                fileLogger.log(line);
                consoleLogger.log(line);
                for (int i=0;i<line.length();i++){
                    //Save location
                    if (line.charAt(i) != ',' && !firstCommaFound) {
                        sbLocation.append(line.charAt(i));
                        location = location + Integer.parseInt(sbLocation.toString());
                    } else if (line.charAt(i) == ',' && !firstCommaFound) {
                        firstCommaFound = true;
                    }
                    //Save direction
                    else if (line.charAt(i) != ',' && firstCommaFound && !secondCommaFound) {
                        sbDirection.append(line.charAt(i));
                        direction = direction + sbDirection;
                    } else if (line.charAt(i) == ',' && firstCommaFound && !secondCommaFound){
                        secondCommaFound = true;
                    }
                    //Save destination
                    else {
                        sbDestination.append(line.charAt(i));
                        destination = destination + Integer.parseInt(sbDestination.toString());
                    }
                }
                for (Location locationObj : locations){
                    locationObj.addExit(direction, location);
                }
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
        if (locations==null)
            return true;
        else
            return false;
    }

    @Override
    public boolean containsKey(Object key) {
        if (locations.containsKey(key))
            return true;
        else
            return false;
    }

    @Override
    public boolean containsValue(Object value) {
        if (locations.containsValue(value))
            return true;
        else
            return false;
    }

    @Override
    public Location get(Object key) {

        return locations.get(key);
    }

    @Override
    public Location put(Integer key, Location value) {
        if (locations.containsKey(key))
            return locations.replace(key, value);
        else {
            return locations.putIfAbsent(key, value);
        }
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
