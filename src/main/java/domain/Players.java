package domain;

import java.util.List;
import java.util.function.Consumer;

public class Players {
    private final List<Player> players;
    private final ResultCalculator resultCalculator;

    public Players(final List<Player> players) {
        this.players = List.copyOf(players);
        this.resultCalculator = new ResultCalculator();
    }

    public int getSize() {
        return players.size();
    }

    public void forEachPlayer(final Consumer<Player> action) {
        players.forEach(action);
    }

    public GameResult calculateResult(Dealer dealer) {
        return resultCalculator.calculate(dealer, this);
    }
}
