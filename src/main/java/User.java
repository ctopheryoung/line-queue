import java.util.List;
import java.util.ArrayList;
import java.sql.Timestamp;
import org.sql2o.*;

public class User {
  private int id;
  private String user_name;
  private String password;

  // private String permission;
  private int score = 0;

  //CONSTRUCTOR//
  public User(String user_name, String password) {
    this.user_name = user_name;
    this.password = password;


  } //PUT IN CONSTRUCTOR: , String permission
    //    this.permission = permission;

  //GETTERS//
  public String getUserName() {
    return user_name;
  }

  public String getPassword() {
    return password;
  }

  public int getId() {
    return id;
  }

  public int getScore() {
    return score;
  }



  // public Timestamp getRightNow() {
  //   Date date = new Date();
  //   long time = date.getTime();
  //   Timestamp right_now = new Timestamp(time);
  //   return right_now;
  // }

  // public String getPermission() {
  //   return permission;
  // }



  @Override
  public boolean equals(Object otherUser){
    if (!(otherUser instanceof User)) {
      return false;
    } else {
      User newUser = (User) otherUser;
      return this.getUserName().equals(newUser.getUserName());
    }
  }

  //CREATE//
  public void save() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO users(user_name, password) VALUES (:user_name, :password)";
      this.id = (int) con.createQuery(sql,true)
      .addParameter("user_name", this.user_name)
      .addParameter("password", this.password)
      // .addParameter("permission", this.permission)
      //, permission, :permission
      .executeUpdate()
      .getKey();
    }
  }

  //READ//
  public static List<User> all() {
    String sql = "SELECT id, user_name, password FROM users";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(User.class);
    }
  }

  public static User find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id, password FROM Users where id=:id";
      User user = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(User.class);
      return user;
    }
  }

  public List<Restaurant> getRestaurants() {
    String sql = "SELECT restaurants.* FROM users JOIN check_ins ON (users.id = check_ins.user_id) JOIN restaurants ON (check_ins.restaurant_id = restaurants.id) WHERE users.id = :user_id";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .addParameter("user_id", id)
      .executeAndFetch(Restaurant.class);
    }
  }

  public static User login(String username) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT id, user_name, password FROM users WHERE user_name = :username";
      return con.createQuery(sql)
        .addParameter("username", username)
        .executeAndFetchFirst(User.class);
    }
  }

  //UPDATE//
  public void update(String newUserName, String newPassword) {
    this.user_name = newUserName;
    this.password = newPassword;
    String sql = "UPDATE users SET user_name = :newUserName AND password = :newPassword WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("newUserName", newUserName)
      .addParameter("newPassword", newPassword)
      .addParameter("id", this.id)
      .executeUpdate();
    }
  }

  public void updatePermission(String newPermission) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE users SET permission = :newPermission WHERE id = :id";
      con.createQuery(sql).addParameter("newPermission", newPermission).addParameter("id", id).executeUpdate();
    }
  }

  public void addRestaurant(Restaurant restaurant) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO check_ins (restaurant_id, user_id, line_length) VALUES (:restaurant_id, :user_id, :line_length)";
      con.createQuery(sql)
      .addParameter("restaurant_id", restaurant.getId())
      .addParameter("user_id", this.getId())
      .addParameter("line_length", 3)
      .executeUpdate();
    }
  }

  public void assignRestaurant(int restaurant_id) {
    restaurant_id = restaurant_id;
    String sql = "UPDATE check_ins SET restaurant_id = :restaurant_id WHERE user_id=:user_id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
         .addParameter("restaurant_id", restaurant_id)
         .addParameter("id", this.id)
         .executeUpdate();
    }
  }

  //DESTROY//
  public void delete() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "DELETE FROM users WHERE id = :id;";
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();

      String check_insQuery = "DELETE FROM check_ins WHERE user_id = :userId";
      con.createQuery(check_insQuery)
      .addParameter("userId", this.getId())
      .executeUpdate();
    }
  }
}
