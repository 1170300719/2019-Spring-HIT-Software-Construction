package phsicalObject;

public class PhysicalObject {
	// Abstraction function:
	// ����AF�Ǵ�һ����¼�����ֵĳ��������͵���ʵ�˶�Ա��ӳ��

	// Representation invariant:
	// ���ֲ���Ϊ��

	// Safety from rep exposure:
	// ���ùؼ�����nameΪprivate final��ֹ����
	protected final String name;

	/**
	 * �������
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**���췽��
	 * @param name ��������
	 */
	public PhysicalObject(String name) {
		this.name = name;
	}
}
