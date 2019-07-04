package track;

public class Track implements Comparable<Track> {
	// Abstraction function:
	// ����AF�Ǵ�һ����¼�����ֺͰ뾶��Track���������͵���ʵ�����ӳ��

	// Representation invariant:
	// ����뾶����Ϊ0�����ֲ���Ϊ��

	// Safety from rep exposure:
	// ���ùؼ�����name��radiusΪprivate final��ֹ����
	// ���б�Ҫ��ʱ��ʹ�÷����Կ���
	private final String name;
	private final double radius;

	/**
	 * ���췽��
	 * 
	 * @param name   ����
	 * @param radius �뾶
	 */
	public Track(String name, double radius) {
		super();
		this.name = name;
		this.radius = radius;
		checkRep();
	}

	/**
	 * ��ù������
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * ��ù���뾶
	 * 
	 * @return
	 */
	public double getRadius() {
		return radius;
	}

//	private void checkRep() {
//		assert(this.radius>=0);
//
//	}

	/**
	 * ��дComparable�ӿڵ�compareTo������
	 * 
	 * @return ���ش�С�ȽϽ��
	 */
	@Override
	public int compareTo(Track o) {
		if (this.radius - o.getRadius() == 0) {
			return 0;
		} else if (this.radius - o.getRadius() > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * ������ַǿգ��뾶����0
	 */
	public void checkRep() {
		assert (this.name != null);
		assert (this.radius >= 0);

	}
}
