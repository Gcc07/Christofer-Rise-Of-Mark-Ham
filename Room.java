import java.util.ArrayList;
import java.util.Dictionary;

public class Room {
    public static String[] roomTypes = {"Loot", "Battle", "Shop", "Boss", "Special"};
    protected int roomNumber;
    protected String type;
    protected ArrayList<Item> roomItems;
    protected ArrayList<Enemy> roomEnemies;
    
    public Room(String type, int roomNumber) {
        this.type = type;
        switch(type) {
        case "Loot":
            roomItems.add(new Item(Item.returnRandomItem()));
        case "Battle":
            roomEnemies.add(new Enemy(Enemy.returnRandomEnemy(roomNumber)));
        }
    }
}

