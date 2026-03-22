package domain.participant;

public class Betting {
    private static final int MIN_BETTING_AMOUNT = 10;

    private final int betting;

    public Betting(int betting) {
        validateBetting(betting);
        this.betting = betting;
    }

    public int getBetting(){
        return betting;
    }

    private void validateBetting(final int betting) {
        if (betting < MIN_BETTING_AMOUNT) {
            throw new IllegalArgumentException("베팅 금액은 10 이상이며 10원 단위여야 합니다.");
        }
        if (betting % MIN_BETTING_AMOUNT != 0) {
            throw new IllegalArgumentException("베팅 금액은 10 이상이며 10원 단위여야 합니다.");
        }
    }
}
