package blackjack.domain.participant;

public class Betting {

    private static final int MIN_BETTING = 0;

    private final int betting;

    public Betting(int betting) {
        validateBetting(betting);

        this.betting = betting;
    }

    public int getBetting() {
        return betting;
    }

    private void validateBetting(int betting) {
        if (betting < MIN_BETTING) {
            throw new IllegalArgumentException(String.format("배팅할 금액은 %d 보다 커야 합니다.", MIN_BETTING));
        }
    }
}
