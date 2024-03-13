package domain;

import controller.dto.GameResult;
import controller.dto.PlayerResult;
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

    public GameResult getResultsOfGame() {
        List<Boolean> results = judge();
        List<String> names = gamers.getNames();

        List<PlayerResult> playerResults = new ArrayList<>();
        for (int i = 0; i < names.size(); i++) {
            playerResults.add(new PlayerResult(names.get(i), results.get(i)));
        }
        return new GameResult(playerResults);
    }
}
