import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class GameFlow {
    // Color codes for game
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String RESET = "\u001B[37m";
    
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

    public static void waitSeconds(int seconds) {
        try {
            Thread.sleep(seconds * 1000L);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

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

    public static final long typeSpeed = 20;
    public static void typewrite(String text) {
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


    public static void runGameLoop() {
        displayTitle();
        int playerDecision = getIntInput("Input: ");
        if (playerDecision == 1) {
            //intro();
            runCharacterCreation();
        }
        
    }

    public static void displayTitle() {
        System.out.println("================================");
        System.out.println(ANSI_CYAN + "CHRISTOFER: " + ANSI_PURPLE + "THE RISE OF MARK-HAM" + RESET);
        System.out.printf("\n1. Play\n2. Settings\n\n");
    }

    public static void intro() {
        waitSeconds(1);
        typewrite(ANSI_BLUE + "\nAFTER his fall at the hands of a legendary Moon Warrior, " 
        + ANSI_GREEN +"Chris-tofer, the Script-King, " 
        + ANSI_BLUE + " resigned into the Ruins of Be-Ta, where he ploted his return to power.");
        typewrite("Whispers, rumors, and half-forgotten tales suggest that the Legendary Conqueror Mark-Ham is, in truth, " + ANSI_GREEN + "Chris-tofer.");
        waitSeconds(1);
        typewrite(50, ANSI_BLUE + "You will be the one to stop his ascension", false);
        typewrite(500, "...", false);
        waitSeconds(1);
        typewrite(10, ANSI_YELLOW + " perchance. \n" + RESET, true);
        waitSeconds(4);
    }


    public static void runCharacterCreation() {
        String name = getStringInput("What is your name?");
        Dictionary<String, Integer> stats = new Hashtable<>(); // Life, Anger, Peace, Smartness, Finesse
        stats.put("Life", (Integer)rollRandom(1, 20));
        stats.put("Anger", (Integer)rollRandom(1, 20));
        stats.put("Peace", (Integer)rollRandom(1, 20));
        stats.put("Smartness", (Integer)rollRandom(1, 20));
        stats.put("Finesse", (Integer)rollRandom(1, 20));

        ArrayList<Item> items = new ArrayList<>(); // Storage spot for items and weapons
        items.add(new Weapon(Weapon.returnRandomWeapon()));
        
        Player player = new Player(name, stats, items);
        
        System.out.println(player.toString());
    }

    public static int rollRandom(int min, int max) {
        int stat = (int) (Math.random() * (max - min + 1) + min);
        return stat;
    }
    
}    
    