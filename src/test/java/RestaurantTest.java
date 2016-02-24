import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;

public class RestaurantTest {

  @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void all_emptyAtFirst() {
      assertEquals(Restaurant.all().size(), 0);
  }

  @Test
  public void all_savesIntoDatabase_true() {
    Restaurant myRestaurant = new Restaurant(1, "Voodoo Donuts", "best donuts around", 1);
    myRestaurant.save();
    assertEquals(Restaurant.all().get(0).getDescription(), "best donuts around");
  }

  @Test
  public void update_restaurant_true() {
    Restaurant testRestaurant = new Restaurant(1, "Voodoo Donuts", "best donuts around", 1);
    testRestaurant.save();
    testRestaurant.update(1, "Sesame Donuts", "best donuts around", 1);
    Restaurant savedRestaurant = Restaurant.all().get(0);
    assertEquals(savedRestaurant.getName(), "Sesame Donuts");
  }

  @Test
  public void restaurant_deletes_true() {
    Restaurant myRestaurant = new Restaurant(1, "Voodoo Donuts", "best donuts around", 1);
    myRestaurant.deleteRestaurant(0);
    assertTrue(Restaurant.find(0) == null);
  }
  @Test
  public void find_findsRestaurantsInDatabase_true() {
    Restaurant myRestaurant = new Restaurant (1, "Voodoo Donuts", "best donuts around", 1);
    myRestaurant.save();
    Restaurant savedRestaurant = Restaurant.find(myRestaurant.getId());
    assertTrue(myRestaurant.equals(savedRestaurant));
  }
}
