package applications.AtomStructure;

import java.util.List;

import circularOrbit.ConcreteCircularOrbit;
import track.Track;

public class AtomCircularOrbit extends ConcreteCircularOrbit<Particle, Particle> {
	// Abstraction function:
	// AtomCircularOrbit��һ���ɶ��Track��������Ӻ�һ������΢����ɵĶԹ���ṹ�ĳ���
	// ����AF��AtomCircularOrbit����ʵ��΢������ṹ��ӳ��

	// Representation invariant:
	// ������������������й��������ͬ�뾶

	// Safety from rep exposure:
	// ͬ����
	// ���б�Ҫ��ʱ��ʹ�÷����Կ���

	public AtomCircularOrbit() {
		super();
	}

	/**
	 * ʵ��ԾǨ����һ������ϵ�һ�������Ƶ���һ�������û�е��ӷ���false
	 * 
	 * @param object
	 * @param t
	 * @return
	 */
	public boolean transit(Track t1, Track t2) {
		if (OrbitMap.get(t1).size() >= 1) {
			OrbitMap.get(t2).add(Particle.getElectron());
			OrbitMap.get(t1).remove(0);
			return true;
		}
		else {
			return false;
		}
	}
	/**��ĳ�����ɾȥһ�����ӣ���Ϊ���ӻ���֮��û����������ֻ��һ�����������
	 * @param t
	 * @return
	 */
	public boolean removeElectron(Track t) {
		if (OrbitMap.get(t).size() >= 1) {
			OrbitMap.get(t).remove(0);
			return true;
		}
		else {
			return false;
		}
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		List<Track> trackList = this.getSortedTracks();
		for (int i = 0; i < trackList.size(); i++) {
			Track currentTrack = trackList.get(i);
			sb.append(currentTrack.getName() + "���У�" + OrbitMap.get(currentTrack).size() + "������\n");
		}
		return sb.toString();
	}

}
