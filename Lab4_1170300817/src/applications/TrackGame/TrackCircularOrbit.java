package applications.TrackGame;

import java.util.List;
import java.util.Map.Entry;

import circularOrbit.ConcreteCircularOrbit;
import phsicalObject.PhysicalObject;
import track.Track;

public class TrackCircularOrbit extends ConcreteCircularOrbit<PhysicalObject, Athlete> {
	// Abstraction function:
	// TrackCircularOrbit��һ���ɶ��Track�����������������������ɵĶԹ���ṹ�ĳ���������
	// OrbitMap�������������һ�Զ�Ĺ�ϵ
	// ����AF��TrackCircularOrbit����ʵ�����˶�Ա�Ĺ���ṹ��ӳ��

	// Representation invariant:
	// ������������������й��������ͬ�뾶��ÿ��������һ���ˣ��������һ�������������������

	// Safety from rep exposure:
	// ͬ����
	// ���б�Ҫ��ʱ��ʹ�÷����Կ���
	public TrackCircularOrbit() {
		super();
	}

	/**
	 * ��дtoString����
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		List<Track> trackList = this.getSortedTracks();
		for (int i = 0; i < trackList.size(); i++) {
			Track currentTrack = trackList.get(i);
			for (Athlete a : OrbitMap.get(currentTrack)) {
				sb.append(currentTrack.getName() + ":" + a.getName() + "\t����:" + a.getIdNum() + "\t����:"
						+ a.getNationality() + "\t����:" + a.getAge() + "\t��óɼ�:" + a.getBestRecord() + "\n");
			}
		}
		return sb.toString();

	}
	/**
	 * checkRep����
	 * ���Ϸ���
	 */
	public boolean checkRep() {
		//��������Ϊ��
		assert this.getCentralObject()==null;
		//ÿ��������һ����
		for(Entry<Track, List<Athlete>> e:OrbitMap.entrySet()) {
			assert e.getValue().size()<=1;
		}
		//�����Ŀ��4-10֮��
		assert OrbitMap.keySet().size()>=4;
		assert OrbitMap.keySet().size()<=10;
		return true;
	}

}
