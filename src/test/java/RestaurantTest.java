import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueifRestaurantsAreSame() {
    Restaurant firstRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202", 3);
    Restaurant secondRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202", 3);
    assertTrue(firstRestaurant.equals(secondRestaurant));

  }

  @Test
  public void save_restaurantToObject() {
    Restaurant newRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202", 3);
    newRestaurant.save();
    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertTrue(newRestaurant.equals(savedRestaurant));
  }

}
