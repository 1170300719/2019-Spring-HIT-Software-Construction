package applications.AtomStructure;

import track.Track;

/**
 * @author Administrator ����¼��
 */
public class Memento {
	private final Track fromTrack;
	private final Track toTrack;

	public Track getFromTrack() {
		return fromTrack;
	}

	public Track getToTrack() {
		return toTrack;
	}

	/**
	 * ���췽��
	 * 
	 * @param fromTrack ��ʼ���
	 * @param toTrack   Ŀ����
	 */
	public Memento(Track fromTrack, Track toTrack) {
		this.fromTrack = fromTrack;
		this.toTrack = toTrack;
	}

}
