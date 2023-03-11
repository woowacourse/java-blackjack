package blackjack.domain.participant;

import java.util.List;

public class Dealer extends Participant {
    private static final int MAX_SCORE_TO_RECEIVE = 16;
    public static final String DEALER_NAME = "딜러";

    @Override
    public boolean isAbleToReceive() {
        return score.getScore() <= MAX_SCORE_TO_RECEIVE;
    }

    public List<String> getOpenCardName() {
        return List.of(cards.get(0).getCardName());
    }

    @Override
    public String getName() {
        return DEALER_NAME;
    }
}
