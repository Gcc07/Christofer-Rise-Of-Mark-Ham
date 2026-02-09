import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;

public class Boss extends Enemy {
    public static String[] bossNames = {"Rith, the Awakener", "Adamaro, First to Desire", "Amzu, Swarm's Hunger", "Mr. McCuen, Secret Saboteur", "Final Form Gardner"};
    protected Dictionary<String, Integer> enemyStats;

    /** Boss version of Enemy
    @param name Name of enemy you are instantiating (use returnRandom for a random boss)
    @param roomNumber Provide the room number so enemy power properly scales
    **/
    public Boss (String name, int roomNumber) {
        super(name, roomNumber);
        this.name = name;
        this.description = "An enemy.";
        this.inspectDescription = "A foe just like any other foe. Take them down.";
        this.attackDescription = "lunges forth at";
        this.itemDrops = new ArrayList<Item>();
        this.critChance = .1f; // 10% crit chance.
        this.critMultiplier = 1.2f;
        this.markPointsValue = 50;
        
        this.enemyStats = new Hashtable<>();
        switch (name) {
            case "Rith, the Awakener" :
                this.inspectDescription = "A lich-like, skeletal dragon with the power to harness the undead and torrents of hellfire.";
                this.description = "A dragon of your undoing.";
                this.attackDescription = "Sprays you with hellfire";
                this.maximumHP = 400;
                this.currentHP = maximumHP;
                this.damage = 40;
                this.critChance = .4f; 
                this.critMultiplier = 1.35f;
                this.markPointsValue = 200;

            case "Adamaro, First to Desire":
                this.inspectDescription = "A powerful demon from the lower rungs of hell. He's known for his devilish deals and terrible magic.";
                this.description = "A demon of great legend.";
                this.attackDescription = "Shoots tendrils of black magic at you";
                this.maximumHP = 600;
                this.currentHP = maximumHP;
                this.damage = 60;
                this.critChance = .4f; 
                this.critMultiplier = 1.4f;
                this.markPointsValue = 300;

            case "Amzu, Swarm's Hunger":
                this.inspectDescription = "A simple nantuko transformed by the power of the dungeon core itself. Though he gained great power and rose to be the leader of the nantuko he lost much of his sanity and composure.";
                this.description = "The tyrant leader of the Nantuko.";
                this.attackDescription = "Strikes you from the air";
                this.maximumHP = 800;
                this.currentHP = maximumHP;
                this.damage = 80;
                this.critChance = .45f; 
                this.critMultiplier = 1.45f;
                this.markPointsValue = 400;

            case "Final Form Gardner":
                this.inspectDescription = "THE ULTIMATE BEING. NO BEING BEFORE HOLDS A CANDLE TO HIS POWER. Except maybe you...";
                this.description = "God Gardner's final form. Just give up and accept your demise.";
                this.attackDescription = "Politely asks you to not ";
                this.maximumHP = 1400;
                this.currentHP = maximumHP;
                this.damage = 140;
                this.critChance = .8f; 
                this.critMultiplier = 1.6f;
                this.markPointsValue = 1000;
              
        }
    }
    public static String returnRandom() { // Returns a random enemy name from the nameChoices list
        int index = (int) (Math.random() * (bossNames.length));
        return bossNames[index];
    }
}