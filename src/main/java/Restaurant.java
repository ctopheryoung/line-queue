import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private String restaurant_name;
  private int user_id;

  public int getId() {
    return id;
  }

  public String getRestaurantName() {
    return restaurant_name;
  }

  public int getUserId() {
    return user_id;
  }

  public Restaurant(String restaurant_name, int user_id) {
    this.restaurant_name = restaurant_name;
    this. user_id = user_id;
  }

  public static List<Restaurant> all() {
    String sql = "SELECT id, restaurant_name, user_id FROM restaurants ORDER BY restaurant_name";
  }

  @Override
  public boolean equals(Object otherRestaurant) {
    if(!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getRestaurantName().equals(newRestaurant.getRestaurantName()) &&
             this.getUserId() == newRestaurant.getUserId();
    }
  }

}
