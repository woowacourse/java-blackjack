package blackjack.domain.participant;

import blackjack.domain.card.Card;

import java.util.List;

public class Dealer extends Participant {
    private static final int MAX_SUM_FOR_MORE_CARD = 16;
    private static final String name = "딜러";

    public Dealer() {
        super(name);
    }

    @Override
    public List<Card> getInitialCards() {
        return cards.subList(0, 1);
    }

    @Override
    public boolean checkMoreCardAvailable() {
        return calculateScore() <= MAX_SUM_FOR_MORE_CARD;
    }
}
