package domain;

import java.util.ArrayList;
import java.util.List;

public class GameRule {
    private static final int BLACKJACK_SCORE = 21;

    private final Participant participant;

    public GameRule(final Participant participant) {
        this.participant = participant;
    }

    public List<Boolean> judge() {
        if (isBusted(participant.dealer())) {
            return judgePlayersIfDealerBusted();
        }

        List<Boolean> gameResult = new ArrayList<>();
        for (Player player : participant.players()) {
            checkWinner(player, gameResult);
        }
        return gameResult;
    }

    private boolean isBusted(final Player player) {
        return player.calculateResultScore() > BLACKJACK_SCORE;
    }

    private void checkWinner(final Player player, final List<Boolean> gameResult) {
        if (isBusted(player)) {
            gameResult.add(false);
            return;
        }
        if (hasSameScore(player)) {
            gameResult.add(hasLessCard(player));
            return;
        }
        gameResult.add(hasMoreScore(player));
    }

    private boolean hasSameScore(final Player player) {
        return player.hasSameScore(participant.dealer());
    }

    private boolean hasLessCard(final Player player) {
        return !player.hasMoreCard(participant.dealer());
    }

    private boolean hasMoreScore(final Player player) {
        return player.hasMoreScore(participant.dealer());
    }

    public List<Boolean> judgePlayersIfDealerBusted() {
        List<Boolean> gameResult = new ArrayList<>();
        for (Player player : participant.players()) {
            gameResult.add(!isBusted(player));
        }
        return gameResult;
    }
}
