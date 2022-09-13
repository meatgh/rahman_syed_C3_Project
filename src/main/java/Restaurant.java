import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Restaurant {
    private String name;
    private String location;
    public LocalTime openingTime;
    public LocalTime closingTime;
    private List<Item> menu = new ArrayList<Item>();

    public Restaurant(String name, String location, LocalTime openingTime, LocalTime closingTime) {
        this.name = name;
        this.location = location;
        this.openingTime = openingTime;
        this.closingTime = closingTime;
    }

    public boolean isRestaurantOpen() {

        //DELETE ABOVE STATEMENT AND WRITE CODE HERE
        int open = getCurrentTime().compareTo(openingTime);
        int close = getCurrentTime().compareTo(closingTime);

        boolean isOpen = true;
        if (open > 0 && close < 0)
            isOpen = true;
        else
            isOpen = false;

        return isOpen;



    }

    public LocalTime getCurrentTime(){ return  LocalTime.now(); }

    public List<Item> getMenu() {
        //DELETE ABOVE RETURN STATEMENT AND WRITE CODE HERE
        return Collections.unmodifiableList(menu);
    }

    private Item findItemByName(String itemName){
        Item itemBeingLookedFor = null;
        for(Item item: menu) {
            if(item.getName().equals(itemName))
                itemBeingLookedFor = item;
            break;
        }
        return itemBeingLookedFor;
    }

    public void addToMenu(String name, int price) {
        Item newItem = new Item(name,price);
        menu.add(newItem);
    }
    
    public void removeFromMenu(String itemName) throws itemNotFoundException {

        Item itemToBeRemoved = findItemByName(itemName);
        if (itemToBeRemoved == null)
            throw new itemNotFoundException(itemName);

        menu.remove(itemToBeRemoved);
    }
    public void displayDetails(){
        System.out.println("Restaurant:"+ name + "\n"
                +"Location:"+ location + "\n"
                +"Opening time:"+ openingTime +"\n"
                +"Closing time:"+ closingTime +"\n"
                +"Menu:"+"\n"+getMenu());

    }

    public String getName() {
        return name;
    }

    public String getLocation() {
        return location;
    }
}
