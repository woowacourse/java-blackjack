package blackjack.domain.game;

import java.util.ArrayList;
import java.util.List;

public class Winner {

    private final List<Player> winners = new ArrayList<>();
    private final List<Player> losers = new ArrayList<>();

    public void compete(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            losers.add(player);
            return;
        }
        if (dealer.isBust() || dealer.isLowerScore(player)) {
            winners.add(player);
            return;
        }
        losers.add(player);
    }

    public int numberOfWinners() {
        return winners.size();
    }

    public int numberOfLosers() {
        return losers.size();
    }

    public boolean contains(final Player player) {
        return winners.contains(player);
    }
}
