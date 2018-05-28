public class TimeParser {
    private static final int MINS_PER_HR = 60;
    private static final int SECS_PER_MIN = 60;
    private static final int MIN_HRS = 0;
    private static final int MAX_HRS = 23;
    private static final int MIN_MINS = 0;
    private static final int MAX_MINS = 59;
    private static final int MIN_SECS = 0;
    private static final int MAX_SECS = 59;

    public static int parseTimeToSeconds(String time) throws NumberFormatException {
        time = time.trim().toLowerCase();
        int firstColon = time.indexOf(':');

        if (firstColon == -1) {
            throw new NumberFormatException("Unrecognized time format");
        }

        int secondColon = time.indexOf(':', firstColon + 1);
        if (secondColon == -1) {
            throw new NumberFormatException("Unrecognized time format");
        }

        int hours = Integer.valueOf(time.substring(0, firstColon));

        int minutes = Integer.valueOf(time.substring(firstColon + 1, secondColon));

        int seconds = Integer.valueOf(time.substring(secondColon + 1, secondColon + 3));
        if (time.contains("pm")) {
            hours += 12;
        } else if (time.contains("am") && hours == 12) {
            hours = 0;
        }
        if ((hours < MIN_HRS || hours > MAX_HRS)
                || (minutes < MIN_MINS || minutes > MAX_MINS)
                || (seconds < MIN_SECS || seconds > MAX_SECS)) {
            throw new IllegalArgumentException("Unacceptable time specified");
        }
        return (((hours * MINS_PER_HR) + minutes) * SECS_PER_MIN) + seconds;
    }
}

