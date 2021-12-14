import java.util.LinkedHashMap;
import java.util.Map;

public class Location {
    private final int locationId;
    private final String description;
    private final LinkedHashMap<String, Integer> exits;

    public Location(int locationId, String description, Map<String, Integer> exits) {
        this.locationId = locationId;
        this.description = description;

        if (exits.size() == 0) {
            this.exits = new LinkedHashMap<>();
            this.exits.put("Q", 0);
        } else {
            this.exits = (LinkedHashMap<String, Integer>) exits;
        }
    }

    protected void addExit(String direction, int location) {
        this.exits.put(direction, location);
    }

    public int getLocationId() {
        return this.locationId;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, Integer> getExits() {
        LinkedHashMap<String, Integer> exitsCopy = this.exits;
        return exitsCopy;
    }
}
