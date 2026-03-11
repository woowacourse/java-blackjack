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
            judgePlayerResult(dealer, player);
            judgeDealerResult(dealer, player);
        }
        return gameStatistics;
    }

    private void judgePlayerResult(Dealer dealer, Player player) {
        GameResult playerResult = GameResult.judge(dealer.getScore(), player.getScore());
        gameStatistics.addPlayerResult(player, playerResult);
    }

    private void judgeDealerResult(Dealer dealer, Player player) {
        GameResult dealerResult = GameResult.judge(player.getScore(), dealer.getScore());
        gameStatistics.addDealerResult(dealerResult);
    }
}
