package net.onur.springbootjournal;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.junit4.SpringRunner;

import net.onur.springbootjournal.model.Journal;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=WebEnvironment.RANDOM_PORT)
public class SpringBootJournalApplicationTests {

	@LocalServerPort
	private int port;
	
	@Test
	public void contextLoads() 
	{
		System.out.println(port);
		
		Journal journal=new Journal();
		assertNotNull(journal);
	}
}
