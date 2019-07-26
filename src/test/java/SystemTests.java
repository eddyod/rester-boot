
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mephistosoftware.rester.model.Person;
import com.mephistosoftware.rester.security.SecurityConstants;



public class SystemTests {

  @Test
  public void testCreateReadDelete() {
	  
	/*  
    RestTemplate restTemplate = new RestTemplate();    
    
    String API_URL = "http://10.195.4.147:8090";
    String email = "X123@yahoo.com";
    Person user = new Person();
    user.setFirstName("Jason");
    user.setLastName("ODonnell");
    user.setEmail(email);
    user.setAddress("X Main St");
    user.setPhone("555-1212");
    user.setActive(true);
    user.setPassword("j1234567");


    ResponseEntity<Person> newUser = restTemplate.postForEntity(API_URL + SecurityConstants.SIGN_UP_URL, user, Person.class);

    Person[] users = restTemplate.getForObject(API_URL + "/persons", Person[].class);
    Assertions.assertThat(users).extracting(Person::getEmail).contains(email);
	
    restTemplate.delete(API_URL + "/person/" + newUser.getBody().getId());
    
    users = restTemplate.getForObject(API_URL + "/persons", Person[].class);
    Assertions.assertThat(users).extracting(Person::getEmail).doesNotContain(email);
	*/
    
  }

}