package blackjack.domain.gameplayer;

import java.util.Objects;

public class Betting {

    private static final String INVALID_RANGE_MESSAGE = "배팅 금액은 0원부터 30만원까지 입력받을 수 있습니다.";
    private static final int MAX_BOUND = 300000;
    private static final int MIN_BOUND = 1;
    private final int betting;

    private Betting(int betting) {
        this.betting = betting;
    }

    public static Betting of(int betting) {
        validate(betting);
        return new Betting(betting);
    }

    private static void validate(int betting) {
        if (isInvalidRange(betting)) {
            throw new IllegalArgumentException(INVALID_RANGE_MESSAGE);
        }
    }

    private static boolean isInvalidRange(int betting) {
        return betting < MIN_BOUND || betting > MAX_BOUND;
    }

    public int getMoney() {
        return betting;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Betting betting1 = (Betting) o;
        return betting == betting1.betting;
    }

    @Override
    public int hashCode() {
        return Objects.hash(betting);
    }
}
