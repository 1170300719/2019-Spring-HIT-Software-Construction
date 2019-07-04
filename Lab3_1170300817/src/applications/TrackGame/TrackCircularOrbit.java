package applications.TrackGame;

import java.util.List;
import circularOrbit.ConcreteCircularOrbit;
import phsicalObject.PhysicalObject;
import track.Track;

public class TrackCircularOrbit extends ConcreteCircularOrbit<PhysicalObject, Athlete> {
	// Abstraction function:
	// TrackCircularOrbit��һ���ɶ��Track�����������������������ɵĶԹ���ṹ�ĳ���������
	// OrbitMap�������������һ�Զ�Ĺ�ϵ
	// ����AF��TrackCircularOrbit����ʵ�����˶�Ա�Ĺ���ṹ��ӳ��

	// Representation invariant:
	// ������������������й��������ͬ�뾶

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
		sb.append(this.getCentralObject().getName() + ":\n");
		List<Track> trackList = this.getSortedTracks();
		for (int i = 0; i < trackList.size(); i++) {
			Track currentTrack = trackList.get(i);
			for (Athlete a : OrbitMap.get(currentTrack)) {
				sb.append(currentTrack.getName() + ":" + a.getName() + "\t���룺" + a.getIdNum() + "\t������"
						+ a.getNationality() + "\t���䣺" + a.getAge() + "\t��óɼ���" + a.getBestRecord() + "\n");
			}
		}
		return sb.toString();

	}

}
