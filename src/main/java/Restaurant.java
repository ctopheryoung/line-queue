import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private String restaurant_name;
  private String phone;
  private String street;
  private String city;
  private String zip;
  private int line_average;
  //********ADD NEW CATS HERE**************

  public int getId() {
    return id;
  }

  public String getRestaurantName() {
    return restaurant_name;
  }

  public String getPhone() {
    return phone;
  }

  public String getStreet() {
    return street;
  }

  public String getCity() {
    return city;
  }

  public String getZip() {
    return zip;
  }

  public int getLineAverage() {
    return line_average;
  }

  //********ADD NEW GETS HERE**************

  public Restaurant(String restaurant_name) {
    this.restaurant_name = restaurant_name;
      //********ADD NEW CATS HERE**************
  }

  public static List<Restaurant> all() {
    String sql = "SELECT id, restaurant_name, user_id FROM restaurants ORDER BY restaurant_name";  //********ADD NEW CATS HERE**************
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  @Override
  public boolean equals(Object otherRestaurant) {
    if(!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getRestaurantName().equals(newRestaurant.getRestaurantName()) &&
             this.getPhone().equals(newRestaurant.getPhone()) &&
             this.getStreet().equals(newRestaurant.getStreet()) &&
             this.getCity().equals(newRestaurant.getCity()) &&
             this.getZip().equals(newRestaurant.getZip()) &&
             this.getLineAverage() == (newRestaurant.getLineAverage());
             //********ADD NEW CATS HERE**************
    }
  }

//CREATE

  public void save() {
    String sql = "INSERT INTO restaurants (restaurant_name, phone, street, city, zip, line_average) VALUES (:restaurant_name, :phone, :street, :city, :zip, :line_average)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("restaurant_name", restaurant_name)
          .addParameter("phone", phone)
          .addParameter("street", street)
          .addParameter("city", city)
          .addParameter("zip", zip)
          .addParameter("line_average", line_average)
          .executeUpdate()
          .getKey();
    }
  }


}
