package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Winner {

    private final List<Player> winners = new ArrayList<>();

    public void compare(final Dealer dealer, final Player player) {
        if (player.isBust()) {
            return;
        }
        if (dealer.isBust() || !(dealer.compare(player))) {
            winners.add(player);
        }
    }

    public int winPlayersCount() {
        return winners.size();
    }

    public boolean contains(final Player player) {
        return winners.contains(player);
    }
}
