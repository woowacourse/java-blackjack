package blackjack.domain;

import java.util.List;

public class Player extends Participant {
    private static final int MAX_SCORE_TO_RECEIVE = 21;

    private Name name;

    public Player(List<Card> cards) {
        super(cards);
    }

    @Override
    public boolean isAbleToReceive() {
        score.calculateScore(extractNumbers());
        return score.getScore() <= MAX_SCORE_TO_RECEIVE;
    }
}
