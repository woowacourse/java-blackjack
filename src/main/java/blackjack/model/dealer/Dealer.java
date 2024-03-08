package blackjack.model.dealer;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.cardgenerator.CardGenerator;

import java.util.List;

public class Dealer {
    private static final int ACTION_CONDITION = 17;
    private static final int NON_ACTION_COUNT = 2;

    private final Cards cards;

    public Dealer(final CardGenerator cardGenerator) {
        this.cards = new Cards(cardGenerator);
    }

    public void doAction(final CardGenerator cardGenerator) {
        while (cards.calculateCardsTotalScore() < ACTION_CONDITION) {
            cards.addCard(cardGenerator);
        }
    }

    public int calculateCardsTotalScore() {
        return cards.calculateCardsTotalScore();
    }

    public boolean isBurst() {
        return cards.isBurst();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public int getActionCount() {
        return cards.size() - NON_ACTION_COUNT;
    }

    public Card getFirstCard() {
        return cards.get(0);
    }

    public List<Card> getCards() {
        return cards.getCards();
    }
}
