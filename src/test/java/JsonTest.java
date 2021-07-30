import com.valenator.ObjectToJsonConverter;
import com.valenator.Person;
import com.valenator.anotate.JsonSerializationException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JsonTest {

  private ObjectToJsonConverter objectToJsonConverter;



  @Before
  public void setUp(){
    this.objectToJsonConverter = new ObjectToJsonConverter();
  }

  @Test
  public void jsonTest() throws JsonSerializationException {
    Person person = new Person("Petr", "Inova", "63", "Kiev");

    String json = objectToJsonConverter.convertToJson(person);
    Assert.assertEquals(json, "{\"personAge\": \"63\", \"firstName\": \"Petr\", \"lastName\": \"Inova\"}");
  }

}
