package blackjack.view.dto;

import blackjack.domain.card.Cards;
import java.util.List;

public class CardsResponse {

    private final int totalScore;
    private final List<String> cardInfos;

    private CardsResponse(final int totalScore, final List<String> cardInfos) {
        this.totalScore = totalScore;
        this.cardInfos = cardInfos;
    }

    public static CardsResponse from(final Cards cards) {
        return new CardsResponse(cards.calculateTotalScore(), cards.getCardInfos());
    }

    public int getTotalScore() {
        return totalScore;
    }

    public List<String> getCardInfos() {
        return cardInfos;
    }
}
