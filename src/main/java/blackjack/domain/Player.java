package blackjack.domain;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isPlayable() {
        return !(isBust() || isBlackJack());
    }
}
