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
  // @Test
  // public void update_restaurant_true() {
  //   Restaurant testRestaurant = new Restaurant(1, "Voodoo Donuts", "best donuts around", 1);
  //   testRestaurant.save();
  //   testRestaurant.update(1, "Sesame Donuts", "best donuts around", 1);
  //   Restaurant savedRestaurant = Restaurant.all().get(0);
  //   assertEquals(savedRestaurant.getName(), "Sesame Donuts");
  // }

  // @Test
  // public void cuisine_deletes_true() {
  //   Cuisine myCuisine = new Cuisine("Chinese");
  //   myCuisine.deleteCuisine();
  //   assertTrue(Cuisine.all() == null);
  // }
}
