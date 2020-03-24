package blackjack.domain.participants;

import blackjack.domain.card.Deck;

public class Dealer extends Participant {
    public static final int DEALER_DRAW_CRITERIA = 17;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(DEALER_NAME);
    }

    public int countAddedCard() {
        return hand.countAddedCard();
    }

    public void drawMoreCard(final Deck deck) {
        while (needsMoreCard()) {
            draw(deck.pop());
        }
    }

    public boolean needsMoreCard() {
        return hand.calculate() < DEALER_DRAW_CRITERIA;
    }
}
