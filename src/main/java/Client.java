
    import java.util.ArrayList;
    import java.util.List;
    import org.sql2o.*;
    

public class Client {
    private String name;
    private int id;
    private int stylistId;

// alter Task constructor to require a Category ID.
    public Task(String name, int stylistId) {
        this.name = name;
        this.stylistId = stylistId;
      }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public int getStylistId() {
      return stylistId;
    }

// getting from database and savin in a list
//  update our Client.all() method's SQL query to include stylistId
      public static List<Client> all() {
        String sql = "SELECT id, name, stylistId FROM clients";
        try(Connection con = DB.sql2o.open()) {
         return con.createQuery(sql).executeAndFetch(Client.class);
        }
      }

// To make this test(find) pass, we'll re-populate our static find() method with code to locate a specific Taskin our database:
    public static Client find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM clients where id=:id";
        Client client = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Client.class);
        return clent;
      }
    }

//Overriding equals()
//update .equals() method 
//to take this property (category id)into account when comparing Taskobjects:
      @Override
      public boolean equals(Object otherClient){
        if (!(otherClient instanceof Client)) {
          return false;
        } else {
          Client newClient = (Client) otherClient;
          return this.getName().equals(newClient.getName()) &&
                 this.getId() == newClient.getId() &&
                 this.getStylistId() == newClient.getStylistId();
        }
      }

// method to store objects in database
//  we need to include the categoryId in the SQL statement within our save() method. 
// This will ensure the categoryId is saved in our database:
      public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO clients(name, stylistId) VALUES (:name, :stylistId)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .addParameter("stylistId", this.stylistId)
            .executeUpdate()
            .getKey();
        }
      }

      // Method to update a task

      public void update(String name) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE clients SET name = :name WHERE id = :id";
    con.createQuery(sql)
      .addParameter("name", name)
      .addParameter("id", id)
      .executeUpdate();
  }
}

// Method to delete a task
public void delete() {
  try(Connection con = DB.sql2o.open()) {
  String sql = "DELETE FROM clients WHERE id = :id;";
  con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
  }
}

