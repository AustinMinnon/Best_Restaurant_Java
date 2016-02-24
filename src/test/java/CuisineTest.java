import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;

public class CuisineTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Cuisine.all().size(), 0);
  }

  @Test
  public void save_savesIntoDatabase_true() {
    Cuisine myCuisine = new Cuisine(1,"Chinese");
    myCuisine.save();
    assertEquals(Cuisine.all().get(0).getType(), "Chinese");
  }

  @Test
  public void update_Cuisine_true() {
    Cuisine testCuisine = new Cuisine(1,"mexican");
    testCuisine.save();
    testCuisine.update("American");
    Cuisine savedCuisine = Cuisine.all().get(0);
    //savedCuisine.save();
    assertEquals(savedCuisine.getType(), "American");
  }

  @Test
  public void Cuisine_deletes_true() {
    Cuisine myCuisine = new Cuisine(1, "mexican");
    myCuisine.deleteCuisine(0);
    assertTrue(Cuisine.find(0) == null);
  }
  @Test
  public void find_findsCuisinesInDatabase_true() {
    Cuisine myCuisine = new Cuisine (1, "mexican");
    myCuisine.save();
    Cuisine savedCuisine = Cuisine.find(myCuisine.getId());
    assertTrue(myCuisine.equals(savedCuisine));
  }
}
