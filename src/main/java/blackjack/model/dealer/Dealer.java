package blackjack.model.dealer;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.card.Score;
import blackjack.model.cardgenerator.CardGenerator;

import java.util.List;

public class Dealer {
    private static final Score END_CONDITION = Score.from(17);
    private static final int NON_ACTION_COUNT = 2;

    private final Cards cards;

    public Dealer(final CardGenerator cardGenerator) {
        this.cards = new Cards(cardGenerator);
    }

    public void hitUntilEnd(final CardGenerator cardGenerator) {
        while (cards.calculateCardsTotalScore().lessThan(END_CONDITION)) {
            cards.addCard(cardGenerator);
        }
    }

    public Score calculateCardsTotalScore() {
        return cards.calculateCardsTotalScore();
    }

    public boolean isBust() {
        return cards.isBust();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public int getActionCount() {
        return cards.getSize() - NON_ACTION_COUNT;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
