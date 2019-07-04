package applications.TrackGame.Strategy;

import java.util.List;
import java.util.Map;

import applications.TrackGame.Athlete;
import track.Track;

public interface Strategy {
	/**
	 * �������
	 * 
	 * @param runnerlist �˶�Ա�б�
	 * @param tracklist  �����б�
	 * @return �������Map
	 */
	public List<Map<Track, List<Athlete>>> Arrange(List<Athlete> runnerlist, List<Track> tracklist);
}
