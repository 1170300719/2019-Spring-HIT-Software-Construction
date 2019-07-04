package applications.SocialNetworkCircle;

import java.util.ArrayList;
import java.util.regex.Pattern;

import exception.illegalParameterException;
import exception.sameLabelException;
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
	private static final ArrayList<String> nameList = new ArrayList<String>();

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
	 * @throws sameLabelException
	 * @throws illegalParameterException
	 */
	public static Person getInstance(String name, int age, String gender)
			throws sameLabelException, illegalParameterException {

		for (String s : nameList) {
			if (s.equals(name)) {
				throw new sameLabelException(name + "Ϊ���Ķ����Ѿ�����");
			}
		}

		Person p = new Person(name, age, gender);
		p.checkRep();
		nameList.add(name);
		return p;

	}

	public void checkRep() throws illegalParameterException {
		if (!Pattern.matches("(\\s*[A-Za-z0-9]+)", name)) {
			throw new illegalParameterException(name + ":�������ִ���");
		}
		if (!Pattern.matches("([MF])", gender)) {
			throw new illegalParameterException(gender + ":�����Ա����");
		}
		if (!Pattern.matches("(\\d{1,3})", String.valueOf(age))) {
			throw new illegalParameterException(gender + ":�����������");
		}

	}
}
