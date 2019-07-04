/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.*;

import org.junit.Test;

/**
 * Tests for ConcreteVerticesGraph.
 * 
 * This class runs the GraphInstanceTest tests against ConcreteVerticesGraph, as
 * well as tests for that particular implementation.
 * 
 * Tests against the Graph spec should be in GraphInstanceTest.
 */
public class ConcreteVerticesGraphTest extends GraphInstanceTest {

	/*
	 * Provide a ConcreteVerticesGraph for tests in GraphInstanceTest.
	 */
	Graph<String> VerticesGraph = new ConcreteVerticesGraph<>();

	@Override
	public Graph<String> emptyInstance() {
		return VerticesGraph;
	}

	/*
	 * Testing ConcreteVerticesGraph...
	 */

	// Testing strategy for ConcreteVerticesGraph.toString()
	// ���ֵȼ��ࣺ��ͼ���е��ޱ�ͼ���б�Ҳ�е��ͼ

	// tests for ConcreteVerticesGraph.toString()
	@Test
	public void testConcreteVerticesGraphtoString() {
		VerticesGraph.add("a");
		VerticesGraph.set("a", "b", 5);
		assertTrue(VerticesGraph.sources("b").containsKey("a"));
		assertEquals((Integer) 5, VerticesGraph.sources("b").get("a"));

		VerticesGraph.set("a", "c", 10);
		VerticesGraph.set("a", "c", 15);
		assertTrue(VerticesGraph.targets("a").containsKey("c"));
		assertEquals((Integer) 15, VerticesGraph.sources("c").get("a"));

		VerticesGraph.set("a", "d", 15);
		VerticesGraph.set("a", "e", 15);
		VerticesGraph.remove("d");
		assertEquals("This graph has 4 vertices", VerticesGraph.toString());
		VerticesGraph.set("a", "e", 0);
		assertFalse(VerticesGraph.targets("a").containsKey("e"));
	}
	/*
	 * Testing Vertex...
	 */
	// Testing strategy for Vertex
	// ����get set����������remove����ʱ�Է���ֵ���ֵȼ��ࣺ����ֵ0�ͷ�0����Ӧɾȥ�ɹ���ʧ�ܶ����
	// ����assertEquals�򵥲���toString����
	@Test
	public void testgetName() {
		Vertex<String> testVertex = new Vertex<String>("a");
		assertEquals("a", testVertex.getName());
	}

	@Test
	public void testsetgetremoveSource() {
		Vertex<String> testVertex = new Vertex<String>("a");
		testVertex.setSouce("b", 4);
		assertTrue(testVertex.getSources().containsKey("b"));
		assertTrue(testVertex.getSources().get("b") == 4);
		assertEquals(4, testVertex.removeSource("b"));
		assertEquals(0, testVertex.removeSource("b"));
		assertFalse(testVertex.getSources().containsKey("b"));
	}

	@Test
	public void testsetgetremoveTarget() {
		Vertex<String> testVertex = new Vertex<String>("a");
		testVertex.setTarget("c", 5);
		assertTrue(testVertex.getTargets().containsKey("c"));
		assertTrue(testVertex.getTargets().get("c") == 5);
		assertEquals(5, testVertex.removeTarget("c"));
		assertEquals(0, testVertex.removeTarget("c"));
		assertFalse(testVertex.getTargets().containsKey("c"));
	}
	@Test
	public void testremoveTarget() {
		Vertex<String> testVertex = new Vertex<String>("a");
		testVertex.setTarget("c", 5);
		assertTrue(testVertex.getTargets().containsKey("c"));
	}

	

	@Test
	public void checktoString() {
		Vertex<String> testVertex = new Vertex<String>("a");
		testVertex.setSouce("b", 4);
		testVertex.setTarget("c", 5);
		assertEquals(testVertex.toString(), "Vertex a has 1 sources and 1 targets");
	}
}
