import java.util.HashMap;
import java.util.Map;
import spark.ModelAndView;
import spark.template.velocity.VelocityTemplateEngine;
import static spark.Spark.*;

public class App {
    public static void main(String[] args) {
      ProcessBuilder process = new ProcessBuilder();
         Integer port;
         if (process.environment().get("PORT") != null) {
             port = Integer.parseInt(process.environment().get("PORT"));
         } else {
             port = 4567;
         }
    
        setPort(port);

        staticFileLocation("/public");
        String layout = "templates/layout.vtl";

       get("/", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      model.put("stylists", Stylist.all());
      model.put("template", "templates/index.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

       post("/stylists", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          String name = request.queryParams("name");
          Stylist newStylist = new Stylist(name);
          newStylist.save();
          model.put("template", "templates/stylist-success.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        get("/stylists", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          model.put("stylists", Stylist.all());
          model.put("template", "templates/stylists.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

        post("/clients", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
        
          Stylist stylist = Stylist.find(Integer.parseInt(request.queryParams("stylistId")));
        
          String name = request.queryParams("name");
          Client newClient = new Client(name, stylist.getId());
        
          newClient.save();
        
          model.put("stylists", stylist);
          model.put("template", "templates/stylist-clients-sucess.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

         get("/clients/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
          model.put("stylists", stylist);
          model.put("template", "templates/clients.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

         get("/stylists/:id", (request, response) -> {
          Map<String, Object> model = new HashMap<String, Object>();
          Stylist stylist = Stylist.find(Integer.parseInt(request.params(":id")));
          model.put("stylist", stylist);
          model.put("template", "templates/stylist.vtl");
          return new ModelAndView(model, layout);
        }, new VelocityTemplateEngine());

    //     get("categories/:id/tasks/new", (request, response) -> {
    //       Map<String, Object> model = new HashMap<String, Object>();
    //       Category category = Category.find(Integer.parseInt(request.params(":id")));
    //       model.put("category", category);
    //       model.put("template", "templates/category-tasks-form.vtl");
    //       return new ModelAndView(model, layout);
    //     }, new VelocityTemplateEngine());

    //     get("/categories/:category_id/tasks/:id", (request, response) -> {
    //   Map<String, Object> model = new HashMap<String, Object>();
    //   Category category = Category.find(Integer.parseInt(request.params(":category_id")));
    //   Task task = Task.find(Integer.parseInt(request.params(":id")));
    //   model.put("category", category);
    //   model.put("task", task);
    //   model.put("template", "templates/task.vtl");
    //   return new ModelAndView(model, layout);
    // }, new VelocityTemplateEngine());

        // route that will update the Task upon form submission now:

//         post("/categories/:category_id/tasks/:id", (request, response) -> {
//   Map<String, Object> model = new HashMap<String, Object>();
//   Task task = Task.find(Integer.parseInt(request.params("id")));
//   String description = request.queryParams("description");
//   Category category = Category.find(task.getCategoryId());
//   task.update(description);
//   String url = String.format("/categories/%d/tasks/%d", category.getId(), task.getId());
//   response.redirect(url);
//   return new ModelAndView(model, layout);
// }, new VelocityTemplateEngine());

//          // a route that specializes in deleting Tasks.

//         post("/categories/:category_id/tasks/:id/delete", (request, response) -> {
//   HashMap<String, Object> model = new HashMap<String, Object>();
//   Task task = Task.find(Integer.parseInt(request.params("id")));
//   Category category = Category.find(task.getCategoryId());
//   task.delete();
//   model.put("category", category);
//   model.put("template", "templates/category.vtl");
//   return new ModelAndView(model, layout);
// }, new VelocityTemplateEngine());

//         post("/tasks", (request, response) -> {
//           Map<String, Object> model = new HashMap<String, Object>();
        
//           Category category = Category.find(Integer.parseInt(request.queryParams("categoryId")));
        
//           String description = request.queryParams("description");
//           Task newTask = new Task(description, category.getId());
        
//           newTask.save();
        
//           model.put("category", category);
//           model.put("template", "templates/category-tasks-success.vtl");
//           return new ModelAndView(model, layout);
//         }, new VelocityTemplateEngine());

//         get("/tasks/:id", (request, response) -> {
//             HashMap<String, Object> model = new HashMap<String, Object>();
//             Task task = Task.find(Integer.parseInt(request.params(":id")));
//             model.put("task", task);
//             model.put("template", "templates/task.vtl");
//             return new ModelAndView(model, layout);
//         }, new VelocityTemplateEngine());

//         get("/categories/new", (request, response) -> {
//           Map<String, Object> model = new HashMap<String, Object>();
//           model.put("template", "templates/category-form.vtl");
//           return new ModelAndView(model, layout);
//         }, new VelocityTemplateEngine());

//         post("/categories", (request, response) -> {
//           Map<String, Object> model = new HashMap<String, Object>();
//           String name = request.queryParams("name");
//           Category newCategory = new Category(name);
//           newCategory.save();
//           model.put("template", "templates/category-success.vtl");
//           return new ModelAndView(model, layout);
//         }, new VelocityTemplateEngine());

//         get("/categories", (request, response) -> {
//           Map<String, Object> model = new HashMap<String, Object>();
//           model.put("categories", Category.all());
//           model.put("template", "templates/categories.vtl");
//           return new ModelAndView(model, layout);
//         }, new VelocityTemplateEngine());

//         get("/categories/:id", (request, response) -> {
//           Map<String, Object> model = new HashMap<String, Object>();
//           Category category = Category.find(Integer.parseInt(request.params(":id")));
//           model.put("category", category);
//           model.put("template", "templates/category.vtl");
//           return new ModelAndView(model, layout);
//         }, new VelocityTemplateEngine());
        
        
    }
}