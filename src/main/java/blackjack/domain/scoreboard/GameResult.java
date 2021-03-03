package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;

import java.util.List;

public class GameResult {
    private final List<Card> resultCards;

    public GameResult(List<Card> resultCards) {
        this.resultCards = resultCards;
    }

    public int calculateScore() {
        return resultCards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }
}