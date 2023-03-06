package domain.player;

import java.util.Map;

import static java.util.stream.Collectors.*;

public class Dealer extends Player {

    private static final int PICK_BOUNDARY = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    public boolean isHittable() {
        return getScore().getValue() <= PICK_BOUNDARY;
    }

    public Map<Player, DealerStatus> getDealerStats(final Players players) {
        return players.getPlayers().stream()
                .collect(toMap(player -> player, this::compareWithPlayer));
    }

    private DealerStatus compareWithPlayer(final Player player) {
        if (bothBlackjack(player)) {
            return DealerStatus.DRAW;
        }
        if (player.isBlackjack()) {
            return DealerStatus.BLACKJACK_LOSE;
        }
        if (this.isBlackjack() || player.isBusted()) {
            return DealerStatus.WIN;
        }
        return this.getScore().compareScore(player.getScore());
    }

    private boolean bothBlackjack(final Player player) {
        return player.isBlackjack() && this.isBlackjack();
    }
}
