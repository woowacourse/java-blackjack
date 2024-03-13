package blackjack.model.result;

import java.util.ArrayList;
import java.util.List;

public class DealerResult {

    private final List<ResultState> dealerResult = new ArrayList<>();

    public DealerResult() {
    }

    public void addWin() {
        dealerResult.add(ResultState.WIN);
    }

    public void addLose() {
        dealerResult.add(ResultState.LOSE);
    }

    public void addTie() {
        dealerResult.add(ResultState.TIE);
    }

    public int countWins() {
        return (int) dealerResult.stream()
                .filter(result -> result == ResultState.WIN)
                .count();
    }

    public int countLoses() {
        return (int) dealerResult.stream()
                .filter(result -> result == ResultState.LOSE)
                .count();
    }

    public int countTies() {
        return (int) dealerResult.stream()
                .filter(result -> result == ResultState.TIE)
                .count();
    }

    public List<ResultState> getDealerResult() {
        return dealerResult;
    }
}
