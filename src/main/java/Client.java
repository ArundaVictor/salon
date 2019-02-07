import java.time.LocalDateTime;
    import java.util.ArrayList;
    import java.util.List;
    import org.sql2o.*;
    

public class Task {
    private String name;
    private boolean completed;
    private LocalDateTime createdAt;
    private int id;
    private int stylistId;

// alter Task constructor to require a Category ID.
    public Task(String description, int categoryId) {
        this.description = description;
        completed = false;
        createdAt = LocalDateTime.now();
        this.categoryId = categoryId;
      }

    public String getDescription() {
        return description;
    }

    public boolean isCompleted() {
        return completed;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public int getId() {
        return id;
    }

    public int getCategoryId() {
      return categoryId;
    }

// getting from database and savin in a list
//  update our Task.all() method's SQL query to include categoryId
      public static List<Task> all() {
        String sql = "SELECT id, description, categoryId FROM tasks";
        try(Connection con = DB.sql2o.open()) {
         return con.createQuery(sql).executeAndFetch(Task.class);
        }
      }

// To make this test(find) pass, we'll re-populate our static find() method with code to locate a specific Taskin our database:
    public static Task find(int id) {
      try(Connection con = DB.sql2o.open()) {
        String sql = "SELECT * FROM tasks where id=:id";
        Task task = con.createQuery(sql)
          .addParameter("id", id)
          .executeAndFetchFirst(Task.class);
        return task;
      }
    }

//Overriding equals()
//update .equals() method 
//to take this property (category id)into account when comparing Taskobjects:
      @Override
      public boolean equals(Object otherTask){
        if (!(otherTask instanceof Task)) {
          return false;
        } else {
          Task newTask = (Task) otherTask;
          return this.getDescription().equals(newTask.getDescription()) &&
                 this.getId() == newTask.getId() &&
                 this.getCategoryId() == newTask.getCategoryId();
        }
      }

// method to store objects in database
//  we need to include the categoryId in the SQL statement within our save() method. 
// This will ensure the categoryId is saved in our database:
      public void save() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "INSERT INTO tasks(description, categoryId) VALUES (:description, :categoryId)";
          this.id = (int) con.createQuery(sql, true)
            .addParameter("description", this.description)
            .addParameter("categoryId", this.categoryId)
            .executeUpdate()
            .getKey();
        }
      }

      // Method to update a task

      public void update(String description) {
  try(Connection con = DB.sql2o.open()) {
    String sql = "UPDATE tasks SET description = :description WHERE id = :id";
    con.createQuery(sql)
      .addParameter("description", description)
      .addParameter("id", id)
      .executeUpdate();
  }
}

// Method to delete a task
public void delete() {
  try(Connection con = DB.sql2o.open()) {
  String sql = "DELETE FROM tasks WHERE id = :id;";
  con.createQuery(sql)
    .addParameter("id", id)
    .executeUpdate();
  }
}

