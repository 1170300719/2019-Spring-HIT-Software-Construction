package applications.SocialNetworkCircle;

import phsicalObject.PhysicalObject;

public class Person extends PhysicalObject {
	// Abstraction function:
	// ����AF�Ǵ�һ����¼�����֣����䣬gender�ĳ��������͵���ʵ�˼������е��˵�ӳ��

	// Representation invariant:
	// ���֣����䣬gender������Ϊ��

	// Safety from rep exposure:
	// ͬ����
	// ���ùؼ�����age,genderΪprivate final��ֹ����
	private final int age;
	private final String gender;

	public Integer getAge() {
		return age;
	}

	public String getGender() {
		return gender;
	}

	/**
	 * @param name
	 * @param age
	 * @param gender
	 */
	public Person(String name, int age, String gender) {
		super(name);
		this.age = age;
		this.gender = gender;
	}

	/**
	 * ��̬��������
	 * 
	 * @param name   ����
	 * @param age    ����
	 * @param gender �Ա�
	 * @return ʵ��
	 */
	public static Person getInstance(String name, int age, String gender) {
		Person p = new Person(name, age, gender);
		return p;
	}

}
