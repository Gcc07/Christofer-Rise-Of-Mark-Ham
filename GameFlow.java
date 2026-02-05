import java.util.Scanner;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Dictionary;

public class GameFlow {
    public static Scanner input = new Scanner(System.in);
    // Display Game Title (Seperate Function)
        // Play
        // Settings
    // 
    public static void main(String[] args) {
        runGameLoop();
    }

    public static void displayTitle() {
        System.out.println("======================");
        System.out.println("CHRISTOFER: THE RISE OF MARK-HAM");
        System.out.printf("\t1. Play\n\t2. Settings\n");
    }
 
    public static void getIntInput() {
        
        try(Scanner input = new Scanner(System.in)) {
            System.out.println("Enter a valid int: ");
            input.nextInt();
        }

    }

    public static ArrayList<Dictionary> createPlayer(){
        Dictionary<String, Integer> softStat = new Hashtable<>(); // HP, MP, Flee Chance, 
        Dictionary<String, Integer> hardStat = new Hashtable<>(); // Life, Anger, Peace, Smartness, Finesse, Weapon, Name
        Dictionary<String, Integer> items = new Hashtable<>(); // All Items
        ArrayList<Dictionary> playerStats = new ArrayList<>(); // hard stats, soft stats, items

        playerStats.add(hardStat);
        playerStats.add(softStat);
        playerStats.add(items);
        
        return playerStats;
    }

    public static void runGameLoop() {
        displayTitle();
        ArrayList<Dictionary> player = createPlayer();
        System.out.println(player.toString());
    }

    public static int rollRandom(int min, int max) {
        int stat = (int) (Math.random() * (max - min + 1) + min);
        return stat;
    }
    
    public class createWeapon() {
        //N’s Odachi of the East - Samosas heals 200% more
        //Cursed Mouse - Either does 50% more or less damage (50 50 chance)
        //Evan’s Great Mace of Destruction - Highest Damaging Weapon in Game (scales with height)
        //Maxwell’s Spear of the Enraged - 20% more damage to Human enemies
        //Sixsev-un-chucks of Troy - always does a multiple of 6 or 7 damage
        //Karan’s Rageful Rapier - scales better with Anger
        //Phil’s Drumsticks
        //Katars of Humanity - Scales with peacefulness and anger
        //Unicycle 
        //A’s Javatana
        //Guatana
        //Sam’s Dagger
        //Mr. McCuen's Pearson Piercer
        //Mr. Gardner's Participation Point Piercer
        String[] weaponChoice = {"N’s Odachi of the East",}

    }
}    
    
