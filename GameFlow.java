import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class GameFlow {

    // Color codes for game
    // A couple things about these codes, you format it as:         CODE + "String" + RESET             In order to make colored text. - Gabe
    // If you need to use them in another file, simply call:            GameFlow.CODE;
    
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GREY = "\u001B[90m"; 
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[37m";
    
    public static Player player;
    public static ArrayList<Room> dungeon;

    // Scanner Input methods

    private static final Scanner input = new Scanner(System.in);
    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            int value = input.nextInt();
            input.nextLine(); // Consume the leftover newline
            return value;
        } catch (Exception e) {
            System.out.println("\nInvalid Input. Try again.");
            input.nextLine();
            return getIntInput(prompt);
        }
    }
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        try {
            return input.nextLine();
        } catch (Exception e) {
            System.out.println("\\nInvalid Input. Try again.");
            input.nextLine();
            return getStringInput(prompt);
        }
    }

    public static void main(String[] args) {
        runGameLoop();
    }

    // Roblox wait function in java!
    public static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    // This writes out a string like typed text with two versions. (This one has variable speed and can use a new line conditionally.)

    public static void typewrite(int speed, String text, boolean useNewLine) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                Thread.sleep(speed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        if (!useNewLine) {
            return;
        }
        System.out.println("");
    }

    // This writes out a string like typed text with two versions. (This is a simple one with a set speed of 20)
    
    public static void typewrite(String text) {
    final long typeSpeed = 20;
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                Thread.sleep(typeSpeed);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                break;
            }
        }
        System.out.println("");
    }

    // Main game loop where everything happens

    public static void runGameLoop() {
        displayTitle();
        int playerDecision = getIntInput("Input: ");
        if (playerDecision == 1) {
            //displayIntro();
            player = runCharacterCreation();
        }
        dungeon = createDungeon(30);
        enterDungeon(dungeon, player);
    }

    public static void displaySettings() {
        // COMPLETE SETTINGS
    }

    public static void displayInGameOptions(Room room, Player player) {
        typewrite("\nMENU\n----");
        typewrite("\n1. Check Self\n2. Use Item\n3. Information Codex\n" + ANSI_BLACK + "4. Return\n"+ ANSI_RED + "5. Exit\n" + RESET);
        int playerDecision = getIntInput("Input: ");
        switch (playerDecision) {
            case 1:
                typewrite(1, player.toString(), true);
                getStringInput("Press enter to continue: ");
                displayInGameOptions(room, player);
                break;
            case 2:

                for (int i = 0; i < player.getInventory().size(); i++) {
                    System.out.print("\n\t" + (i + 1) + ".) ");
                    typewrite(player.getInventory().get(i).toString());
                }
                playerDecision = getIntInput("Select an Item to use: ");
                player.useItem(player.getInventory().get(playerDecision));
            case 3:
                //TODO Information codex

            case 4: 
                break;

            default:
                runGameLoop();
                break;
        }
        
    }
    // Initial title display 
    public static void displayTitle() {
        System.out.println("================================");
        System.out.println(ANSI_CYAN + "CHRISTOFER: " + ANSI_PURPLE + "THE RISE OF MARK-HAM" + RESET);
        System.out.printf("\n1. Play\n2. Settings\n\n");
    }

    // Intro for after play confirm
    public static void displayIntro() {
        waitSeconds(1);
        typewrite(ANSI_BLUE + "\nAFTER his fall at the hands of a legendary " + ANSI_CYAN + "Moon Warrior, " + RESET
        + ANSI_GREEN +"Chris-tofer, the Script-King, " 
        + ANSI_BLUE + "resigned himself to the Ruins of Be-Ta.");
        waitSeconds(1);
        typewrite("It is said that in those forsaken Dungeons, he plots his return to power.");
        waitSeconds(1);
        typewrite("Whispers, rumors, and half-forgotten tales suggest that " + ANSI_PURPLE + "World Conqueror Mark-Ham " + ANSI_BLUE + "is, in truth, " + ANSI_GREEN + "a vassal of Chris-tofer." + ANSI_BLUE);
        waitSeconds(1);
        typewrite("If " + ANSI_GREEN + "The Script-King's" + ANSI_BLUE + " plan to merge with"+ ANSI_BLACK + " the Core of the Dungeon " + ANSI_BLUE + "comes to fruition, his influence on this world might become to much to handle.");
        waitSeconds(1);
        typewrite(50, ANSI_BLUE + "\nYou will be the one to stop his reckless ascension", false);
        typewrite(500, "...", false);
        waitSeconds(1);
        typewrite(10, ANSI_YELLOW + " perchance. \n" + RESET, true);
        waitSeconds(3);
        typewrite(50, "Lets begin. ", false);
        waitSeconds(2);
    }


    public static Player runCharacterCreation() {
        String name = getNameSequence();

        Dictionary<String, Integer> stats = new Hashtable<>(); // PLAYER STATS: Life, Anger, Peace, Smartness, Finesse (Adding them to the stats Dictionary)
        stats.put("Life", (Integer)rollRandom(1, 20));
        stats.put("Anger", (Integer)rollRandom(1, 20));
        stats.put("Peace", (Integer)rollRandom(1, 20));
        stats.put("Smartness", (Integer)rollRandom(1, 20));
        stats.put("Finesse", (Integer)rollRandom(1, 20));

        String fortune = Player.getPlayerFortune(getTotalDictionaryValue(stats));
        
        ArrayList<Item> items = new ArrayList<>(); // Storage spot for items and weapons
        items.add(new Weapon(Weapon.returnRandom()));
        items.add(new Item(Item.returnRandom()));

        Player player = new Player(name, fortune, stats, items);
        
        typewrite(ANSI_BLUE + "Intriguing, " + name.split(" ")[0]);
        waitSeconds(1);
        typewrite(50,  "Good luck in there." + RESET, true);
        waitSeconds(1);
        
        typewrite(1, player.toString(), true);
        getStringInput("Press enter to continue: ");
        return player;
    }

    public static int rollRandom(int min, int max) {
        int stat = (int) (Math.random() * (max - min + 1) + min);
        return stat;
    }

    public static float rollRandomFloat() {
        float stat = (float)(Math.random());
        return stat;
    }
    
    public static void enterDungeon(ArrayList<Room> Dungeon, Player player) {
        for (Room room : Dungeon) {
            typewrite(ANSI_BLUE +  "\nYou have entered room " + room.getRoomNumber() + " of the Ruins of Be'Ta." + RESET);
            exploreRoom(room, player);
        }
    }

    public static ArrayList<Room> createDungeon(int numOfRooms) {
        ArrayList<Room> tempDungeon = new ArrayList<>();
        
        for (int i = 1; i <= numOfRooms; i++) {
            if (i == rollRandom(1, numOfRooms)) {
                tempDungeon.add(new Room("Special", i)); // Add special room for the dungeon size
            }
            else if (i == 1) {
                tempDungeon.add(new Room("Battle", i)); // Add battle room for first floor
            }
            else if (i == 3) {
                tempDungeon.add(new Room("Loot", i)); // add loot room for third floor (so player understands what it is.)
            }
            else if (i % 10 == 0) {
                tempDungeon.add(new Room("Boss", i)); // Add boss room for every 10 floors
            }
            else if (i % 5 == 0) {
                tempDungeon.add(new Room("Shop", i)); // Add shop room for every 5 floors
            }
            else if (i % 7 == 0) {
                tempDungeon.add(new Room("Loot", i)); // Add loot room every 7 floors 
            }
            else {
                tempDungeon.add(new Room("Battle", i)); // Add battle room for any other room
            }
            
        }
        return tempDungeon;
    }

    public static void exploreRoom(Room room, Player player) {
        typewrite(ANSI_BLUE + room.getDescription() + RESET);
        typewrite(5, "\n1. Search Room\n2. Approach Enemy\n3. Move To Next Room\n" + ANSI_BLACK + "4. Menu\n" + RESET, false);
        int playerDecision = getIntInput("\nInput: ");
        switch (playerDecision) {
            
            case 1: // If the player wants to search the room, they have a chance of being ambushed. (encounterChance)
                System.out.println(room.getEnemies());
                float encounterChance = rollRandomFloat();
                if (encounterChance <= player.getEncounterChance() && (!room.getEnemies().isEmpty()) ) { 
                    System.out.println("ENCOUNTER CHANCE: " + encounterChance + " | PLAYER %: " + player.getEncounterChance());
                    Enemy foundEnemy = room.selectRandomEnemy();
                    typewrite(ANSI_BLUE + "You search the room");
                    waitSeconds(1);
                    typewrite(150, "...", true);
                    waitSeconds(1);
                    typewrite(200, "...", true);
                    waitSeconds(2);
                    typewrite(ANSI_RED + "Whilst searching, you were ambushed!" + RESET);
                    battle(player, foundEnemy); // If the encounter chance ends up matching, then a battle will happen.
                } 
                else {
                    System.out.println(room.getItems());
                    typewrite(ANSI_BLUE + "You search the room");
                    waitSeconds(1);
                    typewrite(150, "...", true);
                    waitSeconds(1);
                    typewrite(200, "...", true);
                    waitSeconds(2);
                    if (!room.getItems().isEmpty()) {
                        Item foundItem = room.takeRandomItem();
                        player.addItemToInventory(foundItem);
                        typewrite("You found a " + ANSI_YELLOW + foundItem.getName() + "!\n" + RESET);
                        waitSeconds(1);
                    }
                    else {
                        typewrite("... But found nothing.\n" + RESET);
                        waitSeconds(1);
                    }
                }
                exploreRoom(room, player);
                break;

            case 2:   
                if (!room.getEnemies().isEmpty()) {
                    Enemy foundEnemy = room.selectRandomEnemy();
                    typewrite(ANSI_BLUE + "\nYou approach " + ANSI_RED + foundEnemy.getName() + ".\n" + RESET);
                    battle(player, foundEnemy);
                    //Then once battle is over, resume roomtivities.
                }
                else {
                    typewrite(ANSI_BLUE + "\nThere is nobody to approach.\n" + RESET);
                }
                exploreRoom(room, player);
                break;

            case 3:
                float nextRoomChance = rollRandomFloat();
                if (nextRoomChance <= player.getEncounterChance() && (!room.getEnemies().isEmpty()) ) { 
                    Enemy foundEnemy = room.selectRandomEnemy();
                    typewrite(ANSI_BLUE + "\nYou attempt to move rooms");
                    waitSeconds(1);
                    typewrite(150, "...", true);
                    waitSeconds(1);
                    typewrite(200, "...", true);
                    waitSeconds(2);
                    typewrite(ANSI_RED + "Enemies block your way!" + RESET);
                    battle(player, foundEnemy); // If the encounter chance ends up matching, then a battle will happen.
                    typewrite("With the enemy dead, you tread on. \n");
                    break;
                } 
                else {
                    typewrite(ANSI_BLUE + "\nYou attempt to move rooms");
                    waitSeconds(1);
                    typewrite(200, "..." + RESET, true);
                    waitSeconds(2);
                    break;
                }

            case 4:
                displayInGameOptions(room, player);
                exploreRoom(room, player);
                break;
            default:
                typewrite("\n");
                exploreRoom(room, player);
                break;
        }
    }

    public static int getTotalDictionaryValue(Dictionary<String, Integer> dictionary) {
        int totalValue = 0;
        for (Integer statValue : Collections.list(dictionary.elements())) {
            totalValue += statValue;
        }
        return totalValue;
    }

    public static String getNameSequence() {
        typewrite(50, "What is your name?", false);
        String name = getStringInput(": ");
        if (name.isEmpty()) {
            typewrite("At least you know you cannot choose.");
            typewrite("Your name is...");
            waitSeconds(1);
            name = getRandomName();
            waitSeconds(1);
            typewrite(10, "\n" + ANSI_YELLOW + name + RESET + "\n", true);
            waitSeconds(1);
            return name;
        }
        else {
            typewrite("You do not choose who you are.");
            waitSeconds(1);
            typewrite("Not here, ");
            waitSeconds(1);
            name = getRandomName();
            typewrite(10, "\n" + ANSI_YELLOW + name + RESET + "\n", true);
            waitSeconds(1);
            return name;
        }
    }

    public static String getRandomName() {
        String[] firstNames = {
            "Alexander", "Sophia", "Marcus", "Gabriel", "Ethan", "Jak",
            "Olivia", "Arham", "Emma", "Liam", "Ant", "Violence",
            "Mason", "Charlotte", "Lucas", "Mia", "Henry",
            "Harper", "Benjamin", "Amelia", "James", "Grace",
            "Daniel", "Lily", "Booh", "Chloe", "Jackson",
            "Zoe", "Samuel", "Syd", "David", "Violet", "Saturnu"
        };
        String[] lastNames = {
            "Kardeenis", "Muhrgi", "Gard-nare", "Zefah", "Mikyuin",
            "Ptahtell", "Tepesh", "Violence", "Thorntin", "Gojo",
            "Thun", "Jack", "Dubmin", "Thitay", "Kal-Fust",
            "Frost", "Oobah", "Bah", "Starless", "Moon",
            "Java", "Stone", "Windowspider", "Midterm", "Final",
        };
        int fIndex = (int) (Math.random() * (firstNames.length));
        int lIndex = (int) (Math.random() * (lastNames.length));
        return firstNames[fIndex] + " " + lastNames[lIndex];
    }

    public static void moveDown() {
        for (int i = 0; i < 20; i++) {
            typewrite("\n");
        }
    }
    public static void battle(Player player, Enemy enemy) {
        waitSeconds(1);
        moveDown();
        typewrite(ANSI_BLUE + enemy.getDescription() + RESET);


        typewrite(5, "\n1. Attack\n2. Use Item\n3. Inspect " + enemy.getName() + "\n" + ANSI_BLACK + "4. Flee\n" + RESET, false);
        int playerDecision = getIntInput("\nInput: ");
        switch (playerDecision) { 

            case 1: // Attack
                typewrite(5, "\n1. Use" + player.getEquippedWeapon() + "\n2. Use Moonblessing\n" + ANSI_BLACK + "3. Return" + RESET, false);
                int fightDecision = getIntInput("\nInput: ");
                switch (fightDecision) {
                    case 1:
                        int playerDamageDealt = getPlayerDamage(player);
                        typewrite(player.getName() + " attacked " + enemy.getName() + "with" player.getEquippedWeapon());
                    case 2:
                    default:
                    break;
                }
            case 2: //TODO Use Item
            case 3: //TODO Study Enemy
            case 4: //TODO Attempt Flee
            default:
                break;
        }
        
        
    }
    public static int getPlayerDamage(Player player) {
        return 4;
    }
    
}    
    