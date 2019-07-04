package applications.AtomStructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.Instant;
import org.apache.log4j.Logger;

import circularOrbitHelper.CircularOrbitAPIs;
import exception.illegalTextGrammarException;
import exception.logicalErrorException;
import logRecord.logKeeper;
import logRecord.loggerFactory;
import track.Track;

public class AtomGame {
	// Abstraction function:
	// AF��һ��������ԭ�ӹ����Ϣ�͵��Ӻ�����Ϣ�ĳ��������͵���ʵ��ԭ�ӵ�ӳ��

	// Representation invariant:
	// Nucleus!=null
	// trackNum!=null
	// atomCircularOrbit!=null

	// Safety from rep exposure:
	// ���ùؼ�����trackNum��Nucleus��atomCircularOrbitΪprivate
	// ���б�Ҫ��ʱ��ʹ�÷����Կ���
	private Integer trackNum;
	private final TransitCareTaker transitCareTaker = new TransitCareTaker();
	private AtomCircularOrbit atomCircularOrbit = null;
	private final AtomCircularOrbitBuilder atomOrbitBuilder = new AtomCircularOrbitBuilder();
	private static Logger LOGGER = loggerFactory.createLogger(AtomGame.class);
	private logKeeper LOGKEEPER;
	private final int[] DefaultRadius = new int[10];

	/**
	 * �˵�
	 */
	public void GameMenu() {
		System.out.println("1.\t��ȡ�ļ�����ϵͳ");
		System.out.println("2.\tԾǨ");
		System.out.println("3.\t����");
		System.out.println("4.\t���ӻ�");
		System.out.println("5.\t��ӡ����ṹ");
		System.out.println("6.\t�����¹��");
		System.out.println("7.\t�����µ���");
		System.out.println("8.\tɾ������");
		System.out.println("9.\tɾ���������");
		System.out.println("10.\t������ֵ");
		System.out.println("11.\t��־��ѯ");
		System.out.println("end.\t ����");
	}

	/**
	 * ����
	 * 
	 * @throws IOException
	 */
	public void gameMain() throws IOException {
		for (int i = 0; i < 10; i++) {
			DefaultRadius[i] = 50 + 100 * i;
		}
		String inputString;
		String[] arguments;
		boolean exitflag = false;
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while (true) {
			GameMenu();
			String input = reader.readLine();
			switch (input) {
			case "1":// ��ȡ�ļ�
				try {
					System.out.println("������Ҫ��ȡ���ļ���������AtomicStructure.txt\n");
					inputString = reader.readLine();
					atomOrbitBuilder.createFromFile("src/txt/" + inputString);
//					atomCircularOrbit =atomOrbitBuilder.createFromFile("src/txt/" + "exception/Atom1.txt");
					atomCircularOrbit = (AtomCircularOrbit) atomOrbitBuilder.getConcreteCircularOrbit();
				} catch (illegalTextGrammarException | IOException | NumberFormatException | logicalErrorException e) {
					LOGGER.error(e.getMessage());
					System.out.println("�����¶�ȡ�ļ�");
					break;
				}
				System.out.println(atomCircularOrbit.toString());
				trackNum = atomCircularOrbit.getTrackNum();
				LOGGER.debug("�ļ���ȡ�ɹ�");
				break;
			case "2":// ԾǨ
				System.out.println("����ԾǨ����ʼ�������ֹ���");
				System.out.println("���뷶ΧΪ0����" + (trackNum - 1));
				inputString = reader.readLine();
				arguments = inputString.split("\\s");
				int TrackNum1 = Integer.valueOf(arguments[0]);
				int TrackNum2 = Integer.valueOf(arguments[1]);
				Track t1 = atomCircularOrbit.getSortedTracks().get(TrackNum1);
				Track t2 = atomCircularOrbit.getSortedTracks().get(TrackNum2);
				if (atomCircularOrbit.transit(t1, t2)) {
					transitCareTaker.addMemento(new Memento(t1, t2));
					LOGGER.debug("ԾǨ�ɹ�:��" + t1.getName() + "��" + t2.getName());
					System.out.println(atomCircularOrbit.toString());
				} else {
					LOGGER.debug("ԾǨʧ��");
				}
				break;
			case "3":// ����
				Memento m = transitCareTaker.getMemento();
				Track fromTrack = m.getToTrack();
				Track toTrack = m.getFromTrack();
				if (atomCircularOrbit.transit(fromTrack, toTrack)) {
					LOGGER.debug("���˳ɹ�");
					System.out.println(atomCircularOrbit.toString());
				} else {
					LOGGER.debug("����ʧ��");
				}
				break;
			case "4":// ���ӻ�
				LOGGER.debug("���ӻ����");
				CircularOrbitAPIs.visualize(atomCircularOrbit);
				break;
			case "5":// �������
				LOGGER.debug("����������");
				System.out.println(atomCircularOrbit.toString());
				break;
			case "6":// �����¹��
				System.out.println("�����¹��\n");
				trackNum++;
				atomCircularOrbit.addTrack(new Track("track" + (trackNum - 1), DefaultRadius[trackNum - 1]));
				System.out.println("�������" + trackNum + "\n");
				LOGGER.debug("������ӳɹ�");
				System.out.println(atomCircularOrbit.toString());
				break;
			case "7":// �����µ���
				System.out.println("������Ҫ���ӵ��ӵĹ��\n");
				System.out.println("���뷶ΧΪ0����" + (trackNum - 1));
				inputString = reader.readLine();
				arguments = inputString.split("\\s");
				int trackIndex1 = Integer.valueOf(arguments[0]);
				Track t = atomCircularOrbit.getSortedTracks().get(trackIndex1);
				atomCircularOrbit.addObjectToTrack(t, Particle.getElectron());
				System.out.println(atomCircularOrbit.toString());
				LOGGER.debug("���ӳɹ�");
				break;
			case "8":// ɾ������
				System.out.println("������Ҫɾ�����ӵĹ��\n");
				System.out.println("���뷶ΧΪ0����" + (trackNum - 1));
				inputString = reader.readLine();
				arguments = inputString.split("\\s");
				Track track2 = atomCircularOrbit.getSortedTracks().get(Integer.valueOf(arguments[0]));
				if (atomCircularOrbit.removeElectron(track2)) {
					LOGGER.debug("ɾ���ɹ�");
				} else {
					LOGGER.debug("ɾ��ʧ��");
				}
				System.out.println(atomCircularOrbit.toString());
				break;
			case "9":// ɾ���������
				System.out.println("������Ҫɾ���Ĺ��\n");
				System.out.println("���뷶ΧΪ0����" + (trackNum - 1));
				inputString = reader.readLine();
				arguments = inputString.split("\\s");
				Track track3 = atomCircularOrbit.getSortedTracks().get(Integer.valueOf(arguments[0]));
				if (atomCircularOrbit.removeTrack(track3)) {
					LOGGER.debug("ɾ���ɹ�");
				} else {
					LOGGER.debug("ɾ��ʧ��");
				}
				System.out.println(atomCircularOrbit.toString());
				trackNum--;
				break;
			case "10":// ������ֵ
				System.out.println("��Ϣ��Ϊ��" + atomCircularOrbit.getObjectDistributionEntropy() + "\n");
				LOGGER.debug("������ֵ���");
				break;
			case "11":// ��־��ѯ
				LOGKEEPER = new logKeeper();
				System.out.println("ѡ��ɸѡ������\n");
				System.out.println("1.\tʱ���");
				System.out.println("2.\t������");
				System.out.println("3.\t����");
				System.out.println("4.\t������");
				input = reader.readLine();
				if (input != null) {
					input = input.trim();
				}else {
					continue;
				}
				switch (input) {
				case "1":// ʱ���
					System.out.println("�����뿪ʼʱ�䣬��ʽ�ο���2019-05-19 02:05:14");
					inputString = reader.readLine();
					final Instant time1 = Instant.parse(inputString.replace(' ', 'T') + "Z");
					System.out.println("���������ʱ�䣬��ʽ�ο���2019-05-19 02:05:14");
					inputString = reader.readLine();
					final Instant time2 = Instant.parse(inputString.replace(' ', 'T') + "Z");
					System.out.println(
							LOGKEEPER.getFilterLogs(p -> p.getTime().isAfter(time1) && p.getTime().isBefore(time2)));
					System.out.println("��ѯ���");
					break;
				case "2":// ������
					System.out.println("���������ͣ�ERROR��DEBUG");
					inputString = reader.readLine();
					final String input1 = new String(inputString);
					System.out.println(LOGKEEPER.getFilterLogs(p -> p.getLogType().equals(input1)));
					System.out.println("��ѯ���");
					break;
				case "3":// ����
					System.out.println("����������");
					inputString = reader.readLine();
					final String input2 = new String(inputString);
					System.out.println(LOGKEEPER.getFilterLogs(p -> p.getClassName().equals(input2)));
					System.out.println("��ѯ���");
					break;
				case "4":// ������
					System.out.println("�����뷽����");
					inputString = reader.readLine();
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
				LOGGER.debug("��Ϸ����");
				exitflag = true;
				break;
			default:
				LOGGER.debug("�������");
				break;
			}
			if (exitflag)
				break;
		}
	}

	public static void main(String[] args) throws IOException {
		new AtomGame().gameMain();
	}
}
