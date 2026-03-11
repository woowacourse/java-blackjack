package domain.result;

import domain.participant.Dealer;
import domain.participant.Player;
import domain.participant.Players;

public class Referee {

    private final GameStatistics gameStatistics;

    public Referee() {
        this.gameStatistics = new GameStatistics();
    }

    public GameStatistics judge(Dealer dealer, Players players) {
        for (Player player : players.getPlayers()) {
            judgeStatisticResult(dealer, player);
        }
        return gameStatistics;
    }

    private void judgeStatisticResult(Dealer dealer, Player player) {
        GameResult playerResult = GameResult.judge(dealer, player);
        gameStatistics.addPlayerResult(player, playerResult);
        gameStatistics.addDealerResult(playerResult.reverse());
    }

}
