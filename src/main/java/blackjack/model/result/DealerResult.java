package blackjack.model.result;

import java.util.ArrayList;
import java.util.List;

public class DealerResult {

    private final List<Result> dealerResult = new ArrayList<>();

    public void addWin() {
        dealerResult.add(Result.WIN);
    }

    public void addLose() {
        dealerResult.add(Result.LOSE);
    }

    public void addTie() {
        dealerResult.add(Result.TIE);
    }

    public int countWins() {
        return (int) dealerResult.stream()
                .filter(result -> result == Result.WIN)
                .count();
    }

    public int countLoses() {
        return (int) dealerResult.stream()
                .filter(result -> result == Result.LOSE)
                .count();
    }

    public int countTies() {
        return (int) dealerResult.stream()
                .filter(result -> result == Result.TIE)
                .count();
    }

    public List<Result> getDealerResult() {
        return dealerResult;
    }
}
