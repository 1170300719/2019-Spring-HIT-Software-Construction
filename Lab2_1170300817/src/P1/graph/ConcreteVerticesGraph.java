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
public class ConcreteVerticesGraph<L> implements Graph<L> {

	private final List<Vertex<L>> vertices = new ArrayList<>();
 
	// Abstraction function:
	// ��vertices�����ͼ����ͼ�ṹ��ӳ��
	
	// Representation invariant:
	// vertices�������ظ���
	
	// Safety from rep exposure:
	// ����verticesΪprivate final
	// ��Ҫʱʹ�÷����Կ���

	/**
	 * ���Ա�ʾ������
	 */
	private void checkRep() {
		assert vertices != null;
	}

	@Override
	public boolean add(L vertex) {
		for (Vertex<L> v : vertices) {
			if (v.getName().equals(vertex)) {
				return false;
			}
		}
		vertices.add(new Vertex<L>(vertex));
		checkRep();
		return true;
	}

	@Override
	public int set(L source, L target, int weight) {
		this.add(source);
		this.add(target);
		Iterator<Vertex<L>> it = vertices.iterator();
		while (it.hasNext()) {
			Vertex<L> v = it.next();
			if (v.getName().equals(source)) {
				v.setTarget(target, weight);
			}
			if (v.getName().equals(target)) {
				v.setSouce(source, weight);
			}
		}
		checkRep();
		return weight;
	}

	@Override
	public boolean remove(L vertex) {
		boolean flag = false;
		Iterator<Vertex<L>> it = vertices.iterator();
		while (it.hasNext()) {
			Vertex<L> v = it.next();
			if (v.getName() == vertex) {
				it.remove();
				flag = true;
			} else {
				if (v.getSources().containsKey(vertex)) {
					v.removeSource(vertex);
				}
				if (v.getTargets().containsKey(vertex)) {
					v.removeTarget(vertex);
				}
			}
		}
		checkRep();
		return flag;
	}

	@Override
	public Set<L> vertices() {
		Set<L> set = new HashSet<>();
		for (Vertex<L> v : vertices) {
			set.add(v.getName());
		}
		return set;
	}

	@Override
	public Map<L, Integer> sources(L target) {
		Map<L, Integer> map = new HashMap<>();
		for (Vertex<L> v : vertices) {
			if (v.getName() == target) {
				map = v.getSources();
				break;
			}
		}
		return new HashMap<L, Integer>(map);
	}

	@Override
	public Map<L, Integer> targets(L source) {
		Map<L, Integer> map = new HashMap<>();
		for (Vertex<L> v : vertices) {
			if (v.getName() == source) {
				map = v.getTargets();
				break;
			}
		}
		return new HashMap<L, Integer>(map);
	}

	@Override
	public String toString() {
		return String.format("This graph has %d vertices", this.vertices.size());
	}

}

/**
 * specification Mutable. This class is internal to the rep of
 * ConcreteVerticesGraph.
 * 
 * <p>
 * PS2 instructions: the specification and implementation of this class is up to
 * you.
 */
class Vertex<L> {
	private final L name;
	private final Map<L, Integer> sources;
	private final Map<L, Integer> targets;
	// Abstraction function:
	// ��name sources targets��ʾ���������ͣ���һ������ͼ�ж����ӳ��

	// Representation invariant:
	// ����weight��ֵӦ����Զ����0

	// Safety from rep exposure:
    // ����Ϣ����Ϊprivate final�������getter����
	/**
	 * �����ִ����µĵ�
	 * 
	 * @param name �������
	 */
	public Vertex(L name) {
		this.name = name;
		sources = new HashMap<>();
		targets = new HashMap<>();
	}

	/**
	 * ����ʾ������
	 */
	private void checkRep() {
		Set<L> keys1 = sources.keySet();
		if (keys1 != null) {
			Iterator<L> iterator = keys1.iterator();
			while (iterator.hasNext()) {
				L key = iterator.next();
				Integer value = sources.get(key);
				assertTrue(value > 0);
			}
		}
		Set<L> keys2 = targets.keySet();
		if (keys2 != null) {
			Iterator<L> iterator = keys2.iterator();
			while (iterator.hasNext()) {
				L key = iterator.next();
				Integer value = sources.get(key);
				assertTrue(value > 0);
			}
		}
	}

	/**
	 * @return ���name
	 */
	public L getName() {
		return name;
	}

	/**
	 * @return �����ܵ���˵�ģ��㣬�߳�����ɵ�HashMap
	 */
	public Map<L, Integer> getSources() {
		return new HashMap<L, Integer>(sources);
	}
	/**
	 * @return ���д˵��ܵ���ģ��㣬�߳�����ɵ�HashMap
	 */
	public Map<L, Integer> getTargets() {
		return new HashMap<L, Integer>(targets);
	}

	/**
	 * ���weight=0��ɾȥ��ǰ���target���ɹ�����ɾȥtarget��weight�������ڷ���0
	 * ���weight��=0��Ϊ��ǰ������һ��target������Ϊweight������õ��Ѵ��ڣ����ؾɵ�weight�����򷵻�0
	 * @param target �ߵ���ֹ��
	 * @param weight �ߵĳ���
	 * @return 
	 */
	public int setTarget(L target, int weight) {
		Integer currentWeight = 0;
		if (weight == 0) {
			currentWeight = this.removeTarget(target);
		} else if (weight > 0) {
			currentWeight = targets.put(target, weight);
			currentWeight = (currentWeight == null )? 0 : currentWeight;
		}
		return currentWeight;
	}
	/**
	 * ���weight=0��ɾȥ��ǰ���source���ɹ�����ɾȥsource��weight�������ڷ���0
	 * ���weight��=0��Ϊ��ǰ������һ��source������Ϊweight������õ��Ѵ��ڣ����ؾɵ�weight�����򷵻�0
	 * @param source �ߵ���ʼ��
	 * @param weight �ߵĳ���
	 * @return 
	 */
	public int setSouce(L source, int weight) {
		Integer previousWeight = 0;
		if (weight == 0) {
			previousWeight = this.removeSource(source);
		} else if (weight > 0) {
			previousWeight = sources.put(source, weight);
			previousWeight = previousWeight == null ? 0 : previousWeight;
		}
		checkRep();
		return previousWeight;
	}

	/**
	 * @param source ɾȥ��source
	 * @return ���ڷ��ؾɵ�weight�����򷵻�0
	 */
	public int removeSource(L source) {
		Integer weight = sources.remove(source);
		return weight == null ? 0 : weight;
	}

	/**
	 * @param target ɾȥ��target
	 * @return ���ڷ��ؾɵ�weight�����򷵻�0
	 */
	public int removeTarget(L target) {
		Integer weight = targets.remove(target);
		return weight == null ? 0 : weight;
	}

	@Override
	public String toString() {
		return String.format("Vertex %s has %d sources and %d targets", this.getName().toString(),
				this.getSources().size(), this.getTargets().size());
	}
}
