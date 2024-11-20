package project4;

import java.util.*;

public class Interval<T extends Comparable<T>> {
    private final T start;
    private final T end;

    public Interval(T start, T end) {
        if (start.compareTo(end) > 0) {
            throw new IllegalArgumentException("End time should be after Start time");
        }
        this.start = start;
        this.end = end;
    }

    public boolean within(T value) {
        return start.compareTo(value) <= 0 && end.compareTo(value) >= 0;
    }

    public boolean subinterval(Interval<T> other) {
        return start.compareTo(other.start) <= 0 && end.compareTo(other.end) >= 0;
    }

    public boolean overlaps(Interval<T> other) {
        return !(end.compareTo(other.start) < 0 || start.compareTo(other.end) > 0);
    }

    @Override
    public String toString() {
        return "Interval{" + "start=" + start + ", end=" + end + '}';
    }
}
