import org.junit.*;
import java.time.LocalDate;
import static org.junit.Assert.*;
import org.junit.Rule;
import java.util.ArrayList;
import java.util.List;

public class CheckInTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
    assertEquals(User.all().size(), 0);
  }

}
