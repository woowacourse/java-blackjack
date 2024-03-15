package domain.participant.player;

import static game.BlackjackGame.BLACKJACK_SCORE;

import domain.participant.Participant;
import domain.participant.attributes.Bet;
import domain.participant.attributes.Name;

public class Player extends Participant {

    private final Bet bet;

    public Player(final Name name, final Bet bet) {
        super(name);
        this.bet = bet;
    }

    @Override
    public int score() {
        int softScore = hand.score(true);
        if (softScore > BLACKJACK_SCORE) {
            return hand.score(false);
        }
        return softScore;
    }

    public Bet getBet() {
        return bet;
    }
}
