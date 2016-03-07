import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private String restaurant_name;
  private String phone;
  private String street;
  private String city;
  private String state;
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

  public String getState() {
    return state;
  }

  public String getZip() {
    return zip;
  }

  public int getLineAverage() {
    return line_average;
  }

  //********ADD NEW GETS HERE**************

  public Restaurant(String restaurant_name, String phone, String street, String city, String state, String zip, int line_average) {
    this.restaurant_name = restaurant_name;
    this.phone = phone;
    this.street = street;
    this.city = city;
    this.state = state;
    this.zip = zip;
    this.line_average = line_average;
      //********ADD NEW CATS HERE**************
  }

  public static List<Restaurant> all() {
    String sql = "SELECT id, restaurant_name, phone, street, city, state, zip, line_average FROM restaurants ORDER BY restaurant_name";  //********ADD NEW CATS HERE**************
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
             this.getState().equals(newRestaurant.getState()) &&
             this.getZip().equals(newRestaurant.getZip()) &&
             this.getLineAverage() == (newRestaurant.getLineAverage());
             //********ADD NEW CATS HERE**************
    }
  }

//CREATE

  public void save() {
    String sql = "INSERT INTO restaurants (restaurant_name, phone, street, city, state, zip, line_average) VALUES (:restaurant_name, :phone, :street, :city, :state, :zip, :line_average)";
    try(Connection con = DB.sql2o.open()) {
      this.id = (int) con.createQuery(sql, true)
          .addParameter("restaurant_name", restaurant_name)
          .addParameter("phone", phone)
          .addParameter("street", street)
          .addParameter("city", city)
          .addParameter("state", state)
          .addParameter("zip", zip)
          .addParameter("line_average", line_average)
          .executeUpdate()
          .getKey();
    }
  }

//READ

  public static Restaurant find(int id) {
    String sql = "SELECT id, restaurant_name, phone, street, city, state, zip, line_average FROM restaurants WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      Restaurant restaurants = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Restaurant.class);
      return restaurants;
    }
  }

//UPDATE

  public void update(String restaurant_name, String phone, String street, String city, String state, String zip, int line_average) {
    String sql = "UPDATE restaurants SET restaurant_name = :restaurant_name, phone = :phone, street = :street, city = :city, state = :state, zip = :zip, line_average = :line_average WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("restaurant_name", restaurant_name)
      .addParameter("phone", phone)
      .addParameter("street", street)
      .addParameter("city", city)
      .addParameter("state", state)
      .addParameter("zip", zip)
      .addParameter("line_average", line_average)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

//DESTROY

  public void delete() {
    String sql = "DELETE FROM restaurants WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public void deleteAll() {
    String deleteQuery = "DELETE FROM restaurants";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(deleteQuery)
      .executeUpdate();
    }
  }

  public void addUser(User user) {
      String sql = "INSERT INTO check-ins (restaurant_id, user_id, check_in, in_line) VALUES (:restaurant_id, :user_id, :check_in, :in_line)";
      try(Connection con =DB.sql2o.open()) {
        con.createQuery(sql)
          .addParameter("restaurant_id", this.getId)
          .addParameter("user_id", user.getId)
          .addParameter("check_in", check_in) //SHOULD THIS BE "user.getCheckIn"?
          .addParameter("in_line", in_line) //SHOULD THIS BE "user.getInLine"?
          .executeUpdate();
      }
  }

  public void getUsers() {
    String sql = "SELECT users.* FROM restaurants " +
                 "JOIN check_ins ON (restaurants.id = check_ins.restaurant_id) " +
                 "JOIN users ON (check-ins.user_id = users.id) " +
                 "WHERE restaurants.id = :id";

                 List<User> users = con.createQuery(sql)
                            .addParameter("id", id)
                            .executeAndFetch(User.class);
                            return users;
  }

}
