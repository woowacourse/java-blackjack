package blackjack.domain.state.running;

import blackjack.domain.card.Deck;
import blackjack.domain.card.HoldingCards;
import blackjack.domain.state.State;
import blackjack.domain.state.finished.Bust;
import blackjack.domain.state.finished.Stand;

public final class Hit extends Running {

    Hit(HoldingCards holdingCards) {
        super(holdingCards);
    }

    @Override
    public State drawCard(Deck deck) {
        holdingCards().addCard(deck.drawCard());
        if (isBust()) {
            return new Bust(holdingCards());
        }
        return new Hit(holdingCards());
    }

    private boolean isBust() {
        return super.holdingCards().isBust();
    }

    @Override
    public State stand() {
        return new Stand(holdingCards());
    }
}
