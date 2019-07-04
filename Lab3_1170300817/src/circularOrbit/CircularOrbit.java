package circularOrbit;

import java.util.List;
import java.util.Set;
import difference.Difference;
import track.Track;

public interface CircularOrbit<L, E> {
	// Abstraction function:
	// CircularOrbit<L, E>��һ���ɶ��Track�����������������������ɵĶԹ���ṹ�ĳ���
	// ����AF��CircularOrbit<L, E>����ʵ�Ĺ���ṹ��ӳ��

	// Representation invariant:
	// ������������������й��������ͬ�뾶

	// Safety from rep exposure:
	// ���ùؼ�����Ϊprotected final
	// ���б�Ҫ��ʱ��ʹ�÷����Կ���
	/**
	 * ������������
	 * 
	 * @param centralObject
	 */
	public void setCentralObject(L centralObject);

	/**
	 * ������������
	 * 
	 * @return ��������
	 */
	public L getCentralObject();

	/**
	 * ����һ�����
	 * 
	 * @param t
	 * @return �ɹ�����true ����false
	 */
	public boolean addTrack(Track t);

	/**
	 * ɾ��һ�����
	 * 
	 * @param t
	 * @return �ɹ�����true ����false
	 */
	public boolean removeTrack(Track t);

	/**
	 * ���ع����Ŀ
	 * 
	 * @return �����Ŀ
	 */
	public Integer getTrackNum();

	/**
	 * ͳ��һ������ж�������
	 * 
	 * @param t ���
	 * @return ���t�ϵ�������
	 */
	public Integer getObjectNumonTrack(Track t);

	/**
	 * ����һ�����������
	 * 
	 * @param t
	 * @return
	 */
	public List<E> getObjectonTrack(Track t);

	/**
	 * ��������������
	 * 
	 * @param t      Ŀ����
	 * @param object ���ӵ�����
	 * @return �ɹ�����true ����false
	 */
	public boolean addObjectToTrack(Track t, E object);

	/**
	 * ɾȥһ������ϵ�ĳ������
	 * 
	 * @param t
	 * @param object
	 * @return �ɹ�����true ����false
	 */
	public boolean removeObjectOnTrack(Track t, E object);

	/**
	 * �������������֮��������ϵ
	 * 
	 * @param object1
	 * @param object2
	 * @param distance
	 * @return �ɹ�����true ����false
	 */
	public boolean addtrackRelation(E object1, E object2, double distance);

	/**
	 * �ڹ���������������֮��������ϵ
	 * 
	 * @param object
	 * @param distance
	 * @return �ɹ�����true ����false
	 */
	public boolean addcentralRelation(E object, double distance);

	/**
	 * ������ϵͳ����Ϣ��
	 * 
	 * @return ��Ϣ��
	 */
	public double getObjectDistributionEntropy();

	/**
	 * �����������߼�����
	 * 
	 * @param e1
	 * @param e2
	 * @return �߼�����
	 */
	public int getLogicalDistance(E e1, E e2);

	/**
	 * �Ƚϵ�ǰorbit��Ŀ��orbit�Ĳ�ͬ
	 * 
	 * @param c Ŀ��orbit
	 * @return һ��different���󣬼�¼����orbit������
	 */
	public Difference getDifference(CircularOrbit<L, E> c);

	/**
	 * ��õ�ǰorbit���������й�����뾶���гɵ�����
	 * 
	 * @return �뾶�����Track����
	 */
	public List<Track> getSortedTracks();

	/**
	 * ���ӻ�����
	 */
	public void drawpicture();

	/**
	 * �жϵ�ǰOrbit�Ƿ����ĳ��Ԫ��e
	 * 
	 * @param e
	 * @return �ɹ�����true ����false
	 */
	public boolean contains(E e);

	/**
	 * ����ĳ��Ԫ��e���ڵ�Object����
	 * 
	 * @param e
	 * @return ���
	 */
	public Track getObjectTrack(E e);

	/**
	 * �������������ӵ�����
	 * 
	 * @return ���幹�ɵļ���
	 */
	public Set<E> getCentralConnectedObject();

	/**
	 * ������ĳ������������ӵ���������
	 * 
	 * @return ���幹�ɵļ���
	 */
	public Set<E> getTrackConnectedObject(E object);
}
