package blackjack.domain;

public class Player extends Participant {

    public Player(String name) {
        super(name);
    }

    @Override
    public boolean isAvailable() {
        return !(isSumMaxBeforeBust()) && !(isBust());
    }

    private boolean isSumMaxBeforeBust() {
        return computeSumOfCards() == SUM_MAXIMUM_BEFORE_BUST;
    }
}
