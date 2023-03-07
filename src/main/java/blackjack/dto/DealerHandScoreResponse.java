package blackjack.dto;

import blackjack.domain.card.Card;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealerHandScoreResponse {

    private final List<Card> hand;
    private final int score;

    public DealerHandScoreResponse(List<Card> hand, int score) {
        this.hand = new ArrayList<>(hand);
        this.score = score;
    }

    public List<Card> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public int getScore() {
        return score;
    }
}
