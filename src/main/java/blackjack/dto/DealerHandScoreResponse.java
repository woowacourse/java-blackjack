package blackjack.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DealerHandScoreResponse {

    private final List<CardDTO> hand;
    private final int score;

    public DealerHandScoreResponse(List<CardDTO> hand, int score) {
        this.hand = new ArrayList<>(hand);
        this.score = score;
    }

    public List<CardDTO> getHand() {
        return Collections.unmodifiableList(hand);
    }

    public int getScore() {
        return score;
    }
}
