package phsicalObject;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;

public class phsicalObjectTest {
	// Testing strategy:
	// ����������Ҫ�����������ܸ������з�֧
	@Test
	public void ObjectTest() {
		PhysicalObject physicalObject=new PhysicalObject("aaa");
		assertEquals("aaa", physicalObject.getName());

	}
}
