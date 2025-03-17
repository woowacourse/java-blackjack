package domain.user;

import domain.GameResult;

public class Betting {

    private final long bettingMoney;

    public Betting(final long bettingMoney) {
        validateBetting(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateBetting(final long bettingMoney) {
        if (bettingMoney <= 0) {
            throw new IllegalArgumentException("배팅금액을 1원 이상 입력해 주세요.");
        }
    }

    public long getBettingMoney() {
        return bettingMoney;
    }

    public long calculateBetting(final GameResult gameResult) {
        if (gameResult == GameResult.BLACKJACK) {
            return (long) (bettingMoney * 1.5);
        }
        if (gameResult == GameResult.LOSE) {
            return -bettingMoney;
        }
        if (gameResult == GameResult.WIN) {
            return bettingMoney;
        }
        return 0;
    }
}
