package game;

import java.util.List;
import java.util.stream.IntStream;

public class Profits {

    private final List<Integer> profits;

    private Profits(List<Integer> profits) {
        this.profits = profits;
    }

    public static Profits evaluateProfits(List<Integer> bettings, List<GameResult> gameResults) {
        List<Integer> profits = IntStream.range(0, bettings.size())
                .mapToObj(i -> gameResults.get(i).evaluate(bettings.get(i)))
                .toList();
        return new Profits(profits);
    }

    public int evaluateDealerProfit() {
        return (-1) * profits.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }

    public List<Integer> getProfits() {
        return profits;
    }
}
