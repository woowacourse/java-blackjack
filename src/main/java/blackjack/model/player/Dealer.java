package blackjack.model.player;

import blackjack.model.card.Card;
import blackjack.model.card.Cards;

public class Dealer extends Participant {
    private static final String NAME = "딜러";

    public Dealer() {
        super(NAME);
    }

    private Dealer(final Cards cards) {
        super(NAME, cards);
    }

    public Participant receive(final Card card) {
        Cards copyOfCards = this.cards.add(card);
        return new Dealer(copyOfCards);
    }

    @Override
    public boolean isFinish() {
        return this.cards.sumScore() > 16;
    }

    @Override
    public Participant hit(final Card card) {
        Cards copyOfCards = this.cards.add(card);
        return new Dealer(copyOfCards);
    }
}
