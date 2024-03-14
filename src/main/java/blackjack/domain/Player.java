package blackjack.domain;

public class Player extends Participant {

    private final Money money;

    public Player(String name, Money money) {
        super(name);

        this.money = money;
    }

    @Override
    public boolean isPlayable() {
        return !(isBust() || isBlackJackScore());
    }
}
