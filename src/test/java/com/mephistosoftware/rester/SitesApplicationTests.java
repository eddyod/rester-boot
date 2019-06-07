package com.mephistosoftware.rester;

import com.mephistosoftware.rester.controller.SiteController;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SitesApplicationTests {

	@Autowired
	SiteController siteController;

	@Test
	public void contextLoads() {
		Assert.assertNotNull(siteController);
	}
}
