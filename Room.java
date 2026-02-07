import java.util.ArrayList;

public class Room {
    protected final int enemyMinAmount = 2; // Maybe change with diffuculty?
    public static String[] roomTypes = {"Loot", "Battle", "Shop", "Boss", "Special"};
    protected int roomNumber;
    protected String type;
    protected int enemyAmount = 0;
    protected ArrayList<Item> roomItems;
    protected ArrayList<Enemy> roomEnemies;
    
    public Room(String type, int roomNumber) {
        this.type = type;

        roomItems = new ArrayList<>();
        roomEnemies = new ArrayList<>();

        switch(type) {
            case "Loot":
                roomItems.add(new Item(Item.returnRandom()));
            case "Battle":
                enemyAmount = GameFlow.rollRandom(enemyMinAmount, calculateMaxEnemies()); // Get the amount of enemies in the room.
                for (int i = 0; i < enemyAmount; i++) {
                    roomEnemies.add(new Enemy(Enemy.returnRandom(), roomNumber));
                }
            case "Shop":
                // No enemies, implement shop functionality
            case "Boss":
            roomEnemies.add(new Enemy(Enemy.returnRandom(), roomNumber));
        }
    }

    public ArrayList<Item> searchRoom() {
        return roomItems;
    }

    public int calculateMaxEnemies() {
        return (int)Math.floor(Math.sqrt(roomNumber)); // OKAY, it looks weird but here's the math behind this: 
        // You take the square root of the room number, then floor it. (So, room 6 will be 2.44948974278, which rounds BACK to 2)
    }
}



