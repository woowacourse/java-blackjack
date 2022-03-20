package blackjack.domain.player;

import blackjack.domain.card.Card;
import blackjack.domain.card.Cards;
import java.util.List;

public class Dealer extends Participant {

    private static final int MAX_SCORE_TO_RECEIVE_CARD = 16;
    private static final int FIRST_OPEN_COUNT = 1;

    public Dealer(Cards cards) {
        super("딜러", cards);
    }

    public boolean isRangeScoreToReceive() {
        return calculateScore() <= MAX_SCORE_TO_RECEIVE_CARD;
    }

    @Override
    public List<Card> openFirstCards() {
        return getCards().subList(0, FIRST_OPEN_COUNT);
    }
}
