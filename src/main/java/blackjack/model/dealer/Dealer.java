package blackjack.model.dealer;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;
import blackjack.model.cardgenerator.CardGenerator;

public class Dealer {
    private static final int ACTION_CONDITION = 17;
    private static final int NON_ACTION_COUNT = 2;

    private final Cards cards;

    public Dealer(final CardGenerator cardGenerator) {
        this.cards = new Cards(cardGenerator);
    }

    public void doAction(final CardGenerator cardGenerator) {
        while (cards.calculateCardsTotal() < ACTION_CONDITION) {
            cards.addCard(cardGenerator);
        }
    }

    public int calculateCardsTotal() {
        return cards.calculateCardsTotal();
    }

    public boolean isBurst() {
        return cards.isBurst();
    }

    public boolean isBlackJack() {
        return cards.isBlackJack();
    }

    public int getActionCount() {
        return cards.getCards().size() - NON_ACTION_COUNT;
    }

    public Card getFirstCard() {
        return cards.getCards().get(0);
    }

    public Cards getCards() {
        return cards;
    }
}
