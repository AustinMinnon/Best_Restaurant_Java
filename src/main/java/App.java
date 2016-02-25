import java.util.Map;
import java.util.HashMap;
import static spark.Spark.*;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;

public class App {

  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    /******************************************************
      Students: TODO: Display all restaurants on main page
    *******************************************************/
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());


    get("/cuisine-form", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/cuisine-form.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/", (request, reponse) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String type = request.queryParams("type");
      Cuisine newCuisine = new Cuisine(type);
      newCuisine.save();
      List<Cuisine> myCuisine = Cuisine.all();
      model.put("cuisines", myCuisine);
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/cuisines/:id", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
       List<Restaurant> restaurants = cuisine.getRestaurants();
       model.put("cuisine", cuisine);
       model.put("restaurants", restaurants);
       model.put("template", "templates/cuisines.vtl");
        return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     post("/cuisines/:id", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
       Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
       List<Restaurant> restaurants = cuisine.getRestaurants();
       model.put("cuisine", cuisine);
       model.put("restaurants", restaurants);
       model.put("template", "templates/index.vtl");
       return new ModelAndView(model, layout);
     }, new VelocityTemplateEngine());

     get("/cuisine/:id", (request, response) -> {
       HashMap<String, Object> model = new HashMap<String, Object>();
      //  Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      //  model.put("restaurants", Restaurant.all());
      //  model.put("cuisines", Cuisine.all());
       model.put("template", "templates/cuisine.vtl");
       return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/cuisine/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      Cuisine cuisine = Cuisine.find(Integer.parseInt(request.params(":id")));
      model.put("restaurants", Restaurant.all());
      model.put("cuisines", Cuisine.all());
      model.put("template", "templates/cuisine.vtl");
      return new ModelAndView(model, layout);
   }, new VelocityTemplateEngine());





    /******************************************************
    STUDENTS:
    TODO: Create page to display information about the selected restaurant
    TODO: Create page to display restaurants by cuisine type
    *******************************************************/

  }
}
