import java.util.ArrayList;

public class Room {
    public static String[] roomTypes = {"Loot", "Battle", "Shop", "Boss", "Special"};
    protected int roomNumber;
    protected String type;
    protected String description;
    protected int amountOfItems = 0;
    protected int amountOfEnemies = 0;
    protected int minimumNumOfItems = 0;
    protected int maximumNumOfItems = 0;
    protected int minimumNumOfEnemies = 0;
    protected int maximumNumOfEnemies = 0;
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
                this.description = "Salvagable goods appear intermittently. It seems like there aren't any hostiles nearby...";

                this.minimumNumOfItems = 3;
                this.maximumNumOfItems = calculateMaxItems(4); // 5 items minus the room number * .2 (Less items the further you go)
                this.amountOfItems = GameFlow.rollRandom(minimumNumOfItems, maximumNumOfItems);
                for (int i = 0; i < amountOfItems; i++) {
                    roomItems.add(new Item(Item.returnRandom()));
                }
                break;
    
            case "Battle":
                this.description = "You see enemies lurking around each corner... maybe it's best to approach them first; they might ambush you.";

                this.minimumNumOfItems = 0;
                this.maximumNumOfItems = calculateMaxItems(3); // 5 items minus the room number * .2 (Less items the further you go)
                this.amountOfItems = GameFlow.rollRandom(minimumNumOfItems, maximumNumOfItems);
                for (int i = 1; i < amountOfItems; i++) {
                    roomItems.add(new Item(Item.returnRandom()));
                }
                
                this.minimumNumOfEnemies = 2;
                this.maximumNumOfEnemies = calculateMaxEnemies();
                amountOfEnemies = GameFlow.rollRandom(minimumNumOfEnemies, maximumNumOfEnemies); // Get the amount of enemies in the room.
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
                this.description = "A bright light shines faintly. You stand before an impossibly large tree. Where... are you?";
                for (int i = 0; i < GameFlow.rollRandom(2, 5); i++) {
                    roomItems.add(new Item("Glowing Apple"));
                }
                
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

    public void addEnemyToRoom(Enemy enemy) {
        roomEnemies.add(enemy);
    }

    public void clearEnemies() {
        roomEnemies.clear();
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public String getRoomType() {
        return type;
    }

    public ArrayList<Item> getItems() {
        return roomItems;
    }
        
    public ArrayList<Enemy> getEnemies() {
        return roomEnemies;
    }
}



