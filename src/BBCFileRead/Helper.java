package BBCFileRead;

import org.joda.time.DateTime;

public class Helper {
    
    public static final long ONE_DAY = 60 * 60 * 24;
    
    public static long calcDelay(int hour, int minute, int second) {
        if (!(0 <= hour && hour <=23 && 0 <= minute && minute <=59 && 0 <=second && second <= 59)) {
            throw new IllegalArgumentException();
        }
        return calcDelay(fixed(hour, minute, second));
    }
    
    private static long calcDelay(DateTime targetDatetimeOfToday) {
        long delay = 0;
        DateTime now = new DateTime();
        
        //ʱ����ѹ���ֻ����ʱ����������ʱ�����ִ��
        if (now.isAfter(targetDatetimeOfToday)) {
            delay = now.plusDays(1).getMillis() - now.getMillis();
            
        //ʱ���δ��
        } else {
            delay = targetDatetimeOfToday.getMillis() - now.getMillis();
        }
        
        return delay;
    }
    
    /**
     * ��������һ��DateTime����
     * 1.����Ϊ����
     * 2.ʱ����Ϊ����ָ����ֵ
     * @param hour 0-23
     * @param minute 0-59
     * @param second 0-59
     * @return
     */
    private static DateTime fixed(int hour, int minute, int second) {
        
        return new DateTime()
                    .withHourOfDay(hour).withMinuteOfHour(minute).withSecondOfMinute(second);
    }

}

