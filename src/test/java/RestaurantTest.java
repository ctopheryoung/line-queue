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
    Restaurant firstRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202");
    Restaurant secondRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202");
    assertTrue(firstRestaurant.equals(secondRestaurant));

  }

  @Test
  public void save_restaurantToObject() {
    Restaurant newRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202");
    newRestaurant.save();
    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertTrue(newRestaurant.equals(savedRestaurant));
  }

  @Test
  public void save_assignsIDToObject() {
    Restaurant newRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202");
    newRestaurant.save();
    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertEquals(newRestaurant.getId(), savedRestaurant.getId());
  }

  @Test
  public void find_locatesAllRestaurantsInDB() {
    Restaurant newRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202");
    newRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(newRestaurant.getId());
    assertTrue(newRestaurant.equals(savedRestaurant));
  }

  @Test
  public void update_updatesRestaurantInDB() {
    Restaurant newRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202");
    newRestaurant.save();
    newRestaurant.update("Marginal Hotcake House", "555-6789", "4534 NW Main St.", "Bangor", "ME", "04401", 4);

    assertTrue(Restaurant.all().get(0).getRestaurantName().equals("Marginal Hotcake House"));
    assertTrue(Restaurant.all().get(0).getPhone().equals("555-6789"));
    assertTrue(Restaurant.all().get(0).getStreet().equals("4534 NW Main St."));
    assertTrue(Restaurant.all().get(0).getCity().equals("Bangor"));
    assertTrue(Restaurant.all().get(0).getState().equals("ME"));
    assertTrue(Restaurant.all().get(0).getZip().equals("04401"));
    assertEquals(Restaurant.all().get(0).getLineAverage(), (4));
  }

  @Test
  public void delete_deletesRestaurantFromDB() {
    Restaurant newRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202");
    newRestaurant.save();
    newRestaurant.delete();
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void deleteAll_deletesAllRestaurantsFromDB() {
    Restaurant firstRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202");
    firstRestaurant.save();
    Restaurant secondRestaurant = new Restaurant("Marginal Hotcake House", "555-6789", "4534 NW Main St.", "Bangor", "ME", "04401");
    secondRestaurant.save();
    secondRestaurant.deleteAll();
    assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void addUser_addsUserToRestaurant() {
    Restaurant newRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202");
    newRestaurant.save();

    User newUser = new User("Stan");
    newUser.save();

    newRestaurant.addUser(newUser);
    User savedUser = newRestaurant.getUsers().get(0);
    assertTrue(newUser.equals(savedUser));
  }

  // @Test
  // public void getUsers_getsAllUsersAssociatedWithOneRestaurant() {
  //   Restaurant newRestaurant = new Restaurant("Original Hotcake House", "555-5555", "1010 SE Powell Rd.", "Portland", "OR", "97202");
  //   newRestaurant.save();
  //
  //   User newUser = new User("Stan");
  //   newUser.save();
  //
  //   newRestaurant.addUser(newUser);
  //   List savedUsers = newRestaurant.getUsers();
  //   assertEquals(savedUsers.size(), 1);
  // }

}
