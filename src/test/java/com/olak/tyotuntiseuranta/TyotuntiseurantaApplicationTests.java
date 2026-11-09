package com.olak.tyotuntiseuranta;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.olak.tyotuntiseuranta.web.WorkController;

@SpringBootTest
class TyotuntiseurantaApplicationTests {

	@Autowired 
	private WorkController controller;

	@Test
	void contextLoads()throws Exception {
		assertThat(controller).isNotNull();
	}

}
