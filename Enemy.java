import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Enemy {
    public static String[] enemyNames = {"Goblin", "Chiikawa", "Slime", "Skeleton", "Hell-Pig", "Nantuko"};
    public static String[] bossNames = {"Rith, the Awakener", "Adamaro, First to Desire", "Amzu, Swarm's Hunger", "Mr. McCuen, Secret Saboteur", "Final Form Gardner"};
    protected Dictionary<String, Integer> enemyStats;

    public Enemy(String name) {
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

    public static String returnRandomEnemy(int roomNum) { // Returns a random enemy name from the nameChoices list
        int index = (int) (Math.random() * (enemyNames.length));
        if (roomNum % 3 == 0){
            return bossNames[index];
        }
        else{
            return enemyNames[index];
        }
    }
}