// import java.util.HashMap;
// import java.util.ArrayList;
// import java.util.List;
// import spark.ModelAndView;
// import spark.template.velocity.VelocityTemplateEngine;
// import static spark.Spark.*;
//
// public class App {
//   public static void main(String[] args) {
//     staticFileLocation("/public");
//     String layout = "templates/layout.vtl";
//
//     //ROOT
//     get("/", (request, response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       model.put("template", "templates/index.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     //USERS ROUTES
//     get("/users", (request, response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       List<User> users = User.all();
//       model.put("users", users);
//       model.put("template", "templates/users.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     post("/users", (request, response) -> {
//       User user = new User(request.queryParams("user_name"));
//       user.save();
//       response.redirect("/users");
//       return null;
//     });
//
//     get("/users/:id", (request, response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       int id = Integer.parseInt(request.params("id"));
//       User user = User.find(id);
//       model.put("user", user);
//       model.put("template", "templates/user.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     get("/users/:id/edit", (request, response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       int id = Integer.parseInt(request.params("id"));
//       User user = User.find(id);
//       model.put("user", user);
//       model.put("template", "templates/user-edit.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//
//     //RESTAURANTS ROUTES
//     get("/restaurants", (request, response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       List<Restaurant> restaurants = Restaurant.all();
//       model.put("restaurants", restaurants);
//       model.put("template", "templates/restaurants.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     post("/restaurants", (request, response) -> {
//       String restaurant_name = request.queryParams("restaurant_name");
//       String phone = request.queryParams("phone");
//       String street = request.queryParams("street");
//       String city = request.queryParams("city");
//       String state = request.queryParams("state");
//       String zip = request.queryParams("zip");
//       Restaurant restaurant = new Restaurant(restaurant_name, phone, street, city, state, zip);
//       restaurant.save();
//       response.redirect("/restaurants");
//       return null;
//     });
//
//     get("/restaurants/:id", (request, response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       int id = Integer.parseInt(request.params("id"));
//       Restaurant restaurant = Restaurant.find(id);
//       model.put("restaurant", restaurant);
//       model.put("template", "templates/restaurant.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//
//     get("/restaurants/:id/edit", (request, response) -> {
//       HashMap<String, Object> model = new HashMap<String, Object>();
//       int id = Integer.parseInt(request.params("id"));
//       Restaurant restaurant = Restaurant.find(id);
//       model.put("restaurant", restaurant);
//       model.put("template", "templates/restaurant-edit.vtl");
//       return new ModelAndView(model, layout);
//     }, new VelocityTemplateEngine());
//   }
// }
