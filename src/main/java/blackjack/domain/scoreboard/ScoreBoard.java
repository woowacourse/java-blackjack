package blackjack.domain.scoreboard;

import blackjack.domain.scoreboard.result.GameResult;
import blackjack.domain.scoreboard.result.UserGameResult;
import blackjack.domain.user.Name;
import blackjack.domain.user.User;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ScoreBoard {
    private final Map<Name, UserGameResult> userResults;
    private final GameResult gameResult;

    public ScoreBoard(Map<Name, UserGameResult> userResults, GameResult gameResult){
        this.userResults = userResults;
        this.gameResult = gameResult;
    }

    public Map<WinOrLose, Long> dealerWinOrLoseCounts(){
        return userResults.keySet().stream()
                .map(userResults::get)
                .map(UserGameResult::getWinOrLose)
                .collect(groupingBy(WinOrLose::opposite, counting()));
    }

    public Map<Name, UserGameResult> getUserResults(){
        return Collections.unmodifiableMap(userResults);
    }

    public GameResult getDealerGameResult(){
        return gameResult;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ScoreBoard)) return false;
        ScoreBoard that = (ScoreBoard) o;
        return Objects.equals(getUserResults(), that.getUserResults()) && Objects.equals(gameResult, that.gameResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUserResults(), gameResult);
    }
}
