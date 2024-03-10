package domain;

import domain.constants.Outcome;
import java.util.List;

public class GameRule {
    private static final int BLACKJACK_SCORE = 21;
    private final Participants participants;

    public GameRule(final Participants participants) {
        this.participants = participants;
    }

    public List<Outcome> judge() {
        if (isBusted(participants.getDealer())) {
            return participants.getOutcomesIf(
                    player -> isNotBusted(player)
            );
        }
        return participants.getOutcomesIf(
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
        if (player.isNotSameScoreAs(participants.getDealer())) {
            return player.hasMoreScoreThan(participants.getDealer());
        }
        return player.hasLessOrSameCardThan(participants.getDealer());
    }

    private boolean isBusted(final Player player) {
        return player.hasMoreScoreThan(BLACKJACK_SCORE);
    }
}
