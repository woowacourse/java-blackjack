package blackjack.domain.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.rule.state.DealerHitState;
import blackjack.domain.rule.state.State;
import java.util.List;

public class Dealer {

    public static final String DEALER_NAME = "딜러";

    private final Deck deck;
    private State state;

    public Dealer(final Deck deck) {
        this.deck = deck;
        this.state = DealerHitState.start(drawCard(), drawCard());
    }

    public Card drawCard() {
        return deck.pick();
    }

    public boolean isNotBlackjack() {
        return !state.isBlackjack();
    }

    public int hit() {
        int count = 0;
        while (state.isHit()) {
            state = state.draw(drawCard());
            count++;
        }

        return count;
    }

    public Hands getOpenedHands() {
        return new Hands(List.of(state.getHands().getFirstCard()));
    }

    public State getState() {
        return state;
    }

    public Hands getHands() {
        return state.getHands();
    }
}
