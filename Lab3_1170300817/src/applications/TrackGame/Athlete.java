/**
 * 
 */
/**
 * @author Administrator
 *
 */
package applications.TrackGame;

import phsicalObject.PhysicalObject;

public class Athlete extends PhysicalObject implements Comparable<Athlete> {
	// Abstraction function:
	// ����AF�Ǵ�һ����¼�����֣�id���룬���������䣬��óɼ��ĳ��������͵���ʵ�˶�Ա��ӳ��

	// Representation invariant:
	// ���֣�id���룬���������䣬��óɼ�������Ϊ��

	// Safety from rep exposure:
	// ���ùؼ�����name,idNum,nationality,age,bestRecordΪprivate final��ֹ����
	private final Integer idNum;
	private final String nationality;
	private final Integer age;
	private final double bestRecord;

	public Integer getIdNum() {
		return idNum;
	}

	public double getBestRecord() {
		return bestRecord;
	}

	/**
	 * ���췽��
	 * 
	 * @param nameString
	 * @param idNum
	 * @param nationality
	 * @param age
	 * @param bestRecord
	 */
	public Athlete(String nameString, Integer idNum, String nationality, Integer age, double bestRecord) {
		super(nameString);
		this.idNum = idNum;
		this.nationality = nationality;
		this.age = age;
		this.bestRecord = bestRecord;
	}

	public String getNationality() {
		return nationality;
	}

	public Integer getAge() {
		return age;
	}

	/**
	 * ��̬��������
	 * 
	 * @param nameString  ����
	 * @param idNum       id����
	 * @param nationality ����
	 * @param age         ����
	 * @param bestRecord  ��óɼ�
	 * @return Athleteʵ��
	 */
	public static Athlete getInstance(String nameString, Integer idNum, String nationality, Integer age,
			double bestRecord) {
		Athlete athlete = new Athlete(nameString, idNum, nationality, age, bestRecord);
		return athlete;
	}

	/**
	 * ��дcompareToʵ��Comparable
	 * 
	 * @param �Ƚ�Ŀ���˶�Ա
	 * @return �ȽϽ��
	 */
	@Override
	public int compareTo(Athlete o) {
		if (this.bestRecord - o.getBestRecord() == 0) {
			return 0;
		} else if (this.bestRecord - o.getBestRecord() > 0) {
			return 1;
		} else {
			return -1;
		}
	}
}