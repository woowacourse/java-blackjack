package domain;

public class Referee {

    private final GameStatistics gameStatistics;

    public Referee() {
        this.gameStatistics = new GameStatistics();
    }

    public GameStatistics judge(Participant dealer, Participants players) {
        for (Participant player : players.getPlayers()) {
            GameResult playerResult = GameResult.judge(dealer.getScore(), player.getScore());
            gameStatistics.addPlayerResult(player, playerResult);

            GameResult dealerResult = GameResult.judge(player.getScore(), dealer.getScore());
            gameStatistics.addDealerResult(dealerResult);
        }
        return gameStatistics;
    }
}
