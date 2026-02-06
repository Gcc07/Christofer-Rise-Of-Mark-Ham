public class Item {
    protected String name;
    protected String description;
    public static String[] itemTypes = {"Karim's Homework", "Evan's Homework", "Samosas", "College Deferral", "Tiny Zingbah",
    "Spotted Mushroom", "Withheld Toothbrush", "Worn Football", "Chess Pawn", "Killer Bunny", "FISH!"};

    public Item(String itemTyp) {
        
    }

    public String toString() {
        return "\nItem: " + name + "\nDescription: " + description;
    }
    
    public static String returnRandomItem() {
        int index = (int) (Math.random() * (itemTypes.length));
        return itemTypes[index];   
    }
}
