package domain;

import java.util.ArrayList;
import java.util.List;

public class GameRule {
    private final Dealer dealer;
    private final List<Gamer> gamers;

    public GameRule(final Dealer dealer, final List<Gamer> gamers) {
        this.dealer = dealer;
        this.gamers = gamers;
    }

    public List<Boolean> judge() {
        if (dealer.isBusted()) {
            return judgePlayersIfDealerBusted();
        }

        List<Boolean> gameResult = new ArrayList<>();
        for (Player player : gamers) {
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

    public List<Boolean> judgePlayersIfDealerBusted() {
        List<Boolean> gameResult = new ArrayList<>();
        for (Player player : gamers) {
            gameResult.add(!player.isBusted());
        }
        return gameResult;
    }
}
