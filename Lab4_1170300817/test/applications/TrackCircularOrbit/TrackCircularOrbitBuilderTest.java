package applications.TrackCircularOrbit;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import applications.TrackGame.TrackCircularOrbit;
import applications.TrackGame.TrackCircularOrbitBuilder;
import applications.TrackGame.TrackGame;
import applications.TrackGame.Strategy.RandomStrategy;
import applications.TrackGame.Strategy.Strategy;
import exception.illegalParameterException;
import exception.illegalTextGrammarException;
import exception.sameLabelException;

public class TrackCircularOrbitBuilderTest {

	@Test
	public void trackException1Test() {
		TrackCircularOrbitBuilder trackBuilder = new TrackCircularOrbitBuilder();
		try {
			trackBuilder.createFromFile("src/txt/exception/TrackGame1.txt");
		} catch (NumberFormatException | illegalTextGrammarException | sameLabelException | IOException
				| illegalParameterException e) {
			assertTrue(e.getMessage().contains(":�˶�Ա����ȱʧ"));
		}
	}

	@Test
	public void trackException2Test() {
		TrackCircularOrbitBuilder trackBuilder = new TrackCircularOrbitBuilder();
		try {
			trackBuilder.createFromFile("src/txt/exception/TrackGame2.txt");
		} catch (NumberFormatException | illegalTextGrammarException | sameLabelException | IOException
				| illegalParameterException e) {
			assertTrue(e.getMessage().contains(":�˶�Ա���ִ���"));
		}
	}

	@Test
	public void trackException3Test() {
		TrackCircularOrbitBuilder trackBuilder = new TrackCircularOrbitBuilder();
		try {
			trackBuilder.createFromFile("src/txt/exception/TrackGame3.txt");
		} catch (NumberFormatException | illegalTextGrammarException | sameLabelException | IOException
				| illegalParameterException e) {
			assertTrue(e.getMessage().contains(":�˶�Ա��������"));
		}
	}

	@Test
	public void trackException4Test() {
		TrackCircularOrbitBuilder trackBuilder = new TrackCircularOrbitBuilder();
		try {
			trackBuilder.createFromFile("src/txt/exception/TrackGame4.txt");
		} catch (NumberFormatException | illegalTextGrammarException | sameLabelException | IOException
				| illegalParameterException e) {
			assertTrue(e.getMessage().contains(":�˶�Ա��óɼ�����"));
		}
	}

	@Test
	public void trackException5Test() {
		TrackCircularOrbitBuilder trackBuilder = new TrackCircularOrbitBuilder();
		try {
			trackBuilder.createFromFile("src/txt/exception/TrackGame5.txt");
		} catch (NumberFormatException | illegalTextGrammarException | sameLabelException | IOException
				| illegalParameterException e) {
			assertTrue(e.getMessage().contains(":��Ч�ı�������"));
		}
	}

	@Test
	public void trackException6Test() {
		TrackCircularOrbitBuilder trackBuilder = new TrackCircularOrbitBuilder();
		try {
			trackBuilder.createFromFile("src/txt/exception/TrackGame6.txt");
		} catch (NumberFormatException | illegalTextGrammarException | sameLabelException | IOException
				| illegalParameterException e) {
			assertTrue(e.getMessage().contains(":��Ч�Ĺ����Ŀ"));
		}
	}

	@Test
	public void trackSameLabelExceptionTest() {
		TrackCircularOrbitBuilder trackBuilder = new TrackCircularOrbitBuilder();
		try {
			trackBuilder.createFromFile("src/txt/exception/TrackGameSameLabel.txt");
		} catch (NumberFormatException | illegalTextGrammarException | sameLabelException | IOException
				| illegalParameterException e) {
			assertTrue(e.getMessage().contains("Ϊ���Ķ����Ѿ�����"));
		}
	}

}
