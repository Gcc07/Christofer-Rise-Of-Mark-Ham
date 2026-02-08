import java.util.Dictionary;
import java.util.Hashtable;

public class Boss extends Enemy {
    public static String[] bossNames = {"Rith, the Awakener", "Adamaro, First to Desire", "Amzu, Swarm's Hunger", "Mr. McCuen, Secret Saboteur", "Final Form Gardner"};
    protected Dictionary<String, Integer> enemyStats;

    /** Boss version of Enemy
    @param name Name of enemy you are instantiating (use returnRandom for a random boss)
    @param roomNumber Provide the room number so enemy power properly scales
    **/
    public Boss(String name, int roomNumber) {
        super(name, roomNumber);
        this.enemyStats = new Hashtable<>();
        switch (name) {
            case "Rith, the Awakener" :
                enemyStats.put("Life", 200);
                enemyStats.put("Damage", 15);
            case "Adamaro, First to Desire":
                enemyStats.put("Life", 350);
                enemyStats.put("Damage", 30); 
            case "Amzu, Swarm's Hunger":
                enemyStats.put("Life", 350);
                enemyStats.put("Damage", 30);
            case "Final Form Gardner":
                enemyStats.put("Life", 350);
                enemyStats.put("Damage", 30); 
              
        }
    }
    public static String returnRandom() { // Returns a random enemy name from the nameChoices list
        int index = (int) (Math.random() * (bossNames.length));
        return bossNames[index];
    }
}