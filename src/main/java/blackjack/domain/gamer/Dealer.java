package blackjack.domain.gamer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

import java.util.List;

public class Dealer extends Gamer {
    private static final int ADDITIONAL_DISTRIBUTE_STANDARD = 16;
    private static final String NAME = "딜러";

    public Dealer(List<Card> cards) {
        super(NAME);
        for (Card card : cards) {
            addCard(card);
        }
    }

    @Override
    public boolean canDraw() {
        return getCardsNumberSum() <= ADDITIONAL_DISTRIBUTE_STANDARD;
    }
}
