import org.junit.*;
import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.ArrayList;
import java.util.List;
import java.sql.Timestamp;

public class UserTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(User.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    User firstUser = new User("Bill", "1234");
    User secondUser = new User("Bill", "1234");
    assertTrue(firstUser.equals(secondUser));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    User myUser = new User("Bill", "1234");
    myUser.save();
    assertTrue(User.all().get(0).equals(myUser));
  }

  // @Test
  // public void find_findUserInDatabase_true() {
  //   User myUser = new User("Bill", "1234");
  //   myUser.save();
  //   User savedUser = User.find(myUser.getId());
  //   assertTrue(myUser.equals(savedUser));
  // }

  @Test
  public void all_returnsAllInstancesOfUser_true() {
    User firstUser = new User("Bill", "1234");
    User secondUser = new User("Ted", "1235");
    firstUser.save();
    secondUser.save();
    assertTrue(User.all().contains(firstUser));
    assertTrue(User.all().contains(secondUser));
  }
}
