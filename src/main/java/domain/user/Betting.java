package domain.user;

import domain.GameResult;

public class Betting {

    private final long bettingMoney;

    public Betting(Long bettingMoney) {
        validateBetting(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateBetting(Long bettingMoney) {
        if (bettingMoney == 0) {
            throw new IllegalArgumentException("배팅금액을 입력해 주세요.");
        }
        if (bettingMoney < 0) {
            throw new IllegalArgumentException("배팅금액은 음수가 불가능합니다.");
        }
    }

    public long getBettingMoney() {
        return bettingMoney;
    }

    public long calculateBetting(GameResult gameResult) {
        if (gameResult == GameResult.BLACKJACK) {
            return (long) (bettingMoney * 1.5);
        }
        if (gameResult == GameResult.LOSE) {
            return -bettingMoney;
        }
        if (gameResult == GameResult.WIN) {
            return bettingMoney;
        }
        return 0L;
    }
}
