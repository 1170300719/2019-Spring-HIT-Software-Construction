/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteEdgesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteEdgesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteEdgesGraphTest extends GraphInstanceTest {

	/*
	 * Provide a ConcreteEdgesGraph for tests in GraphInstanceTest.
	 */
	Graph<String> graph = new ConcreteEdgesGraph<>();
	@Override
	public Graph<String> emptyInstance() {
		return graph;
	}
	/*
	 * Testing ConcreteEdgesGraph...
	 */

	// Testing strategy for ConcreteEdgesGraph.toString()
	// �ֱ����һ��������ͼ��һ���ޱߵĿ�ͼ


	// tests for ConcreteEdgesGraph.toString()
	@Test
	public void testConcreteEdgesGraph() {
		assertFalse(graph.add(null));
		graph.add("a");
		graph.add("");
		graph.set("a", "b", 5);
		assertTrue(graph.sources("b").containsKey("a"));
		assertEquals((Integer)5, graph.sources("b").get("a"));
		graph.set("e", "c", 10);
		assertTrue(graph.targets("e").containsKey("c"));
		graph.set("a", "d", 15);
		graph.set("a", "e", 15);
		graph.set("a", "e", 10);
		assertEquals((Integer)10, graph.sources("e").get("a"));
		graph.remove("d");
		graph.set("a", "e", 0);
		assertFalse(graph.remove("h"));
	}
	//���Կ�ͼ
	@Test
	public void testemptyConcreteEdgesGraph() {
		Graph<String> emptygraph = new ConcreteEdgesGraph<>();
		assertEquals("", emptygraph.toString());
	}
	// ����toString����
	@Test
	public void testConcreteEdgesGraphtoString() {
		graph.set("a", "b", 5);
		assertEquals("a->b Ȩ��Ϊ5\n", graph.toString());
		
	}


	/*
	 * Testing Edge...
	 */
	static Edge<String> testedge = new Edge<String>("a", "b", 5);
	// Testing strategy for Edge
	// ���һЩgetter��setter��������һ��testedge���򵥲������Ƿ�����������
	// ����toString����������assertEquals�����������ȷ��

	@Test
	public void checkgetSource() {
		assertEquals(testedge.getSource(), "a");
	}

	@Test
	public void checkgetTarget() {
		assertEquals(testedge.getTarget(), "b");
	}

	@Test
	public void checkgetWeight() {
		assertEquals(testedge.getWeight(), 5);
	}
	
	@Test
	public void checktoString() {
		assertEquals("a->b Ȩ��Ϊ5\n",testedge.toString());
	}
}
