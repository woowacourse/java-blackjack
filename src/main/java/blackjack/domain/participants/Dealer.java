package blackjack.domain.participants;

import blackjack.domain.card.Card;
import blackjack.domain.card.Deck;

public class Dealer extends AbstractParticipant {
    public static final int DEALER_DRAW_CRITERIA = 17;

    public Dealer() {
        super();
        this.name = "딜러";
    }

    public int addedCardCount() {
        return cards.addedCardCount();
    }

    // 테스트용
    public void draw(Card card) {
        cards.add(card);
    }

    @Override
    public void drawMoreCard(final Deck deck) {
        while (needsMoreCard()) {
            draw(deck);
        }
    }

    private boolean needsMoreCard() {
        return cards.calculate() < DEALER_DRAW_CRITERIA;
    }

    @Override
    public boolean isDealer() {
        return true;
    }
}
