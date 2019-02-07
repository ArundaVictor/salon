import java.util.List;
import java.util.Arrays;
import org.sql2o.*;

public class Category {
  private String name;
  private int id;

  public Category(String name) {
    this.name = name;
  }

  public String getName() {
    return name;
  }

// Here, we construct a basic SQL query requesting all id and description data from the categoriestable.
  public static List<Category> all() {
    String sql = "SELECT id, name FROM categories";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Category.class);
    }
  }

  public int getId() {
   return id;
  }
  
// To make it pass (find test), we can re-populate our static find() method with code to locate a specific Category:
// Here we are using a select query using where id=:id. 
// We use .addParameter("id", id)to pass in the id argument to the sql query 
// and then we run .executeAndFetchFirst(Category.class);. 
// This will return the first item in the collection returned by our database, 
// cast as a Category object. Finally, we return that Category.
    public static Category find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM categories where id=:id";
          Category category = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Category.class);
          return category;
        }
      }

// To make a list of our in memory objects, we first construct a task array containing those objects. 
// We then use a new method Arrays.asList(tasks) to save those items into a list. 
// (You will need to add import java.util.Arrays; to the top of the file to use Arrays.asList.)
      public List<Task> getTasks() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM tasks where categoryId=:id";
          return con.createQuery(sql)
            .addParameter("id", this.id)
            .executeAndFetch(Task.class);
        }
      }
// equals() method 
// We should also modify our equals() method to account for this new property:(ids)
      @Override
      public boolean equals(Object otherCategory) {
        if (!(otherCategory instanceof Category)) {
          return false;
        } else {
          Category newCategory = (Category) otherCategory;
          return this.getName().equals(newCategory.getName()) &&
                 this.getId() == newCategory.getId();
        }
      }

// .save() method
// we'll use save() to assign the object the same id as its data in the database:
      public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO categories(name) VALUES (:name)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("name", this.name)
            .executeUpdate()
            .getKey();
        }
      }
}