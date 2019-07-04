package applications.TrackGame;

import circularOrbit.CircularOrbitBuilder;
import phsicalObject.PhysicalObject;

public class TrackCircularOrbitBuilder extends CircularOrbitBuilder<PhysicalObject, Athlete> {

	/**
	 * �����������͵�����
	 */
	public void createCircularOrbit(Integer gameType) {
		concreteCircularOrbit = new TrackCircularOrbit();
		concreteCircularOrbit.setCentralObject(new PhysicalObject(Integer.toString(gameType)));
	}
	/**
	 * �����������͵�����
	 */
	@Override
	public void createCircularOrbit() {
		concreteCircularOrbit = new TrackCircularOrbit();
	}


}
