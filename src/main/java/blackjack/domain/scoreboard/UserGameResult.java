package blackjack.domain.scoreboard;

import blackjack.domain.card.Card;

import java.util.List;
import java.util.Objects;

public class UserGameResult {
    private final GameResult gameResult;
    private final WinOrLose winOrLose;

    public UserGameResult(List<Card> resultCards, String name, WinOrLose winOrLose) {
        this.gameResult = new GameResult(resultCards, name);
        this.winOrLose = winOrLose;
    }

    public int calculateScore() {
        return gameResult.calculateScore();
    }

    public List<Card> getResultCards() {
        return gameResult.getResultCards();
    }

    public WinOrLose getWinOrLose() {
        return winOrLose;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserGameResult that = (UserGameResult) o;
        return Objects.equals(gameResult, that.gameResult) && winOrLose == that.winOrLose;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gameResult, winOrLose);
    }
}