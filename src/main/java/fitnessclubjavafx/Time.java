package fitnessclubjavafx;

/**
 * Enum class representing the times when fitness classes are offered.
 *
 * @author Ved Patel, Vivek Manthri
 */
public enum Time {

    MORNING(9, 30),
    AFTERNOON(14, 0),
    EVENING(18, 30);

    private final int hour;
    private final int minute;

    /**
     * Parameterized constructor requires 2 parameters to create a Time object
     *
     * @param hour   the hour of the time
     * @param minute the minute of the time
     */
    Time(int hour, int minute) {
        this.hour = hour;
        this.minute = minute;
    }

    /**
     * Returns a string representation of the time
     *
     * @return a string for the time with the format hour:minute
     */
    @Override
    public String toString() {
        return String.format("%01d:%02d", hour, minute);
    }
}
