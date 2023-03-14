package blackjack.controller.dto;

import blackjack.domain.card.Card;
import blackjack.domain.card.Hand;
import java.util.List;
import java.util.stream.Collectors;

public class CardsResponse {

    private final int score;
    private final List<String> cardInfos;

    private CardsResponse(final int score, final List<String> cardInfos) {
        this.score = score;
        this.cardInfos = cardInfos;
    }

    public static CardsResponse of(final int score, final Hand hand) {
        return new CardsResponse(score, createCardInfos(hand.getCards()));
    }

    private static List<String> createCardInfos(final List<Card> cards) {
        return cards.stream()
                .map(card -> card.getNumberName() + card.getSuitName())
                .collect(Collectors.toList());
    }

    public int getScore() {
        return score;
    }

    public List<String> createCardInfos() {
        return cardInfos;
    }
}
