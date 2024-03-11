package blackjack.domain;

import java.util.Objects;

public class Batting {
    private static final int MIN_BAT = 1;
    private static final int MAX_BAT = 10_000_000;

    private final Double bat;

    private Batting(Double bat) {
        this.bat = bat;
    }

    public static Batting from(Double bat) {
        validateBat(bat);
        return new Batting(bat);
    }

    private static void validateBat(Double bat) {
        if (bat < MIN_BAT || bat > MAX_BAT) {
            throw new IllegalArgumentException("배팅은 최소 " + MIN_BAT + "부터 "
                    + MAX_BAT + "까지 가능합니다.");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Batting batting = (Batting) o;
        return Objects.equals(bat, batting.bat);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bat);
    }

    public Double getBat() {
        return bat;
    }
}
