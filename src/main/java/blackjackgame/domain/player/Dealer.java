package blackjackgame.domain.player;

import java.util.List;

import blackjackgame.domain.card.Card;
import blackjackgame.domain.state.Ready;
import blackjackgame.domain.state.State;

public class Dealer extends Player {
    private static final int DEALER_HIT_STANDARD = 16;

    private Dealer(State state) {
        super(state);
    }

    public static Dealer from(List<Card> cards) {
        State state = new Ready();
        for (Card card : cards) {
            state = state.draw(card);
        }
        return new Dealer(state);
    }

    @Override
    protected List<Card> startingCards() {
        return List.of(cards().get(1));
    }

    public boolean canHit() {
        return isRunning() && isLessThan(DEALER_HIT_STANDARD);
    }

    public String getName() {
        return "딜러";
    }
}
