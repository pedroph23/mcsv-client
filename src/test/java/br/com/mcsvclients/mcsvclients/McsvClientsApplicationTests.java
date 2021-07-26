package br.com.mcsvclients.mcsvclients;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class McsvClientsApplicationTests extends Mockito {

	@Test
	void contextLoads() {
		McsvClientsApplication.main(new String[] {});
	}
}
