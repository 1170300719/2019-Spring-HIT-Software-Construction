package P3;

import java.util.ArrayList;
import java.util.List;

public class Person {
	private String name;//��������
	private List<Person> friendList;//��������

	private static ArrayList<String> personlist = new ArrayList<String>();

	/* ���췽�� */
	public Person(String nameString) {
		if(personlist.contains(nameString))
		{
			System.out.println("�����ظ�");
			System.exit(0);
		}
		else {
			this.name = nameString;
			friendList = new ArrayList<>();
			personlist.add(nameString);
		}

	}
	/* �������� */
	public void addFriend(Person pb) {
		friendList.add(pb);
	}
	/* ��ȡ���� */
	public String getName() {
		return name;
	}
	/* ��ȡ�����б� */
	public List<Person> getFriendList() {
		return this.friendList;
	}
}
