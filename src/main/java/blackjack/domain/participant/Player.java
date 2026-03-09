package blackjack.domain.participant;

import blackjack.domain.PlayingCards;

public class Player extends Participant {

    private boolean stopDrawing;

    public Player(String nickname, Role role) {
        super(nickname, PlayingCards.createEmptyHands(), role);
        stopDrawing = false;
    }

    public void stop() {
        stopDrawing = true;
    }

    public boolean isDrawable() {
        if (stopDrawing) {
            return false;
        }
        return hand.isDrawable();
    }
}
