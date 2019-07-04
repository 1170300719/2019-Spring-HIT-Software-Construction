/* Copyright (c) 2015-2016 MIT 6.005 course staff, all rights reserved.
 * Redistribution of original or derived work requires permission of course staff.
 */
package P1.poet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.File;
import java.io.IOException;

import org.junit.Test;

/**
 * Tests for GraphPoet.
 */
public class GraphPoetTest {
	// Testing strategy
	// ��GraphPoet���ļ�������л��֣�
	// һ�����룬�������룬���ļ�
	// ��poem��ѡ����̽��л��֣�
	// ����Ȩ����1����Ҫ�Ƚ�ѡ��Ȩ�ش��
	// ��toString�����뻮�֣�
	// ���ļ����ǿ��ļ�
	@Test(expected = AssertionError.class)
	public void testAssertionsEnabled() {
		assert false; // make sure assertions are enabled with VM argument: -ea
	}

	// ���ļ�����
	@Test
	public void testempty() throws IOException {
		final GraphPoet nimoy = new GraphPoet(new File("test\\P1\\poet\\empty.txt"));
		final String input = "To strange worlds";
		assertEquals(nimoy.poem(input), "To strange worlds");
	}

	// ��������
	@Test
	public void testSingleLine() throws IOException {
		final GraphPoet nimoy = new GraphPoet(new File("test\\P1\\poet\\characters.txt"));
		final String input = "a c e g i";
		assertEquals(nimoy.poem(input), "a b c d e f g h i");
	}

	// ��������
	@Test
	public void testMutiLine() throws IOException {
		final GraphPoet nimoy = new GraphPoet(new File("test\\P1\\poet\\Mutiline.txt"));
		final String input = "To strange worlds";
		assertEquals(nimoy.poem(input), "To explore strange new worlds");
	}
	// ������Ҫѡ������
		@Test
		public void testSelect() throws IOException {
			final GraphPoet nimoy = new GraphPoet(new File("test\\P1\\poet\\select.txt"));
			final String input = "a c";
			assertEquals(nimoy.poem(input), "a b c");
		}

	// ���tostring�ǿ��ļ�
	@Test
	public void testtoStringempty() throws IOException {
		final GraphPoet nimoy = new GraphPoet(new File("test\\P1\\poet\\empty.txt"));
		System.out.println(nimoy.toString());
		assertEquals("", nimoy.toString());
	}

	// ���tostring�ǿ��ļ�
	@Test
	public void testtoString() throws IOException {
		final GraphPoet nimoy = new GraphPoet(new File("test\\P1\\poet\\tostring.txt"));
		assertTrue(nimoy.toString().contains("a->b Ȩ��Ϊ1"));
		assertTrue(nimoy.toString().contains("b->c Ȩ��Ϊ1"));
	}

}
