package blackjack.domain.gamer;

import blackjack.domain.card.Card;

public class Dealer extends Gamer {

    private static final int MIN_SUM_OF_CARDS = 16;

    public Dealer(Card... cards) {
        super("딜러", cards);
    }

    @Override
    public boolean canReceiveAdditionalCards() {
        return cards.sum() <= MIN_SUM_OF_CARDS;
    }
}
