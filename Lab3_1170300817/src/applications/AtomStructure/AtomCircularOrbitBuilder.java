package applications.AtomStructure;

import circularOrbit.CircularOrbitBuilder;

public class AtomCircularOrbitBuilder extends CircularOrbitBuilder<Particle, Particle>{

	/**
	 * ��дcreateCircularOrbit������AtomCircularOrbit����
	 */
	@Override
	public void createCircularOrbit() {
		concreteCircularOrbit = new AtomCircularOrbit();
		
	}

}
