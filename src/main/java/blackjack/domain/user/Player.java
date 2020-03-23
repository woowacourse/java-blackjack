package blackjack.domain.user;

public class Player extends AbstractUser {
    private final BettingMoney bettingMoney;

    private Player(String name, String bettingMoney) {
        super(name, Cards.emptyCards());
        this.bettingMoney = BettingMoney.from(bettingMoney);
    }

    public static Player of(String name, String bettingMoney) {
        return new Player(name, bettingMoney);
    }
}