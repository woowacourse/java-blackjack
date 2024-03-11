package domain;

import java.util.ArrayList;
import java.util.List;

public class GameRule {
    private final Dealer dealer;
    private final Gamers gamers;

    public GameRule(final Dealer dealer, final Gamers gamers) {
        this.dealer = dealer;
        this.gamers = gamers;
    }

    public List<Boolean> judge() {
        if (dealer.isBusted()) {
            return judgeGamersIfDealerBusted();
        }

        List<Boolean> gameResult = new ArrayList<>();
        for (Player player : gamers.listOf()) {
            checkWinner(player, gameResult);
        }
        return gameResult;
    }

    private void checkWinner(final Player player, final List<Boolean> gameResult) {
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

    public List<Boolean> judgeGamersIfDealerBusted() {
        List<Boolean> gameResult = new ArrayList<>();
        for (Gamer gamer : gamers.listOf()) {
            gameResult.add(!gamer.isBusted());
        }
        return gameResult;
    }
}
