package applications.SocialNetworkCircle;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import circularOrbitHelper.CircularOrbitAPIs;
import track.Track;

public class SocialNetworkGame {
	// Abstraction function:
	// ����AF��һ���������˼ʹ�ϵ���ݺͲ����ĳ��������͵���ʵ���˼ʹ�ϵ�㼶��������ӳ��

	// Representation invariant:
	// socialCircularOrbit!=null
	// centralUser!=null

	// Safety from rep exposure:
	// ���ùؼ�����personList��centralUser��socialCircularOrbitΪprivate 
	// ���б�Ҫ��ʱ��ʹ�÷����Կ���
	private Person centralUser;
	private final List<Person> personList = new ArrayList<Person>();
	private final List<relationKeeper> keeperList = new ArrayList<relationKeeper>();
	private SocialNetCircularOrbit socialCircularOrbit = null;
	private  SocialNetCircularOrbitBuilder socialCircularOrbitBuilder = new SocialNetCircularOrbitBuilder();
	public int[] DefaultRadius = new int[10];

	public void GameMenu() {
		System.out.println("1.\t��ȡ�ļ�����ϵͳ");
		System.out.println("2.\t���ӻ�");
		System.out.println("3.\t��ѯ����λ��");
		System.out.println("4.\t��ӡ����ṹ");
		System.out.println("5.\t�����µ�����");
		System.out.println("6.\t�����µĹ�ϵ����������");
		System.out.println("7.\tɾ��һ����ϵ����������");
		System.out.println("8.\t������ֵ");
		System.out.println("9.\t������Ϣ��ɢ��");
		System.out.println("10.\t���������û�֮����߼�����");
		System.out.println("end.\t ����");
	}

	public void gameMain() throws IOException {
		String inputString;
		String[] arguments;
		String name1;
		String name2;
		Person person1=null;
		Person person2=null;
		boolean exitflag = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			GameMenu();
			String input = reader.readLine().trim();
			switch (input) {
			case "1":// ��ȡ�ļ�
				BufferedReader in = new BufferedReader(new FileReader(new File("src/txt/SocialNetworkCircle_Larger.txt")));
				String fileline;
				String centralUserPatternString = "CentralUser\\s*::=\\s*<(\\w+),\\s*(\\d+),\\s*([MF])>";
				String friendPatternString = "Friend\\s*::=\\s*<(\\w+),\\s*(\\d+),\\s*([MF])>";
				String socialTiePatternString = "SocialTie\\s*::=\\s*<(\\w+),\\s*(\\w+),\\s*([01]\\.{1}\\d+)>";

				while ((fileline = in.readLine()) != null) {
					Matcher centralUserMatcher = Pattern.compile(centralUserPatternString).matcher(fileline);
					Matcher friendMatcher = Pattern.compile(friendPatternString).matcher(fileline);
					Matcher socialTieMatcher = Pattern.compile(socialTiePatternString).matcher(fileline);
					if (centralUserMatcher.find()) {
						String nameString = centralUserMatcher.group(1);
						int age = Integer.valueOf(centralUserMatcher.group(2));
						String gender = centralUserMatcher.group(3);
						centralUser = Person.getInstance(nameString, age, gender);
					} else if (friendMatcher.find()) {
						String nameString = friendMatcher.group(1);
						int age = Integer.valueOf(friendMatcher.group(2));
						String gender = friendMatcher.group(3);
						Person person = Person.getInstance(nameString, age, gender);
						personList.add(person);
						socialCircularOrbitBuilder.createCircularOrbit();
					} else if (socialTieMatcher.find()) {
						Double weight = Double.valueOf(socialTieMatcher.group(3));
						relationKeeper currKeeper = new relationKeeper(socialTieMatcher.group(1),
								socialTieMatcher.group(2), weight);
						keeperList.add(currKeeper);
					}
				}
				socialCircularOrbitBuilder.createCircularOrbit();
				assert (centralUser!=null):"�����û���ʧ������ʧ��";
				assert (personList.size()!=0):"�û���ʧ������ʧ��";
				assert (keeperList.size()!=0):"��ϵ��ʧ������ʧ��";
				socialCircularOrbitBuilder.bulidRelations(centralUser, personList, keeperList);
				socialCircularOrbit = (SocialNetCircularOrbit) socialCircularOrbitBuilder.getConcreteCircularOrbit();
				System.out.println("��ȡ�ɹ�");
				break;
			case "2":// ���ӻ�
				CircularOrbitAPIs.visualize(socialCircularOrbit);
				break;
			case "3":// ��ѯ����λ��
				System.out.println("������Ҫ��ѯ�ĺ�������");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				String nameString = arguments[0];
				Iterator<Person> iterator = personList.iterator();
				Person p1 = null;
				while (iterator.hasNext()) {
					Person person = iterator.next();
					if (person.getName().equals(nameString)) {
						p1 = person;
					}
				}
				if (p1 == null) {
					System.out.println("�����������");
					break;
				}
				Track track = socialCircularOrbit.getObjectTrack(p1);
				System.out.println("Ŀ��λ��" + track.getName());
				break;
			case "4":// ��ӡ����ṹ
				System.out.println(socialCircularOrbit.toString());
				break;
			case "5":// �����µ�����
				System.out.println("�����µ�����");
				System.out.println("�������� ���� �Ա�");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				 person1 = Person.getInstance(arguments[0], Integer.valueOf(arguments[1]), arguments[2]);
				personList.add(person1);
				break;
			case "6":// �����µĹ�ϵ����������
				System.out.println("�����µĹ�ϵ");
				System.out.println("��������1 ����2 Ȩ��");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				name1 = arguments[0];
				name2 = arguments[1];
				Double weight = Double.valueOf(arguments[2]);
				Iterator<Person> iterator1 = personList.iterator();
				 person1 = null;
				 person2 = null;
				while (iterator1.hasNext()) {
					Person person = iterator1.next();
					if (person.getName().equals(name1)) {
						person1 = person;
					}
					if (person.getName().equals(name2)) {
						person2 = person;
					}
				}
				if (person1 == null || person2 == null) {
					System.out.println("�����������");
					break;
				}
				if (person1.getName().equals(centralUser.getName())) {
					socialCircularOrbit.addcentralRelation(person2, weight);
				} else if (person2.getName().equals(centralUser.getName())) {
					socialCircularOrbit.addcentralRelation(person1, weight);
				} else {
					socialCircularOrbit.addtrackRelation(person1, person2, weight);
					socialCircularOrbit.addtrackRelation(person2, person1, weight);
				}
				socialCircularOrbit.reArrange();
				break;
			case "7":// ɾ��һ����ϵ����������
				System.out.println("ɾ��һ����ϵ");
				System.out.println("��������1 ����2");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				 name1 = arguments[0];
				 name2 = arguments[1];
				Iterator<Person> iterator2 = personList.iterator();
				 person1 = null;
				 person2 = null;
				while (iterator2.hasNext()) {
					Person personxy = iterator2.next();
					if (personxy.getName().equals(name1)) {
						person1 = personxy;
					}
					if (personxy.getName().equals(name2)) {
						person2 = personxy;
					}
				}
				if (person1 == null || person2 == null) {
					System.out.println("�����������");
					break;
				}
				if (person1.getName().equals(centralUser.getName())) {
					socialCircularOrbit.addcentralRelation(person2, 0);
				} else if (person2.getName().equals(centralUser.getName())) {
					socialCircularOrbit.addcentralRelation(person1, 0);
				} else {
					socialCircularOrbit.addtrackRelation(person1, person2, 0);
					socialCircularOrbit.addtrackRelation(person2, person1, 0);
				}
				
				socialCircularOrbit.reArrange();
				break;
			case "8"://������ֵ
				System.out.println("��Ϣ��Ϊ��" + socialCircularOrbit.getObjectDistributionEntropy() + "\n");
				break;
			case "9"://������Ϣ��ɢ��
				System.out.println("������Ϣ��ɢ��:��������");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				 name1 = arguments[0];
				Iterator<Person> iterator9 = personList.iterator();
				 person1=null;
				while (iterator9.hasNext()) {
					Person personTmp = iterator9.next();
					if (personTmp.getName().equals(name1)) {
						person1 = personTmp;
					}
				}
				if (person1 == null) {
					System.out.println("�����������");
					break;
				}
				System.out.println("��Ϣ��ɢ��Ϊ��" + socialCircularOrbit.getIntimacy(person1) + "\n");
				break;
			case "10"://�����߼�����
				System.out.println("�����߼�����");
				System.out.println("��������1 ����2");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				name1 = arguments[0];
				name2= arguments[1];
				Iterator<Person> iterator10 = personList.iterator();
				 person1 = null;
				 person2 = null;
				while (iterator10.hasNext()) {
					Person personxy = iterator10.next();
					if (personxy.getName().equals(name1)) {
						person1 = personxy;
					}
					if (personxy.getName().equals(name2)) {
						person2 = personxy;
					}
				}
				if (person1 == null || person2 == null) {
					System.out.println("�����������");
					break;
				}
				System.out.println("���룺"+socialCircularOrbit.getLogicalDistance(person1, person2));
				break;
			case "end":// ������Ϸ
				exitflag = true;
				break;
			default:
				System.out.println("�������");
				break;
			}
			if (exitflag)
				break;

		}
	}

	public static void main(String[] args) throws IOException {
		new SocialNetworkGame().gameMain();
	}
}
