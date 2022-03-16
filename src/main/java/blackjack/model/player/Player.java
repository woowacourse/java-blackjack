package blackjack.model.player;

import blackjack.model.card.CardDeck;
import blackjack.model.card.Cards;

public class Player extends Participant {

    public Player(final String name) {
        super(name);
    }

    private Player(final String name, final Cards cards) {
        super(name, cards);
    }

    @Override
    public Participant drawCardsBy(final CardDeck deck) {
        Cards copyOfCards = null;
        for (int i = 0; i < Cards.START_CARD_COUNT; i++) {
            copyOfCards = this.cards.add(deck.draw());
        }
        return new Player(this.name, copyOfCards);
    }

    @Override
    public boolean isFinish() {
        return this.cards.sumScore() >= 21;
    }

    @Override
    public Participant hitBy(final CardDeck deck) {
        Cards copyOfCards = this.cards.add(deck.draw());
        return new Player(this.name, copyOfCards);
    }
}
