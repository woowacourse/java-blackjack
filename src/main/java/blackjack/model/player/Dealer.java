package blackjack.model.player;

import blackjack.model.card.CardDeck;
import blackjack.model.card.Cards;

public class Dealer extends Participant {
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    private Dealer(final Cards cards) {
        super(NAME, cards);
    }

    @Override
    public Participant drawCardsBy(final CardDeck deck) {
        Cards copyOfCards = null;
        for (int i = 0; i < Cards.START_CARD_COUNT; i++) {
            copyOfCards = this.cards.add(deck.draw());
        }
        return new Dealer(copyOfCards);
    }

    @Override
    public boolean isFinish() {
        return this.cards.sumScore() > 16;
    }

    @Override
    public Participant hitBy(final CardDeck deck) {
        Cards copyOfCards = this.cards.add(deck.draw());
        return new Dealer(copyOfCards);
    }
}
