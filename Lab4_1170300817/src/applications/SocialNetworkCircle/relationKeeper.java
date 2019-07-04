package applications.SocialNetworkCircle;

import java.util.ArrayList;
import exception.sameLabelException;

/**
 * @author Administrator ��ȡ�ļ�ʱ�õĸ����ࣺ��Ϊ��ȡ�ļ�����Ĺ���û������ȡ��������
 *         ������ʵ����Ӧ���������Թ���relationKeeper����ÿ����ϵ������string��
 */
public class relationKeeper {
	private final String fromString;
	private final String toString;
	private final double weight;
	private static final ArrayList<relationKeeper> keeperList = new ArrayList<relationKeeper>();

	public String getFromString() {
		return fromString;
	}

	public String getToString() {
		return toString;
	}

	public double getWeight() {
		return weight;
	}

	/**
	 * ���췽��
	 * 
	 * @param fromString
	 * @param toString
	 * @param weight
	 * @throws sameLabelException
	 */
	public relationKeeper(String fromString, String toString, double weight) throws  sameLabelException{
		super();
		this.fromString = fromString;
		this.toString = toString;
		this.weight = weight;
		if (fromString.equals(toString)) {
			throw new sameLabelException("��" + fromString + "��" + toString + "�ı���Ч");
		}
		for (relationKeeper k : keeperList) {
			if (k.getFromString().equals(toString) && k.getToString().equals(fromString)) {
				throw new sameLabelException("��" + fromString + "��" + toString + "�ı��Ѿ�����");
			}
			if (k.getFromString().equals(fromString) && k.getToString().equals(toString)) {
				throw new sameLabelException("��" + fromString + "��" + toString + "�ı��Ѿ�����");
			}
		}
		keeperList.add(this);
	}
}
