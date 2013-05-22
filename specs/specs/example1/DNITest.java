package specs.example1;

import org.concordion.integration.junit4.ConcordionRunner;
import org.junit.Before;
import org.junit.runner.RunWith;

import com.example.ServiceLetter;

/* Run this class as a JUnit test. */

@RunWith(ConcordionRunner.class)
public class DNITest {

	@Before
	public void setUp() {
	}

	public String getLetter(String DNI) {
		ServiceLetter sl = new ServiceLetter();
		String letter = sl.getLetter(DNI);
		return letter;
	}
}
