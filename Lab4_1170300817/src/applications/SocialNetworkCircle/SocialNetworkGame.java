package applications.SocialNetworkCircle;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import applications.AtomStructure.AtomGame;
import circularOrbitHelper.CircularOrbitAPIs;
import exception.illegalParameterException;
import exception.illegalTextGrammarException;
import exception.objectNoFoundException;
import exception.sameLabelException;
import exception.unDefinedPersonException;
import logRecord.logKeeper;
import logRecord.loggerFactory;
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
	private List<Person> personList = new ArrayList<Person>();
	private SocialNetCircularOrbit socialCircularOrbit = null;
	private SocialNetCircularOrbitBuilder socialCircularOrbitBuilder = new SocialNetCircularOrbitBuilder();
	private static Logger LOGGER = loggerFactory.createLogger(AtomGame.class);
	private logKeeper LOGKEEPER;

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
		System.out.println("11.\t��־��ѯ");
		System.out.println("end.\t ����");
	}

	public void gameMain() throws IOException {
		String inputString;
		String[] arguments;
		String name1;
		String name2;
		Person person1 = null;
		Person person2 = null;
		boolean exitflag = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			GameMenu();
			String input = reader.readLine();
			if (input != null) {
				input = input.trim();
			}else {
				continue;
			}
			switch (input) {
			case "1":// ��ȡ�ļ�
				try {
					System.out.println("������Ҫ��ȡ���ļ���������SocialNetworkCircle.txt\n");
					inputString = reader.readLine().trim();
					personList = socialCircularOrbitBuilder.createFromFile("src/txt/" + inputString);
//					personList=socialCircularOrbitBuilder.createFromFile("src/txt/" + "SocialNetworkCircle.txt");

				} catch (illegalTextGrammarException | unDefinedPersonException | sameLabelException
						| illegalParameterException e) {
					LOGGER.error(e.getMessage());
					System.out.println("�����¶�ȡ�ļ�");
					break;
				} catch (NumberFormatException e) {
					e.printStackTrace();
				}
				socialCircularOrbit = (SocialNetCircularOrbit) socialCircularOrbitBuilder.getConcreteCircularOrbit();
				centralUser = socialCircularOrbit.getCentralObject();
				LOGGER.debug("��ȡ�ɹ�");
				break;
			case "2":// ���ӻ�
				CircularOrbitAPIs.visualize(socialCircularOrbit);
				LOGGER.debug("���ӻ����");
				break;
			case "3":// ��ѯ����λ��
				System.out.println("������Ҫ��ѯ�ĺ�������");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				String nameString = arguments[0];
				try {
					Track track = socialCircularOrbit.getObjectTrack(nameString);
					System.out.println("Ŀ��λ��" + track.getName());
					LOGGER.debug("��ѯ���");
					break;
				} catch (objectNoFoundException e) {
					System.out.println("��ѯʧ��");
					LOGGER.error(e.getMessage());
					break;
				}
			case "4":// ��ӡ����ṹ
				System.out.println(socialCircularOrbit.toString());
				LOGGER.debug("��ӡ����ṹ");
				break;
			case "5":// �����µ�����
				System.out.println("�����µ�����");
				System.out.println("�������� ���� �Ա�");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				try {
					person1 = Person.getInstance(arguments[0], Integer.valueOf(arguments[1]), arguments[2]);
				} catch (sameLabelException | NumberFormatException | illegalParameterException e) {
					System.out.println("����ʧ��");
					LOGGER.error(e.getMessage());
					break;
				}
				LOGGER.debug("���ӳɹ�\"");
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
					LOGGER.error("�����������");
					break;
				}
				if (person1.getName().equals(centralUser.getName())) {
					socialCircularOrbit.addcentralRelation(person2, weight);
					LOGGER.debug("������ϵ�ɹ�");
				} else if (person2.getName().equals(centralUser.getName())) {
					socialCircularOrbit.addcentralRelation(person1, weight);
					LOGGER.debug("������ϵ�ɹ�");
				} else {
					socialCircularOrbit.addtrackRelation(person1, person2, weight);
					socialCircularOrbit.addtrackRelation(person2, person1, weight);
					LOGGER.debug("������ϵ�ɹ�");
				}
				socialCircularOrbit.reArrange();
				LOGGER.debug("������ϵͼ�ɹ�");
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
					LOGGER.error("�����������");
					break;
				}
				if (person1.getName().equals(centralUser.getName())) {
					socialCircularOrbit.addcentralRelation(person2, 0);
					LOGGER.debug("ɾ����ϵ�ɹ�");
				} else if (person2.getName().equals(centralUser.getName())) {
					socialCircularOrbit.addcentralRelation(person1, 0);
					LOGGER.debug("ɾ����ϵ�ɹ�");
				} else {
					socialCircularOrbit.addtrackRelation(person1, person2, 0);
					socialCircularOrbit.addtrackRelation(person2, person1, 0);
					LOGGER.debug("ɾ����ϵ�ɹ�");
				}
				LOGGER.debug("������ϵͼ�ɹ�");
				socialCircularOrbit.reArrange();
				break;
			case "8":// ������ֵ
				System.out.println("��Ϣ��Ϊ��" + socialCircularOrbit.getObjectDistributionEntropy() + "\n");
				LOGGER.debug("������Ϣ�����");
				break;
			case "9":// ������Ϣ��ɢ��
				System.out.println("������Ϣ��ɢ��:��������");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				name1 = arguments[0];
				Iterator<Person> iterator9 = personList.iterator();
				person1 = null;
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
				LOGGER.debug("������Ϣ��ɢ�����");
				break;
			case "10":// �����߼�����
				System.out.println("�����߼�����");
				System.out.println("��������1 ����2");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				name1 = arguments[0];
				name2 = arguments[1];
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
				System.out.println("���룺" + socialCircularOrbit.getLogicalDistance(person1, person2));
				LOGGER.debug("�����߼��������");
				break;
			case "11":// ��ѯ��־
				LOGKEEPER = new logKeeper();
				System.out.println("ѡ��ɸѡ������\n");
				System.out.println("1.\tʱ���");
				System.out.println("2.\t������");
				System.out.println("3.\t����");
				System.out.println("4.\t������");
				input = reader.readLine().trim();
				switch (input) {
				case "1":// ʱ���
					System.out.println("�����뿪ʼʱ�䣬��ʽ�ο���2019-05-19 02:05:14");
					inputString = reader.readLine().trim();
					final Instant time1 = Instant.parse(inputString.replace(' ','T')+"Z");
					System.out.println("���������ʱ�䣬��ʽ�ο���2019-05-19 02:05:14");
					inputString = reader.readLine().trim();
					final Instant time2 = Instant.parse(inputString.replace(' ','T')+"Z");
					System.out.println(LOGKEEPER.getFilterLogs(p -> p.getTime().isAfter(time1)&&p.getTime().isBefore(time2)));
					System.out.println("��ѯ���");
					break;
				case "2":// ������
					System.out.println("���������ͣ�ERROR��DEBUG");
					inputString = reader.readLine().trim();
					final String input1 = new String(inputString);
					System.out.println(LOGKEEPER.getFilterLogs(p -> p.getLogType().equals(input1)));
					System.out.println("��ѯ���");
					break;
				case "3":// ����
					System.out.println("����������");
					inputString = reader.readLine().trim();
					final String input2 = new String(inputString);
					System.out.println(LOGKEEPER.getFilterLogs(p -> p.getClassName().equals(input2)));
					System.out.println("��ѯ���");
					break;
				case "4":// ������
					System.out.println("�����뷽����");
					inputString = reader.readLine().trim();
					final String input3 = new String(inputString);
					System.out.println(LOGKEEPER.getFilterLogs(p -> p.getMethodName().equals(input3)));
					System.out.println("��ѯ���");
					break;
				default:
					LOGGER.debug("�������");
					break;
				}

				break;
			case "end":// ������Ϸ
				exitflag = true;
				LOGGER.debug("��Ϸ����");
				break;
			default:
				System.out.println("�������");
				LOGGER.debug("�������");
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
