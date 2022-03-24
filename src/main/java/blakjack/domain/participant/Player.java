package blakjack.domain.participant;

import blakjack.domain.Chip;
import blakjack.domain.PlayerName;
import blakjack.domain.PrivateArea;
import blakjack.domain.card.Card;

public final class Player extends Participant {
    public Player(final PlayerName playerName, final Chip chip) {
        super(new PrivateArea(playerName.getValue()), chip);
    }

    @Override
    public void hit(final Card card) {
        state = state.draw(card);
    }
}
