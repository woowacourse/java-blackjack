package domain.participant.player;

import static game.BlackjackGame.BLACKJACK_SCORE;

import domain.participant.Participant;

public class Player extends Participant {

    public Player(final String name) {
        super(name);
    }

    @Override
    public int score() {
        int softScore = hand.score(true);
        if (softScore > BLACKJACK_SCORE) {
            return hand.score(false);
        }
        return softScore;
    }
}
