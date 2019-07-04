/**
 * 
 */
/**
 * @author Administrator
 *
 */
package difference;

import java.util.List;
import java.util.Set;

public class Difference {
	// Abstraction function:
	// trackNumDiff��¼������Ĳ��죬NumDiff�б��¼�����������Ŀ���죬ObjectDiff��¼�������
	// AF�Ǵ�һ����¼������Orbit����������ݵĳ��������͵���ʵ����Orbit��ʵ�����ӳ��

	// Representation invariant:
	// NumDiff��=null ObjectDiff��=null

	// Safety from rep exposure:
	// ���ùؼ�����trackNumDiff��trackNumDiffNumDiff��ObjectDiffΪprivate final��ֹ����

	private final Integer trackNumDiff;
	private final List<Integer> NumDiff;
	private final List<List<Set<String>>> ObjectDiff;

	/**
	 * ���췽��
	 * @param trackNumDiff
	 * @param numDiff
	 * @param ObjectDiff
	 */
	public Difference(Integer trackNumDiff, List<Integer> numDiff, List<List<Set<String>>> ObjectDiff) {
		this.trackNumDiff = trackNumDiff;
		this.NumDiff = numDiff;
		this.ObjectDiff = ObjectDiff;
	}

	/**
	 * �����������Difference�����string
	 * @result ������String
	 */
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("��������죺" + trackNumDiff + "\n");
		int num = 0;
		for (int i = 0; i < NumDiff.size(); i++) {
			num = i + 1;
			stringBuilder.append("���" + num + "��������Ŀ���죺" + NumDiff.get(i) + "\n");
		}
		for (int i = 0; i < ObjectDiff.size(); i++) {
			num = i + 1;
			stringBuilder
					.append("���" + num + "��������죺" + ObjectDiff.get(i).get(0) + "-" + ObjectDiff.get(i).get(1) + "\n");
		}
		return stringBuilder.toString();
	}
}