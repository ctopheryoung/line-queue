import java.util.List;
import java.util.ArrayList;
import org.sql2o.*;

public class User {
  private int id;
  private String user_name;

  //CONSTRUCTOR//
  public User(String user_name) {
    this.user_name = user_name;
  }

  //GETTERS//
  public String getUserName() {
    return user_name;
  }

  public int getId() {
    return id;
  }

  @Override
  public boolean equals(Object otherUser){
    if (!(otherUser instanceof User)) {
      return false;
    } else {
      User newUser = (User) otherUser;
      return this.getUserName().equals(newUser.getUserName());
    }
  }

  //READ//
  public static List<User> all() {
    String sql = "SELECT id, user_name FROM users";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql)
      .executeAndFetch(User.class);
    }
  }
}
