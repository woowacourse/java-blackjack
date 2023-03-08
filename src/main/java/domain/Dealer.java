package domain;

import java.util.Collections;
import java.util.List;

public class Dealer extends Participant {
    private static final int INITIAL_CARD_INDEX = 1;
    private static final BlackjackScore MORE_CARD_LIMIT_SCORE = BlackjackScore.from(16);

    public Dealer(PlayerName playerName) {
        super(playerName);
    }

    public boolean isAbleToReceiveCard() {
        return !calculateBlackjackScore().isGreaterThan(MORE_CARD_LIMIT_SCORE);
    }

    @Override
    public List<Card> getInitialCards() {
        return Collections.unmodifiableList(
                cards.getCards().subList(0, INITIAL_CARD_INDEX)
        );
    }
}
