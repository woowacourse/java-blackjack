package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.Objects;

public class GameResult {
    private final List<Card> resultCards;
    private final WinOrLose winOrLose;

    public GameResult(List<Card> resultCards, WinOrLose winOrLose) {
        this.resultCards = resultCards;
        this.winOrLose = winOrLose;
    }

    public int calculateScore() {
        return resultCards.stream()
                .mapToInt(Card::getScore)
                .sum();
    }

    public WinOrLose getWinOrLose(){
        return winOrLose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GameResult that = (GameResult) o;
        return Objects.equals(resultCards, that.resultCards) && winOrLose == that.winOrLose;
    }

    @Override
    public int hashCode() {
        return Objects.hash(resultCards, winOrLose);
    }
}