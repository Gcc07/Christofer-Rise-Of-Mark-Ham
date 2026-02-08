import java.util.ArrayList;

// This class is almost a combination of player and weapon. Since these don't have weapons, they just attack with their assigned attack value.
public class Enemy { 
    public static String[] enemyNames = {
    "Goblin", 
    "Chiikawa", 
    "Slime", 
    "Skeleton", 
    "Hell-Pig", 
    "Nantuko",
    };
    protected String name;
    protected String description;
    protected String inspectDescription; // Lowkey just CHECK from undertale
    protected String attackDescription; // _____ lunges forth!
    protected int currentHP;
    protected int maximumHP;
    protected int damage;
    protected float critChance;
    protected boolean isDead = false;
    protected ArrayList<Item> itemDrops;
    
    /** I added these for the enemy class creator so it's less confusing - Gabe
    @param name Name of enemy you are instantiating (use returnRandom for a random enemy (NOT INCLUDING BOSSES))
    @param roomNumber Provide the room number so enemy power properly scales
    **/
    public Enemy(String name, int roomNumber) {
        this.name = name;
        this.description = "An enemy.";
        this.inspectDescription = "A foe just like any other foe. Take them down.";
        this.attackDescription = "lunges forth";
        this.itemDrops = new ArrayList<Item>();
        this.critChance = .1f; // 10% crit chance.
        switch (name) {
            case "Goblin" :
                this.inspectDescription = "A devilish, cackling little creature. It clearly hasn't been introduced to the idea of a bath.";
                this.maximumHP = 100;
                this.currentHP = maximumHP;
                this.damage = 20;
            case "Chiikawa":
                this.inspectDescription = "If it weren't in your way, you'd be brave enought to pet it.";
                this.maximumHP = 150;
                this.currentHP = maximumHP;
                this.damage = 40;
            case "Slime":
                this.inspectDescription = "It's a slimy little thing. Nothing more, nothing less.";
                this.attackDescription = "oobles towards you";
                this.maximumHP = 80;
                this.currentHP = maximumHP;
                this.damage = 10;
            case "Skeleton":
                this.inspectDescription = "Evan The Destroyer is known for taking these down. You can too. (Beware, it's armed. It could be more damaging than expected...)";
                this.maximumHP = 50;
                this.currentHP = maximumHP;
                this.damage = 75;
            case "Hell-Pig":
                this.inspectDescription = "A hellish sight, it's been mottled and mamed by who-knows what.";
                this.attackDescription = "flails it's arms";
                this.maximumHP = 300;
                this.currentHP = maximumHP;
                this.damage = 12;
            case "Nantuko":
                this.inspectDescription = "What in Earlycolegia even is this thing? It's mean and green.";
                this.attackDescription = "casts a damaging spell";
                this.maximumHP = 150;
                this.currentHP = maximumHP;
                this.damage = 40;
        }
    }
    public static String returnRandom() { // Returns a random enemy name from the enemyNames list
        int index = (int) (Math.random() * (enemyNames.length));
        return enemyNames[index];
    }

    public String getName() { 
        return name;
    }
    
    public String getDescription() {
        return description;
    }
}