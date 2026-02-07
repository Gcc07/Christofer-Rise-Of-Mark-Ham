import java.util.ArrayList;

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
            roomItems.add(new Item(Item.returnRandom()));
        case "Battle":
            roomEnemies.add(new Enemy(Enemy.returnRandom(roomNumber)));
        case "Shop":
            // No enemies, implement shop functionality
        case "Boss":
           roomEnemies.add(new Enemy(Enemy.returnRandom(roomNumber)));
        }

        public ArrayList<Item> searchRoom() {
            return roomItems;
        }
    }
}



