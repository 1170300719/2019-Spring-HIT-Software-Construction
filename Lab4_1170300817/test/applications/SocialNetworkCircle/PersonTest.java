package applications.SocialNetworkCircle;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

import exception.illegalParameterException;
import exception.sameLabelException;

public class PersonTest {
	@Test
	public void personTest() {
		Person p = null;
		try {
			p = Person.getInstance("aaa", 16, "M");
			assertTrue(p.getAge() == 16);
			assertTrue(p.getGender().equals("M"));
		} catch (sameLabelException | illegalParameterException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void personeEX1Test() {
		try {
			Person.getInstance(",.a", 16, "M");
		} catch (illegalParameterException | sameLabelException e) {
			assertTrue(e.getMessage().contains("�������ִ���"));
		}
	}

	@Test
	public void personeEX2Test() {
		try {
			Person.getInstance("aas", 16, "S");
		} catch (illegalParameterException | sameLabelException e) {
			assertTrue(e.getMessage().contains("�����Ա����"));
		}
	}

	@Test
	public void personeEX3Test() {
		try {
			Person.getInstance("aax", 1111, "M");
		} catch (illegalParameterException | sameLabelException e) {
			assertTrue(e.getMessage().contains("�����������"));
		}
	}
}
