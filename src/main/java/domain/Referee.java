package domain;

public class Referee {

    private final GameStatistics gameStatistics;

    public Referee() {
        this.gameStatistics = new GameStatistics();
    }

    public GameStatistics judge(Participant dealer, Participants players) {
        for (Participant player : players.getPlayers()) {
            GameResult gameResult = GameResult.judge(dealer.getScore(), player.getScore());
            gameStatistics.addPlayerResult(player, gameResult);
        }
        return gameStatistics;
    }
}
