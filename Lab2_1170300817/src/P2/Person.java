package P2;

import java.util.ArrayList;

public class Person {
	private String name;// ��������
	private static ArrayList<String> personlist = new ArrayList<String>();

	/* ���췽�� */
	public Person(String nameString) {
		if (personlist.contains(nameString)) {
			System.out.println("�����ظ�");
//			System.exit(0);
		} else {
			this.name = nameString;
			personlist.add(nameString);
		}
	}

	/* ��ȡ���� */
	public String getName() {
		return name;
	}
}
