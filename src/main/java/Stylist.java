import java.util.List;
import java.util.Arrays;
import org.sql2o.*;

public class Stylist {
  private String name;
  private int id;
  private String description;

 public Stylist(String name) {
    this.name = name;
    this.description = description;
  }

  public String getName() {
    return name;
  }

  public String getDescription() {
    return description;
  }

  public int getId() {
   return id;
  }

  public static List<Stylist> all() {
    String sql = "SELECT id, name FROM stylists";
    try(Connection con = DB.sql2o.open()) {
      return con.createQuery(sql).executeAndFetch(Stylist.class);
    }
  }
  
    public static Stylist find(int id) {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM stylists where id=:id";
          Stylist stylist = con.createQuery(sql)
            .addParameter("id", id)
            .executeAndFetchFirst(Stylist.class);
          return stylist;
        }
      }

      public List<Client> getClients() {
        try(Connection con = DB.sql2o.open()) {
          String sql = "SELECT * FROM clients where StylistId=:id";
          return con.createQuery(sql)
            .addParameter("id", this.id)
            .executeAndFetch(Client.class);
        }
      }

      public void updateDescription(String name) {
    try(Connection con = DB.sql2o.open()) {
      String sql = "UPDATE stylists SET name=:name WHERE id=:id";
      con.createQuery(sql)
        .addParameter("name", name)
        .addParameter("id", this.id)
        .executeUpdate();
    }
  }


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