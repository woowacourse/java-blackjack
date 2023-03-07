package blackjack.view.dto;

import blackjack.domain.card.Card;
import java.util.List;
import java.util.stream.Collectors;

public class CardsResponse {

    private final int totalScore;
    private final List<String> cardInfos;

    private CardsResponse(final int totalScore, final List<String> cardInfos) {
        this.totalScore = totalScore;
        this.cardInfos = cardInfos;
    }

    public static CardsResponse of(final int score, final List<Card> cards) {
        return new CardsResponse(score, getCardInfos(cards));
    }

    private static List<String> getCardInfos(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getNumberName() + card.getSuitName())
                .collect(Collectors.toList());
    }

    public int getTotalScore() {
        return totalScore;
    }

    public List<String> getCardInfos() {
        return cardInfos;
    }
}
