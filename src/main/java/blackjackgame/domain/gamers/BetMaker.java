package blackjackgame.domain.gamers;

public class BetMaker {
    private final Double betMoney;

    public BetMaker(Double betMoney) {
        validateBetMoneyBiggerThanZero(betMoney);
        this.betMoney = betMoney;
    }

    private void validateBetMoneyBiggerThanZero(Double betMoney) {
        if (betMoney < 0) {
            throw new IllegalArgumentException("0 미만의 값은 가질 수 없습니다.");
        }
    }

    public Double getBetMoney() {
        return betMoney;
    }
}
