package domain.player;

import domain.score.Score;

import java.util.Map;

import static java.util.stream.Collectors.toMap;

public class Dealer extends Player {

    private static final String DEALER_NAME = "딜러";
    private static final int DEALER_HIT_BOUNDARY = 16;


    public Dealer() {
        super(new PlayerName(DEALER_NAME));
    }

    public Map<Player, DealerStatus> getDealerStats(final Players players) {
        return players.getPlayers().stream()
                .collect(toMap(player -> player, this::compareWithPlayer));
    }

    private DealerStatus compareWithPlayer(final Player player) {
        if (isBothBlackjack(player)) {
            return DealerStatus.DRAW;
        }
        if (player.isBlackjack()) {
            return DealerStatus.BLACKJACK_LOSE;
        }
        if (this.isBlackjack() || player.isBusted()) {
            return DealerStatus.WIN;
        }
        return this.compareNormalCase(player.getScore());
    }

    private DealerStatus compareNormalCase(Score score) {
        return getScore().compareScore(score);
    }

    public boolean isHittable() {
        return getScore()
                .isSmallerOrEqual(Score.from(DEALER_HIT_BOUNDARY));
    }

    private boolean isBothBlackjack(final Player player) {
        return player.isBlackjack() && this.isBlackjack();
    }
}
