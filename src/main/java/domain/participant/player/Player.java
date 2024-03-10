package domain.participant.player;

import static game.BlackjackGame.BLACKJACK_SCORE;

import domain.participant.Name;
import domain.participant.Participant;

public class Player extends Participant {

    private Player(final Name name) {
        super(name);
    }

    public static Player from(final Name name) {
        return new Player(name);
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
