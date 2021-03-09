package blackjack.domain.scoreboard.result;

import blackjack.domain.betting.BettingMoney;
import blackjack.domain.card.Cards;
import blackjack.domain.scoreboard.WinOrLose;
import blackjack.domain.user.ParticipantName;

import java.util.Objects;

public class UserGameResult implements Resultable {
    private final GameResult gameResult;
    private final WinOrLose winOrLose;
    private final BettingMoney bettingMoney;

    public UserGameResult(Cards resultCards, String name, WinOrLose winOrLose, BettingMoney bettingMoney) {
        this.gameResult = new GameResult(resultCards, name);
        this.winOrLose = winOrLose;
        this.bettingMoney = bettingMoney;
    }

    public WinOrLose getWinOrLose() {
        return winOrLose;
    }

    public BettingMoney getBettingMoney(){
        return bettingMoney;
    }

    @Override
    public Cards getCards() {
        return gameResult.getCards();
    }

    @Override
    public ParticipantName getName() {
        return gameResult.getName();
    }

    @Override
    public int getScore() {
        return gameResult.getScore();
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