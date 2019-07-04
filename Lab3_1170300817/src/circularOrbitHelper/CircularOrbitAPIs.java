package circularOrbitHelper;

import circularOrbit.CircularOrbit;
import difference.Difference;

public class CircularOrbitAPIs {
	// Abstraction function:
	// ��һ��circularOrbit��ĳЩ���ܵĵ�����

	// Representation invariant:
	// circularOrbit������null

	// Safety from rep exposure:
	// ���б�Ҫ��ʱ��ʹ�÷����Կ���

	/**
	 * ���ӻ�
	 * 
	 * @param c
	 */
	public static <L, E> void visualize(CircularOrbit<L, E> c) {
		c.drawpicture();
	}

	/**
	 * ������Ϣ��
	 * 
	 * @param c ��Ҫ�����Orbit
	 * @return ��Ϣ��
	 */
	public static <L, E> double getObjectDistributionEntropy(CircularOrbit<L, E> c) {
		return c.getObjectDistributionEntropy();
	}

	/**
	 * ����������߼�����
	 * 
	 * @param c��Ҫ�����Orbit
	 * @param e1          ����1
	 * @param e2          ����2
	 * @return
	 */
	public static <L, E> int getLogicalDistance(CircularOrbit<L, E> c, E e1, E e2) {
		return c.getLogicalDistance(e1, e2);
	}

	/**
	 * �����ϵͳ��Ĳ�ͬ
	 * 
	 * @param c1 ��Ҫ�Ƚϵ�Orbit
	 * @param c2 ��Ҫ�Ƚϵ�Orbit
	 * @return Difference�����¼��֮ͬ��
	 */
	public static <L, E> Difference getDifference(CircularOrbit<L, E> c1, CircularOrbit<L, E> c2) {
		return c1.getDifference(c2);
	}

}