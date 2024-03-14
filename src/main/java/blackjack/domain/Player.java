package blackjack.domain;

public class Player extends Participant {

    private final Money money;

    public Player(String name, Money money) {
        super(name);

        if (money.isNegative()) {
            throw new IllegalArgumentException("사용자의 초기 돈은 음수가 될 수 없습니다.");
        }

        this.money = money;
    }

    public Money calculateProfit(GameResult gameResult) {
        return money.multiply(gameResult.getProfitMultiplier());
    }

    @Override
    public boolean isPlayable() {
        return !(isBust() || isBlackJackScore());
    }
}
