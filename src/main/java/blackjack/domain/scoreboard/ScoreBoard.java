package blackjack.domain.scoreboard;

import blackjack.domain.user.User;

import java.util.Collections;
import java.util.Map;
import java.util.Objects;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class ScoreBoard {
    private final Map<User, GameResult> userResults;
    private final DealerGameResult dealerGameResult;

    public ScoreBoard(Map<User, GameResult> userResults, DealerGameResult dealerGameResult){
        this.userResults = userResults;
        this.dealerGameResult = dealerGameResult;
    }

    public Map<User, GameResult> getUserResults(){
        return Collections.unmodifiableMap(userResults);
    }

    public DealerGameResult getDealerGameResult(){
        return dealerGameResult;
    }

    public Map<WinOrLose, Long> dealersWinOrLoses(){
        return userResults.keySet().stream()
                .map(userResults::get)
                .map(GameResult::getWinOrLose)
                .collect(groupingBy(WinOrLose::opposite, counting()));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ScoreBoard that = (ScoreBoard) o;
        return Objects.equals(userResults, that.userResults) && Objects.equals(dealerGameResult, that.dealerGameResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userResults, dealerGameResult);
    }
}
