import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class GameFlow {
    // Display Game Title (Seperate Function)
        // Play
        // Settings
    // 
    public static void main(String[] args) {
        runGameLoop();
    }

    public static void runGameLoop() {
        displayTitle();
        int playerDecision = getIntInput("Input: ");
        if (playerDecision == 1) {
            runCharacterCreation();
        }

    }

    public static void displayTitle() {
        System.out.println("================================");
        System.out.println("CHRISTOFER: THE RISE OF MARK-HAM");
        System.out.printf("\n1. Play\n2. Settings\n\n");
    }
 
    private static final Scanner input = new Scanner(System.in);
    public static int getIntInput(String prompt) {
        System.out.print(prompt);
        try {
            return input.nextInt();
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

    public static void runCharacterCreation() {
        String name = getStringInput("Who are you?");

        Dictionary<String, Integer> stats = new Hashtable<>(); // Life, Anger, Peace, Smartness, Finesse
        stats.put("Life", (Integer)rollRandom(1, 20));
        stats.put("Anger", (Integer)rollRandom(1, 20));
        stats.put("Peace", (Integer)rollRandom(1, 20));
        stats.put("Smartness", (Integer)rollRandom(1, 20));
        stats.put("Finesse", (Integer)rollRandom(1, 20));

        ArrayList<Item> items = new ArrayList<>(); // Storage spot for items and weapons
        items.add(new Weapon(Weapon.chooseRandomWeapon()));
        
        Player player = new Player(name, stats, items);
        
        System.out.println(player.toString());
    }

    public static int rollRandom(int min, int max) {
        int stat = (int) (Math.random() * (max - min + 1) + min);
        return stat;
    }
    
}    
    
