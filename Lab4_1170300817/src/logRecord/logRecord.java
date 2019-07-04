package logRecord;

import java.time.Instant;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class logRecord {
    String logType; //log����
    String reason;
    String className; //����
    String methodName; //������
    Instant time; //ʱ��

    public logRecord(String line) {
        String regex = "TYPE\\((.*)\\) TIME\\((.*)\\) METHOD\\(:(.*)\\.(.*)\\(.*\\)\\) REASON\\((.*)\\)";
        Matcher matcher = Pattern.compile(regex).matcher(line);
        if(matcher.find()) {
            logType = matcher.group(1);
            className = matcher.group(3);
            methodName = matcher.group(4);
            reason = matcher.group(5);
            String timeStr = matcher.group(2);
            time = Instant.parse(timeStr.replace(' ','T')+"Z");
        }
    }

    @Override
    public String toString() {
        return String.format("��������:\t%s%n����:\t%s%n������:\t%s%nʱ��:\t%s%n��ϸ��Ϣ:\t%s%n%n",
                logType,className,methodName,time,reason);
    }

    public String getLogType() {
        return logType;
    }

    public String getReason() {
        return reason;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }

    public Instant getTime() {
        return time;
    }
}