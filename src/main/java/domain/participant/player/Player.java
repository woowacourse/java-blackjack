package domain.participant.player;

import static game.BlackjackGame.BLACKJACK_SCORE;

import domain.participant.Participant;
import domain.participant.attributes.Bet;

public class Player extends Participant {

    private final Bet bet;

    public Player(final String name) {
        super(name);
        this.bet = new Bet(0);
    }

    public Player(final String name, final int bet) {
        super(name);
        this.bet = new Bet(bet);
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
