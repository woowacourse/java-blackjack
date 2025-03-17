package game;

import java.util.List;
import java.util.stream.IntStream;

public class Profits {

    private final List<Integer> profits;

    private Profits(List<Integer> profits) {
        this.profits = profits;
    }

    public static Profits of(Players players, GameResults gameResults) {
        List<Integer> profits = IntStream.range(0, players.getPlayers().size())
                .mapToObj(i -> players.getPlayers().get(i).evaluate(gameResults.getGameResults().get(i)))
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
