import java.util.ArrayList;

public class Room {
    protected final int enemyMinAmount = 2; // Maybe change with diffuculty?
    public static String[] roomTypes = {"Loot", "Battle", "Shop", "Boss", "Special"};
    protected int roomNumber;
    protected String type;
    protected String description;
    protected int amountOfItems = 0;
    protected int amountOfEnemies = 0;
    protected int minimumNumOfItems = 0;
    protected int maximumNumOfItems = 0;
    protected ArrayList<Item> roomItems;
    protected ArrayList<Enemy> roomEnemies;
    
    @SuppressWarnings("OverridableMethodCallInConstructor")
    public Room(String type, int roomNumber) {
        
        this.type = type;
        this.roomNumber = roomNumber;
        this.roomItems = new ArrayList<>();
        this.roomEnemies = new ArrayList<>();
        switch(type) {

            case "Loot":
                this.minimumNumOfItems = 3;
                this.maximumNumOfItems = calculateMaxItems(5); // 5 items minus the room number * .2 (Less items the further you go)
                this.amountOfItems = GameFlow.rollRandom(minimumNumOfItems, maximumNumOfItems);

                this.description = "A room full of loot. It seems like there aren't any hostiles nearby...";
                for (int i = 0; i < amountOfItems; i++) {
                    roomItems.add(new Item(Item.returnRandom()));
                }
                break;
    
            case "Battle":
                this.minimumNumOfItems = 0;
                this.maximumNumOfItems = calculateMaxItems(2); // 5 items minus the room number * .2 (Less items the further you go)
                this.amountOfItems = GameFlow.rollRandom(minimumNumOfItems, maximumNumOfItems);
                
                this.description = "You see enemies lurking around each corner... maybe it's best to approach them first; they might ambush you.";
                amountOfEnemies = GameFlow.rollRandom(enemyMinAmount, calculateMaxEnemies()); // Get the amount of enemies in the room.
                for (int i = 0; i < amountOfEnemies; i++) {
                    roomEnemies.add(new Enemy(Enemy.returnRandom(), roomNumber));
                }
                break;

            case "Shop":
                this.description = "There is a merchant set up selling goods. It seems the denizens of these ruins aren't all hostile.";
                // No enemies, implement shop functionality
                break;

            case "Boss":
                this.description = "You sense it. There's an evil presence in here.";
                roomEnemies.add(new Boss(Boss.returnRandom(), roomNumber));
                break;

            case "Special":
                this.description = "A bright light shines faintly. Where... are you?";
                break;

            default: 
                this.description = "Uhh... I think you misspelled the type of room somewhere... -- GC ";
                break;
        }
    }

    public Item takeRandomItem() {
        int itemIndex = (int) (Math.random() * (roomItems.size()));
        return roomItems.remove(itemIndex);
    }
    public Enemy selectRandomEnemy() {
        int enemyIndex = (int) (Math.random() * (roomEnemies.size()));
        return roomEnemies.remove(enemyIndex);
    }

    public String getDescription() {
        return description;
    }

    public int calculateMaxEnemies() {
        return (int)Math.floor(Math.sqrt(roomNumber)); // OKAY, it looks weird but here's the math behind this: 
        // You take the square root of the room number, then floor it. (So, room 6 will be 2.44948974278, which rounds BACK to 2)
    }

    public int calculateMaxItems(int initialMax) {
        return Math.abs(initialMax - (int)(Math.ceil(roomNumber * .2)));
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public ArrayList<Item> getItems() {
        return roomItems;
    }
        
    public ArrayList<Enemy> getEnemies() {
        return roomEnemies;
    }
}



