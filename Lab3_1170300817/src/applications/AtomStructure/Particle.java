package applications.AtomStructure;

import phsicalObject.PhysicalObject;

public class Particle extends PhysicalObject {
	// Abstraction function:
	// ����AF�Ǵ�һ����¼��΢�����ֵĳ��������͵���ʵ΢����ӳ��

	// Representation invariant:
	// ���ֶ�����Ϊ��

	// Safety from rep exposure:
	// ͬ����

	public Particle(String name) {
		super(name);
	}

	/**
	 * �������
	 * 
	 * @return
	 */
	public static Particle getElectron() {
		Particle electon = new Particle("Electon");
		return electon;
	}

	/**
	 * ����ԭ�Ӻ�
	 * 
	 * @param name
	 * @return
	 */
	public static Particle getNucleus(String name) {
		Particle Nucleus = new Particle(name);
		return Nucleus;
	}
}
