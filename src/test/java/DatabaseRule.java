import org.junit.rules.ExternalResource;
import org.sql2o.*;

public class DatabaseRule extends ExternalResource {

  protected void before() {
    DB.sql2o = new Sql2o("jdbc:postgresql://localhost:5432/line_queue_test", null, null);
   }

  protected void after() {
    try(Connection con = DB.sql2o.open()) {
      String deleteRestaurantsQuerey = "DELETE FROM restaurants *;";
      String deleteUsersQuerey = "DELETE FROM users *;";
      String deleteCheckInsQuerey = "DELETE FROM check_ins *;";

      con.createQuery(deleteRestaurantsQuerey).executeUpdate();
      con.createQuery(deleteUsersQuerey).executeUpdate();      con.createQuery(deleteCheckInsQuerey).executeUpdate();
    }
  }
}
