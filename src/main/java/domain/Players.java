package domain;

import java.util.List;

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

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public GameResult calculateResult(Dealer dealer) {
        return resultCalculator.calculate(dealer, this);
    }
}
