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
    
    public static ArrayList<Room> Dungeon;
    // Scanner Input methods

    private static final Scanner input = new Scanner(System.in);
    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            int value = input.nextInt();
            input.nextLine(); // Consume the leftover newline
            return value;
        } catch (Exception e) {
            System.out.println("This is an invalid input. Try again.");
            input.nextLine();
            return getIntInput(prompt);
        }
    }
    public static String getStringInput(String prompt) {
        System.out.print(prompt);
        try {
            return input.nextLine();
        } catch (Exception e) {
            System.out.println("This is an invalid input. Try again.");
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
            displayIntro();
            runCharacterCreation();
        }
        Dungeon = createDungeon(10);
        enterDungeon(Dungeon);
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


    public static void runCharacterCreation() {
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
        
        typewrite(1, player.toString(), true);
    }

    public static int rollRandom(int min, int max) {
        int stat = (int) (Math.random() * (max - min + 1) + min);
        return stat;
    }
    
    public static void enterDungeon(ArrayList<Room> Dungeon) {
        for (Object room : Dungeon) {
            
        }
    }

    public static ArrayList<Room> createDungeon(int numOfRooms) {
        ArrayList<Room> tempDungeon = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            tempDungeon.add(new Room(Room.roomTypes[0], i));
        }
        return tempDungeon;
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
            typewrite("False.");
            typewrite("Your name is...");
            waitSeconds(1);
            name = getRandomName();
            typewrite(10, name, true);
            return name;
        }
        else {
            typewrite("You do not choose who you are.");
            waitSeconds(1);
            typewrite("Not here, ");
            waitSeconds(1);
            name = getRandomName();
            typewrite(10, name, true);
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
            "Ptahtell", "Tapel", "Violence", "Thorntin", "Gojo",
            "Thun", "Jack", "Dubmin", "Thitay", "Kal-Fust",
            "Frost", "Oobah", "Bah", "Starless", "Moon",
            "Java", "Stone", "Windowspider", "Midterm", "Final",
        };
        int fIndex = (int) (Math.random() * (firstNames.length));
        int lIndex = (int) (Math.random() * (lastNames.length));
        return firstNames[fIndex] + " " + lastNames[lIndex];
    }
}    
    