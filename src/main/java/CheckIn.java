import org.sql2o.*;
import java.util.List;
import java.sql.Timestamp;

public class CheckIn {
  private int id;
  private int restaurant_id;
  private int user_id;
  private int line_length;
  private Timestamp modified;


  public int getId() {
    return id;
  }

  public int getRestaurantId() {
    return restaurant_id;
  }

  public int getUserId() {
    return user_id;
  }

  public int getLineLength() {
    return line_length;
  }

  public Timestamp getModified() {
    return modified;
  }

  public CheckIn(int restaurant_id, int user_id, int line_length, Timestamp modified) {
    this.restaurant_id = restaurant_id;
    this.user_id = user_id;
    this.line_length = line_length;
    this.modified = modified;
  }


  public static List<CheckIn> all() {
    String sql = "SELECT id, restaurant_id, user_id, line_length, modified FROM check_ins";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(CheckIn.class);
    }
  }

  // @Override
  // public boolean equals(Object otherCheckIn) {
  //   if(!(otherCheckIn instanceof CheckIn)) {
  //     return false;
  //   } else {
  //     CheckIn newCheckin = (CheckIn) otherCheckIn;
  //     return
  //     this.getRestaurantId == newCheckin.getRestaurantId() &&
  //           this.getUserId == newCheckin.getUserId() &&
  //           this.getLineLength == newCheckin.getLineLength() &&
  //           this.getModified == newCheckin.getModified();
  //   }
  // }

  public static CheckIn find(int id) {
    String sql = "SELECT id, restaurant_id, user_id, line_length, modified FROM check_ins WHERE id = :id";
    try(Connection con = DB.sql2o.open()) {
      CheckIn checkins = con.createQuery(sql)
        .addParameter("id", id)
        .executeAndFetchFirst(CheckIn.class);
      return checkins;
    }
  }

  public Timestamp getTime() {
    String sql = "SELECT modified FROM check_ins WHERE restaurant_id = :id";
    try(Connection con = DB.sql2o.open()) {
      Timestamp timestamp = con.createQuery(sql)
        .addParameter("id", restaurant_id)
        .executeScalar(Timestamp.class);
      }

  }
}
