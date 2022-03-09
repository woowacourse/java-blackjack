package blackjack.domain;

import java.util.ArrayList;
import java.util.List;

public class Winner {

    private final List<Player> winners = new ArrayList<>();

    public void compare(final Dealer dealer, final Player player) {
        if (player.isBurst()) {
            return;
        }
        if (!(dealer.compare(player))) {
            winners.add(player);
        }
    }

    public boolean contains(final Player player) {
        return winners.contains(player);
    }
}
