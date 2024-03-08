package domain;

import domain.constants.Outcome;
import java.util.ArrayList;
import java.util.List;

public class GameRule {
    private static final int BLACKJACK_SCORE = 21;

    private final Participant participant;

    public GameRule(final Participant participant) {
        this.participant = participant;
    }

    public List<Outcome> judge() {
        if (isNotBusted(participant.dealer())) {
            return judgePlayersWhenDealerBusted();
        }

        List<Outcome> gameResult = new ArrayList<>();
        for (Player player : participant.players()) {
            checkWinner(player, gameResult);
        }
        return gameResult;
    }

    private boolean isNotBusted(final Player player) {
        return player.calculateResultScore() <= BLACKJACK_SCORE;
    }

    public List<Outcome> judgePlayersWhenDealerBusted() {
        List<Outcome> gameResult = new ArrayList<>();
        for (Player player : participant.players()) {
            gameResult.add(Outcome.from(isNotBusted(player)));
        }
        return gameResult;
    }

    private void checkWinner(final Player player, final List<Outcome> gameResult) {
        if (isNotBusted(player)) {
            gameResult.add(Outcome.from(false));
            return;
        }
        if (hasSameScoreWithDealer(player)) {
            gameResult.add(Outcome.from(true));
            return;
        }
        gameResult.add(Outcome.from(hasMoreScoreThanDealer(player)));
    }

    private boolean hasSameScoreWithDealer(final Player player) {
        return player.hasSameScore(participant.dealer());
    }

    private boolean hasMoreScoreThanDealer(final Player player) {
        return player.hasMoreScore(participant.dealer());
    }
}
