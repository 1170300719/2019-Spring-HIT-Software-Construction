package phsicalObject;

import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class PhsicalObjectTest {
	// Testing strategy:
	// ����������Ҫ�����������ܸ������з�֧
	@Test
	public void ObjectTest() {
		PhysicalObject physicalObject=new PhysicalObject("aaa");
		assertEquals("aaa", physicalObject.getName());

	}
}
