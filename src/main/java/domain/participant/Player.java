package domain.participant;

import domain.PlayerCommand;
import domain.card.Card;
import domain.card.Cards;
import java.util.List;

public class Player extends Participant {

    private static final int BUST_BOUNDARY_EXCLUSIVE = 21;

    public Player(final Name name, final List<Card> cards) {
        super(name, cards);
    }

    public void receiveAdditionalCard(final PlayerCommand command, final Cards cards) {
        if (command.isHit()) {
            this.receiveCard(cards.getCard());
        }
    }

    public boolean isNotBust() {
        return calculateScore() < BUST_BOUNDARY_EXCLUSIVE;
    }
}
