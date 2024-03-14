package domain.state.run;

import domain.card.Card;
import domain.player.Hands;
import domain.state.State;
import domain.state.fininsh.Blackjack;
import domain.state.fininsh.Bust;

public class Hit extends Running {
    public Hit(final Hands hands) {
        super(hands);
    }

    @Override
    public State draw(final Card card) {
        getHands().hit(card);
        if (getHands().getValue().size() == 2 && getHands().calculateScore() == 21) {
            return new Blackjack(getHands());
        }
        if (getHands().calculateScore() > 21) {
            return new Bust(getHands());
        }
        return new Hit(getHands());
    }

    @Override
    public State stand() {
        return null;
    }
}
