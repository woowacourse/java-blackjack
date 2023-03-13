package domain.player;

import domain.card.Hand;
import domain.gameresult.ScoreComparator;

public class Dealer extends Player {

    public static final int DEALER_MIN_SCORE = 16;
    public static final String DEALER_NAME = "딜러";

    public Dealer(Hand hand) {
        super(hand, Name.dealerName(), new Bet(10000));
    }

    public Bet compareWith(Gambler participant) {
        return ScoreComparator.compare(this, participant);
    }
}
