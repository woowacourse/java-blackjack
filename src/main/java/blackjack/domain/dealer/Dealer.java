package blackjack.domain.dealer;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hands;
import blackjack.domain.bet.BetLeverage;
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

    public int hit() {
        while (state.isHit()) {
            state = state.draw(drawCard());
        }

        return state.countHit();
    }

    public Card drawCard() {
        return deck.pick();
    }

    public BetLeverage calculateBetLeverage(final State state) {
        return state.calculateBetLeverage(this.state);
    }

    public boolean isNotBlackjack() {
        return !state.isBlackjack();
    }

    public Hands getOpenedHands() {
        return new Hands(List.of(state.getHands().getFirstCard()));
    }

    public Hands getHands() {
        return state.getHands();
    }

    public State getState() {
        return state;
    }
}
