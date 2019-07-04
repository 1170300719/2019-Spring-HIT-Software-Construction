/**
 * 
 */
/**
 * @author Administrator
 *
 */
package applications.TrackGame;

import java.util.ArrayList;
import java.util.regex.Pattern;

import exception.illegalParameterException;
import exception.sameLabelException;
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
	private static final ArrayList<String> nameList = new ArrayList<String>();

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
	 * @throws sameLabelException
	 */
	public static Athlete getInstance(String nameString, Integer idNum, String nationality, Integer age,
			double bestRecord) throws sameLabelException, illegalParameterException {

		for (String s : nameList) {
			if (s.equals(nameString)) {
				throw new sameLabelException(nameString + "Ϊ���Ķ����Ѿ�����");
			}
		}
		Athlete athlete = new Athlete(nameString, idNum, nationality, age, bestRecord);
		nameList.add(nameString);
		athlete.checkRep();
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

	public void checkRep() throws illegalParameterException {
		if (!Pattern.matches("[A-Za-z]+", this.name)) {
			throw new illegalParameterException(this.name + ":�˶�Ա���ִ���");
		}
		if (!Pattern.matches("([A-Z]{3})", nationality)) {
			throw new illegalParameterException(nationality + ":�˶�Ա��������");
		}
		if (!Pattern.matches("(\\d{1,2}\\.\\d{1,2})", String.valueOf(bestRecord))) {
			throw new illegalParameterException(bestRecord + ":�˶�Ա��óɼ�����");
		}
	}
}