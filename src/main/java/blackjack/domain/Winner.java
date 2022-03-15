package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Winner {

    private final List<Player> winners = new ArrayList<>();

    public void decide(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return;
        }
        if (dealer.isBust() || !(dealer.compare(player))) {
            winners.add(player);
        }
    }

    public int countWinner() {
        return winners.size();
    }

    public int countLoser(Players players) {
        return players.countWinner(countWinner());
    }

    public boolean contains(final Player player) {
        return winners.contains(player);
    }
}
