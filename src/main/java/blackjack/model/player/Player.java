package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public class Player extends Participant {

    public Player(final String name) {
        super(name);
    }

    private Player(final String name, final Cards cards) {
        super(name, cards);
    }

    public Participant receive(final Card card) {
        Cards copyOfCards = this.cards.add(card);
        return new Player(this.name, copyOfCards);
    }

    @Override
    public boolean isFinish() {
        return this.cards.sumScore() >= 21;
    }

    @Override
    public Participant hit(final Card card) {
        Cards copyOfCards = this.cards.add(card);
        return new Player(this.name, copyOfCards);
    }
}
