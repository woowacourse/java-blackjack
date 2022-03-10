package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Winner {

    private final List<Player> winners = new ArrayList<>();

    public void compare(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return;
        }
        if (dealer.isBust() || dealer.isLowerScore(player)) {
            winners.add(player);
        }
    }

    public int numberOfWinners() {
        return winners.size();
    }

    public boolean contains(final Player player) {
        return winners.contains(player);
    }
}
