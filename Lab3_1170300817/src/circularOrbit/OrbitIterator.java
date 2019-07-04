package circularOrbit;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import track.Track;

public class OrbitIterator<E extends Comparable<E>> implements Iterator<E> {
	private List<E> ObjectList;
	private int index;
	private int size;

	/**
	 * ���췽������ʼ�� ObjectList��ΪOrbitIterator�ĵ������
	 * 
	 * @param orbitMap ��������
	 */
	public OrbitIterator(Map<Track, List<E>> orbitMap) {
		index = 0;
		size = 0;
		for (Map.Entry<Track, List<E>> entry : orbitMap.entrySet()) {
			size += entry.getValue().size();
		}
		ObjectList = orbitMap.keySet().stream().sorted().map(orbitMap::get).reduce(new ArrayList<>(),
				(result, element) -> {
					Collections.sort(element);
					result.addAll(element);
					return result;
				});
	}

	/**
	 * �ж��Ƿ�����һ��
	 * 
	 * @return �Ƿ���true
	 */
	@Override
	public boolean hasNext() {
		return index < size;
	}

	/**
	 * ��ȡ��һ����������ķ���
	 * 
	 * @return ��һ��E����
	 */
	@Override
	public E next() {
		return ObjectList.get(index++);
	}

}
