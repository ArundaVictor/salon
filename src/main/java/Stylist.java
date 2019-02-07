import java.util.List;
import java.util.Arrays;
import org.sql2o.*;

public class Stylist {
  private String name;
  private int id;

  public Stylist(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

  public int getId() {
   return id;
  }

  // Here, we construct a basic SQL query requesting all id and description data from the styliststable.
  public static List<Stylist> all() {
    String sql = "SELECT id, name FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }
  
// To make it pass (find test), we can re-populate our static find() method with code to locate a specific Stylist:
// Here we are using a select query using where id=:id. 
// We use .addParameter("id", id)to pass in the id argument to the sql query 
// and then we run .executeAndFetchFirst(Stylist.class);. 
// This will return the first item in the collection returned by our database, 
// cast as a Stylist object. Finally, we return that Stylist.
    public static Stylist find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM stylists where id=:id";
          Stylist stylist = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Stylist.class);
          return stylist;
        }
      }

// To make a list of our in memory objects, we first construct a task array containing those objects. 
// We then use a new method Arrays.asList(tasks) to save those items into a list. 
// (You will need to add import java.util.Arrays; to the top of the file to use Arrays.asList.)
      public List<Task> getTasks() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM tasks where StylistId=:id";
          return con.createQuery(sql)
            .addParameter("id", this.id)
            .executeAndFetch(Task.class);
        }
      }
// equals() method 
// We should also modify our equals() method to account for this new property:(ids)
      @Override
      public boolean equals(Object otherStylist) {
        if (!(otherStylist instanceof Stylist)) {
          return false;
        } else {
          Stylist newStylist = (Stylist) otherStylist;
          return this.getName().equals(newStylist.getName()) &&
                 this.getId() == newStylist.getId();
        }
      }

// .save() method
// we'll use save() to assign the object the same id as its data in the database:
      public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO stylists(name) VALUES (:name)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .executeUpdate()
            .getKey();
        }
      }
}