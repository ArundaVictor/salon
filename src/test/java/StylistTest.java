import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;


public class StylistTest {

     @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Home");
    assertEquals(true, testStylist instanceof Stylist);
  }

      @Test
  public void getName_StylistInstantiatesWithName_Home() {
    Stylist testStylist = new Stylist("Home");
    assertEquals("Home", testStylist.getName());
  }

// update one of our existing tests to use our new .save() method:
      @Test
      public void all_returnsAllInstancesOfStylist_true() {
        Stylist firstStylist = new Stylist("Home");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Work");
        secondStylist.save();
        assertEquals(true, Stylist.all().get(0).equals(firstStylist));
        assertEquals(true, Stylist.all().get(1).equals(secondStylist));
      }

// We'll also modify our existing test for the getId() method:
     @Test
     public void getId_categoriesInstantiateWithAnId_1() {
       Stylist testStylist = new Stylist("Home");
       testStylist.save();
       assertTrue(testStylist.getId() > 0);
     }

  //   @Test
  // public void find_returnsStylistWithSameId_secondStylist() {
  //   Stylist firstStylist = new Stylist("Home");
  //   Stylist secondStylist = new Stylist("Work");
  //   assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
  // }

	@Test
	public void getTasks_initiallyReturnsEmptyList_ArrayList() {
	  Stylist testStylist = new Stylist("Home");
	  assertEquals(0, testStylist.getTasks().size());
  }

// find objects saved in the database.
     @Test
     public void find_returnsStylistWithSameId_secondStylist() {
       Stylist firstStylist = new Stylist("Home");
       firstStylist.save();
       Stylist secondStylist = new Stylist("Work");
       secondStylist.save();
       assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
     }

// Overriding equals()
      @Test
      public void equals_returnsTrueIfNamesAretheSame() {
        Stylist firstStylist = new Stylist("Household chores");
        Stylist secondStylist = new Stylist("Household chores");
        assertTrue(firstStylist.equals(secondStylist));
      }

// Saving New Objects to the Database test
      @Test
      public void save_savesIntoDatabase_true() {
        Stylist myStylist = new Stylist("Household chores");
        myStylist.save();
        assertTrue(Stylist.all().get(0).equals(myStylist));
      }

// Assigning Unique IDs
      @Test
      public void save_assignsIdToObject() {
        Stylist myStylist = new Stylist("Household chores");
        myStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(myStylist.getId(), savedStylist.getId());
      }

// to retrieve all Tasks saved into a specific Stylist.import java.util.Arrays;
      @Test
      public void getTasks_retrievesALlTasksFromDatabase_tasksList() {
        Stylist myStylist = new Stylist("Household chores");
        myStylist.save();
        Task firstTask = new Task("Mow the lawn", myStylist.getId());
        firstTask.save();
        Task secondTask = new Task("Do the dishes", myStylist.getId());
        secondTask.save();
        Task[] tasks = new Task[] { firstTask, secondTask };
        assertTrue(myStylist.getTasks().containsAll(Arrays.asList(tasks)));
      }

}
