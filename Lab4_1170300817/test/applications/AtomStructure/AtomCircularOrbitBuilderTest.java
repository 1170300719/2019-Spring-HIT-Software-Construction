package applications.AtomStructure;

import static org.junit.Assert.assertTrue;

import java.io.IOException;

import org.junit.Test;

import exception.illegalTextGrammarException;
import exception.logicalErrorException;

public class AtomCircularOrbitBuilderTest {
	AtomCircularOrbitBuilder builder = new AtomCircularOrbitBuilder();

	@Test
	public void creatTest()
			throws NumberFormatException, illegalTextGrammarException, IOException, logicalErrorException {
		builder.createCircularOrbit();
		builder.createFromFile("src/txt/AtomicStructure.txt");
	}

	@Test
	public void exception1Test() {
		try {
			builder.createFromFile("src/txt/exception/Atom1.txt");
		} catch (NumberFormatException | illegalTextGrammarException | IOException | logicalErrorException e) {
			assertTrue(e.getMessage().contains(":Ԫ�����ֲ���ȱʧ"));
		}
	}

	@Test
	public void exception2Test()
			throws NumberFormatException, illegalTextGrammarException, IOException, logicalErrorException {
		try {
			builder.createFromFile("src/txt/exception/Atom2.txt");
		} catch (NumberFormatException | illegalTextGrammarException | IOException | logicalErrorException e) {
			assertTrue(e.getMessage().contains(":Ԫ�����ִ���"));
		}
	}

	@Test
	public void exception3Test()
			throws NumberFormatException, illegalTextGrammarException, IOException, logicalErrorException {
		try {
			builder.createFromFile("src/txt/exception/Atom3.txt");
		} catch (NumberFormatException | illegalTextGrammarException | IOException | logicalErrorException e) {
			assertTrue(e.getMessage().contains(":���������ȱʧ"));
		}
	}

	@Test
	public void exception4Test()
			throws NumberFormatException, illegalTextGrammarException, IOException, logicalErrorException {
		try {
			builder.createFromFile("src/txt/exception/Atom4.txt");
		} catch (NumberFormatException | illegalTextGrammarException | IOException | logicalErrorException e) {
			assertTrue(e.getMessage().contains(":���������"));
		}
	}

	@Test
	public void exception5Test()
			throws NumberFormatException, illegalTextGrammarException, IOException, logicalErrorException {
		try {
			builder.createFromFile("src/txt/exception/Atom5.txt");
		} catch (NumberFormatException | illegalTextGrammarException | IOException | logicalErrorException e) {
			assertTrue(e.getMessage().contains(":������Ӳ�������"));
		}
	}

	@Test
	public void exception6Test()
			throws NumberFormatException, illegalTextGrammarException, IOException, logicalErrorException {
		try {
			builder.createFromFile("src/txt/exception/AtomLogicalErr.txt");
		} catch (NumberFormatException | illegalTextGrammarException | IOException | logicalErrorException e) {
			assertTrue(e.getMessage().contains("�����ǰ��һ�´���"));
		}
	}

}
