package applications.AtomStructure;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

import track.Track;

public class AtomCircularOrbitTest {
	// Testing strategy:
	// ����һЩ���෽��
	AtomCircularOrbit atomCircularOrbit = new AtomCircularOrbit();
	Track t1 = new Track("track1", 100);
	Track t2 = new Track("track2", 200);
	Track t3 = new Track("track3", 300);
	Particle e1 = Particle.getElectron();
	Particle e2 = Particle.getElectron();
	Particle e3 = Particle.getElectron();

	@Test
	public void transitTest() {
		atomCircularOrbit.addTrack(t1);
		atomCircularOrbit.addTrack(t2);
		atomCircularOrbit.addTrack(t3);
		atomCircularOrbit.addObjectToTrack(t1, e1);
		atomCircularOrbit.addObjectToTrack(t2, e2);
		atomCircularOrbit.addObjectToTrack(t3, e3);
		atomCircularOrbit.transit(t1, t2);
		assertFalse(atomCircularOrbit.transit(t1, t2));
		assertTrue(atomCircularOrbit.getObjectNumonTrack(t2) == 2);
	}

	@Test
	public void transitReturnTest() {
		TransitCareTaker transitCareTaker = new TransitCareTaker();
		atomCircularOrbit.addTrack(t1);
		atomCircularOrbit.addTrack(t2);
		atomCircularOrbit.addTrack(t3);
		atomCircularOrbit.addObjectToTrack(t1, e1);
		atomCircularOrbit.addObjectToTrack(t2, e2);
		atomCircularOrbit.addObjectToTrack(t3, e3);
		atomCircularOrbit.transit(t1, t2);
		assertTrue(atomCircularOrbit.getObjectNumonTrack(t2) == 2);
		transitCareTaker.addMemento(new Memento(t1, t2));
		Memento m = transitCareTaker.getMemento();
		Track fromTrack = m.getToTrack();
		Track toTrack = m.getFromTrack();
		atomCircularOrbit.transit(fromTrack, toTrack);
		assertTrue(atomCircularOrbit.getObjectNumonTrack(t2) == 1);
		assertTrue(transitCareTaker.getMemento()==null);
	}

	@Test
	public void removeElectronTest() {
		atomCircularOrbit.addTrack(t1);
		atomCircularOrbit.addTrack(t2);
		atomCircularOrbit.addTrack(t3);
		atomCircularOrbit.addObjectToTrack(t1, e1);
		atomCircularOrbit.addObjectToTrack(t2, e2);
		atomCircularOrbit.addObjectToTrack(t3, e3);
		atomCircularOrbit.removeElectron(t1);
		atomCircularOrbit.removeElectron(t2);
		assertFalse(atomCircularOrbit.removeElectron(t2));
		assertTrue(atomCircularOrbit.getObjectNumonTrack(t1) == 0);
		assertTrue(atomCircularOrbit.getObjectNumonTrack(t2) == 0);
	}

	@Test
	public void toStringTest() {
		atomCircularOrbit.addTrack(t1);
		atomCircularOrbit.addTrack(t2);
		atomCircularOrbit.addTrack(t3);
		atomCircularOrbit.addObjectToTrack(t1, e1);
		atomCircularOrbit.addObjectToTrack(t2, e2);
		atomCircularOrbit.addObjectToTrack(t3, e3);
		System.out.println(atomCircularOrbit.toString());
		assertTrue(atomCircularOrbit.toString().contains("track1���У�1������"));
		assertTrue(atomCircularOrbit.toString().contains("track2���У�1������"));
		assertTrue(atomCircularOrbit.toString().contains("track3���У�1������"));

	}

}
