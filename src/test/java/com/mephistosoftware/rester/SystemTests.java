package com.mephistosoftware.rester;


import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.mephistosoftware.rester.model.Person;


public class SystemTests {

  @Test
  public void testCreateReadDelete() {
    RestTemplate restTemplate = new RestTemplate();
    
    
    
    String url = "http://localhost:8090/api/v1/users/register";
    /*
    AppUser user = new AppUser();
    user.setFirstName("Edward");
    user.setLastName("ODonnell");
    user.setEmail("joe@blow.com");
    user.setPhone("555-1212");
    user.setSuperUser(true);
    user.setStaff(true);
    user.setActive(true);
    user.setPassword("x1234567");


    ResponseEntity<AppUser> entity = restTemplate.postForEntity(url, user, AppUser.class);

    AppUser[] users = restTemplate.getForObject("http://localhost:8090/api/v1/users", AppUser[].class);
    Assertions.assertThat(users).extracting(AppUser::getUsername).containsOnly("eddyod");
	
    restTemplate.delete("http://localhost:8090/api/v1/users/" + entity.getBody().getId());
    Assertions.assertThat(restTemplate.getForObject(url, AppUser[].class)).isEmpty();
    */
  }

}
