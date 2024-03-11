package blackjack.domain;

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

    public Double getBat() {
        return bat;
    }
}
