import java.util.ArrayList;
import java.util.Collections;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Optional;
import java.util.Scanner;

public class GameFlow {

    // These are ANSI Color codes for game
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
    
    
    public static long typeSpeedMultiplier = 1;
    public static final long BASE_TYPE_SPEED = 20; // Base amount between each character print
    public static final long MIN_TYPESPEED_MULTIPLER = 1;
    public static final long MAX_TYPESPEED_MULTIPLER = 10;

    public static boolean useWaiting = true;

    public static boolean useIntro = true;
    
    public static boolean gameOver = false;

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

    // Main method for running game loop
    public static void main(String[] args) {
        while(!gameOver){
            runGameLoop();
        }
        
    }

    // Roblox wait function in java!
    public static void waitSeconds(int seconds) {
        if (useWaiting) {
            try {
                Thread.sleep(seconds * 1000L);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }

    // Used for even more specific time-based stuff
    private static void waitMilliseconds(long ms) {
    try {
        Thread.sleep(ms);
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
                Thread.sleep(speed / typeSpeedMultiplier);
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
        for (char c : text.toCharArray()) {
            System.out.print(c);
            System.out.flush();
            try {
                Thread.sleep(BASE_TYPE_SPEED / typeSpeedMultiplier);
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
            if (useIntro) {
                displayIntro();
            }
            player = runCharacterCreation();
        } else if (playerDecision == 2) {
            displaySettings();
        }
        dungeon = createDungeon(30);
        enterDungeon(dungeon, player);
    }


    // Displays game options before entering the dungeon / playing.
    public static void displaySettings() {
        System.out.println("\n================================");
        System.out.println(ANSI_CYAN + "CHRISTOFER: " + ANSI_PURPLE + "THE RISE OF MARK-HAM" + RESET);
        System.out.printf("\n1. Set standard typewrite speed (current speed: " + typeSpeedMultiplier + "x )" + 
        "\n2. Toggle introduction before gameplay (using intro: " + useIntro + ")" +
        "\n3. Toggle cinematic waiting (waiting: " + useWaiting + ")" +
        "\n4. Back to home\n\n");
        int playerDecision = getIntInput("Input: ");
        switch(playerDecision) {
            case 1:
                int selectedSpeed = getIntInput("\nEnter new text speed (1x - 10x): ");
                if (selectedSpeed >= MIN_TYPESPEED_MULTIPLER && selectedSpeed <= MAX_TYPESPEED_MULTIPLER) {
                    typeSpeedMultiplier = selectedSpeed;
                } else {
                    System.out.println(ANSI_RED + "\nERROR: Your type speed must be between " + MIN_TYPESPEED_MULTIPLER + " and " + MAX_TYPESPEED_MULTIPLER + RESET);
                    waitSeconds(1);
                }
                displaySettings();
            case 2:
                useIntro = !useIntro;
                displaySettings();
            case 3:
                useWaiting = !useWaiting;
                displaySettings();
            case 4:
                runGameLoop();
            case 5: // hidden debug setting
                useWaiting = false;
                useIntro = false;
                typeSpeedMultiplier = 5;
                displaySettings();
            default:
                displaySettings();
        }

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
                    typewrite(1, player.getInventory().get(i).toString(),true);
                }
                playerDecision = getIntInput("Select an Item to use: ");
                try {
                    
                    Item item = player.getInventory().get(playerDecision - 1);
                    typewrite("\n" + ANSI_YELLOW + player.getName() + item.getUseMessage() + item.getName() + RESET+ "\n");
                    player.useItem(player.getInventory().get(playerDecision-1));
                } catch (Exception e) {
                    typewrite(ANSI_RED + "Something happened." + RESET);
                    break;
                }
                
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
        waitSeconds(2);
        typewrite(50, "Lets begin. ", false);
        waitSeconds(2);
    }

    public static void displayOutro(Player player) {
        typewrite(ANSI_BLUE + "\nAFTER his second defeat at the hands of the " + player.getFortune() + ANSI_YELLOW + " " + player.getName() + RESET
        + ANSI_GREEN +",\nChris-tofer the Script-King,");
        waitSeconds(4);
        typewrite(100, ANSI_RED + "was vaporized into a cloud of dust." + ANSI_BLUE, true);
        waitSeconds(1);
        typewrite("\nIt is said that in those forsaken Ruins of Be'Ta, there is no longer something to fear.");
        waitSeconds(1);
        typewrite("\nYou've done it, " + ANSI_YELLOW + player.getName() + ".");
        waitSeconds(2);
        typewrite("\nFINAL STATS: " + RESET);
        typewrite(1, player.toString(), true);

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
        
        typewrite(20, ANSI_BLUE + getRandomNameReaction(), false);
        waitSeconds(1);
        typewrite(ANSI_YELLOW +  name.split(" ")[0]);
        waitSeconds(1);
        typewrite(50,  ANSI_BLUE + "Good luck in there.\n" + RESET, false);
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
        displayOutro(player);
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
        try {
            Room lastRoom = tempDungeon.remove(numOfRooms); // Removes last room whilst storing it
            lastRoom.clearEnemies(); // Clears enemies
            lastRoom.addEnemyToRoom(new Boss("Final Form Gardner", numOfRooms)); // Addes it back with final boss 
            tempDungeon.add(lastRoom); 
        } catch (Exception e) {
            // Do nothing

        }
        return tempDungeon;
    }

    public static void exploreRoom(Room room, Player player) {
        typewrite(ANSI_BLUE + room.getDescription() + RESET);
        typewrite(5, "\n1. Search Room\n2. Approach Enemy\n3. Move To Next Room\n" + ANSI_BLACK + "4. Menu\n" + RESET, false);
        int playerDecision = getIntInput("\nInput: ");
        switch (playerDecision) {
            
            case 1: // If the player wants to search the room, they have a chance of being ambushed. (encounterChance)
                //System.out.println(room.getEnemies()); DEBUGGING

                float encounterChance = rollRandomFloat();
                if (encounterChance <= player.getEncounterChance() && (!room.getEnemies().isEmpty()) ) { 
                    // System.out.println("ENCOUNTER CHANCE: " + encounterChance + " | PLAYER %: " + player.getEncounterChance()); // DEBUGGING

                    Enemy foundEnemy = room.selectRandomEnemy();
                    typewrite(ANSI_BLUE + "\nYou search the room");
                    waitSeconds(1);
                    typewrite(150, "...", true);
                    waitSeconds(1);
                    typewrite(200, "...", true);
                    waitSeconds(2);
                    typewrite(ANSI_RED + "Whilst searching, you were ambushed!" + RESET);
                    Object winner = battle(player, foundEnemy); // If the encounter chance ends up matching, then a battle will happen.
                    determineWinner(winner, player, foundEnemy, room);
                    
                } 
                else {
                    // System.out.println(room.getItems()); DEBUGGING
                    typewrite(ANSI_BLUE + "\nYou search the room");
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
                    
                    Object winner = battle(player, foundEnemy); // If the encounter chance ends up matching, then a battle will happen.
                    determineWinner(winner, player, foundEnemy, room);
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

                    Object winner = battle(player, foundEnemy); // If the encounter chance ends up matching, then a battle will happen.
                    determineWinner(winner, player, foundEnemy, room);
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
        typewrite(40, ANSI_BLUE + "\nWhat is your name?", false);
        String name = getStringInput(": ");
        if (name.isEmpty()) {
            typewrite(500, "...", true);

            typewrite("At least you know you cannot choose.");
            waitSeconds(1);
            typewrite("Your name is:");
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

    public static String getRandomNameReaction(){
        String[] reactions = {"I like that name, ", "It's been a pleasure, ", "How interesting, ", "I hope there's more to you than this, ", "Perhaps you are the one, "};
        int rIndex = (int) (Math.random() * (reactions.length));
        return reactions[rIndex];
    }

    public static void moveDown() {
        for (int i = 0; i < 20; i++) {
            typewrite("\n");
        }
    }

    public static void determineWinner(Object winner, Player player, Enemy enemy, Room room) {
        if (winner instanceof Player) {
            typewrite(ANSI_BLUE + "\nDefeated " + ANSI_RED + enemy.getName() + ANSI_BLUE); 
            for (Item i : enemy.itemDrops) {
                player.addItemToInventory(i);
                typewrite(ANSI_YELLOW + i.getName() + ANSI_BLUE + " added to inventory.");
            }
            player.gainMP(enemy.getMarkPointsValue());
            typewrite(ANSI_PURPLE + enemy.getMarkPointsValue() +  " MP " + ANSI_BLUE +"gained." + RESET + "\n");

            room.getEnemies().remove(enemy); // Remove enemy after battle.
                        
        } else if (winner instanceof Enemy) {
            gameOver();
         } else {

            typewrite(ANSI_BLUE + "\nYou fled." + RESET);
            waitSeconds(1);
        }
    }
    
    public static Object battle(Player player, Enemy enemy) {
        waitSeconds(1);
        moveDown();
        typewrite(ANSI_BLUE + enemy.getDescription() + RESET);


        while(!player.isDead() && !enemy.isDead()) {
            typewrite(5, "\n1. Attack\n2. Use Item\n3. Inspect " + enemy.getName() + "\n" + "4. Inspect Self\n" + ANSI_BLACK + "5. Flee\n" + RESET, false);
            int playerDecision = getIntInput("\nInput: ");
            switch (playerDecision) { 

                case 1: // Attack
                    typewrite(5, "\n1. Use " + player.getEquippedWeapon().getName() + "\n2. Use Moonblessing\n" + ANSI_BLACK + "3. Return" + RESET, true);
                    int fightDecision = getIntInput("\nInput: ");
                    switch (fightDecision) {
                        case 1:
                            
                            // Player turn
                            typewrite("\n");
                            int playerDamageDealt = getPlayerDamage(player);
                            enemy.takeDamage(playerDamageDealt);
                            typewrite("\n" + ANSI_BLUE + player.getName() + " attacked " + ANSI_RED + enemy.getName() + ANSI_BLUE
                            + " with " + ANSI_YELLOW + player.getEquippedWeapon().getName() + ANSI_BLUE 
                            + " for " + ANSI_YELLOW + playerDamageDealt
                            + " DMG!" + RESET);

                            if (enemy.isDead()) {
                                return player; // If the enemy is dead, return player
                            }
                            // Enemy turn
                            typewrite(ANSI_BLUE + "\n... The Enemy Responds: ");
                            waitSeconds(1);
                            int enemyDamageDealt = applyCritChanceMultiplier(enemy.getDamage(), enemy.getCritChance(), enemy.getCritMultiplier());
                            player.takeDamage(enemyDamageDealt);
                            typewrite("\n" + ANSI_RED + enemy.getName() + ANSI_BLUE + " " + enemy.getAttackDescription() + " " + player.getName() + ANSI_BLUE
                            + " for " + ANSI_YELLOW + enemyDamageDealt
                            + " DMG!" + RESET);

                            //TODO Enemy actions OTHER than attack.
                            break;

                        case 2:
                            //TODO implement moon blessing
                            typewrite(ANSI_BLACK + "\nYou don't have a Moon Blessing");
                            break;
                        default:
                        break;
                    }
                    break;
                case 2:
                    for (int i = 0; i < player.getInventory().size(); i++) {
                    System.out.print("\n\t" + (i + 1) + ".) ");
                    typewrite(1, player.getInventory().get(i).toString(),true);
                    }
                    playerDecision = getIntInput("Select an Item to use: ");
                    try {
                        
                        Item item = player.getInventory().get(playerDecision - 1);
                        typewrite("\n" + ANSI_YELLOW + player.getName() + item.getUseMessage() + item.getName() + RESET+ "\n");
                        player.useItem(player.getInventory().get(playerDecision-1));

                    } catch (Exception e) {
                        break;
                    }
                    break;
                case 3: 
                    typewrite("\n" + ANSI_BLUE + enemy.inspect());
                    typewrite("\nEnemy health: " + enemy.getHealth() + "/" + enemy.getMaxHealth() + RESET);
                    break;
                case 4:
                    typewrite("\n" + ANSI_BLUE + player.inspect());
                    typewrite("\nYour health: " + player.getHealth() + "/" + player.getMaxHealth() + RESET);
                    break;
                case 5:
                    float fleeChance = rollRandomFloat();
                    if (fleeChance <= player.getFleeChance()) { 
                        if (!enemy.isBoss()) {
                            return Optional.empty();  // Fleeing, so no winner
                        }
                        typewrite("\nYour legs won't carry you far enough quick enough.\n");
                        
                    } else {
                        typewrite(ANSI_BLUE + "\nYou tried to flee, ");
                        waitSeconds(1);
                        typewrite(ANSI_BLUE + "\n... But you couldn't get away! ");
                        waitSeconds(1);
                        int enemyDamageDealt = applyCritChanceMultiplier(enemy.getDamage(), enemy.getCritChance(), enemy.getCritMultiplier());
                        player.takeDamage(enemyDamageDealt);
                        typewrite("\n" + ANSI_RED + enemy.getName() + ANSI_BLUE + " " + enemy.getAttackDescription() + " " + player.getName() + ANSI_BLUE
                        + " for " + ANSI_YELLOW + enemyDamageDealt
                        + " DMG!" + RESET);
                    }
                default:
                    break;
            }
        }
        if (player.isDead()) {
            return enemy;
        }
        else if (enemy.isDead()) {
            return player;
        }
        else {
            return null;
        }
    } 

    public static void gameOver() {
        gameOver = true;
        waitSeconds(2);
        moveDown();
        typewrite(ANSI_RED + "Game Over " + RESET);
        runGameLoop();
        
    }
    public static int getPlayerDamage(Player player) {
        Weapon weapon = player.getEquippedWeapon();
        int baseDamage = weapon.getDamage();
        float multiplier = multiplierMiniGame(50);
        int damageBeforeMiniGameMultiplier = applyCritChanceMultiplier(baseDamage, weapon.getCritChance(), weapon.getCritMultiplier());
        int final_damage = (int)(damageBeforeMiniGameMultiplier * multiplier);
        return final_damage;
    }

    public static int applyCritChanceMultiplier(int baseDamage, float critChance, float critMultiplier) {
        float checkCritChance = rollRandomFloat();

        if (checkCritChance <= critChance) {
            return (int)(baseDamage * critMultiplier);
        }
        else {
            return baseDamage;
        }
    }

    // AI USAGE: Because we don't yet have experience with threads or flags, I queried claude to assist in the usage of them for this method.
    public static float multiplierMiniGame(long millisecondSpeed) {
        float multipler;
        final boolean[] enterPressed = {false};
        final int[] pressPhase = {-1}; // -1 = not pressed, 0-2 = phase types (red, yellow then green.)

        Thread inputThread = new Thread(() -> {
            try {
                System.in.read(); // Wait for any key press (Enter)
                enterPressed[0] = true;
                // Don't try to move cursor - just detect the press
            } catch (Exception e) {}
        });

        inputThread.setDaemon(true);
        inputThread.start();

        // Phase 0: Red (low damage)
        System.out.print(ANSI_RED);
        for (int i = 0; i < 5; i++) {
            System.out.print("|█|\n"); // Whitespace character (Will be color)
            System.out.flush();
            waitMilliseconds(millisecondSpeed); // 100ms per character
            if (enterPressed[0] && pressPhase[0] == -1) { // If it is pressed
                pressPhase[0] = 0;
                System.out.print("\033[1A\033[K"); // Move up 1 line, clear to end of line
                System.out.print("|X|\n");
                break;
            }
        }
        System.out.print(ANSI_YELLOW);
        for (int i = 0; i < 4; i++) {
            System.out.print("|█|\n"); // Whitespace character (Will be color)
            System.out.flush();
            waitMilliseconds(millisecondSpeed); // 100ms per character
            if (enterPressed[0] && pressPhase[0] == -1) { // If it is pressed
                pressPhase[0] = 1;
                System.out.print("\033[1A\033[K"); // Move up 1 line, clear to end of line
                System.out.print("|X|\n");
                break;
            }
        }
        System.out.print(ANSI_GREEN);
        for (int i = 0; i < 2; i++) {
            System.out.print("|█|\n"); // Whitespace character (Will be color)
            System.out.flush();
            waitMilliseconds(millisecondSpeed); // 100ms per character
            if (enterPressed[0] && pressPhase[0] == -1) { // If it is pressed
                pressPhase[0] = 2;
                System.out.print("\033[1A\033[K"); // Move up 1 line, clear to end of line
                System.out.print("|X|\n");
                break;
            }
        }
        System.out.print(ANSI_BLUE);
        for (int i = 0; i < 1; i++) {
            System.out.print("|█|\n"); // Whitespace character (Will be color)
            System.out.flush();
            waitMilliseconds(millisecondSpeed); // 100ms per character
            if (enterPressed[0] && pressPhase[0] == -1) { // If it is pressed
                pressPhase[0] = 3;
                System.out.print("\033[1A\033[K"); // Move up 1 line, clear to end of line
                System.out.print("|X|\n");
                break;
            }
        }
        System.out.print(ANSI_GREEN);
        for (int i = 0; i < 2; i++) {
            System.out.print("|█|\n"); // Whitespace character (Will be color)
            System.out.flush();
            waitMilliseconds(millisecondSpeed); // 100ms per character
            if (enterPressed[0] && pressPhase[0] == -1) { // If it is pressed
                pressPhase[0] = 2;
                System.out.print("\033[1A\033[K"); // Move up 1 line, clear to end of line
                System.out.print("|X|\n");
                break;
            }
        }
        System.out.print(ANSI_YELLOW);
        for (int i = 0; i < 4; i++) {
            System.out.print("|█|\n"); // Whitespace character (Will be color)
            System.out.flush();
            waitMilliseconds(millisecondSpeed); // 100ms per character
            if (enterPressed[0] && pressPhase[0] == -1) { // If it is pressed
                pressPhase[0] = 1;
                System.out.print("\033[1A\033[K"); // Move up 1 line, clear to end of line
                System.out.print("|X|\n");
                break;
            }
        }
        System.out.print(ANSI_RED);
        for (int i = 0; i < 5; i++) {
            System.out.print("|█|\n"); // Whitespace character (Will be color)
            System.out.flush();
            waitMilliseconds(millisecondSpeed);
            if (enterPressed[0] && pressPhase[0] == -1) { // If it is pressed
                pressPhase[0] = 0;
                System.out.print("\033[1A\033[K"); // Move up 1 line, clear to end of line
                System.out.print("|X|\n");
                break;
            }
        }

        System.out.println(RESET);
        switch (pressPhase[0]) {
            case -1: // Didn't click
                typewrite(10, ANSI_GREY + "Miss!",false);
                multipler = 0.6f;
                break;
            case 0: // Red
                typewrite(10,ANSI_RED + "Yikes!",false);
                multipler = 0.8f;
                break;
            case 1: // Yellow
                typewrite(10,ANSI_YELLOW + "Meh.",false);
                multipler = 1f;
                break;
            case 2: // Green
                typewrite(10,ANSI_GREEN + "Hit!",false);
                multipler = 1.1f;
                break;
            case 3: // Blue
                typewrite(10,ANSI_CYAN+ "Brilliant!",false);
                multipler = 1.5f;
                break;
            default:
                multipler = .5f;
                break;
        }
        System.out.println();
        return multipler;
    }

}
