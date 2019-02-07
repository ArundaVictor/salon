import org.junit.*;
import static org.junit.Assert.*;
import org.sql2o.*;
import java.util.Arrays;


public class StylistTest {

     @Rule
  public DatabaseRule database = new DatabaseRule();

  @Test
  public void Stylist_instantiatesCorrectly_true() {
    Stylist testStylist = new Stylist("Lana");
    assertEquals(true, testStylist instanceof Stylist);
  }

      @Test
  public void getName_StylistInstantiatesWithName_Home() {
    Stylist testStylist = new Stylist("Lana");
    assertEquals("Lana", testStylist.getName());
  }

// update one of our existing tests to use our new .save() method:
      @Test
      public void all_returnsAllInstancesOfStylist_true() {
        Stylist firstStylist = new Stylist("Lana");
        firstStylist.save();
        Stylist secondStylist = new Stylist("Michelle");
        secondStylist.save();
        assertEquals(true, Stylist.all().get(0).equals(firstStylist));
        assertEquals(true, Stylist.all().get(1).equals(secondStylist));
      }

// We'll also modify our existing test for the getId() method:
     @Test
     public void getId_stylistsInstantiateWithAnId_1() {
       Stylist testStylist = new Stylist("Lana");
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
	public void getClients_initiallyReturnsEmptyList_ArrayList() {
	  Stylist testStylist = new Stylist("Lana");
	  assertEquals(0, testStylist.getClients().size());
  }

// find objects saved in the database.
     @Test
     public void find_returnsStylistWithSameId_secondStylist() {
       Stylist firstStylist = new Stylist("Lana");
       firstStylist.save();
       Stylist secondStylist = new Stylist("Michelle");
       secondStylist.save();
       assertEquals(Stylist.find(secondStylist.getId()), secondStylist);
     }

// Overriding equals()
      @Test
      public void equals_returnsTrueIfNamesAretheSame() {
        Stylist firstStylist = new Stylist("Babs");
        Stylist secondStylist = new Stylist("Babs");
        assertTrue(firstStylist.equals(secondStylist));
      }

// Saving New Objects to the Database test
      @Test
      public void save_savesIntoDatabase_true() {
        Stylist myStylist = new Stylist("Babs");
        myStylist.save();
        assertTrue(Stylist.all().get(0).equals(myStylist));
      }

// Assigning Unique IDs
      @Test
      public void save_assignsIdToObject() {
        Stylist myStylist = new Stylist("Babs");
        myStylist.save();
        Stylist savedStylist = Stylist.all().get(0);
        assertEquals(myStylist.getId(), savedStylist.getId());
      }

// to retrieve all Clients saved into a specific Stylist.import java.util.Arrays;
      @Test
      public void getClients_retrievesALlClientsFromDatabase_clientsList() {
        Stylist myStylist = new Stylist("Babs");
        myStylist.save();
        Client firstClient = new Client("Emma", myStylist.getId());
        firstClient.save();
        Client secondClient = new Client("Caroline", myStylist.getId());
        secondClient.save();
        Client[] Clients = new Client[] { firstClient, secondClient };
        assertTrue(myStylist.getClients().containsAll(Arrays.asList(Clients)));
      }

}
