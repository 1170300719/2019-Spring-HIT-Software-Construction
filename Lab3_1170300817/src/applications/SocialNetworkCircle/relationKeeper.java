package applications.SocialNetworkCircle;

/**
 * @author Administrator ��ȡ�ļ�ʱ�õĸ����ࣺ��Ϊ��ȡ�ļ�����Ĺ���û������ȡ��������
 *         ������ʵ����Ӧ���������Թ���relationKeeper����ÿ����ϵ������string��
 */
public class relationKeeper {
	private final String fromString;
	private final String toString;
	private final double weight;

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
	 */
	public relationKeeper(String fromString, String toString, double weight) {
		super();
		this.fromString = fromString;
		this.toString = toString;
		this.weight = weight;
	}
}
