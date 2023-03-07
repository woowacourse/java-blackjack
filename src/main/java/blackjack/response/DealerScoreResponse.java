package blackjack.response;

import java.util.List;

public class DealerScoreResponse {

    private final List<CardResponse> cards;
    private final int score;

    public DealerScoreResponse(final List<CardResponse> cards, final int score) {
        this.cards = cards;
        this.score = score;
    }

    public List<CardResponse> getCards() {
        return cards;
    }

    public int getScore() {
        return score;
    }
}
