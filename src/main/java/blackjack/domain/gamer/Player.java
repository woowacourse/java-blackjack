package blackjack.domain.gamer;

import blackjack.domain.MatchResult;
import blackjack.domain.card.Card;
import blackjack.domain.state.State;

public class Player extends Participants {

    private State state;
    private double money;

    public Player(String name) {
        super(name);
    }

    public void draw(final Card card) {
        state = state.draw(card);
    }

    public double profit() {
        return state.profit(money);
    }

    @Override
    public boolean canDraw() {
        return this.getTakenCards().calculateScore() < MatchResult.MAX_SCORE;
    }
}
