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
        if (isBusted(participant.dealer())) {
            return judgePlayersWhenDealerBusted();
        }

        List<Outcome> gameResult = new ArrayList<>();
        for (Player player : participant.players()) {
            checkWinner(player, gameResult);
        }
        return gameResult;
    }

    public List<Outcome> judgePlayersWhenDealerBusted() {
        List<Outcome> gameResult = new ArrayList<>();
        for (Player player : participant.players()) {
            gameResult.add(Outcome.from(isNotBusted(player)));
        }
        return gameResult;
    }

    private boolean isNotBusted(final Player player) {
        return player.calculateResultScore() <= BLACKJACK_SCORE;
    }

    private void checkWinner(final Player player, final List<Outcome> gameResult) {
        if (isBusted(player)) {
            gameResult.add(Outcome.from(false));
            return;
        }
        if (isNotSameWithDealer(player)) {
            gameResult.add(Outcome.from(player.hasMoreScore(participant.dealer())));
            return;
        }
        gameResult.add(Outcome.from(hasLessThanOrEqualsCardWithDealer(player)));
    }

    private boolean isNotSameWithDealer(final Player player) {
        return player.calculateResultScore() != participant.dealer().calculateResultScore();
    }

    private boolean hasLessThanOrEqualsCardWithDealer(final Player player) {
        return player.hasLessOrSameCard(participant.dealer());
    }

    private boolean isBusted(final Player player) {
        return !isNotBusted(player);
    }


    private boolean hasSameScoreWithDealer(final Player player) {
        return player.hasSameScore(participant.dealer());
    }
}
