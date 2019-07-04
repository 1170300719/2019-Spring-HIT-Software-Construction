package applications.TrackGame;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import circularOrbit.CircularOrbitBuilder;
import exception.illegalParameterException;
import exception.illegalTextGrammarException;
import exception.sameLabelException;
import phsicalObject.PhysicalObject;

public class TrackCircularOrbitBuilder extends CircularOrbitBuilder<PhysicalObject, Athlete> {
	public Integer gameType;
	public Integer trackNum;


	public Integer getGameType() {
		return gameType;
	}

	public Integer getTrackNum() {
		return trackNum;
	}

	/**
	 * �����������͵�����
	 */
	@Override
	public void createCircularOrbit() {
		concreteCircularOrbit = new TrackCircularOrbit();
	}

	public List<Athlete> createFromFile(String fileString)
			throws illegalTextGrammarException, NumberFormatException, IOException, sameLabelException, illegalParameterException {
		List<Athlete> athleteList = new LinkedList<>();
		BufferedReader in = new BufferedReader(new FileReader(new File(fileString)));
		String fileline;
		String athletepattern = "Athlete\\s*::=\\s*<([a-zA-Z]+),(\\d+),([A-Z]{3}),(\\d+),(\\d{1,2}\\.\\d{2})>";
		String gamepattern = "Game\\s*::=\\s*(100|200|400)";
		String trackpattern = "NumOfTracks\\s*::=\\s*([4-9]|10)";

		while ((fileline = in.readLine()) != null) {
			Matcher athleteMatcher = Pattern.compile(athletepattern).matcher(fileline);
			Matcher gameMatcher = Pattern.compile(gamepattern).matcher(fileline);
			Matcher trackMatcher = Pattern.compile(trackpattern).matcher(fileline);
			if (athleteMatcher.find()) {
				String nameString = athleteMatcher.group(1);
				Integer idNum = Integer.valueOf(athleteMatcher.group(2));
				String nationality = athleteMatcher.group(3);
				int age = Integer.valueOf(athleteMatcher.group(4));
				double bestRecord = Double.valueOf(athleteMatcher.group(5));
				Athlete a;
				a = Athlete.getInstance(nameString, idNum, nationality, age, bestRecord);
				athleteList.add(a);
			} else if (gameMatcher.find()) {
				gameType = Integer.valueOf(gameMatcher.group(1));
			} else if (trackMatcher.find()) {
				trackNum = Integer.valueOf(trackMatcher.group(1));
			} else {
				analyzeInput(fileline);
			}
		}
		in.close();
		return athleteList;
	}

	public String analyzeInput(String readLine) throws illegalTextGrammarException {
		String msgString = null;
		String[] arguments = readLine.trim().split("\\s");
		if (arguments[0].equals("Athlete")) {
			String[] parameter = arguments[2].split(",");
			if (parameter.length != 5) {
				throw new illegalTextGrammarException(readLine + ":�˶�Ա����ȱʧ");
			}
			if (!Pattern.matches("(<[A-Za-z]+)", parameter[0])) {
//				System.out.println(111);
				throw new illegalTextGrammarException(readLine + ":�˶�Ա���ִ���");
			}
			if (!Pattern.matches("([A-Z]{3})", parameter[2])) {
				throw new illegalTextGrammarException(readLine + ":�˶�Ա��������");
			}
			if (!Pattern.matches("(\\d{1,2}\\.\\d{2}>)", parameter[4])) {
				throw new illegalTextGrammarException(readLine + ":�˶�Ա��óɼ�����");
			}
		} else if (arguments[0].equals("Game")) {
			if (!Pattern.matches("(100|200|400)", arguments[2])) {
				throw new illegalTextGrammarException(readLine + ":��Ч�ı�������");
			}
		} else if (arguments[0].equals("NumOfTracks")) {
			if (!Pattern.matches("([4-9]|10)", arguments[2])) {
				throw new illegalTextGrammarException(readLine + ":��Ч�Ĺ����Ŀ");
			}
		}
		return msgString;
	}
}
