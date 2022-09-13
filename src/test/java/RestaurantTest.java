import java.util.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class RestaurantTest {

    //REFACTOR ALL THE REPEATED LINES OF CODE
    //date, files, network calls - Mockito can be used in these cases to mock these attributes
    Restaurant restaurant = Mockito.mock(Restaurant.class);
    String name = "Bawarchi";
    String location = "Hyderabad";

    LocalTime openingTime = LocalTime.parse("10:30:00");
    LocalTime closingTime = LocalTime.parse("22:00:00");

    //>>>>>>>>>>>>>>>>>>>>>>>>>OPEN/CLOSED<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    //-------FOR THE 2 TESTS BELOW, YOU MAY USE THE CONCEPT OF MOCKING, IF YOU RUN INTO ANY TROUBLE
    @Test
    public void is_restaurant_open_should_return_true_if_time_is_between_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE

        LocalTime openTime = LocalTime.now().minusHours(3);
        LocalTime closeTime = LocalTime.now().plusHours(9);


        Mockito.when(restaurant.openingTime).thenReturn(openTime);
        Mockito.when(restaurant.closingTime).thenReturn(closeTime);
        Mockito.when(restaurant.getName()).thenReturn(name);
        Mockito.when(restaurant.getLocation()).thenReturn(location);

        assertTrue(restaurant.isRestaurantOpen());



    }

    @Test
    public void is_restaurant_open_should_return_false_if_time_is_outside_opening_and_closing_time(){
        //WRITE UNIT TEST CASE HERE


        LocalTime openTime = LocalTime.now().plusHours(4);
        LocalTime closeTime = LocalTime.now().plusHours(-2);


        Mockito.when(restaurant.openingTime).thenReturn(openTime);
        Mockito.when(restaurant.closingTime).thenReturn(closeTime);
        Mockito.when(restaurant.getName()).thenReturn(name);
        Mockito.when(restaurant.getLocation()).thenReturn(location);

        assertFalse(restaurant.isRestaurantOpen());

    }

    //<<<<<<<<<<<<<<<<<<<<<<<<<OPEN/CLOSED>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


    //>>>>>>>>>>>>>>>>>>>>>>>>>>>MENU<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<<
    @Test
    public void adding_item_to_menu_should_increase_menu_size_by_1(){
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.addToMenu("Sizzling brownie",319);
        assertEquals(initialMenuSize+1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_from_menu_should_decrease_menu_size_by_1() throws itemNotFoundException {
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        int initialMenuSize = restaurant.getMenu().size();
        restaurant.removeFromMenu("Vegetable lasagne");
        assertEquals(initialMenuSize-1,restaurant.getMenu().size());
    }
    @Test
    public void removing_item_that_does_not_exist_should_throw_exception() {
        restaurant =new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);
        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);

        assertThrows(itemNotFoundException.class,
                ()->restaurant.removeFromMenu("French fries"));
    }
    //<<<<<<<<<<<<<<<<<<<<<<<MENU>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    //<<<<<<<<<<<<<<<<<<<<<<<BILL/TOTAL>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

    // parameters to be used are name of the item and the price of each item to be totalled,
    // we total the cost of the order, start with writing the test case that fails
    // and write the respective methods, then ensure test passes
    // - writing the failing test case here now
    @Test

    public void when_item_names_are_passed_it_should_give_total_cost_of_order(){
        //Arrange -
        // Arrange part of the TDD - arranging the menu and the items in it,
        // then we'll get the items customer has chosen to order
        // then we'll get the total cost of the order for chosen items

        restaurant = new Restaurant("Amelie's cafe","Chennai",openingTime,closingTime);

        List<String> orderItems = new ArrayList<>();



        //Act - using the methods to get items and order total, one method is used below
        // and another is used directly during the assertion part of the TDD which is 'orderTotal'.

        restaurant.addToMenu("Sweet corn soup",119);
        restaurant.addToMenu("Vegetable lasagne", 269);
        restaurant.addToMenu("Sizzling brownie",319);
        restaurant.addToMenu("Biryani",350);
        restaurant.addToMenu("dish5",250);
        restaurant.addToMenu("dish6",209);


        restaurant.orderItems("Sweet corn soup");
        restaurant.orderItems("dish5");
        restaurant.orderItems("dish6");

        //Assert - Asserting that the expected order total is equal to actual order total value
        assertEquals(578, restaurant.displayOrderTotal(orderItems));






    }

}