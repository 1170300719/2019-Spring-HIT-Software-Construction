package centralObject;

import static org.junit.Assert.assertTrue;

public class CentralObject {
	// Abstraction function:
	// ����AF�Ǵ�һ����¼�����ֳ��������͵���ʵ����ṹ���������ӳ��

	// Representation invariant:
	// ���ֲ���Ϊ��

	// Safety from rep exposure:
	// ���ùؼ�����nameΪprivate final��ֹ����
	private final String name;

	/**
	 * �������
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * ���췽��
	 * 
	 * @param name
	 */
	public CentralObject(String name) {
		this.name = name;
		checkRep();
	}
	/**
	 * ���Ϸ��Եķ���
	 * �ж����ַǿ�
	 */
	public void checkRep() {
		assertTrue(this.name!=null);
	}
}
