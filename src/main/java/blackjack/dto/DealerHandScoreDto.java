package blackjack.dto;

import java.util.ArrayList;
import java.util.List;

public class DealerHandScoreDto {

    private final List<CardDto> hand;
    private final int score;

    public DealerHandScoreDto(List<CardDto> hand, int score) {
        this.hand = new ArrayList<>(hand);
        this.score = score;
    }

    public List<CardDto> getHand() {
        return hand;
    }

    public int getScore() {
        return score;
    }
}
