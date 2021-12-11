import java.util.LinkedHashMap;
import java.util.Map;

public class Location {

    /** TODO
     * declare private final locationId, description, exits
     */
    private final int locationId;
    private final String description;
    private final LinkedHashMap<String, Integer> exits;

    public Location(int locationId, String description, Map<String, Integer> exits) {
        /** TODO
         * set the locationId and the description
         */
        this.locationId = locationId;
        this.description = description;

        /** TODO
         * if exits are not null, set the exit
         * otherwise, set the exit HashMap to (Q,0)
         */
        if (exits != null) {
            this.exits = (LinkedHashMap<String, Integer>) exits;
        } else {
            this.exits = new LinkedHashMap<>();
            this.exits.put("Q", 0);
        }
    }

    protected void addExit(String direction, int location) {
        /** TODO
         * put the direction and the location in the exits HashMap
         */
        this.exits.put(direction, location);
    }

    public int getLocationId() {
        /** TODO
         * complete getter to return the location id
         */
        return this.locationId;
    }

    public String getDescription() {
        /** TODO
         * complete getter to return the description
         */
        return this.description;
    }

    public Map<String, Integer> getExits() {
        /** TODO
         * complete getter to return a copy of exits
         * (preventing modification of exits from outside the Location instance)
         */
        LinkedHashMap<String, Integer> exitsCopy = this.exits;
        return exitsCopy;
    }
}
