package blackjack.domain.player;

import blackjack.domain.player.Player;

public class Participant extends Player {

    public Participant(final String name) {
        super(name);
    }

    @Override
    public boolean canAddCard() {
        return getTotalScore() <= 21;
    }
}
