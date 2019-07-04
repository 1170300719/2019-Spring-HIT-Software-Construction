package debug;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TopVotedCandidateTest {
	@Test
	public void TopVotedCandidateTest1() {
		int[] persons = { 0, 1, 1, 0, 0, 1, 0 };//ͶƱ������
		int[] times = { 0, 5, 10, 15, 20, 25, 30 };//ͶƱ��ʱ��

		int[] input = { 3, 12, 25, 15, 24, 8 };//�����ʱ���
		int[] output = { 0, 1, 1, 0, 0, 1 };//����Ļ�ʤ��
		TopVotedCandidate topVotedCandidate = new TopVotedCandidate(persons, times);
		for (int i = 0; i < 6; i++) {//ѭ������
			assertEquals(topVotedCandidate.q(input[i]), output[i]);
		}
	}

}
