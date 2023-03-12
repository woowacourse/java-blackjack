package blackjack.domain.player.state;

import blackjack.domain.player.Hand;

public abstract class Running extends PlayerStatus {
    Running(Hand hand) {
        super(hand);
    }

    @Override
    public boolean isRunning() {
        return true;
    }

    @Override
    public PlayerStatus stay() {
        return new Stay(hand());
    }
}
