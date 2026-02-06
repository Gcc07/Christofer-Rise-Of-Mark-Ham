import java.util.Dictionary;

public class Weapon extends Item{
    protected int damage;
    protected float critChance;
    protected Dictionary<String, Item> items;
    public static String[] nameChoices = {"N’s Odachi of the East", "Cursed Mouse", "Evan’s Great Mace of Destruction", "Maxwell’s Spear of the Enraged", "Sixsev-un-chucks of Troy"};
    public Weapon(String name) { 
        this.name = name;
        switch(name) { // I used claude to create the switch cases after I made one singular case. - GABE
            // Also, I wrote all of the item descriptions.
            case "N's Odachi of the East":
                this.damage = 45;
                this.critChance = 0.15f;
                this.description = "An eastern artifact once wielded by a great warrior. It has equations engraved into it's hilt";
                // Special: Samosas heal 200% more
                break;
                
            case "Cursed Mouse":
                this.damage = 30;
                this.critChance = 0.10f;
                this.description = "A weapon forged of plasticite, it periodically phases in and out of reality.";
                // Special: 50/50 chance for 50% more or less damage
                break;
                
            case "Evan's Great Mace of Destruction":
                this.damage = 100;
                this.critChance = 0.05f;
                this.description = "It is said in legend; there is only one man, harking from the lands of min-craftia, who might wield this tool of destruction to it's fullest potential.";
                // Special: Highest damage, scales with height
                break;
                
            case "Maxwell's Spear of the Enraged":
                this.damage = 50;
                this.critChance = 0.20f;
                this.description = "Rage pulsates throughout this spear. You feel angry simply wielding it.";
                // Special: 20% more damage to Human enemies
                break;
                
            case "Sixsev-un-chucks of Troy":
                this.damage = 42; // Multiple of 6 and 7
                this.critChance = 0.12f;
                this.description = "A six-inch link of pure iron chained two a seven-inch hilt of gold. You feel indecisive in it's presence.";
                // Special: Always does multiple of 6 or 7 damage
                break;
                
            case "Karan's Quiet Rapier":
                this.damage = 40;
                this.critChance = 0.18f;
                this.description = "A rapier imbued with an calm presence. Silence fills the room when holding it in your hand.";
                // Special: Scales better with Anger
                break;
                
            case "Phil's 'sticks":
                this.damage = 35;
                this.critChance = 0.15f;
                this.description = "Wooden armaments meant two be dual wielded, clearly with the purpose of causing destruction in the air tonight.";
                break;
                
            case "Katars of Humanity":
                this.damage = 38;
                this.critChance = 0.14f;
                this.description = "Eastern Weapons fitted to ones palm, unmistakeabily drawing power from a balance of rage and calm.";
                // Special: Scales with peacefulness and anger
                break;
                
            case "Unicycle":
                this.damage = 25;
                this.critChance = 0.25f;
                this.description = "A weapon, perhaps from another time... It grants you speed untold.";
                break;
                
            case "Gahaurtana":
                this.damage = 52;
                this.critChance = 0.13f;
                this.description = "A simple blade with the engraving 'Bills <3' etched into the hilt";
                break;
                
            case "Sam's Dagger":
                this.damage = 20;
                this.critChance = 0.5f;
                this.description = "A dagger of unknown orgin. It has a tag hanging off to the side, proclaiming 'sam'";
                break;
                
            case "Mr. McCuen's Pearson Piercer":
                this.damage = 55;
                this.critChance = 0.11f;
                this.description = "A terrifying thing. A horrible thing. A thing wielded by a man whom yielded to no-one; yet he could not fight...\nPearson.";
                break;
                
            case "Mr. Gardner's Participation Point Piercer":
                this.damage = 28;
                this.critChance = 0.30f;
                this.description = "The lands of Earlycolegia only exist because of the Whim of Mr. Gardner. His defeat at the hands of the moon warrior was fate, yet he did not die.\nThis weapon is proof of that.";
                break;
                
            default:
                this.damage = 10;
                this.critChance = 0.05f;
                this.description = "BASE WEAPON";
                break;
        }
    }
    public static String chooseRandomWeapon() {
        int index = (int) (Math.random() * (nameChoices.length));
        return nameChoices[index];
    }

    public String toString() {
        return name + "\n" + description;
    }
}
