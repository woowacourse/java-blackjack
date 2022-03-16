package blackjack.domain.bet;

public class Betting {

    private static final int MONEY_UNIT = 10;

    private final int betting;

    public Betting(final int betting) {
        validateBetting(betting);
        this.betting = betting;
    }

    private void validateBetting(final int betting) {
        validateNegative(betting);
        validateMoneyUnit(betting);
    }

    private void validateNegative(final int betting) {
        if (betting < 0) {
            throw new IllegalArgumentException("베팅 금액에 음수를 입력할 수 없습니다.");
        }
    }

    private void validateMoneyUnit(final int betting) {
        if (betting % MONEY_UNIT != 0) {
            throw new IllegalArgumentException("베팅 금액은 10원 단위로 입력해주세요.");
        }
    }
}
