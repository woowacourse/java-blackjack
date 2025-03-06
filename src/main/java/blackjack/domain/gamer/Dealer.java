package blackjack.domain.gamer;

public class Dealer extends Gamer {

    private static final int MIN_SUM_OF_CARDS = 16;

    @Override
    public boolean canReceiveAdditionalCards() {
        return cards.sum() <= MIN_SUM_OF_CARDS;
    }
}
