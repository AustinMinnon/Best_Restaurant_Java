import org.sql2o.*;
import java.util.List;

public class Restaurant {
  private int id;
  private String name;
  private String description;
  private int cuisine_id;

  public Restaurant (String name, String description, int cuisine_id) {
    //this.id = id;
    this.name = name;
    this.description = description;
    this.cuisine_id = cuisine_id;
  }

  public int getId() {
    return id;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getCuisineId() {
    return cuisine_id;
  }

  @Override
  public boolean equals(Object otherRestaurant){
    if (!(otherRestaurant instanceof Restaurant)) {
      return false;
    } else {
      Restaurant newRestaurant = (Restaurant) otherRestaurant;
      return this.getName().equals(newRestaurant.getName()) &&
        this.getId() == newRestaurant.getId() && this.getDescription().equals(newRestaurant.getDescription()) && this.getCuisineId() == (newRestaurant.getCuisineId());
    }
  }

  public void save() {
    try (Connection con = DB.sql2o.open()) {
      String sql = "INSERT INTO restaurants(name, description, cuisine_id) VALUES (:name, :description, :cuisine_id)";
      this.id = (int) con.createQuery(sql, true)
        // .addParameter("id", id)
        .addParameter("name", name)
        .addParameter("description", description)
        .addParameter("cuisine_id", cuisine_id)
        .executeUpdate()
        .getKey();
    }
  }


  public static List<Restaurant> all() {
    String sql = "SELECT id, name, description, cuisine_id FROM restaurants";
    try (Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Restaurant.class);
    }
  }

  public void update(String name, String description, int cuisine_id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE restaurants SET id = :id, name = :name, description = :description, cuisine_id = :cuisine_id WHERE id = :id";
      con.createQuery(sql)
      .addParameter("id", id)
      .addParameter("name", name)
      .addParameter("description", description)
      .addParameter("cuisine_id", cuisine_id)
      .executeUpdate();
      }
  }

  public static void deleteRestaurant(int id) {
    String sql = "DELETE FROM restaurants WHERE id=:id";
    try(Connection con = DB.sql2o.open()) {
      con.createQuery(sql)
      .addParameter("id", id)
      .executeUpdate();
    }
  }

  public static Restaurant find(int id) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM restaurants where id=:id";
      Restaurant restaurant = con.createQuery(sql)
      .addParameter("id", id)
      .executeAndFetchFirst(Restaurant.class);
      return restaurant;
    }
  }

  public List<Cuisine> getCuisines() {
    try(Connection con = DB.sql2o.open()) {
      String sql = "SELECT * FROM cuisine where cuisine_id=:cuisine_id";
      return con.createQuery(sql)
        .addParameter("cuisine_id", cuisine_id)
        .executeAndFetch(Cuisine.class);
      }
    }
}
