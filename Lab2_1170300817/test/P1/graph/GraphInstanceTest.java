/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import java.util.Collections;
import org.junit.Test;

/**
 * Tests for instance methods of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST NOT add constructors, fields, or non-@Test methods
 * to this class, or change the spec of {@link #emptyInstance()}. Your tests
 * MUST only obtain Graph instances by calling emptyInstance(). Your tests MUST
 * NOT refer to specific concrete implementations.
 */
public abstract class GraphInstanceTest {
	/**
	 * Testing strategy
	 * 
	 * ����add�����������룺 vertex���Ѵ���graph�ĵ㡢��δ����graph�ĵ�
	 * 
	 * ����set�����������룺 source��target���Ѵ���graph�ĵ㡢��δ����graph�ĵ� weight��0 ����0
	 * 
	 * ����remove�����������룺 vertex���Ѵ���graph�ĵ㡢��δ����graph�ĵ㡢���������������ĵ㡣
	 * 
	 * ����vertices������ graph����ͼ���ǿ�ͼ
	 * 
	 * ����sources�������֣� target���б����ӵĵ㡢�ޱ����ӵĵ�
	 * 
	 * ����targets�������֣� source���б����ӵĵ㡢�ޱ����ӵĵ�
	 * 
	 */
	/**
	 * Overridden by implementation-specific test classes.
	 * 
	 * @return a new empty graph of the particular implementation being tested
	 */
	public abstract Graph<String> emptyInstance();

	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	@Test
	public void testInitialVerticesEmpty() {
		assertEquals("expected new graph to have no vertices", Collections.emptySet(), emptyInstance().vertices());
	}

	// �����ظ�����
	@Test
	public void testexistAdd() {
		Graph<String> graph = emptyInstance();
		graph.add("a");
		graph.add("a");
		assertTrue(graph.vertices().contains("a"));
	}

	// ����add����
	@Test
	public void testnotexistAdd() {
		Graph<String> graph = emptyInstance();
		graph.add("a");
		assertTrue(graph.vertices().contains("a"));
	}

	// ����set����������source��targetδ����ͼ�С���������vertices
	@Test
	public void testSet() {
		Graph<String> graph = emptyInstance();
		graph.set("a", "b", 4);
		assertTrue(graph.vertices().contains("a"));
		assertTrue(graph.vertices().contains("b"));
		graph.set("a", "c", 4);
		assertTrue(graph.vertices().contains("c"));
	}

	// ����set����������source��targetδ����ͼ��
	@Test
	public void testunSet() {
		Graph<String> graph = emptyInstance();
		graph.add("a");
		graph.add("b");
		graph.set("a", "b", 4);
		assertTrue(graph.vertices().contains("a"));
		assertTrue(graph.vertices().contains("b"));
		assertEquals((Integer) 4, Integer.valueOf(graph.targets("a").get("b")));
	}

	// ����Remove����,Ŀ�������������������vertices��
	@Test
	public void testunconnectedRemove() {
		Graph<String> graph = emptyInstance();
		graph.add("b");
		graph.remove("b");
		assertFalse(graph.vertices().contains("b"));
	}

	// ����Remove����,Ŀ����������ߣ���������targets��
	
	@Test
	public void testconnectedRemove() {
		Graph<String> graph = emptyInstance();
		graph.set("a", "c", 4);
		graph.remove("c");
		assertFalse(graph.targets("a").keySet().contains("c"));
	}

	// ����vertices����,��ͼ
	@Test
	public void testemptyVertices() {
		Graph<String> graph = emptyInstance();
		assertTrue(graph.vertices().isEmpty());
	}
	
}
