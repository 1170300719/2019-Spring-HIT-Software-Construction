package applications.AtomStructure;

import static org.junit.jupiter.api.Assertions.assertAll;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import circularOrbitHelper.CircularOrbitAPIs;
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
	private Particle Nucleus;
	private final TransitCareTaker transitCareTaker = new TransitCareTaker();
	private AtomCircularOrbit atomCircularOrbit=null;
	private final AtomCircularOrbitBuilder atomOrbitBuilder = new AtomCircularOrbitBuilder();
	public int[] DefaultRadius = new int[10];

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
		System.out.println("end.\t ����");
	}

	/**
	 * ����
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
				BufferedReader in = new BufferedReader(new FileReader(new File("src/txt/AtomicStructure.txt")));
				String fileline;
				String elementpattern = "ElementName\\s*::=\\s*([a-zA-Z]+)";
				String trackpattern = "NumberOfTracks\\s*::=\\s*(\\d+)";
				String electronpattern = "NumberOfElectron\\s*::=\\s*([\\d+\\/\\d+,;]+)";
				while ((fileline = in.readLine()) != null) {
					Matcher elementMatcher = Pattern.compile(elementpattern).matcher(fileline);
					Matcher trackMatcher = Pattern.compile(trackpattern).matcher(fileline);
					Matcher electronMatcher = Pattern.compile(electronpattern).matcher(fileline);
					if (elementMatcher.find()) {
						Nucleus = Particle.getNucleus(elementMatcher.group(1));
					} else if (trackMatcher.find()) {
						trackNum = Integer.valueOf(trackMatcher.group(1));
					} else if (electronMatcher.find()) {
						for (int i = 0; i < 10; i++) {
							DefaultRadius[i] = 50 + 100 * i;
						}
						List<Track> trackList = new ArrayList<Track>();
						Map<Track, List<Particle>> elementMap = new HashMap<Track, List<Particle>>();
						String[] NUm = electronMatcher.group(1).split(";");
						for (int i = 0; i < NUm.length; i++) {
							Track track = new Track("track" + i, DefaultRadius[i]);
							trackList.add(track);
							String[] value = NUm[i].split("/");
							int objNum = Integer.valueOf(value[1]);
							List<Particle> currentList = new LinkedList<Particle>();
							for (int j = 0; j < objNum; j++) {
								Particle p = Particle.getElectron();
								currentList.add(p);
							}
							elementMap.put(track, currentList);
							assert (currentList.size()!=0):"���Ӷ�ʧ������ʧ��";
						}
						atomOrbitBuilder.createCircularOrbit();
						atomOrbitBuilder.bulidTracks(trackList);
						atomOrbitBuilder.bulidPhysicalObjects(Nucleus, elementMap);
						atomCircularOrbit = (AtomCircularOrbit) atomOrbitBuilder.getConcreteCircularOrbit();
					}
				}
				System.out.println(trackNum);
				assert (trackNum!=null):"�������ʧ������ʧ��";
				atomCircularOrbit.checkRep();
				System.out.println(atomCircularOrbit.toString());
				break;
			case "2":// ԾǨ
				System.out.println("����ԾǨ����ʼ�������ֹ���");
				System.out.println("���뷶ΧΪ0����" + (trackNum - 1));
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				int TrackNum1 = Integer.valueOf(arguments[0]);
				int TrackNum2 = Integer.valueOf(arguments[1]);
				Track t1 = atomCircularOrbit.getSortedTracks().get(TrackNum1);
				Track t2 = atomCircularOrbit.getSortedTracks().get(TrackNum2);
				if (atomCircularOrbit.transit(t1, t2)) {
					transitCareTaker.addMemento(new Memento(t1, t2));
					System.out.println("ԾǨ�ɹ�");
					System.out.println(atomCircularOrbit.toString());
				} else {
					System.out.println("ԾǨʧ��");
				}
				break;
			case "3":// ����
				Memento m = transitCareTaker.getMemento();
				Track fromTrack = m.getToTrack();
				Track toTrack = m.getFromTrack();
				if (atomCircularOrbit.transit(fromTrack, toTrack)) {
					System.out.println("���˳ɹ�");
					System.out.println(atomCircularOrbit.toString());
				} else {
					System.out.println("����ʧ��");
				}
				break;
			case "4":// ���ӻ�
				CircularOrbitAPIs.visualize(atomCircularOrbit);
				break;
			case "5":// �������
				System.out.println(atomCircularOrbit.toString());
				break;
			case "6":// �����¹��
				System.out.println("�����¹��\n");
				trackNum++;
				atomCircularOrbit.addTrack(new Track("track" + (trackNum - 1), DefaultRadius[trackNum - 1]));
				System.out.println("�������" + trackNum + "\n");
				System.out.println(atomCircularOrbit.toString());
				break;
			case "7":// �����µ���
				System.out.println("������Ҫ���ӵ��ӵĹ��\n");
				System.out.println("���뷶ΧΪ0����" + (trackNum - 1));
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				int trackIndex1 = Integer.valueOf(arguments[0]);
				Track t=atomCircularOrbit.getSortedTracks().get(trackIndex1);
				atomCircularOrbit.addObjectToTrack(t, Particle.getElectron());
				System.out.println(atomCircularOrbit.toString());
				System.out.println("���ӳɹ�");
				break;
			case "8":// ɾ������
				System.out.println("������Ҫɾ�����ӵĹ��\n");
				System.out.println("���뷶ΧΪ0����" + (trackNum - 1));
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				Track track2=atomCircularOrbit.getSortedTracks().get(Integer.valueOf(arguments[0]));
				if (atomCircularOrbit.removeElectron(track2)) {
					System.out.println("ɾ���ɹ�");
				}
				else {
					System.out.println("ɾ��ʧ��");
				}
				System.out.println(atomCircularOrbit.toString());
				break;
			case "9":// ɾ���������
				System.out.println("������Ҫɾ���Ĺ��\n");
				System.out.println("���뷶ΧΪ0����" + (trackNum - 1));
				inputString = reader.readLine().trim();
				arguments = inputString.split("\\s");
				Track track3=atomCircularOrbit.getSortedTracks().get( Integer.valueOf(arguments[0]));
				if (atomCircularOrbit.removeTrack(track3)) {
					System.out.println("ɾ���ɹ�");
				}
				else {
					System.out.println("ɾ��ʧ��");
				}
				System.out.println(atomCircularOrbit.toString());
				trackNum--;
				break;
			case "10":// ������ֵ
				System.out.println("��Ϣ��Ϊ��" + atomCircularOrbit.getObjectDistributionEntropy() + "\n");
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
		new AtomGame().gameMain();
	}
}
