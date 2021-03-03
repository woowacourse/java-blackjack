package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.Objects;

//todo : GameResult와의 중복 제거
public class DealerGameResult {
    private final List<Card> resultCards;

    public DealerGameResult(List<Card> resultCards) {
        this.resultCards = resultCards;
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
        DealerGameResult that = (DealerGameResult) o;
        return Objects.equals(resultCards, that.resultCards);
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCards);
    }
}
