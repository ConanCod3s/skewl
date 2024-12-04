package Project4;

public class Time implements Comparable<Time> {
    private final int hours;
    private final int minutes;
    private final String amPM;

    public Time(int hours, int minutes, String amPM) throws InvalidTime {
        if (hours < 1 || hours > 12 || minutes < 0 || minutes > 59 || (!amPM.equals("AM") && !amPM.equals("PM"))) {
            throw new InvalidTime("Invalid time input");
        }
        this.hours = hours;
        this.minutes = minutes;
        this.amPM = amPM;
    }

    public Time(String time) throws InvalidTime {
        try {
            String[] parts = time.split(" ");
            String[] timeParts = parts[0].split(":");
            int hours = Integer.parseInt(timeParts[0]);
            int minutes = Integer.parseInt(timeParts[1]);
            String amPM = parts[1].toUpperCase();

            if (hours < 1 || hours > 12 || minutes < 0 || minutes > 59 || (!amPM.equals("AM") && !amPM.equals("PM"))) {
                throw new InvalidTime("Invalid time");
            }

            this.hours = hours;
            this.minutes = minutes;
            this.amPM = amPM;
        } catch (Exception e) {
            throw new InvalidTime("Invalid time");
        }
    }

    @Override
    public int compareTo(Time other) {
        if (!this.amPM.equals(other.amPM)) {
            return this.amPM.equals("AM") ? -1 : 1;
        }
        if (this.hours != other.hours) {
            return Integer.compare(this.hours, other.hours);
        }
        return Integer.compare(this.minutes, other.minutes);
    }

    @Override
    public String toString() {
        return String.format("%02d:%02d %s", hours, minutes, amPM);
    }
}
