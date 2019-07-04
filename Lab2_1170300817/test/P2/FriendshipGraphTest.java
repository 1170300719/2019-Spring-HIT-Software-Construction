package P2;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import org.junit.Test;

public class FriendshipGraphTest {
	// Testing strategy
	// ��addVertex��������л��֣�
	// �����������ظ�����
	// ��addEdge��������л��֣�
	// ��ʼ���Ѵ��ڣ���ʼ�㲻����
	// ��tgetDistance�����뻮�֣�
	// ������ڣ����벻����
	
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	// �����ظ�������Ч
	@Test
	public void testsamePerson() {
		FriendshipGraph graph = new FriendshipGraph();
		Person a = new Person("a");
		graph.addVertex(a);
		Person f = new Person("a");
		assertFalse(graph.getPeople().vertices().contains(f));
	}

	// addEdge��ʼ���Ѵ���
	@Test
	public void testsaddEdge() {
		FriendshipGraph graph = new FriendshipGraph();
		Person a = new Person("a");
		Person b = new Person("b");
		graph.addVertex(a);
		graph.addVertex(b);
		graph.addEdge(a, b);
		assertEquals("expected distance", 1, graph.getDistance(a, b));
	}

	// addEdge��ʼ�㲻����
	@Test
	public void testsaddEdgenotexist() {
		FriendshipGraph graph = new FriendshipGraph();
		Person a = new Person("a");
		Person b = new Person("b");
		graph.addEdge(a, b);
		assertEquals("expected distance", 1, graph.getDistance(a, b));
	}

	//���Դ��ھ���Ͳ����ھ���
	@Test
	public void testFriendshipGraph() {
		FriendshipGraph graph = new FriendshipGraph();
		Person a = new Person("a");
		Person b = new Person("b");
		Person c = new Person("c");
		Person d = new Person("d");
		graph.addEdge(a, b);
		graph.addEdge(b, c);
		graph.addEdge(c, d);
		assertEquals("expected distance", 1, graph.getDistance(a, b));
		assertEquals("expected distance", 1, graph.getDistance(b, c));
		assertEquals("expected distance", 1, graph.getDistance(c, d));
		assertEquals("expected distance", 2, graph.getDistance(a, c));
		assertEquals("expected distance", 2, graph.getDistance(b, d));
		assertEquals("expected distance", 3, graph.getDistance(a, d));
		assertEquals("expected distance", -1, graph.getDistance(b, a));
	}

}
