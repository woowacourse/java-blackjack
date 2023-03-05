package domain;

import java.util.List;
import java.util.stream.Collectors;

public class Dealer extends Player {

    private static final int PICK_BOUNDARY = 16;
    private static final String DEALER_NAME = "딜러";

    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    public boolean isHittable() {
        return getScore().getValue() <= PICK_BOUNDARY;
    }

    public List<DealerStatus> getDealerStats(final Players players) {
        return players.getPlayers().stream()
                .map(this::compareWithPlayer)
                .collect(Collectors.toList());
    }

    private DealerStatus compareWithPlayer(final Player player) {
        if (player.isBlackJack()) {
            return DealerStatus.LOSE;
        }
        if (this.isBlackJack()) {
            return DealerStatus.WIN;
        }
        if (player.isBustedPlayer() && this.isBustedPlayer()) {
            return DealerStatus.DRAW;
        }
        return this.getScore().compareScore(player.getScore());
    }
}
