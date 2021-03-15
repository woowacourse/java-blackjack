package blackjack.domain.state;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;

public class Blackjack extends Finished {
    public Blackjack(Cards cards) {
        super(cards);
    }

    @Override
    public State takeCard(Card card) {
        cards.takeCard(card);
        return new Burst(cards);
    }

    @Override
    public boolean isBlackjack() {
        return true;
    }

    @Override
    public boolean isBurst() {
        return false;
    }

}
