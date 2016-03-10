import java.util.HashMap;
import java.util.ArrayList;
import java.util.List;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
  public static void main(String[] args) {
    staticFileLocation("/public");
    String layout = "templates/layout.vtl";

    //ROOT
    get("/", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/login.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //USERS ROUTES...

    //LIST OF ALL USERS
    get("/users", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      List<User> users = User.all();
      model.put("users", users);
      model.put("template", "templates/users.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //AFTER LOGGING IN PAGE/ACCOUNT HOME PAGE
    post("/welcome", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      String username = request.queryParams("username");
      String password = request.queryParams("password");
      User currentUser = User.login(username);
      model.put("password", password);
      model.put("currentUser", currentUser);
      model.put("template", "templates/welcome.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:userId/home", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int userId = Integer.parseInt(request.params("userId"));
      User currentUser = User.find(userId);
      model.put("currentUser", currentUser);
      model.put("allRestaurants", Restaurant.all());
      model.put("template", "templates/user.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("new-user", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      model.put("template", "templates/new-user.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/save-new-user", (request, response) -> {
      String user_name = request.queryParams("newUserName");
      String password = request.queryParams("newPassword");
      User newUser = new User(user_name, password);
      newUser.save();
      response.redirect("/");
      return null;
    });

    post("/edit-user", (request, response) -> {
      int id = Integer.parseInt(request.queryParams("userId"));
      String username = request.queryParams("username");
      String password = request.queryParams("password");
      User user = User.find(id);
      user.update(username, password);
      response.redirect("/"+id+"/home");
      return null;
    });

    post("/add-restaurant", (request, response) -> {
      int restaurantId = Integer.parseInt(request.queryParams("restaurant_id"));
      int userId = Integer.parseInt(request.queryParams("user_id"));
      Restaurant restaurant = Restaurant.find(restaurantId);
      User user = User.find(userId);
      user.addRestaurant(restaurant);
      response.redirect("/users/" + userId);
      return null;
    });

    get("/users/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      User user = User.find(id);
      model.put("user", user);
      model.put("allRestaurants", Restaurant.all());
      model.put("template", "templates/user.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("users/:id", (request, response) -> {
      int id = Integer.parseInt(request.params("id"));
      User user = User.find(id);
      String user_name = request.queryParams("user_name");
      String password = request.queryParams("password");
      user.update(user_name, password);
      response.redirect("/users/" + id);
      return null;
    });

    get("/users/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      User user = User.find(id);
      model.put("currentUser", user)
      model.put("user", user);
      model.put("template", "templates/user-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    //RESTAURANTS ROUTES
    get("/:userId/restaurants", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int userId = Integer.parseInt(request.params("userId"));
      User currentUser = User.find(userId);
      List<Restaurant> restaurants = Restaurant.all();
      model.put("currentUser", currentUser);
      model.put("restaurants", restaurants);
      model.put("template", "templates/restaurants.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:userId/add-restaurant", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int userId = Integer.parseInt(request.params("userId"));
      User currentUser = User.find(userId);
      model.put("currentUser", currentUser);
      model.put("template", "templates/new-restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("/:userId/restaurants", (request, response) -> {
      int userId = Integer.parseInt(request.params("userId"));
      String restaurant_name = request.queryParams("restaurant_name");
      String phone = request.queryParams("phone");
      String street = request.queryParams("street");
      String city = request.queryParams("city");
      String state = request.queryParams("state");
      String zip = request.queryParams("zip");
      Restaurant restaurant = new Restaurant(restaurant_name, phone, street, city, state, zip);
      restaurant.save();
      response.redirect("/"+userId+"/restaurants");
      return null;
    });

    post("/add-user", (request, response) -> {
      int userId = Integer.parseInt(request.queryParams("user_id"));
      int restaurantId = Integer.parseInt(request.queryParams("restaurant_id"));
      User user = User.find(userId);
      Restaurant restaurant = Restaurant.find(restaurantId);
      restaurant.addUser(user);
      response.redirect("/restaurants/" + restaurantId);
      return null;
    });

    get("/:userId/restaurants/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Restaurant restaurant = Restaurant.find(id);
      int userId = Integer.parseInt(request.params("userId"));
      User currentUser = User.find(userId);
      model.put("currentUser", currentUser);
      model.put("restaurant", restaurant);
      model.put("template", "templates/restaurant.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    get("/:userId/restaurants/:id/edit", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Restaurant restaurant = Restaurant.find(id);
      model.put("restaurant", restaurant);
      model.put("template", "templates/restaurant-edit.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

    post("restaurants/:id", (request, response) -> {
      HashMap<String, Object> model = new HashMap<String, Object>();
      int id = Integer.parseInt(request.params("id"));
      Restaurant restaurant = Restaurant.find(id);
      String restaurant_name = request.queryParams("restaurant_name");
      String phone = request.queryParams("phone");
      String street = request.queryParams("street");
      String city = request.queryParams("city");
      String state = request.queryParams("state");
      String zip = request.queryParams("zip");
      restaurant.update(restaurant_name, phone, street, city, state, zip);
      response.redirect("/restaurants/" + id);
      return null;
    });
  }
}
