package domain;

import java.util.ArrayList;
import java.util.List;

public class GameRule {
    private final Participant participant;

    public GameRule(final Participant participant) {
        this.participant = participant;
    }

    public List<Boolean> judge() {
        final Dealer dealer = participant.dealer();
        if (dealer.isBusted()) {
            return judgePlayersIfDealerBusted();
        }

        List<Boolean> gameResult = new ArrayList<>();
        for (Player player : participant.players()) {
            checkWinner(player, gameResult);
        }
        return gameResult;
    }

    private void checkWinner(final Player player, final List<Boolean> gameResult) {
        final Dealer dealer = participant.dealer();
        if (player.isBusted()) {
            gameResult.add(false);
            return;
        }
        if (player.hasSameScoreAs(dealer)) {
            gameResult.add(player.hasLessCardThan(dealer));
            return;
        }
        gameResult.add(player.hasMoreScoreThan(dealer));
    }

    public List<Boolean> judgePlayersIfDealerBusted() {
        List<Boolean> gameResult = new ArrayList<>();
        for (Player player : participant.players()) {
            gameResult.add(!player.isBusted());
        }
        return gameResult;
    }
}
