package applications.TrackGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import applications.TrackGame.Strategy.RandomStrategy;
import applications.TrackGame.Strategy.RecordStrategy;
import applications.TrackGame.Strategy.Strategy;
import circularOrbitHelper.CircularOrbitAPIs;
import phsicalObject.PhysicalObject;
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
	private Integer gameType;
	private Integer trackNum;
	private final List<Athlete> athleteList = new LinkedList<>();
	private final List<TrackCircularOrbit> trackOrbitList = new LinkedList<>();
	private final TrackCircularOrbitBuilder trackBuilder = new TrackCircularOrbitBuilder();

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
//		System.out.println("12.\t4�˽�����");
		System.out.println("end.\t ����");
	}

	/**
	 * ��Ϸ����
	 * @throws IOException
	 */
	public void gameMain() throws IOException {
		String inputString;
		String[] arguments;
		boolean exitflag = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			GameMenu();
			String input = reader.readLine().trim();
			switch (input) {
			case "1":// ��ȡ�ļ�
				BufferedReader in = new BufferedReader(new FileReader(new File("src/txt/TrackGame.txt")));
				String fileline;
				String athletepattern = "Athlete\\s*::=\\s*<(\\w+),(\\w+),(\\w+),(\\w+),([\\w,\\.]+)>";
				String gamepattern = "Game\\s*::=\\s*([\\w,\\.]+)";
				String trackpattern = "NumOfTracks\\s*::=\\s*(\\w+)";

				while ((fileline = in.readLine()) != null) {
					Matcher athleteMatcher = Pattern.compile(athletepattern).matcher(fileline);
					Matcher gameMatcher = Pattern.compile(gamepattern).matcher(fileline);
					Matcher trackMatcher = Pattern.compile(trackpattern).matcher(fileline);
					if (athleteMatcher.find()) {
						String nameString = athleteMatcher.group(1);
						int idNum = Integer.valueOf(athleteMatcher.group(2));
						String nationality = athleteMatcher.group(3);
						int age = Integer.valueOf(athleteMatcher.group(4));
						double bestRecord = Double.valueOf(athleteMatcher.group(5));
						Athlete a = Athlete.getInstance(nameString, idNum, nationality, age, bestRecord);
						athleteList.add(a);
					} else if (gameMatcher.find()) {
						gameType = Integer.valueOf(gameMatcher.group(1));
					} else if (trackMatcher.find()) {
						trackNum = Integer.valueOf(trackMatcher.group(1));
					}
				}
				System.out.println("�ļ���ȡ�ɹ�");
				break;
			case "2":// �����������
				Strategy strategy1 = new RandomStrategy();
				this.arrangeOrbit(strategy1);
				arrangeOutout();
				System.out.println("�������\n");
				break;
			case "3":// ���ɼ���������
				Strategy strategy2 = new RecordStrategy();
				this.arrangeOrbit(strategy2);
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
				Athlete a = Athlete.getInstance(nameString, idNum, nationality, age, bestRecord);
				athleteList.add(a);
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

		int OrbitNum=arrangementMap.size();
		
		trackOrbitList.clear();
		for (int i = 0; i < OrbitNum; i++) {
			Map<Track, List<Athlete>> currentMap = arrangementMap.get(i);
			trackBuilder.createCircularOrbit(gameType);
			trackBuilder.bulidTracks(trackList);
			trackBuilder.bulidPhysicalObjects(new PhysicalObject("Orbit" + i), currentMap);
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

	public static void main(String[] args) throws IOException {
		new TrackGame().gameMain();
	}
}
