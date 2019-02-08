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

         get("/stylists/:stylist_id/clients/:id", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params(":stylist_id")));
      Client client = Client.find(Integer.parseInt(request.params(":id")));
      model.put("stylist", stylist);
      model.put("client", client);
      model.put("template", "templates/client.vtl");
      return new ModelAndView(model, layout);
    }, new VelocityTemplateEngine());

         post("/stylists/:stylist_id/clients/:id", (request, response) -> {
  Map<String, Object> model = new HashMap<String, Object>();
  Client client = Client.find(Integer.parseInt(request.params("id")));
  String name = request.queryParams("name");
  Stylist stylist = Stylist.find(client.getStylistId());
  client.update(name);
  String url = String.format("/stylists/%d/clients/%d", stylist.getId(), client.getId());
  response.redirect(url);
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());

          post("/stylists/:stylist_id/clients/:id/delete", (request, response) -> {
  HashMap<String, Object> model = new HashMap<String, Object>();
  Client client = Client.find(Integer.parseInt(request.params("id")));
  Stylist stylist = Stylist.find(client.getStylistId());
  client.delete();
  model.put("stylist", stylist);
  model.put("template", "templates/stylist.vtl");
  return new ModelAndView(model, layout);
}, new VelocityTemplateEngine());




         post("/stylist/:id/update", (request, response) -> {
      Map<String, Object> model = new HashMap<String, Object>();
      Stylist stylist = Stylist.find(Integer.parseInt(request.params("id")));
      String description = request.queryParams("description");
      stylist.updateDescription(description);
      String name = request.queryParams("name");
      stylist.updateDescription(name);
      model.put("stylist", stylist.getName());
      String url = String.format("/stylist/%d", stylist.getId());
      response.redirect(url);
      return null;
    });  

          


   
        
    }
}