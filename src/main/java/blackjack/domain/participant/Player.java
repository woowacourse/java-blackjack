package blackjack.domain.participant;

public class Player extends Participant {

    private static final int MIN_PLAYER_MONEY_AMOUNT = 1;

    private final PlayerMoney money;

    public Player(String name, PlayerMoney money) {
        super(name);

        if (!money.isGreaterEqualThan(MIN_PLAYER_MONEY_AMOUNT)) {
            String message = String.format("사용자의 초기 돈은 최소 %d 이상입니다.", MIN_PLAYER_MONEY_AMOUNT);

            throw new IllegalArgumentException(message);
        }

        this.money = money;
    }

    public PlayerMoney getMoney() {
        return money;
    }

    @Override
    public boolean isPlayable() {
        return !(isBust() || isBlackJackScore());
    }
}
