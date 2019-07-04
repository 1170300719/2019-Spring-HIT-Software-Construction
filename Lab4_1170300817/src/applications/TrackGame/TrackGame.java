package applications.TrackGame;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import applications.AtomStructure.AtomGame;
import applications.TrackGame.Strategy.RandomStrategy;
import applications.TrackGame.Strategy.RecordStrategy;
import applications.TrackGame.Strategy.Strategy;
import circularOrbitHelper.CircularOrbitAPIs;
import exception.illegalParameterException;
import exception.illegalTextGrammarException;
import exception.objectNoFoundException;
import exception.sameLabelException;
import logRecord.logKeeper;
import logRecord.loggerFactory;
import track.Track;

public class TrackGame {
	// Abstraction function:
	// ����AF��һ�������ű����������ݺͲ����ĳ��������͵���ʵ�ı�������ϵͳ��ӳ��

	// Representation invariant:
	// ������������������й��������ͬ�뾶
	// �������غŵ��˶�Ա

	// Safety from rep exposure:
	// ���ùؼ�����athleteList��trackOrbitList��trackBuilderΪprivate final
	// ���б�Ҫ��ʱ��ʹ�÷����Կ���
//	private Integer gameType;
	private Integer trackNum;
	private List<Athlete> athleteList = new LinkedList<>();
	private final List<TrackCircularOrbit> trackOrbitList = new LinkedList<>();
	private final TrackCircularOrbitBuilder trackBuilder = new TrackCircularOrbitBuilder();
	private static Logger LOGGER = loggerFactory.createLogger(AtomGame.class);
	private logKeeper LOGKEEPER;

	/**
	 * �˵�
	 */
	public void GameMenu() {
		System.out.println("1.\t��ȡ�ļ�");
		System.out.println("2.\t�����������");
		System.out.println("3.\t���ɼ���������");
		System.out.println("4.\t����������");
		System.out.println("5.\t�������˶�Ա");
		System.out.println("6.\tɾ���˶�Ա");
		System.out.println("7.\tɾ������");
		System.out.println("8.\t������Ϣ��");
		System.out.println("9.\t��������ѡ�ֵ����������");
		System.out.println("10.\t���ӻ�");
		System.out.println("11.\t��ʾ������Ϣ");
		System.out.println("12.\t��־��ѯ");
//		System.out.println("12.\t4�˽�����");
		System.out.println("end.\t ����");
	}

	/**
	 * ��Ϸ����
	 * 
	 * @throws IOException
	 * @throws objectNoFoundException
	 */
	public void gameMain() throws IOException, objectNoFoundException {
		String inputString;
		String[] arguments;
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
					System.out.println("������Ҫ��ȡ���ļ���������TrackGame.txt\n");
					inputString = reader.readLine().trim();
					athleteList = trackBuilder.createFromFile("src/txt/" + inputString);
//					athleteList=trackBuilder.createFromFile("src/txt/" + "TrackGame.txt");

				} catch (illegalTextGrammarException | sameLabelException | illegalParameterException e) {
					System.out.println(e.getMessage());
					System.out.println("�����¶�ȡ�ļ�");
					break;
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
//				gameType = trackBuilder.getGameType();
				trackNum = trackBuilder.getTrackNum();
				System.out.println("�ļ���ȡ�ɹ�");
				break;
			case "2":// �����������
				Strategy strategy1 = new RandomStrategy();
				this.arrangeOrbit(strategy1);
				checkRep();
				arrangeOutout();
				System.out.println("�������\n");
				break;
			case "3":// ���ɼ���������
				Strategy strategy2 = new RecordStrategy();
				this.arrangeOrbit(strategy2);
				checkRep();
				arrangeOutout();
				System.out.println("�������\n");
				break;
			case "4":// ���ӹ����
				trackNum++;
				System.out.println("�������" + trackNum + "\n");
				System.out.println("�����·��䷽��\n");
				break;
			case "5":// �����˶�Ա
				System.out.println("������Ҫ���ӵ��˶�Ա�����֣�id�����������䣬��óɼ�\n");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				String nameString = arguments[0];
				int idNum = Integer.valueOf(arguments[1]);
				String nationality = arguments[2];
				int age = Integer.valueOf(arguments[3]);
				double bestRecord = Double.valueOf(arguments[4]);
				Athlete a;
				try {
					a = Athlete.getInstance(nameString, idNum, nationality, age, bestRecord);
					athleteList.add(a);
				} catch (sameLabelException | illegalParameterException e) {
					System.out.println(e.getMessage());
				}
				System.out.println("���ӳɹ�\n");
				break;
			case "6":// ɾ���˶�Ա
				System.out.println("������Ҫɾ�����˶�Ա��id\n");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				int id = Integer.valueOf(arguments[0]);
				Iterator<Athlete> iterator = athleteList.iterator();
				boolean flag1 = false;
				while (iterator.hasNext()) {
					if (iterator.next().getIdNum() == id) {
						iterator.remove();
						System.out.println("ɾ���ɹ�\n");
						flag1 = true;
					}
				}
				if (!flag1) {
					System.out.println("ɾ��ʧ��\n");
				}

				break;
			case "7":// ɾ������
				trackNum--;
				System.out.println("�����-1");
				System.out.println("��ǰ�������" + trackNum);
				System.out.println("�����·��䷽��");
				break;
			case "8":// ������Ϣ��
				System.out.println("������Ҫ������Ϣ�صĹ��ϵͳ���");
				System.out.println("���뷶ΧΪ0����" + (trackOrbitList.size() - 1));
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				int currentOrbit = Integer.valueOf(arguments[0]);
				System.out.println("��Ϣ��Ϊ��" + trackOrbitList.get(currentOrbit).getObjectDistributionEntropy() + "\n");
				break;
			case "9":// ��������ѡ�ֵ����������
				System.out.println("����������Ҫ�������˶�Ա��id\n");
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				int id1 = Integer.valueOf(arguments[0]);
				int id2 = Integer.valueOf(arguments[1]);
				Athlete a1 = null;
				Athlete a2 = null;

				Iterator<Athlete> iterator1 = athleteList.iterator();

				while (iterator1.hasNext()) {
					Athlete tmp = iterator1.next();
					if (tmp.getIdNum() == id1) {
						a1 = tmp;
					}
					if (tmp.getIdNum() == id2) {
						a2 = tmp;
					}
				}
				Track t1 = null;
				Track t2 = null;
				TrackCircularOrbit o1 = null;
				TrackCircularOrbit o2 = null;
				if (a1 == null || a2 == null) {
					System.out.println("ѡ�ֲ�����");
					break;
				}
				for (TrackCircularOrbit Orb : trackOrbitList) {
					if (Orb.contains(a1)) {
						t1 = Orb.getObjectTrack(a1);
						o1 = Orb;
					}
					if (Orb.contains(a2)) {
						t2 = Orb.getObjectTrack(a2);
						o2 = Orb;
					}
				}
				o1.removeObjectOnTrack(t1, a1);
				o2.removeObjectOnTrack(t2, a2);
				o1.addObjectToTrack(t1, a2);
				o2.addObjectToTrack(t2, a1);
				System.out.println("�����ɹ�");

				break;
			case "10":// ���ӻ�
				System.out.println("������Ҫ��ʾ�Ĺ��ϵͳ���");
				System.out.println("���뷶ΧΪ0����" + (trackOrbitList.size() - 1));
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				int currentOrbit1 = Integer.valueOf(arguments[0]);
				CircularOrbitAPIs.visualize(trackOrbitList.get(currentOrbit1));
				break;
			case "11":// ��ʾ������Ϣ
				arrangeOutout();
				break;
			case "12":// ��ѯ��־
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
					final Instant time1 = Instant.parse(inputString.replace(' ', 'T') + "Z");
					System.out.println("���������ʱ�䣬��ʽ�ο���2019-05-19 02:05:14");
					inputString = reader.readLine().trim();
					final Instant time2 = Instant.parse(inputString.replace(' ', 'T') + "Z");
					System.out.println(
							LOGKEEPER.getFilterLogs(p -> p.getTime().isAfter(time1) && p.getTime().isBefore(time2)));
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
//			case "12":// 4�˽���
//				Strategy strategy3 = new Relay4Strategy();
//				this.arrangeOrbit(strategy3);
//				arrangeOutout();
//				System.out.println("�������\n");
//				break;
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

	public void arrangeOrbit(Strategy strategy) {

		List<Track> trackList = new ArrayList<Track>();
		int[] DefaultRadius = new int[8];
		for (int i = 0; i < 8; i++) {
			DefaultRadius[i] = 50 + 100 * i;
		}
		for (int i = 0; i < trackNum; i++) {
			trackList.add(new Track("tarck" + i, DefaultRadius[i]));
		}
		List<Map<Track, List<Athlete>>> arrangementMap = strategy.Arrange(new ArrayList<>(athleteList), trackList);

		int OrbitNum = arrangementMap.size();

		trackOrbitList.clear();
		for (int i = 0; i < OrbitNum; i++) {
			Map<Track, List<Athlete>> currentMap = arrangementMap.get(i);
			trackBuilder.createCircularOrbit();
			trackBuilder.bulidTracks(trackList);
			trackBuilder.bulidPhysicalObjects(currentMap);
			TrackCircularOrbit newOrbit = (TrackCircularOrbit) trackBuilder.getConcreteCircularOrbit();
			trackOrbitList.add(newOrbit);
		}
	}

	public void arrangeOutout() {
		for (int i = 0; i < trackOrbitList.size(); i++) {
			TrackCircularOrbit CurrentOrbit = trackOrbitList.get(i);
			System.out.println(CurrentOrbit.toString());
		}
	}

	/**
	 * checkRep
	 */
	public void checkRep() {
		for (TrackCircularOrbit Orbit : trackOrbitList) {
			Orbit.checkRep();
		}
	}

	public static void main(String[] args) throws IOException, objectNoFoundException {
		new TrackGame().gameMain();
	}
}
