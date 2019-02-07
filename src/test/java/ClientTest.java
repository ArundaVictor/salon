import org.junit.*;
import static org.junit.Assert.*;
import java.time.LocalDateTime;
import org.sql2o.*;


public class ClientTest {

 @Rule
  public DatabaseRule database = new DatabaseRule();

    @Test
    public void Client_instantiatesCorrectly_true() {
        Client myClient = new Client("Caroline", 1);
        assertEquals(true, myClient instanceof Client);
    }

    @Test
    public void Client_instantiatesWithName_String() {
        Client myClient = new Client("Caroline", 1);
        assertEquals("Caroline", myClient.getName());
    }

    // @Test
    // public void isCompleted_isFalseAfterInstantiation_false() {
    //     Client myClient = new Client("Mow the lawn", 1);
    //     assertEquals(false, myClient.isCompleted());
    // }

    // @Test
    // public void getCreatedAt_instantiatesWithCurrentTime_today() {
    //     Client myClient = new Client("Mow the lawn", 1);
    //     assertEquals(LocalDateTime.now().getDayOfWeek(), myClient.getCreatedAt().getDayOfWeek());
    // }

// returns all data in database while still saving using sava()
      @Test
      public void all_returnsAllInstancesOfClient_true() {
        Client firstCliet = new Client("Caroline", 1);
        firstClient.save();
        Client secondClient = new Client("Caroline", 1);
        secondClient.save();
        assertEquals(true, Client.all().get(0).equals(firstClient));
        assertEquals(true, Client.all().get(1).equals(secondClient));
      }

    @Test
    public void clear_emptiesAllClientsFromArrayList_0() {
        Client myClient = new Client("Caroline", 1);
        assertEquals(Client.all().size(), 0);
    }

// this test checks that the id is greater than 0 from DB
    @Test
    public void getId_clientInstantiateWithAnID() {
      Client myClient = new Client("Caroline", 1);
      myClient.save();
      assertTrue(myClient.getId() > 0);
    }
// Our Client.find(myClient.getId()) uses the Client's id to query the database and return a Client object.
      @Test
      public void find_returnsClientWithSameId_secondClient() {
        Client firstClient = new Client("Caroline", 1);
        firstClient.save();
        Client secondClient = new Client("Caroline", 1);
        secondClient.save();
        assertEquals(Client.find(secondClient.getId()), secondClient);
      }

// a test to check whether to instances of info retriieved from the database are the same 
    @Test
    public void equals_returnsTrueIfNamesAretheSame() {
      Client firstClient = new Client("Caroline", 1);
      Client secondClient = new Client("Caroline", 1);
      assertTrue(firstClient.equals(secondClient));
    }

// saving objects into the database
    @Test
    public void save_returnsTrueIfNamesAretheSame() {
      Client myClient = new Client("Caroline", 1);
      myClient.save();
      assertTrue(Client.all().get(0).equals(myClient));
    }

// test ids of Clients in database and application
    @Test
    public void save_assignsIdToObject() {
      Client myClient = new Client("Mow the lawn", 1);
      myClient.save();
      Client savedClient = Client.all().get(0);
      assertEquals(myClient.getId(), savedClient.getId());
    }

// Next, we'll write a unit test that ensures 
//we can save a StylistId into our Clients table, 
//thereby associating a Client with its Stylist:
      @Test
      public void save_savesStylistIdIntoDB_true() {
        Stylist myStylist = new Stylist("Joy");
        myStylist.save();
        Client myClient = new Client("Caroline", myStylist.getId());
        myClient.save();
        Client savedClient = Client.find(myClient.getId());
        assertEquals(savedClient.getStylistId(), myStylist.getId());
      }

      // code to handle updating a specific entry in our database.

      @Test
public void update_updatesClientName_true() {
  Client myClient = new Client("Caroline", 1);
  myClient.save();
  myClient.update("Emma");
  assertEquals("Emma", Client.find(myClient.getId()).getName());
}

// code to handle locating and deleting database entries
@Test
public void delete_deletesClient_true() {
  Client myClient = new Client("Caroline", 1);
  myClient.save();
  int myClientId = myClient.getId();
  myClient.delete();
  assertEquals(null, Client.find(myClientId));
}

}
