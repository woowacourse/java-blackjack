package blackjack.domain.participant;

import blackjack.domain.card.Card;
import java.util.List;

public class Player extends Participant {
    public Player(final String name, final List<Card> cards) {
        super(name, cards);
    }

    public boolean canDraw() {
        return !state.isFinished();
    }

    @Override
    public List<Card> getInitCards() {
        return getCards();
    }

    @Override
    public List<Card> getCards() {
        return state.cards().values();
    }
}
