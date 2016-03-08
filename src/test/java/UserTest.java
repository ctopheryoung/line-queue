import org.junit.*;
import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.ArrayList;
import java.util.List;

public class UserTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(User.all().size(), 0);
  }

  @Test
  public void equals_returnsTrueIfNamesAreTheSame() {
    User firstUser = new User("Bill", "password", "permission", "password", "permission");
    User secondUser = new User("Bill", "password", "permission");
    assertTrue(firstUser.equals(secondUser));
  }

  @Test
  public void save_savesIntoDatabase_true() {
    User myUser = new User("Bill", "password", "permission");
    myUser.save();
    assertTrue(User.all().get(0).equals(myUser));
  }

  @Test
  public void find_findUserInDatabase_true() {
    User myUser = new User("Bill", "password", "permission");
    myUser.save();
    User savedUser = User.find(myUser.getId());
    assertTrue(myUser.equals(savedUser));
  }

  @Test
  public void all_returnsAllInstancesOfUser_true() {
    User firstUser = new User("Bill", "password", "permission");
    User secondUser = new User("Ted", "password", "permission");
    firstUser.save();
    secondUser.save();
    assertTrue(User.all().contains(firstUser));
    assertTrue(User.all().contains(secondUser));
  }
}
