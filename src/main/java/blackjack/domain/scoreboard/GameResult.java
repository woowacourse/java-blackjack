package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class GameResult {
    private final List<Card> resultCards;
    private final String name;

    public GameResult(List<Card> resultCards, String name) {
        this.resultCards = resultCards;
        this.name = name;
    }

    public List<Card> getResultCards() {
        return Collections.unmodifiableList(resultCards);
    }

    public int calculateScore() {
        return resultCards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return Objects.equals(resultCards, that.resultCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCards);
    }
}
