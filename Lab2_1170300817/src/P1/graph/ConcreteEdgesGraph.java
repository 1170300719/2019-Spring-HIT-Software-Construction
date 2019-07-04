/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.graph;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * An implementation of Graph.
 * 
 * <p>
 * PS2 instructions: you MUST use the provided rep.
 */
public class ConcreteEdgesGraph<L> implements Graph<L> {
 
	private final Set<L> vertices = new HashSet<>();
	private final List<Edge<L>> edges = new ArrayList<>();

	// Abstraction function:
	// vertices ��ʾͼ�еĵ� edges ��ʾͼ�еı�
	// ����AF�Ǵ������ֳ����������ͣ�������ͼ��ӳ��

	// Representation invariant:
	// edges Ӧ������ʼ�ڵ㣬����Ӧ���Ǵ���0��ʵ��
	// ÿ��vertex������vertices������
	// ÿ����֮�䣬���ֻ����һ����

	// Safety from rep exposure:
	// ����vertices��edgesΪprivate final
	// ���б�Ҫ��ʱ��ʹ�÷����Կ���

	@Override
	public boolean add(L vertex) {
		if (vertex == null || vertex == "")
			return false;
		boolean ans = this.vertices.add(vertex);
		return ans;
	}

	@Override
	public int set(L source, L target, int weight) {
		int currentweight = 0;
		if (weight != 0) {
			for (Edge<L> e : edges) {
				if (e.getSource().equals(source) && e.getTarget().equals(target)) {
					currentweight = e.getWeight();
					edges.remove(e);
					break;
				}
			}
			vertices.add(source);
			vertices.add(target);
			edges.add(new Edge<L>(source, target, weight));
		} else {
			for (Edge<L> e : edges) {
				if (e.getSource().equals(source) && e.getTarget().equals(target)) {
					edges.remove(e);
					currentweight = 0;
					break;
				}
			}
		}
		checkRep();
		return currentweight;
	}

	/**
	 * Remove a vertex from this graph; any edges to or from the vertex are also
	 * removed.
	 * 
	 * @param vertex label of the vertex to remove
	 * @return true if this graph included a vertex with the given label; otherwise
	 *         false (and this graph is not modified)
	 */
	@Override
	public boolean remove(L vertex) {
		if (vertices.contains(vertex)) {
			vertices.remove(vertex);
			Iterator<Edge<L>> it = edges.iterator();
			while (it.hasNext()) {
				Edge<L> e = it.next();
				if (e.getSource().equals(vertex) || e.getTarget().equals(vertex)) {
					it.remove();
				}
			}
			checkRep();
			return true;
		} else {
			checkRep();
			return false;
		}
	}

	/**
	 * Get all the vertices in this graph.
	 * 
	 * @return the set of labels of vertices in this graph
	 */
	@Override
	public Set<L> vertices() {
		return new HashSet<L>(vertices);
	}

	/**
	 * Get the source vertices with directed edges to a target vertex and the
	 * weights of those edges.
	 * 
	 * @param target a label
	 * @return a map where the key set is the set of labels of vertices such that
	 *         this graph includes an edge from that vertex to target, and the value
	 *         for each key is the (nonzero) weight of the edge from the key to
	 *         target
	 */

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> map = new HashMap<>();
		for (Edge<L> e : edges) {
			if (e.getTarget().equals(target)) {
				map.put(e.getSource(), e.getWeight());
			}
		}
		return map;
	}

	/**
	 * Get the target vertices with directed edges from a source vertex and the
	 * weights of those edges.
	 * 
	 * @param source a label
	 * @return a map where the key set is the set of labels of vertices such that
	 *         this graph includes an edge from source to that vertex, and the value
	 *         for each key is the (nonzero) weight of the edge from source to the
	 *         key
	 */

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> map = new HashMap<>();
		for (Edge<L> e : edges) {
			if (e.getSource().equals(source)) {
				map.put(e.getTarget(), e.getWeight());
			}
		}
		checkRep();
		return map;
	}

	/**
	 * ����ʾ������
	 */

	private void checkRep() {
		final int sizeOfEdges = edges.size();
		final int sizeOfVertices = vertices.size();
		int maxNumberOfEdges = sizeOfVertices * (sizeOfVertices - 1) / 2;
		assertTrue(maxNumberOfEdges >= sizeOfEdges);
	}

	@Override
	public String toString() {
		String s = "";
		for (Edge<L> e : edges) {
			s = s + e.toString();
		}
		return s;
	}
}

/**
 * specification Immutable. This class is internal to the rep of
 * ConcreteEdgesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Edge<L> {

	private final L source, target;
	private final int weight;

	// Abstraction function:
	// ��source��target��weight��ɵĳ��������ͣ�������߼��ϵ�ӳ��

	// Representation invariant:
	// weight >=0 source��target��Ϊ��

	// Safety from rep exposure:
	// ����Ϣ����Ϊprivate�������getter����
	
	/**
	 * @param s �µıߵ�source
	 * @param t �µıߵ�target
	 * @param w �µıߵ�weight
	 */
	public Edge(L s, L t, int w) {
		source = s;
		target = t;
		weight = w;
	}

	/**
	 *  ����ʾ������
	 */
	public void checkRep() {
		assert source != null;
		assert source != null;
		assert weight > 0;
	}

	/**
	 * 
	 * @return ���رߵ�source
	 */
	public L getSource() {
		return source;
	}

	/**
	 * 
	 * @return ���رߵ�target
	 */
	public L getTarget() {
		return target;
	}

	/**
	 * 
	 * @return ���رߵ�weight
	 */
	public int getWeight() {
		checkRep();
		return weight;
	}

	@Override
	public String toString() {
		return source.toString() + "->" + target.toString() + " Ȩ��Ϊ" + weight + '\n';
	}

}
