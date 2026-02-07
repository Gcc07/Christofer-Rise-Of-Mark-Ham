import java.util.ArrayList;

public class Room {
    protected final int enemyMinAmount = 2; // Maybe change with diffuculty?
    public static String[] roomTypes = {"Loot", "Battle", "Shop", "Boss", "Special"};
    protected int roomNumber;
    protected String type;
    protected String description;
    protected int enemyAmount = 0;
    protected ArrayList<Item> roomItems;
    protected ArrayList<Enemy> roomEnemies;
    
    public Room(String type, int roomNumber) {
        this.type = type;
        this.roomNumber = roomNumber;
        roomItems = new ArrayList<>();
        roomEnemies = new ArrayList<>();
        switch(type) {
            case "Loot":
                this.description = "A room full of loot. It seems like there aren't any hostiles nearby...";
                roomItems.add(new Item(Item.returnRandom()));
            case "Battle":
                this.description = "A room will enemies parading around... approach them, or they might approach you.";
                enemyAmount = GameFlow.rollRandom(enemyMinAmount, calculateMaxEnemies()); // Get the amount of enemies in the room.
                for (int i = 0; i < enemyAmount; i++) {
                    roomEnemies.add(new Enemy(Enemy.returnRandom(), roomNumber));
                }
            case "Shop":
                this.description = "A room with a merchant set up selling goods. It seems these ruins' denizens aren't entirely hostile.";
                // No enemies, implement shop functionality
            case "Boss":
                this.description = "No point in tiptoeing around the subject. There's an evil presence in here.";
            roomEnemies.add(new Enemy(Enemy.returnRandom(), roomNumber));
        }
    }

    public ArrayList<Item> searchRoom() {
        return roomItems;
    }

    public String getDescription() {
        return description;
    }

    public int calculateMaxEnemies() {
        return (int)Math.floor(Math.sqrt(roomNumber)); // OKAY, it looks weird but here's the math behind this: 
        // You take the square root of the room number, then floor it. (So, room 6 will be 2.44948974278, which rounds BACK to 2)
    }

    public int getRoomNumber() {
        return roomNumber;
    }
}



