package blackjack.view.dto;

import java.util.List;

public class CardsResponse {

    private final int totalScore;
    private final List<String> cardInfos;

    public CardsResponse(final int totalScore, final List<String> cardInfos) {
        this.totalScore = totalScore;
        this.cardInfos = cardInfos;
    }

    public int getTotalScore() {
        return totalScore;
    }

    public List<String> getCardInfos() {
        return cardInfos;
    }
}
