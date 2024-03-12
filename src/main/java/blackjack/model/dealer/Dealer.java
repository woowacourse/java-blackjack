package blackjack.model.dealer;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.cardgenerator.CardGenerator;
import java.util.List;

public class Dealer {
    private static final int HIT_END_CONDITION = 17;
    private static final int NON_HIT_COUNT = 2;

    private final Cards cards;

    public Dealer(final CardGenerator cardGenerator) {
        this.cards = new Cards(cardGenerator);
    }

    public void drawUntilEnd(final CardGenerator cardGenerator) {
        cards.addCardUntilTotalScoreReached(cardGenerator, HIT_END_CONDITION);
    }

    public int calculateCardsTotalScore() {
        return cards.calculateTotalScore();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public int getHitCount() {
        return cards.size() - NON_HIT_COUNT;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
