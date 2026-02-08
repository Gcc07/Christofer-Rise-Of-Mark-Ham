import java.util.Dictionary;
import java.util.Hashtable;

public class Enemy {
    public static String[] enemyNames = {"Goblin", "Chiikawa", "Slime", "Skeleton", "Hell-Pig", "Nantuko"};
    protected Dictionary<String, Integer> enemyStats;
    protected String name;

    /** I added these for the enemy class creator so it's less confusing - Gabe
    @param name Name of enemy you are instantiating (use returnRandom for a random enemy (NOT INCLUDING BOSSES))
    @param roomNumber Provide the room number so enemy power properly scales
    **/
    public Enemy(String name, int roomNumber) {
        this.name = name;
        this.enemyStats = new Hashtable<>();
        switch (name) {
            case "Goblin" :
                enemyStats.put("Life", 200);
                enemyStats.put("Damage", 15);
            case "Chiikawa":
                enemyStats.put("Life", 350);
                enemyStats.put("Damage", 30); 
            case "Slime":
                enemyStats.put("Life", 350);
                enemyStats.put("Damage", 30);
            case "Skeleton":
                enemyStats.put("Life", 350);
                enemyStats.put("Damage", 30); 
            case "Hell-Pig":
                enemyStats.put("Life", 350);
                enemyStats.put("Damage", 30); 
            case "Nantuko":
                enemyStats.put("Life", 350);
                enemyStats.put("Damage", 30); 
        }
    }
    public static String returnRandom() { // Returns a random enemy name from the enemyNames list
        int index = (int) (Math.random() * (enemyNames.length));
        return enemyNames[index];
    }

    public String getName() { 
        return name;
    }
}