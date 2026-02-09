public class MainTEST {
    
    public static void main(String[] args) {
        GameFlow.multiplierMiniGame(50);
        Room room = new Room("Battle", 1);
        
        System.out.println("ROOM: " + room.getRoomType() + " | Max: " + room.maximumNumOfItems + " | Min: " + room.minimumNumOfItems + " | Actual: " + room.amountOfItems);
        System.out.println(room.getItems());
        System.out.println("ROOM: " + room.getRoomType() + " | Max: " + room.maximumNumOfEnemies + " | Min: " + room.minimumNumOfEnemies + " | Actual: " + room.amountOfEnemies);
        System.out.println(room.getEnemies());
        System.out.println(room.calculateMaxEnemies());
        


    }
}
