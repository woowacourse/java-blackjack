package domain;

import domain.constants.Outcome;
import java.util.List;

public class GameRule {
    private static final int BLACKJACK_SCORE = 21;
    private final Players players;

    public GameRule(final Players players) {
        this.players = players;
    }

    public List<Outcome> judge() {
        if (isBusted(players.getDealer())) {
            return players.getOutcomesIf(
                    player -> isNotBusted(player)
            );
        }
        return players.getOutcomesIf(
                player -> isWinner(player)
        );
    }

    private boolean isNotBusted(final Player player) {
        return player.calculateResultScore() <= BLACKJACK_SCORE;
    }

    private boolean isWinner(final Player player) {
        if (isBusted(player)) {
            return true;
        }
        if (player.isNotSameScoreAs(players.getDealer())) {
            return player.hasMoreScoreThan(players.getDealer());
        }
        return player.hasLessOrSameCardThan(players.getDealer());
    }

    private boolean isBusted(final Player player) {
        return player.hasMoreScoreThan(BLACKJACK_SCORE);
    }
}
