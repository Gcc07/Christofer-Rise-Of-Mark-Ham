import java.util.ArrayList;
import java.util.Dictionary;

public class Room {
    public static String[] roomTypes = {"Loot", "Battle", "Shop", "Boss", "Special"};
    protected String type;
    protected ArrayList<Item> roomItems;
    
    public Room(String type) {
        this.type = type;
        switch(type) {
        case "Loot":
            roomItems.add(new Item())
        }

    }
}

