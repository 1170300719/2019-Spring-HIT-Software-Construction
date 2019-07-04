package circularOrbit;

import java.util.List;
import java.util.Map;

import phsicalObject.PhysicalObject;
import track.Track;

public abstract class CircularOrbitBuilder<L, E extends PhysicalObject> {
	protected ConcreteCircularOrbit<L, E> concreteCircularOrbit;

	/**
	 * ���ع�����ɵĶ���
	 * 
	 * @return ConcreteCircularOrbit
	 */
	public ConcreteCircularOrbit<L, E> getConcreteCircularOrbit() {
		return concreteCircularOrbit;
	}

	/**
	 * ����ʵ��ʱ�����������͵�����
	 */
	public abstract void createCircularOrbit();

	/**
	 * ���ݴ����trackList��ʼ��concreteCircularOrbit
	 * 
	 * @param trackList
	 */
	public void bulidTracks(List<Track> trackList) {
		for (Track t : trackList) {
			concreteCircularOrbit.addTrack(t);
		}
	}

	/**
	 * ���ݴ����centralObj��ObjectMap��ʼ��concreteCircularOrbit
	 * 
	 * @param centralObj �������������
	 * @param ObjectMap  �����OrbitMap
	 */
	public void bulidPhysicalObjects(L centralObj, Map<Track, List<E>> ObjectMap) {
		concreteCircularOrbit.setCentralObject(centralObj);
		for (Map.Entry<Track, List<E>> e : ObjectMap.entrySet()) {
			for (E object : e.getValue()) {
				concreteCircularOrbit.addObjectToTrack(e.getKey(), object);
			}
		}
	}
	public void bulidPhysicalObjects(Map<Track, List<E>> ObjectMap) {
		for (Map.Entry<Track, List<E>> e : ObjectMap.entrySet()) {
			for (E object : e.getValue()) {
				concreteCircularOrbit.addObjectToTrack(e.getKey(), object);
			}
		}
	}

}
